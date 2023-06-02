package com.app.pepens.activity

import android.Manifest
import android.app.Activity
import android.app.AlertDialog
import android.app.DatePickerDialog
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.location.Geocoder
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.os.Handler
import android.provider.MediaStore
import android.provider.Settings
import android.text.Editable
import android.util.Log
import android.view.Gravity
import android.view.View
import android.view.Window
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import com.app.pepens.BuildConfig
import com.app.pepens.R
import com.app.pepens.model.LogData
import com.app.pepens.model.Login.Login
import com.app.pepens.model.Login.LoginData
import com.app.pepens.utils.NetworkClient
import com.app.pepens.utils.APIsRequrst
import com.app.pepens.utils.Urls
import com.app.pepens.utils.Utils
import com.bumptech.glide.Glide
import com.google.android.libraries.places.api.Places
import com.google.android.libraries.places.api.model.Place
import com.google.android.libraries.places.widget.Autocomplete
import com.google.android.libraries.places.widget.AutocompleteActivity
import com.google.android.libraries.places.widget.model.AutocompleteActivityMode
import com.google.android.material.appbar.AppBarLayout
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.logEvent
import com.theartofdev.edmodo.cropper.CropImage
import com.theartofdev.edmodo.cropper.CropImageView
import kotlinx.android.synthetic.main.activity_update_profile.*
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import java.io.File
import java.net.URI
import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.abs

class UpdateProfileActivity : AppCompatActivity(), View.OnClickListener {

    private val TAG = UpdateProfileActivity::class.java.simpleName
    private lateinit var firebaseAnalytics: FirebaseAnalytics
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var progressDialog: Dialog
    private var loginData: LoginData? = null
    private val REQUEST_PERMISSION_CODE = 111
    private var bitmap: Bitmap? = null
    private var filePath: String? = null
    private val TAKE_CROP = 303
    private val PICK_CROP = 404
    private val TAKE_PHOTO = 101
    private val PICK_PHOTO = 202
    private lateinit var imageName: String
    private lateinit var imageUri: Uri
    private var sdf: SimpleDateFormat? = null
    private var uYear: Int = 0
    private var uMonth: Int = 0
    private var uDay: Int = 0
    private var gender: String? = null
    private val AUTOCOMPLETE_REQUEST_CODE = 1
    private var address: String? = null
    private var latitude: Double = 0.0
    private var longitude: Double = 0.0
    private var city: String? = null
    private var state: String? = null
    private var country: String? = null
    private var postalCode: String? = null
    private var knownName: String? = null

    companion object {
        var tvConnection: TextView? = null
    }

    fun dialog(value: Boolean) {
        tvConnection!!.visibility = View.VISIBLE
        if (value) {
            tvConnection!!.setBackgroundColor(Color.parseColor("#09AF00"))
            tvConnection!!.text = "Back Online."
            Handler().postDelayed({
                tvConnection!!.visibility = View.GONE
            }, 2000)
        } else {
            tvConnection!!.setBackgroundColor(Color.RED)
            tvConnection!!.text = "No connection."
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_profile)

        toolbar.setTitle(R.string.profile)
        toolbar.setNavigationIcon(R.drawable.ic_back)
        setSupportActionBar(toolbar)
        toolbar.setNavigationOnClickListener {
            finish()
        }
        app_bar_layout.addOnOffsetChangedListener(AppBarLayout.OnOffsetChangedListener { appBarLayout, verticalOffset ->
            when {
                abs(verticalOffset) == app_bar_layout.totalScrollRange -> {
                    cardView.visibility = View.GONE
                }
                verticalOffset == 0 -> {
                    cardView.visibility = View.VISIBLE
                }
            }
        })

        sharedPreferences = getSharedPreferences("USER_DATA", Context.MODE_PRIVATE)
        progressDialog = Utils().progressDialog(this)

        Utils().updatePagesLog(
            this,
            LogData(
                areaId = MainActivity.login!!.loginData!!.areaId,
                pageId = Urls.UPDATE_PROFILE_PAGE,
                title = "Update Profile Page",
                eventName = "Page Open"
            )
        )

        Places.initialize(applicationContext, resources.getString(R.string.maps_key))

        firebaseAnalytics = FirebaseAnalytics.getInstance(this@UpdateProfileActivity)

        firebaseAnalytics.logEvent(FirebaseAnalytics.Event.SCREEN_VIEW) {
            param(FirebaseAnalytics.Param.SCREEN_NAME, "Update Profile Screen")
            param(FirebaseAnalytics.Param.SCREEN_CLASS, UpdateProfileActivity::class.java.simpleName)
        }

        tvConnection = findViewById(R.id.tv_connection)

        sdf = SimpleDateFormat("dd-MM-yyyy")
        val dateParts = sdf!!.format(Date()).split("-")
        uDay = dateParts[0].toInt()
        uMonth = dateParts[1].toInt() - 1
        uYear = dateParts[2].toInt()

        progressDialog.show()
        getUser()

        cardView.setOnClickListener(this)
        ivMale.setOnClickListener(this)
        ivFemale.setOnClickListener(this)
        etDOB.setOnClickListener(this)
        etAddress.setOnClickListener(this)
        layoutNext.setOnClickListener(this)
    }

