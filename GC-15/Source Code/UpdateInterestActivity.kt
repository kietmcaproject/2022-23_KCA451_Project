package com.app.pepens.activity

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.GridLayoutManager
import com.app.pepens.R
import com.app.pepens.adapter.InterestAdapter
import com.app.pepens.model.Interest.Interest
import com.app.pepens.model.Login.Login
import com.app.pepens.utils.Urls
import com.app.pepens.utils.Utils
import com.app.expensemanager.util.VolleySingleton
import com.android.volley.DefaultRetryPolicy
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.bumptech.glide.Glide
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_update_interest.*
import org.json.JSONArray
import org.json.JSONObject

class UpdateInterestActivity : AppCompatActivity(), View.OnClickListener {

    private val TAG = UpdateBasicProfileActivity::class.java.simpleName
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var progressDialog: Dialog
    private var login: Login? = null
    private var interest: Interest? = null
    private var interestAdapter: InterestAdapter? = null

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_interest)

        if (intent != null) {
            login = intent.getParcelableExtra("loginUser")
        }

        sharedPreferences = getSharedPreferences("USER_DATA", Context.MODE_PRIVATE)
        progressDialog = Utils().progressDialog(this)

        if (login != null) {
            Glide.with(applicationContext)
                .load(login!!.loginData!!.defaultPictureUrl)
                .into(ivProfilePicHeader)
            tvTitleHeader.text = login!!.loginData!!.name
        } else {
            Glide.with(applicationContext)
                .load(sharedPreferences.getString("DEFAULT_PICTURE_URL", null))
                .into(ivProfilePicHeader)
            tvTitleHeader.text = sharedPreferences.getString("NAME", null)
        }

        progressDialog.show()
        getInterests()

        layoutNext.background.setTint(Color.LTGRAY)
        ivNext.background.setTint(Color.LTGRAY)
        ivNext.setColorFilter(Color.WHITE)
        layoutNext.isEnabled = false

        ivBack.setOnClickListener(this)
        layoutNext.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.ivBack -> {
                if (login != null) {
                    exitFromApp()
                } else {
                    finish()
                }
            }

            R.id.layoutNext -> {
                progressDialog.show()
                updateInterest()
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    private fun getInterests() {
        val request = StringRequest(Request.Method.GET, "${Urls.GET_INTERESTS}${sharedPreferences.getString("USER_ID", null)}${Urls.INTERESTS}false",
            { response ->
                Log.e(TAG, "getInterests: $response")
                if (recyclerView != null) {
                    val jsonObject = JSONObject(response.toString())
                    if (jsonObject.has("IsSuccess") && jsonObject.getBoolean("IsSuccess")) {
                        interest = Gson().fromJson(response.toString(), Interest::class.java)
                        recyclerView.visibility = View.VISIBLE
                        recyclerView.layoutManager = GridLayoutManager(applicationContext, 4)
                        interestAdapter = InterestAdapter(this, interest!!)
                        recyclerView.adapter = interestAdapter

                        updateView()
                    }
                    progressDialog.dismiss()
                }
            }, {
                Log.e(TAG, "getInterests: $it")
                recyclerView.visibility = View.GONE
                progressDialog.dismiss()
                Toast.makeText(applicationContext, "${it.message}", Toast.LENGTH_SHORT).show()
            })
        request.retryPolicy = DefaultRetryPolicy(10000, 2, 1f)
        VolleySingleton.getInstance(applicationContext).addToRequestQueue(request)
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    fun updateItems(position: Int) {
        if (interest!!.interestData!![position].userId == 0) {
            interest!!.interestData!![position].userId =
                sharedPreferences.getString("USER_ID", "0")!!.toInt()
        } else {
            interest!!.interestData!![position].userId = 0
        }
        interestAdapter!!.notifyDataSetChanged()

        updateView()
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    private fun updateView() {
        var count = 0
        for (i in 0 until interest!!.interestData!!.size) {
            if (interest!!.interestData!![i].userId != 0) {
                count++
            }
        }
        if (count >= 5) {
            layoutNext.background.setTint(ContextCompat.getColor(this, R.color.colorPrimary))
            ivNext.background.setTint(ContextCompat.getColor(this, R.color.colorBG))
            ivNext.setColorFilter(ContextCompat.getColor(this, R.color.colorPrimary))
            layoutNext.isEnabled = true
        } else {
            layoutNext.background.setTint(Color.LTGRAY)
            ivNext.background.setTint(Color.LTGRAY)
            ivNext.setColorFilter(Color.WHITE)
            layoutNext.isEnabled = false
        }
    }

    private fun updateInterest() {
        var jsonArray = JSONArray()
        for (i in 0 until interest!!.interestData!!.size) {
            if (interest!!.interestData!![i].userId != 0) {
                val jsonObject = JSONObject()
                jsonObject.put("InterestId", interest!!.interestData!![i].interestId)
                jsonObject.put("UserId", interest!!.interestData!![i].userId)
                jsonArray.put(jsonObject)
            }
        }
        val jsonObject = JSONObject()
        jsonObject.put("UserId", sharedPreferences.getString("USER_ID", null))
        jsonObject.put("Interests", jsonArray)
        Log.e(TAG, "updateIterest: $jsonObject")
        val request = JsonObjectRequest(Request.Method.POST, Urls.UPDATE_INTERESTS, jsonObject, { response ->
                Log.e(TAG, "updateIterest: $response")
                val jsonObject = JSONObject(response.toString())
                progressDialog.dismiss()
                if (jsonObject.getBoolean("IsSuccess")) {
                    if (login != null) {
                        var intent = Intent(applicationContext, MainActivity::class.java)
                        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP
                        intent.putExtra("loginUser", login)
                        startActivity(intent)
                        finish()
                    } else {
                        finish()
                    }
                }
            }, {
                Log.e(TAG, "updateIterest: $it")
                progressDialog.dismiss()
                Toast.makeText(this, "Volley error: $it", Toast.LENGTH_SHORT).show()
            })
        request.retryPolicy = DefaultRetryPolicy(10000, 0, 1f)
        VolleySingleton.getInstance(this).addToRequestQueue(request)
    }

    private fun exitFromApp() {
        MaterialAlertDialogBuilder(this@UpdateInterestActivity)
            .setTitle(R.string.exit)
            .setMessage(R.string.exit_message)
            .setCancelable(false)
            .setPositiveButton(R.string.yes) { dialog, id ->
                finish()
            }
            .setNegativeButton(R.string.no) { dialog, id ->
                dialog.dismiss()
            }
            .show()
    }

}