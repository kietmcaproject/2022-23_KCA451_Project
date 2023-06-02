package com.app.pepens.activity

import android.Manifest
import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.content.IntentSender
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.location.Address
import android.location.Geocoder
import android.location.Location
import android.location.LocationManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.provider.Settings
import android.util.Log
import android.view.View
import android.view.Window
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.app.ActivityCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.app.pepens.R
import com.app.pepens.adapter.ItemsAdapter
import com.app.pepens.fragment.HomeFragment
import com.app.pepens.fragment.PickAndDrop.PickAndDropFragment
import com.app.pepens.fragment.PlaceOrderFragment
import com.app.pepens.fragment.Restaurants.PlaceRestaurantOrderFragment
import com.app.pepens.fragment.Restaurants.RestaurantsFragment
import com.app.pepens.fragment.ShoppingFragment
import com.app.pepens.model.AvailableLocations.AvailableLocation
import com.app.pepens.model.CartItemsList
import com.app.pepens.model.Coordinates.GeoCoordinates
import com.app.pepens.model.ItemsList
import com.app.pepens.model.LogData
import com.app.pepens.model.Login.Login
import com.app.pepens.model.RestaurantCartList
import com.app.pepens.utils.Urls
import com.app.pepens.utils.Utils
import com.app.expensemanager.util.VolleySingleton
import com.android.volley.DefaultRetryPolicy
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.api.GoogleApiClient
import com.google.android.gms.common.api.ResolvableApiException
import com.google.android.gms.location.*
import com.google.android.libraries.maps.CameraUpdateFactory
import com.google.android.libraries.maps.GoogleMap
import com.google.android.libraries.maps.OnMapReadyCallback
import com.google.android.libraries.maps.SupportMapFragment
import com.google.android.libraries.maps.model.CircleOptions
import com.google.android.libraries.maps.model.LatLng
import com.google.android.libraries.places.api.Places
import com.google.android.libraries.places.api.model.Place
import com.google.android.libraries.places.widget.Autocomplete
import com.google.android.libraries.places.widget.AutocompleteActivity
import com.google.android.libraries.places.widget.model.AutocompleteActivityMode
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_location.*
import org.json.JSONObject
import kotlin.math.roundToInt