    private fun getUser() {
        val retrofit: Retrofit? = NetworkClient.getRetrofitClient()
        val APIsRequrst: APIsRequrst = retrofit!!.create(APIsRequrst::class.java)
        var call = APIsRequrst.getUser(userId = sharedPreferences.getString("USER_ID", null)!!, token = "")
        call!!.enqueue(object : Callback<LoginData> {
            override fun onResponse(call: Call<LoginData>?, response: Response<LoginData>?) {
                when {
                    response!!.code() == 200 -> {
                        Log.e(
                            TAG,
                            "getUser     onResponse: 200 $response        ${response.message()}"
                        )
                        loginData = response!!.body()!!
                        progressDialog.dismiss()
                        if (loginData != null) {
                            Glide.with(applicationContext)
                                .load(loginData!!.defaultPictureUrl)
                                .into(ivProfilePic)
                            etName.text =
                                Editable.Factory.getInstance().newEditable(loginData!!.firstName)
                            etLastName.text =
                                Editable.Factory.getInstance().newEditable(loginData!!.lastName)
                            if (!loginData!!.dateOfBirth.isNullOrEmpty()) {
                                var dateFormat = if (loginData!!.dateOfBirth!!.length <= 21) {
                                    SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
                                } else {
                                    SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS")
                                }
                                val oldDate = dateFormat.parse(loginData!!.dateOfBirth)
                                val dateFormat2 = SimpleDateFormat("dd-MM-yyyy")
                                etDOB.text = Editable.Factory.getInstance()
                                    .newEditable(dateFormat2.format(oldDate).toString())

                                sdf = SimpleDateFormat("dd-MM-yyyy")
                                val dateParts = sdf!!.format(oldDate).split("-")
                                uDay = dateParts[0].toInt()
                                uMonth = dateParts[1].toInt() - 1
                                uYear = dateParts[2].toInt()
                            }

                            if (loginData!!.gender == "Male") {
                                gender = "Male"
                                ivMaleCheck.visibility = View.VISIBLE
                                ivMC.visibility = View.VISIBLE
                                ivFemaleCheck.visibility = View.GONE
                                ivFC.visibility = View.GONE
                            } else if (loginData!!.gender == "Female") {
                                gender = "Female"
                                ivMaleCheck.visibility = View.GONE
                                ivMC.visibility = View.GONE
                                ivFemaleCheck.visibility = View.VISIBLE
                                ivFC.visibility = View.VISIBLE
                            }
                            if (!loginData!!.email.isNullOrEmpty())
                                etEmail.text =
                                    Editable.Factory.getInstance().newEditable(loginData!!.email)
                            if (!loginData!!.liveInAddress.isNullOrEmpty())
                                etAddress.text =
                                    Editable.Factory.getInstance().newEditable(loginData!!.liveInAddress)

                            if (!loginData!!.city.isNullOrEmpty())
                                city = loginData!!.city

                            if (!loginData!!.state.isNullOrEmpty())
                                state = loginData!!.state

                            if (!loginData!!.country.isNullOrEmpty())
                                country = loginData!!.country

                            if (!loginData!!.zipcode.isNullOrEmpty())
                                postalCode = loginData!!.zipcode

//                            if (!loginData!!.city.isNullOrEmpty())
//                                etCity.text =
//                                    Editable.Factory.getInstance().newEditable(loginData!!.city)
//                            if (!loginData!!.state.isNullOrEmpty())
//                                etState.text =
//                                    Editable.Factory.getInstance().newEditable(loginData!!.state)
//                            if (!loginData!!.country.isNullOrEmpty())
//                                etCountry.text =
//                                    Editable.Factory.getInstance().newEditable(loginData!!.country)
//                            if (!loginData!!.zipcode.isNullOrEmpty())
//                                etZip.text =
//                                    Editable.Factory.getInstance().newEditable(loginData!!.zipcode)
                        }
                    }
                    response.code() == 404 -> {
                        Log.e(
                            TAG,
                            "getUser     onResponse: 400 Error ${response.code()}        ${response.message()}"
                        )
                        progressDialog.dismiss()
                    }
                    else -> {
                        Log.e(
                            TAG,
                            "getUser     onResponse: ${response.code()}        ${response.message()}"
                        )
                        progressDialog.dismiss()
                    }
                }
            }

            override fun onFailure(call: Call<LoginData>?, t: Throwable) {
                Log.e(TAG, "getUser     onFailure: $call")
                progressDialog.dismiss()
            }
        })
    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.cardView -> {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    checkPermissions()
                } else {
                    getImagesPopup()
                }
            }

            R.id.ivMale -> {
                gender = "Male"
                ivMaleCheck.visibility = View.VISIBLE
                ivMC.visibility = View.VISIBLE
                ivFemaleCheck.visibility = View.GONE
                ivFC.visibility = View.VISIBLE
            }

            R.id.ivFemale -> {
                gender = "Female"
                ivMaleCheck.visibility = View.GONE
                ivMC.visibility = View.GONE
                ivFemaleCheck.visibility = View.VISIBLE
                ivFC.visibility = View.VISIBLE
            }

            R.id.etDOB -> {
                val cal = Calendar.getInstance()
                val year = cal.get(Calendar.YEAR)
                val month = cal.get(Calendar.MONTH)
                val day = cal.get(Calendar.DAY_OF_MONTH)
                val datePickerDialog = DatePickerDialog(
                    this@UpdateProfileActivity, R.style.MySpinnerDatePickerStyle,
                    DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                        etDOB.text = Editable.Factory.getInstance()
                            .newEditable("$dayOfMonth-${monthOfYear + 1}-$year")
                        uYear = year
                        uMonth = monthOfYear
                        uDay = dayOfMonth
                    },
                    year,
                    month,
                    day
                )
                datePickerDialog.datePicker.maxDate = System.currentTimeMillis()
                datePickerDialog.updateDate(uYear, uMonth, uDay)
                datePickerDialog.datePicker.spinnersShown = true
                datePickerDialog.datePicker.calendarViewShown = false
                datePickerDialog.show()
            }

            R.id.etAddress -> {
                val fields = listOf(
                    Place.Field.ID,
                    Place.Field.NAME,
                    Place.Field.LAT_LNG,
                    Place.Field.ADDRESS,
                    Place.Field.ADDRESS_COMPONENTS
                )
                val intent = Autocomplete.IntentBuilder(AutocompleteActivityMode.FULLSCREEN, fields)
                    .build(this)
                startActivityForResult(intent, AUTOCOMPLETE_REQUEST_CODE)
            }

            R.id.layoutNext -> {
                if (etName.text.toString().isEmpty()) {
                    etName.error = "Please enter First Name"
                    etName.requestFocus()
                } else if (etLastName.text.toString().isEmpty()) {
                    etLastName.error = "Please enter Last Name"
                    etLastName.requestFocus()
                } else if (gender.isNullOrEmpty()) {
                    Toast.makeText(applicationContext, "Please choose gender", Toast.LENGTH_SHORT)
                        .show()
                } else if (etAddress.text.toString().isEmpty()) {
                    etAddress.error = "Please enter Address"
                    etAddress.requestFocus()
                } else if (etDOB.text.toString().isEmpty()) {
                    Toast.makeText(
                        applicationContext,
                        "Please choose Date of Birth",
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    if (Utils().verifyAvailableNetwork(applicationContext)) {
                        progressDialog.show()
                        updateProfile()
                    }
                }
            }
        }
    }

    private fun checkPermissions() {
        if (ContextCompat.checkSelfPermission(applicationContext, Manifest.permission.CAMERA)
            + ContextCompat.checkSelfPermission(
                this@UpdateProfileActivity,
                Manifest.permission.READ_EXTERNAL_STORAGE
            )
            + ContextCompat.checkSelfPermission(
                this@UpdateProfileActivity,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            )
            != PackageManager.PERMISSION_GRANTED
        ) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(
                    this@UpdateProfileActivity,
                    Manifest.permission.CAMERA
                ) || ActivityCompat.shouldShowRequestPermissionRationale(
                    this@UpdateProfileActivity,
                    Manifest.permission.READ_EXTERNAL_STORAGE
                ) || ActivityCompat.shouldShowRequestPermissionRationale(
                    this@UpdateProfileActivity,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE
                )
            ) {
                ActivityCompat.requestPermissions(
                    this@UpdateProfileActivity,
                    arrayOf(
                        Manifest.permission.CAMERA,
                        Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE
                    ),
                    REQUEST_PERMISSION_CODE
                )
            } else {
                ActivityCompat.requestPermissions(
                    this@UpdateProfileActivity,
                    arrayOf(
                        Manifest.permission.CAMERA,
                        Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE
                    ),
                    REQUEST_PERMISSION_CODE
                )
            }
        } else {
            getImagesPopup()
        }
    }

    override fun onRequestPermissionsResult(RC: Int, per: Array<String>, PResult: IntArray) {
        when (RC) {
            REQUEST_PERMISSION_CODE ->
                if (PResult.isNotEmpty() && (PResult[0] + PResult[1]) == PackageManager.PERMISSION_GRANTED) {
                    getImagesPopup()
                } else {
                    val builder = AlertDialog.Builder(this@UpdateProfileActivity)
                    builder.setTitle("Please Grant Permissions")
                    builder.setMessage("To update profile picture please grant Camera & Gallery permissions, Tap YES for allow permissions to access photos, media and files on your device.")
                    builder.setPositiveButton("YES") { dialog, which ->
                        dialog.dismiss()
                        if (ActivityCompat.shouldShowRequestPermissionRationale(
                                this,
                                Manifest.permission.READ_CONTACTS
                            )
                        ) {
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                checkPermissions()
                            }
                        } else {
                            val i = Intent()
                            i.action = Settings.ACTION_APPLICATION_DETAILS_SETTINGS
                            i.addCategory(Intent.CATEGORY_DEFAULT)
                            i.data = Uri.parse("package:" + applicationContext.packageName)
                            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                            i.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY)
                            i.addFlags(Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS)
                            startActivity(i)
                        }
                    }
                    builder.setNegativeButton("No") { dialog, which ->
                        Toast.makeText(
                            applicationContext,
                            "You can't add image in account.",
                            Toast.LENGTH_SHORT
                        ).show()
                        dialog.dismiss()
                    }
                    val dialog: AlertDialog = builder.create()
                    dialog.show()
                }
        }
    }

    private fun getImagesPopup() {
        var imageDialog = Dialog(this)
        imageDialog!!.requestWindowFeature(Window.FEATURE_NO_TITLE)
        imageDialog!!.setContentView(R.layout.dialog_image_picker)
        imageDialog!!.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        imageDialog!!.window!!.setGravity(Gravity.BOTTOM)
        imageDialog!!.window!!.setLayout(
            ConstraintLayout.LayoutParams.MATCH_PARENT,
            ConstraintLayout.LayoutParams.WRAP_CONTENT
        )
        var tvTitle = imageDialog!!.findViewById(R.id.tvTitle) as TextView
        tvTitle.text = "Update Profile Picture"
        var clGallery = imageDialog!!.findViewById(R.id.clGallery) as ConstraintLayout
        var clCamera = imageDialog!!.findViewById(R.id.clCamera) as ConstraintLayout
        clGallery.setOnClickListener {
            chooseFromGallery()
            imageDialog!!.dismiss()
        }
        clCamera.setOnClickListener {
            takeFromCamera()
            imageDialog!!.dismiss()
        }
        imageDialog!!.show()
    }

    private fun chooseFromGallery() {
        val checkSelfPermission =
            ContextCompat.checkSelfPermission(
                this,
                android.Manifest.permission.WRITE_EXTERNAL_STORAGE
            )
        if (checkSelfPermission != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE), 1)
        } else {
            openAlbum()
        }
    }

    private fun openAlbum() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
                val permissions = arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE)
                requestPermissions(permissions, REQUEST_PERMISSION_CODE)
            } else {
                pickImageFromGallery()
            }
        } else {
            pickImageFromGallery()
        }
    }

    private fun pickImageFromGallery() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, PICK_CROP)
    }

    private fun takeFromCamera() {
        var cal = Calendar.getInstance()
        var sFile = getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        var ss = File.createTempFile((cal.timeInMillis).toString(), ".jpg", sFile)
        var intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        if (intent.resolveActivity(packageManager) != null) {
            imageName = ss.name
            imageUri = FileProvider.getUriForFile(
                this@UpdateProfileActivity,
                "${BuildConfig.APPLICATION_ID}.files",
                ss
            )
            intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri)
            startActivityForResult(intent, TAKE_CROP)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            TAKE_CROP ->
                if (resultCode == Activity.RESULT_OK) {
                    CropImage.activity(imageUri)
                        .setGuidelines(CropImageView.Guidelines.ON)
                        .setMultiTouchEnabled(true)
                        .setFixAspectRatio(true)
                        .start(this@UpdateProfileActivity)
                }

            PICK_CROP ->
                if (resultCode == RESULT_OK) {
                    var uri = data!!.data
                    CropImage.activity(uri!!)
                        .setGuidelines(CropImageView.Guidelines.ON)
                        .setMultiTouchEnabled(true)
                        .setFixAspectRatio(true)
                        .start(this@UpdateProfileActivity)
//                        .setAspectRatio(16, 9)
                }

            TAKE_PHOTO ->
                if (resultCode == Activity.RESULT_OK) {
                    filePath = File(URI(imageUri.toString())).toString()
                    Log.e(TAG, "onActivityResult: $filePath")
                    bitmap = MediaStore.Images.Media.getBitmap(contentResolver, imageUri)
                    ivProfilePic.setImageBitmap(bitmap)
                }

            PICK_PHOTO ->
                if (resultCode == Activity.RESULT_OK) {
                    val picUri: Uri? = data!!.data
                    filePath = File(URI(picUri.toString())).toString()
                    Log.e(TAG, "onActivityResult: $filePath")
                    bitmap = MediaStore.Images.Media.getBitmap(contentResolver, picUri)
                    ivProfilePic.setImageBitmap(bitmap)
                }

            203 -> {
                var result = CropImage.getActivityResult(data)
                if (resultCode == RESULT_OK) {
                    filePath = File(URI(result.uri.toString())).toString()
                    Log.e(TAG, "onActivityResult: $filePath")
                    bitmap = MediaStore.Images.Media.getBitmap(contentResolver, result.uri)
                    ivProfilePic.setImageBitmap(bitmap)
                }
            }

            AUTOCOMPLETE_REQUEST_CODE -> {
                when (resultCode) {
                    Activity.RESULT_OK -> {
                        data?.let {
                            val place = Autocomplete.getPlaceFromIntent(data)
                            Log.e(
                                TAG,
                                "Place: ${place.name}   ${place.id}     ${place.latLng!!.latitude}, ${place.latLng!!.longitude}     ${place.address}     ${place.addressComponents}"
                            )
                            address = place.address
                            etAddress.text = Editable.Factory.getInstance().newEditable(address)
                            latitude = place.latLng!!.latitude
                            longitude = place.latLng!!.longitude
                            try {
                                val geocoder = Geocoder(applicationContext, Locale.getDefault())
                                var addresses = geocoder.getFromLocation(latitude, longitude, 1)
                                if (addresses != null && addresses.isNotEmpty()) {
                                    address = addresses[0].getAddressLine(0)
                                    if (!addresses[0].locality.isNullOrEmpty()) {
                                        city = addresses[0].locality
                                    }
                                    if (!addresses[0].adminArea.isNullOrEmpty()) {
                                        state = addresses[0].adminArea
                                    }
                                    if (!addresses[0].countryName.isNullOrEmpty()) {
                                        country = addresses[0].countryName
                                    }
                                    if (!addresses[0].postalCode.isNullOrEmpty()) {
                                        postalCode = addresses[0].postalCode
                                    }
                                }
                            } catch (e: Exception) {
                                address = place.address
                                city = ""
                                state = ""
                                country = ""
                                postalCode = ""
                            }
                        }
                    }
                    AutocompleteActivity.RESULT_ERROR -> {
                        data?.let {
                            val status = Autocomplete.getStatusFromIntent(data)
                            Log.e(TAG, status.statusMessage!!)
                        }
                    }
                    Activity.RESULT_CANCELED -> {
                        // The user canceled the operation.
                        Log.e(TAG, "onActivityResult: Cancel")
                    }
                }
                return
            }
        }
    }

    private fun updateProfile() {
        if (MainActivity.login != null) {
            MainActivity.login!!.loginData!!.liveInAddress = etAddress.text!!.trim().toString()
            MainActivity.login!!.loginData!!.liveInCity = city
            MainActivity.login!!.loginData!!.liveInState = state
            MainActivity.login!!.loginData!!.liveInCountry = country
            MainActivity.login!!.loginData!!.liveInZipcode = postalCode
        }
        val jsonObject = JSONObject()
        jsonObject.put("UserId", sharedPreferences.getString("USER_ID", null))
        jsonObject.put("FirstName", etName.text!!.trim())
        jsonObject.put("LastName", etLastName.text!!.trim())
        jsonObject.put("Email", etEmail.text!!.trim())
        jsonObject.put("CountryCode", loginData!!.countryCode)
        jsonObject.put("MobileNo", loginData!!.mobileNo)
        jsonObject.put("Gender", gender)
        jsonObject.put("DateOfBirth", Utils().convertDate("${etDOB.text.toString()} 00:00"))
        jsonObject.put("LiveInAddress", etAddress.text!!.trim())
        jsonObject.put("LiveInCity", city)
        jsonObject.put("LiveInState", state)
        jsonObject.put("LiveInZipcode", postalCode)
        jsonObject.put("LiveInCountry", country)
        jsonObject.put("DefaultPicture", loginData!!.defaultPicture)
        jsonObject.put("IsEmailVerified", false)
        jsonObject.put("IsMobileVerified", true)
        jsonObject.put("Token", loginData!!.token)
        jsonObject.put("AuthenticationUsing", loginData!!.authenticationUsing)
        jsonObject.put("AuthenticationToken", loginData!!.authenticationToken)
        jsonObject.put("Status", loginData!!.status)
        jsonObject.put("ModifiedBy", sharedPreferences.getString("USER_ID", null))
        Log.e(TAG, "updateProfile: $jsonObject")
        val retrofit: Retrofit? = NetworkClient.getRetrofitClient()
        val APIsRequrst: APIsRequrst = retrofit!!.create(APIsRequrst::class.java)
        val business = RequestBody.create(MultipartBody.FORM, jsonObject.toString())
        var call: Call<Login>? = null
        call = if (!filePath.isNullOrEmpty()) {
            val businessBody = RequestBody.create(MediaType.parse("multipart/form-data"), File(filePath))
            val businessPicture: MultipartBody.Part = MultipartBody.Part.createFormData(
                "DefaultPicture",
                File(filePath)!!.name,
                businessBody
            )
            APIsRequrst.updateUserWithImage(businessPicture, business)
        } else {
            APIsRequrst.updateUser(business)
        }
        call!!.enqueue(object : Callback<Login> {
            override fun onResponse(call: Call<Login>?, response: Response<Login>?) {
                when {
                    response!!.code() == 200 -> {
                        Log.e(TAG, "onResponse: ${response!!.body()}")
                        var login: Login = response!!.body()!!
                        Log.e(TAG, "onResponse: $login")
                        progressDialog.dismiss()
                        if (login!!.isSuccess) {
                            loginData = login!!.loginData
                            saveUserDetail()
                        } else {
                            Toast.makeText(
                                applicationContext,
                                "${login!!.message}",
                                Toast.LENGTH_SHORT
                            ).show()
//                            Utils().updateLog(this@UpdateProfileActivity, "${sharedPreferences.getString("USER_ID", null)}", Utils().getCurrentDateTime(), "$TAG Error", "${response.code()}, ${response.message()}\n${jsonObject}\n${login}")
                        }
                    }
                    response.code() == 404 -> {
                        Log.e(
                            TAG,
                            "onResponse: 400 Error ${response.code()}        ${response.message()}"
                        )
                        progressDialog.dismiss()
                        Toast.makeText(
                            applicationContext,
                            "${response.message()}",
                            Toast.LENGTH_SHORT
                        ).show()
//                        Utils().updateLog(this@UpdateProfileActivity, "${sharedPreferences.getString("USER_ID", null)}", Utils().getCurrentDateTime(), "$TAG Error", "${response.code()}, ${response.message()}\n${jsonObject}")
                    }
                    else -> {
                        Log.e(TAG, "onResponse: ${response.code()}        ${response.message()}")
                        progressDialog.dismiss()
                        Toast.makeText(
                            applicationContext,
                            "${response.message()}",
                            Toast.LENGTH_SHORT
                        ).show()
//                        Utils().updateLog(this@UpdateProfileActivity, "${sharedPreferences.getString("USER_ID", null)}", Utils().getCurrentDateTime(), "$TAG Error", "${response.code()}, ${response.message()}\n${jsonObject}")
                    }
                }
            }

            override fun onFailure(call: Call<Login>?, t: Throwable) {
                Log.e(TAG, "onFailure: $call")
                progressDialog.dismiss()
                Toast.makeText(
                    applicationContext,
                    "Server error, please try after some time",
                    Toast.LENGTH_SHORT
                ).show()
//                Utils().updateLog(this@UpdateProfileActivity, "${sharedPreferences.getString("USER_ID", null)}", Utils().getCurrentDateTime(), "$TAG Error", "${t.message}\n${jsonObject}")
            }
        })
    }

    private fun saveUserDetail() {
        val editor = sharedPreferences.edit()
        editor.putString("USER_ID", loginData!!.userId.toString())
        editor.putString("COUNTRY_CODE", loginData!!.countryCode)
        editor.putString("MOBILE_NO", loginData!!.mobileNo)
        editor.putString("TOKEN", loginData!!.token)
        editor.putString("NAME", "${etName.text!!.trim()} ${etLastName.text!!.trim()}")
        editor.putString("FIRST_NAME", etName.text!!.trim().toString())
        editor.putString("LAST_NAME", etLastName.text!!.trim().toString())
        editor.putString("EMAIL", "${etEmail.text!!.trim()}")
        editor.putString("DEFAULT_PICTURE_URL", loginData!!.defaultPictureUrl)
        editor.apply()
        progressDialog.dismiss()
        finish()
    }

}