class LocationActivity : AppCompatActivity(), View.OnClickListener, OnMapReadyCallback,
    GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener,
    LocationListener {

    private val TAG = LocationActivity::class.java.simpleName
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var progressDialog: Dialog
    private var login: Login? = null
    private var latitude: Double = 28.6139
    private var longitude: Double = 77.2090
    private var mMap: GoogleMap? = null
    private var mapFragment: SupportMapFragment? = null
    private var geoCoder: Geocoder? = null
    private var addressList: List<Address>? = null
    private var address: String? = null
    private var partnerId: Int = 0
    private var areaId: Int = 0
    private var knownName: String? = null
    private var locality: String? = null
    private var zipcode: String? = null
    private var city: String? = null
    private var state: String? = null
    private var country: String? = null
    private val AUTOCOMPLETE_REQUEST_CODE = 1
    private var zoom = 12F
    private val REQUEST_CODE = 101
    private val REQUEST_LOCATION = 102
    private var mGoogleApiClient: GoogleApiClient? = null
    private var location: Location? = null
    private var locationRequest: LocationRequest? = null
    private var locationManager: LocationManager? = null
    private var oldAreaId = 0
    private var accuracy = 0f
    private var usingCurrentLocation = false
    private var updateLocationPermission = false
    private var availableLocation: AvailableLocation? = null
    private var availableDialog: Dialog? = null
    private var ivHeaderBack: ImageView? = null
    private var tvHeader: TextView? = null
    private var recyclerViewAvailable: RecyclerView? = null
    private var availableArrayList: ArrayList<ItemsList> = ArrayList()
    private var availableAdapter: ItemsAdapter? = null

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
        setContentView(R.layout.activity_location)

//        Utils().restartAppAtCrash(this, LocationActivity::class.java.simpleName)

        tvConnection = findViewById(R.id.tv_connection)

        sharedPreferences = getSharedPreferences("USER_DATA", Context.MODE_PRIVATE)
        progressDialog = Utils().progressDialog(this)

        mGoogleApiClient = GoogleApiClient.Builder(this)
            .addConnectionCallbacks(this)
            .addOnConnectionFailedListener(this)
            .addApi(LocationServices.API)
            .build()

        Places.initialize(applicationContext, resources.getString(R.string.maps_key))
        geoCoder = Geocoder(this@LocationActivity)

        mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment

        if (intent != null) {
            login = intent.getParcelableExtra("loginUser")
            Log.e(
                TAG,
                "onCreate: ${login!!.loginData!!.latitude}     ${login!!.loginData!!.longitude}"
            )
            if (login != null) {
                if (login!!.loginData!!.latitude != null && login!!.loginData!!.longitude != null) {
                    if (!login!!.loginData!!.latitude.isNullOrEmpty() && !login!!.loginData!!.longitude.isNullOrEmpty() && login!!.loginData!!.latitude != "0" && login!!.loginData!!.longitude != "0" && login!!.loginData!!.latitude != "0.0" && login!!.loginData!!.longitude != "0.0") {
                        zoom = 12F
                        latitude = login!!.loginData!!.latitude!!.toDouble()
                        longitude = login!!.loginData!!.longitude!!.toDouble()
                        areaId = login!!.loginData!!.areaId.toInt()
                        partnerId = login!!.loginData!!.partnerId
                        if (tvAccuracy != null) {
                            mapFragment!!.getMapAsync(this)
                        }
                    } else {
                        checkLocation()
                    }
                } else {
                    checkLocation()
                }
            } else if (MainActivity.login != null) {
                zoom = 16F
                latitude = MainActivity.login!!.loginData!!.latitude!!.toDouble()
                longitude = MainActivity.login!!.loginData!!.longitude!!.toDouble()
                areaId = MainActivity.login!!.loginData!!.areaId.toInt()
                partnerId = MainActivity.login!!.loginData!!.partnerId
                if (tvAccuracy != null) {
                    mapFragment!!.getMapAsync(this)
                }
            } else {
                checkLocation()
            }
        } else if (MainActivity.login != null) {
            zoom = 16F
            latitude = MainActivity.login!!.loginData!!.latitude!!.toDouble()
            longitude = MainActivity.login!!.loginData!!.longitude!!.toDouble()
            areaId = MainActivity.login!!.loginData!!.areaId.toInt()
            partnerId = MainActivity.login!!.loginData!!.partnerId
            if (tvAccuracy != null) {
                mapFragment!!.getMapAsync(this)
            }
        } else {
            checkLocation()
        }

        checkAvailableLocations()

        ivBack.setOnClickListener(this)
        ivAutoDetect.setOnClickListener(this)
        ivAutoDetectMSG.setOnClickListener(this)
        btnChange.setOnClickListener(this)
        btnAuto.setOnClickListener(this)
        tvAvailable.setOnClickListener(this)
        tvSave.setOnClickListener(this)
    }

    override fun onResume() {
        super.onResume()

        Utils().updatePagesLog(
            this,
            LogData(
                pageId = Urls.STARTUP_LOCATION_PAGE,
                title = "Update location",
                eventName = "Page Open"
            )
        )

        if (updateLocationPermission) {
            checkLocation()
        }
    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.ivBack -> {
                finish()
            }

            R.id.ivAutoDetect -> {
                checkLocation()
            }

            R.id.ivAutoDetectMSG -> {
                checkLocation()
            }

            R.id.btnAuto -> {
                checkLocation()
            }

            R.id.btnChange -> {
                tvAccuracy.visibility = View.GONE
                progressBar.visibility = View.GONE
                usingCurrentLocation = false
                if (mGoogleApiClient!!.isConnected) {
                    mGoogleApiClient!!.disconnect()
                }
                val fields = listOf(
                    Place.Field.ID,
                    Place.Field.NAME,
                    Place.Field.LAT_LNG,
                    Place.Field.ADDRESS,
                    Place.Field.TYPES
                )
                val intent = Autocomplete.IntentBuilder(AutocompleteActivityMode.FULLSCREEN, fields)
                    .setCountry("IN").build(this)
                startActivityForResult(intent, AUTOCOMPLETE_REQUEST_CODE)
            }

            R.id.tvAvailable -> {
                tvAccuracy.visibility = View.GONE
                progressBar.visibility = View.GONE
                usingCurrentLocation = false
                if (mGoogleApiClient!!.isConnected) {
                    mGoogleApiClient!!.disconnect()
                }
                if (availableLocation != null) {
                    dialogAvailable()
                } else {
                    Toast.makeText(
                        this,
                        "Currently we are not available in this location",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

            R.id.tvSave -> {
                if (areaId > 0 && partnerId > 0) {
                    progressDialog.show()
                    updatePinCode()
                } else {
                    Toast.makeText(this, "Unable to save, please change or move location", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun checkLocation() {
        tvErrorMsg.visibility = View.GONE
//        btnSave.isEnabled = false
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                    REQUEST_CODE
                )
            } else {
                permission()
            }
        } else {
            permission()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == AUTOCOMPLETE_REQUEST_CODE) {
            when (resultCode) {
                Activity.RESULT_OK -> {
                    data?.let {
                        val place = Autocomplete.getPlaceFromIntent(data)
                        address = place.address
                        latitude = place.latLng!!.latitude
                        longitude = place.latLng!!.longitude
                        progressBar.visibility = View.GONE
                        tvAccuracy.visibility = View.GONE
                        usingCurrentLocation = false
                        progressDialog.show()
                        if (tvAccuracy != null) {
                            mapFragment!!.getMapAsync(this)
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
                    Log.e(TAG, "onActivityResult: Cancel")
                }
            }
            return
        } else if (requestCode == REQUEST_LOCATION) {
            when (resultCode) {
                Activity.RESULT_OK -> {
                    usingCurrentLocation = true
                    progressBar.visibility = View.VISIBLE
                    if (mGoogleApiClient!!.isConnected) {
                        mGoogleApiClient!!.disconnect()
                    }
                    mGoogleApiClient!!.connect()
                }
                Activity.RESULT_CANCELED -> {
                    MaterialAlertDialogBuilder(this)
                        .setTitle("Location Permission")
                        .setMessage("The app needs location permissions. Please grant location permission to continue using the features of the app. Tap on 'YES' to grant permission from setting.")
                        .setCancelable(false)
                        .setPositiveButton(R.string.yes) { dialog, id ->
                            checkLocation()
                        }.show()
                }
            }
        }
        super.onActivityResult(requestCode, resultCode, data)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            REQUEST_CODE -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Log.e(TAG, "onRequestPermissionsResult: 1")
                    permission()
                } else {
                    Log.e(TAG, "onRequestPermissionsResult: 2")
                    updateLocationPermission = false
                    MaterialAlertDialogBuilder(this)
                        .setTitle("Location Permission")
                        .setMessage("The app needs location permissions. Please grant location permission to continue using the features of the app. Tap on 'YES' to grant permission from setting.")
                        .setCancelable(false)
                        .setPositiveButton(R.string.yes) { dialog, id ->
                            updateLocationPermission = true
                            startActivity(Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS).apply {
                                data = Uri.fromParts("package", "com.app.pepens", null)
                            })
                        }.show()
                }
            }
        }
    }

    private fun permission() {
        locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        var hasGps = locationManager!!.isProviderEnabled(LocationManager.GPS_PROVIDER)
        if (!hasGps) {
            locationRequest = LocationRequest.create()
                .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
                .setInterval(6000)
            var builder = LocationSettingsRequest.Builder().addLocationRequest(locationRequest!!)
            var result = LocationServices.getSettingsClient(this).checkLocationSettings(builder.build())
            result.addOnSuccessListener { locationSettingsResponse ->

            }
            result.addOnFailureListener { exception ->
                if (exception is ResolvableApiException) {
                    try {
                        exception.startResolutionForResult(this, REQUEST_LOCATION)
                    } catch (sendEx: IntentSender.SendIntentException) {
                        // Ignore the error.
                    }
                }
            }
        } else {
            usingCurrentLocation = true
            progressBar.visibility = View.VISIBLE
            if (mGoogleApiClient!!.isConnected) {
                mGoogleApiClient!!.disconnect()
            }
            progressDialog.show()
            mGoogleApiClient!!.connect()
        }
    }

    override fun onConnected(p0: Bundle?) {
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return
        }
        if (mGoogleApiClient != null) {
            location = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient)
        }
        startLocationUpdates()
    }

    private fun startLocationUpdates() {
        locationRequest =
            LocationRequest.create().setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
                .setInterval(6000)
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            // public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return
        }
        LocationServices.FusedLocationApi.requestLocationUpdates(
            mGoogleApiClient,
            locationRequest,
            this
        )
    }

    override fun onConnectionSuspended(p0: Int) {
        Log.d("TAG", "Connection Suspended  $p0")
        progressDialog.dismiss()
        progressBar.visibility = View.GONE
        if (mGoogleApiClient != null) {
            mGoogleApiClient!!.connect()
        }
    }

    override fun onConnectionFailed(p0: ConnectionResult) {
        Log.d("TAG", "Connection failed. Error: " + p0.errorCode)
        progressDialog.dismiss()
        progressBar.visibility = View.GONE
        if (mGoogleApiClient != null) {
            mGoogleApiClient!!.connect()
        }
    }

    override fun onLocationChanged(p0: Location?) {
        Log.d("requestMain1", "--->>>>    ${p0!!.latitude}, ${p0.longitude} ${p0.accuracy}")
        latitude = p0.latitude
        longitude = p0.longitude
        accuracy = p0.accuracy

        if (tvAccuracy != null) {
            progressDialog.dismiss()
            mapFragment!!.getMapAsync(this)
        }
    }

    override fun onMapReady(googleMap: GoogleMap?) {
//        btnSave.isEnabled = false
        mMap = googleMap
        var radius = accuracy.toDouble()
        zoom = when {
            accuracy < 100.0 -> {
                18F
            }
            accuracy < 300.0 -> {
                16F
            }
            accuracy < 600.0 -> {
                14.5F
            }
            accuracy < 1500.0 -> {
                14F
            }
            else -> {
                13F
            }
        }
        Log.e(TAG, "onMapReady: $accuracy $radius")
        mMap!!.clear()
        if (usingCurrentLocation) {
            if (accuracy > 0 && accuracy <= Urls.MINIMUM_ACCURACY) {
                usingCurrentLocation = false
                progressBar.visibility = View.VISIBLE
                tvAccuracy.visibility = View.GONE
//                btnSave.isEnabled = true
                if (mGoogleApiClient != null) {
                    if (mGoogleApiClient!!.isConnected) {
                        progressBar.visibility = View.GONE
                        mGoogleApiClient!!.disconnect()
                    }
                }
            } else {
                tvAccuracy.visibility = View.VISIBLE
                tvAccuracy.text =
                    "Please wait finding accurate location. Current accuracy is ${accuracy.roundToInt()}m"
                tvErrorMsg.visibility = View.GONE
//                btnSave.isEnabled = false
            }
        } else {
            progressBar.visibility = View.GONE
            tvAccuracy.visibility = View.GONE
            tvErrorMsg.visibility = View.GONE
//            btnSave.isEnabled = true
        }
        var add = LatLng(latitude, longitude)
        mMap!!.addCircle(
            CircleOptions()
                .center(LatLng(latitude, longitude))
                .radius(radius)
                .strokeWidth(1.5f)
                .strokeColor(0x663F51B5)
                .fillColor(0x333F51B5)
        )
        mMap!!.moveCamera(CameraUpdateFactory.newLatLngZoom(add, zoom))
        mMap!!.setOnCameraMoveStartedListener { it ->
            when (it) {
                GoogleMap.OnCameraMoveStartedListener.REASON_GESTURE -> {
                    Log.e(TAG, "onMapReady: The user gestured on the map.")
                    usingCurrentLocation = false
                    progressBar.visibility = View.GONE
                    tvAccuracy.visibility = View.GONE
//                    btnSave.isEnabled = false
                    if (mGoogleApiClient != null) {
                        if (mGoogleApiClient!!.isConnected) {
                            mGoogleApiClient!!.disconnect()
                        }
                    }
                }
                GoogleMap.OnCameraMoveStartedListener.REASON_API_ANIMATION -> {
                    Log.e(TAG, "onMapReady: The user tapped something on the map.")
                    usingCurrentLocation = false
                    progressBar.visibility = View.GONE
                    tvAccuracy.visibility = View.GONE
//                    btnSave.isEnabled = false
                    if (mGoogleApiClient != null) {
                        if (mGoogleApiClient!!.isConnected) {
                            mGoogleApiClient!!.disconnect()
                        }
                    }
                }
                GoogleMap.OnCameraMoveStartedListener.REASON_DEVELOPER_ANIMATION -> {
                    Log.e(TAG, "onMapReady: The app moved the camera.")
                }
            }
        }
        mMap!!.setOnCameraIdleListener {
            mMap!!.clear()
            val midLatLng: LatLng = mMap!!.cameraPosition.target
            try {
                try {
                    latitude = midLatLng.latitude
                    longitude = midLatLng.longitude
                    addressList = geoCoder!!.getFromLocation(latitude, longitude, 3)
                } catch (e: Exception) {
                    latitude = midLatLng.latitude
                    longitude = midLatLng.longitude
                    addressList = null
                }
                Log.e(TAG, "onMapReady:- $accuracy $radius")
                mMap!!.addCircle(
                    CircleOptions()
                        .center(LatLng(latitude, longitude))
                        .radius(radius)
                        .strokeWidth(1.5f)
                        .strokeColor(0x663F51B5)
                        .fillColor(0x333F51B5)
                )
                if (addressList != null && addressList!!.isNotEmpty()) {
                    var addressPosition = 0
                    var addressType = 0
                    var p =
                        addressList!!.indexOfFirst { item -> !item.thoroughfare.isNullOrEmpty() }
                    if (p > -1) {
                        addressPosition = p
                        addressType = 1
                    } else {
                        var p =
                            addressList!!.indexOfFirst { item -> !item.featureName.isNullOrEmpty() }
                        if (p > -1) {
                            addressPosition = p
                            addressType = 2
                        } else {
                            addressPosition = 0
                            addressType = 0
                        }
                    }
                    when (addressType) {
                        0 -> {
                            address =
                                "${addressList!![addressPosition].subAdminArea}, ${addressList!![addressPosition].adminArea}, ${addressList!![addressPosition].countryName}, ${addressList!![addressPosition].postalCode}"
                        }
                        1 -> {
                            address =
                                "${addressList!![addressPosition].thoroughfare}, ${addressList!![addressPosition].subAdminArea}, ${addressList!![addressPosition].adminArea}, ${addressList!![addressPosition].countryName}, ${addressList!![addressPosition].postalCode}"
                        }
                        2 -> {
                            address =
                                "${addressList!![addressPosition].featureName}, ${addressList!![addressPosition].subAdminArea}, ${addressList!![addressPosition].adminArea}, ${addressList!![addressPosition].countryName}, ${addressList!![addressPosition].postalCode}"
                        }
                    }
                    address = address!!.replace(
                        "Unnamed Road",
                        "${addressList!![addressPosition].locality}"
                    )
                    address = address!!.replace("null,", "")
                    locality = if (!addressList!![addressPosition].locality.isNullOrEmpty()) {
                        addressList!![addressPosition].locality
                    } else {
                        null
                    }
                    city = if (!addressList!![addressPosition].subAdminArea.isNullOrEmpty()) {
                        addressList!![addressPosition].subAdminArea
                    } else {
                        null
                    }
                    if (city.isNullOrEmpty()) {
                        city = if (!addressList!![addressPosition].locality.isNullOrEmpty()) {
                            addressList!![addressPosition].locality
                        } else {
                            null
                        }
                    }
                    state = if (!addressList!![addressPosition].adminArea.isNullOrEmpty()) {
                        addressList!![addressPosition].adminArea
                    } else {
                        null
                    }
                    country = if (!addressList!![addressPosition].countryName.isNullOrEmpty()) {
                        addressList!![addressPosition].countryName
                    } else {
                        null
                    }
                    zipcode = if (!addressList!![addressPosition].postalCode.isNullOrEmpty()) {
                        addressList!![addressPosition].postalCode
                    } else {
                        null
                    }
                    knownName = if (!addressList!![addressPosition].thoroughfare.isNullOrEmpty()) {
                        addressList!![addressPosition].thoroughfare
                    } else {
                        null
                    }
                    tvAddress.text = "$address"
                    checkGeoCoordinates()
                } else {
                    locality = null
                    progressDialog.dismiss()
                    Toast.makeText(
                        this,
                        "Unable to fetch location please select location again.",
                        Toast.LENGTH_SHORT
                    ).show()
                    tvSave.isEnabled = false
                    tvSave.setBackgroundColor(resources.getColor(R.color.grey))
                }
            } catch (e: Exception) {
                Log.e(TAG, "onMapReady: $e")
            }
        }
    }

    override fun onPause() {
        super.onPause()

        if (mGoogleApiClient != null) {
            if (mGoogleApiClient!!.isConnected) {
                progressDialog.dismiss()
                mGoogleApiClient!!.disconnect()
            }
        }
    }

    private fun checkGeoCoordinates() {
        val request = StringRequest(Request.Method.GET,
            "${Urls.GET_AREA_BY_GEOCORDINATES}$latitude&long=$longitude",
            { response ->
                Log.e(TAG, "checkGeoCoordinates: $response")
                if (tvAddress != null) {
                    Utils().updatePagesLog(
                        this,
                        LogData(
                            pageId = Urls.STARTUP_LOCATION_PAGE,
                            title = "Check Location",
                            eventName = "Location Change",
                            note = "$response"
                        )
                    )
                    progressDialog.dismiss()
                    val jsonObject = JSONObject(response.toString())
                    if (jsonObject.getBoolean("IsSuccess")) {
                        var geoCoordinates =
                            Gson().fromJson(response.toString(), GeoCoordinates::class.java)
                        partnerId = geoCoordinates!!.geoCoordinatesData!!.partnerId
                        areaId = geoCoordinates!!.geoCoordinatesData!!.areaId
                        tvErrorMsg.visibility = View.GONE
                        tvAvailable.visibility = View.GONE

                        if (accuracy > Urls.MINIMUM_ACCURACY && usingCurrentLocation) {
                            tvSave.isEnabled = false
                            tvSave.setBackgroundColor(resources.getColor(R.color.grey))
                            tvAccuracy.visibility = View.VISIBLE
                            tvAccuracy.text =
                                "Please wait finding accurate location. Current accuracy is ${accuracy.roundToInt()}m"
                        } else {
                            progressBar.visibility = View.GONE
                            tvAccuracy.visibility = View.GONE
                            tvSave.isEnabled = true
                            tvSave.setBackgroundColor(resources.getColor(R.color.colorPrimary))
                            if (mGoogleApiClient != null) {
                                if (mGoogleApiClient!!.isConnected) {
                                    progressBar.visibility = View.GONE
                                    mGoogleApiClient!!.disconnect()
                                }
                            }
                        }
                    } else {
                        partnerId = 0
                        areaId = 0
                        tvErrorMsg.visibility = View.VISIBLE
                        tvAvailable.visibility = View.VISIBLE
                        tvSave.isEnabled = false
                        tvSave.setBackgroundColor(resources.getColor(R.color.grey))
                    }
                }
            },
            {
                Log.e(TAG, "checkGeoCoordinates: $it")
                if (tvAccuracy != null) {
                    progressDialog.dismiss()
                    partnerId = 0
                    areaId = 0
                    tvErrorMsg.visibility = View.VISIBLE
                    tvAvailable.visibility = View.VISIBLE
                    tvSave.isEnabled = false
                    tvSave.setBackgroundColor(resources.getColor(R.color.grey))
                }
            })
        request.retryPolicy = DefaultRetryPolicy(10000, 0, 1f)
        VolleySingleton.getInstance(this).addToRequestQueue(request)
    }

    private fun checkAvailableLocations() {
        val request = StringRequest(Request.Method.GET, "${Urls.GET_AVAILABLE_LOCATIONS}", { response ->
                Log.e(TAG, "checkAvailableLocations: $response")
                if (tvAddress != null) {
                    val jsonObject = JSONObject(response.toString())
                    availableLocation = if (jsonObject.getBoolean("IsSuccess")) {
                        Gson().fromJson(response.toString(), AvailableLocation::class.java)
                    } else {
                        null
                    }
                }
            }, {
                Log.e(TAG, "checkAvailableLocations: $it")
                if (tvAccuracy != null) {
                    availableLocation = null
                }
            })
        request.retryPolicy = DefaultRetryPolicy(10000, 0, 1f)
        VolleySingleton.getInstance(this).addToRequestQueue(request)
    }

    private fun dialogAvailable() {
        availableDialog = Dialog(this)
        availableDialog!!.requestWindowFeature(Window.FEATURE_NO_TITLE)
        availableDialog!!.setContentView(R.layout.dialog_layout)
        availableDialog!!.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        availableDialog!!.window!!.setLayout(
            ConstraintLayout.LayoutParams.MATCH_PARENT,
            ConstraintLayout.LayoutParams.MATCH_PARENT
        )
        ivHeaderBack = availableDialog!!.findViewById(R.id.ivBack) as ImageView
        tvHeader = availableDialog!!.findViewById(R.id.tvHeader) as TextView
        var ivSearch = availableDialog!!.findViewById(R.id.ivSearch) as ImageView
        ivSearch.visibility = View.GONE
        tvHeader!!.text = "Available Locations"
        recyclerViewAvailable = availableDialog!!.findViewById(R.id.recyclerView) as RecyclerView
        ivHeaderBack!!.setOnClickListener {
            availableDialog!!.dismiss()
        }
        availableArrayList.clear()
        for (i in availableLocation!!.availableLocationData!!.indices) {
            availableArrayList.add(
                ItemsList(
                    availableLocationData = availableLocation!!.availableLocationData!![i],
                    name = "${availableLocation!!.availableLocationData!![i].areaName}, ${availableLocation!!.availableLocationData!![i].state}",
                    isSelected = false
                )
            )
        }
        recyclerViewAvailable!!.layoutManager =
            LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        availableAdapter = ItemsAdapter(this, availableArrayList)
        recyclerViewAvailable!!.adapter = availableAdapter
        availableAdapter!!.onItemClick = { it ->
            availableDialog!!.dismiss()
            tvErrorMsg.visibility = View.GONE
            tvAvailable.visibility = View.GONE
            tvAccuracy.visibility = View.GONE
            progressBar.visibility = View.GONE
            usingCurrentLocation = false
            if (mGoogleApiClient!!.isConnected) {
                mGoogleApiClient!!.disconnect()
            }
            areaId = it.availableLocationData!!.areaId
            tvAddress.text =
                "${it.availableLocationData!!.areaName}, ${it.availableLocationData!!.state}, ${it.availableLocationData!!.zipcode}"
            tvErrorMsg.visibility = View.GONE
            latitude = it.availableLocationData!!.latitude!!.toDouble()
            longitude = it.availableLocationData!!.longitude!!.toDouble()
            progressDialog.show()
            mapFragment!!.getMapAsync(this)
        }
        availableDialog!!.show()
    }

    private fun updatePinCode() {
        val jsonObject = JSONObject()
        jsonObject.put("UserId", sharedPreferences.getString("USER_ID", null))
        jsonObject.put("PartnerId", partnerId)
        jsonObject.put("AreaId", areaId)
        if (locality.isNullOrEmpty() || locality == "null") {
            jsonObject.put("Locality", city)
        } else {
            jsonObject.put("Locality", locality)
        }
        jsonObject.put("Zipcode", zipcode)
        jsonObject.put("City", city)
        jsonObject.put("State", state)
        jsonObject.put("Country", country)
        jsonObject.put("Latitude", latitude)
        jsonObject.put("Longitude", longitude)
        jsonObject.put("CurrentLocation", address)
        jsonObject.put("IsUseCurrentLocation", usingCurrentLocation)
        Log.e(TAG, "updateLocation: $jsonObject")
        Utils().updatePagesLog(
            this,
            LogData(
                areaId = "$areaId",
                pageId = Urls.STARTUP_LOCATION_PAGE,
                title = "Update Location",
                eventName = "Save & Proceed",
                note = "$jsonObject"
            )
        )
        val request = JsonObjectRequest(
            Request.Method.POST,
            Urls.UPDATE_SHOPPING,
            jsonObject,
            { response ->
                Log.e(TAG, "updateLocation: $response")
                progressDialog.dismiss()
                val jsonObject = JSONObject(response.toString())
                if (jsonObject.getBoolean("IsSuccess")) {
                    PlaceRestaurantOrderFragment.addressData = null
                    PlaceOrderFragment.addressData = null
                    HomeFragment.apiDataReceived = false
                    RestaurantsFragment.apiDataReceived = false
                    ShoppingFragment.apiDataReceived = false
                    PickAndDropFragment.apiDataReceived = false
                    PickAndDropFragment.addressType = null
                    if (login != null) {
                        try {
                            oldAreaId = login!!.loginData!!.partnerId
                            login!!.loginData!!.zipcode = zipcode
                            login!!.loginData!!.latitude = latitude.toString()
                            login!!.loginData!!.longitude = longitude.toString()
                            login!!.loginData!!.currentLocation = address
                            login!!.loginData!!.isUseCurrentLocation = usingCurrentLocation
                            login!!.loginData!!.areaId = areaId.toString()
                            login!!.loginData!!.partnerId = partnerId
                            login!!.loginData!!.locality = locality
                            login!!.loginData!!.areaId = areaId.toString()
                            var intent = Intent(applicationContext, MainActivity::class.java)
                            intent.flags =
                                Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP
                            intent.putExtra("loginUser", login)
                            startActivity(intent)
                            finish()
                        } catch (e: Exception) {
                            var intent = Intent(applicationContext, MainActivity::class.java)
                            intent.flags =
                                Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP
                            intent.putExtra("loginUser", login)
                            startActivity(intent)
                            finish()
                        }
                    } else {
                        try {
                            oldAreaId = MainActivity.login!!.loginData!!.partnerId
                            MainActivity.login!!.loginData!!.zipcode = zipcode
                            MainActivity.login!!.loginData!!.latitude = latitude.toString()
                            MainActivity.login!!.loginData!!.longitude = longitude.toString()
                            MainActivity.login!!.loginData!!.currentLocation = address
                            MainActivity.login!!.loginData!!.isUseCurrentLocation =
                                usingCurrentLocation
                            MainActivity.login!!.loginData!!.areaId = areaId.toString()
                            MainActivity.login!!.loginData!!.partnerId = partnerId
                            MainActivity.login!!.loginData!!.locality = locality
                            MainActivity.login!!.loginData!!.areaId = areaId.toString()
                            if (oldAreaId != partnerId!!.toInt()) {
                                progressDialog.show()
                                clearCart()
                            } else {
                                finish()
                            }
                        } catch (e: Exception) {
                            if (oldAreaId != partnerId!!.toInt()) {
                                progressDialog.show()
                                clearCart()
                            } else {
                                finish()
                            }
                        }
                    }
                } else {
                    tvErrorMsg.visibility = View.VISIBLE
                }
            }, {
                Log.e(TAG, "updateLocation: $it")
                progressDialog.dismiss()
                Utils().volleyError(it, this)
            })
        request.retryPolicy = DefaultRetryPolicy(10000, 0, 1f)
        VolleySingleton.getInstance(this).addToRequestQueue(request)
    }

    private fun clearCart() {
        progressDialog.show()
        val request = StringRequest(
            Request.Method.GET,
            "${
                Urls.CLEAR_CART + sharedPreferences.getString(
                    "USER_ID",
                    "0"
                )
            }${Urls.BUSINESS_CATEGORY_ID}0${Urls.BUSINESS_ID}0",
            { response ->
                Log.e(TAG, "getCartItems: $response")
                progressDialog.dismiss()
                var cartArrayList: ArrayList<CartItemsList> = ArrayList()
                cartArrayList.clear()
                Utils().updateItemInCart(this, cartArrayList)
                var restaurantCartList: ArrayList<RestaurantCartList> = ArrayList()
                restaurantCartList.clear()
                Utils().updateRestaurantInCart(this, restaurantCartList)
                finish()
            },
            {
                Log.e(TAG, "getCartItems: $it")
                progressDialog.dismiss()
            })
        request.retryPolicy = DefaultRetryPolicy(10000, 2, 1f)
        VolleySingleton.getInstance(this).addToRequestQueue(request)
    }

}