 package com.app.pepens.activity

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.app.pepens.R
import com.app.pepens.fragment.PaymentsFragment
import com.app.pepens.model.OrderSuccess.OrderSuccess
import com.razorpay.Checkout
import com.razorpay.PaymentData
import com.razorpay.PaymentResultWithDataListener
import org.json.JSONObject

class PaymentActivity : AppCompatActivity(), PaymentResultWithDataListener {

    private var TAG = PaymentActivity::class.java.simpleName
    private lateinit var sharedPreferences: SharedPreferences
    private var orderSuccess: OrderSuccess? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_payment)

        sharedPreferences = getSharedPreferences("USER_DATA", Context.MODE_PRIVATE)

        Checkout.preload(applicationContext)

        orderSuccess = PaymentsFragment.orderSuccess
        startPayment()
    }

    private fun startPayment() {
        val co = Checkout()
        co.setKeyID("${MainActivity.login!!.loginData!!.razorpayKeyId}")
//        co.setKeyID("rzp_test_59GnziRh4QKO6p")
        co.setImage(R.drawable.a2z_logo)
        try {
            val options = JSONObject()
            options.put("name", getString(R.string.app_name))
            options.put("description", "")
            options.put("image", "https://s3.amazonaws.com/rzp-mobile/images/rzp.png")
            options.put("currency", "INR")
            options.put("order_id", "${orderSuccess!!.orderSuccessData!!.paymentGateway!!.paymentGatewayOrderId}")
            options.put("amount", "${orderSuccess!!.orderSuccessData!!.paymentGateway!!.paymentGatewayAmount}")

            val preFill = JSONObject()
            preFill.put("email", "${sharedPreferences.getString("EMAIL", "")}")
            preFill.put("contact", "${sharedPreferences.getString("COUNTRY_CODE", "")}${sharedPreferences.getString("MOBILE_NO", "")}")
            options.put("prefill", preFill)
            co.open(this@PaymentActivity, options)
        } catch (e: Exception) {
            Log.e(TAG, "startPayment: ${e.message}")
            Toast.makeText(this@PaymentActivity, "StartPayment: " + e.message, Toast.LENGTH_LONG).show()
        }
    }

    override fun onPaymentError(errorCode: Int, response: String?, paymentData: PaymentData?) {
        try {
            Log.e(TAG, "onPaymentError: $errorCode  $response")
            Toast.makeText(this, "$response", Toast.LENGTH_LONG).show()
            val jsonObject = JSONObject()
            val jsonObject1 = JSONObject()
            jsonObject1.put("PaymentGatewayOrderId", orderSuccess!!.orderSuccessData!!.paymentGateway!!.paymentGatewayOrderId)
            jsonObject1.put("PaymentGatewayErrorCode", errorCode)
            jsonObject1.put("PaymentGatewayErrorDescription", response)
            jsonObject1.put("PaymentGateway", "Razorpay")
            jsonObject.put("PaymentGateway", jsonObject1)
            jsonObject.put("OrderId", orderSuccess!!.orderSuccessData!!.orderId)
            jsonObject.put("UserId", sharedPreferences.getString("USER_ID", null))
            Log.e(TAG, "$jsonObject")
            PaymentsFragment.paymentSuccess = false
            PaymentsFragment.afterRequest = true
            PaymentsFragment.jsonObjectData = jsonObject
            finish()
        } catch (e: Exception) {
            Log.e(TAG, "onPaymentError: -- $e")
        }
    }

    override fun onPaymentSuccess(razorpayPaymentId: String?, paymentData: PaymentData) {
        try {
            Log.e(TAG, "onPaymentSuccess: $razorpayPaymentId  ${paymentData.signature}")
//            Toast.makeText(this, "Payment Successful", Toast.LENGTH_LONG).show()
            val jsonObject = JSONObject()
            val jsonObject1 = JSONObject()
            jsonObject1.put("PaymentGatewayPaymentId", razorpayPaymentId)
            jsonObject1.put("PaymentGatewayOrderId", orderSuccess!!.orderSuccessData!!.paymentGateway!!.paymentGatewayOrderId)
            jsonObject1.put("PaymentGatewaySignature", paymentData.signature)
            jsonObject1.put("PaymentGateway", "Razorpay")
            jsonObject.put("PaymentGateway", jsonObject1)
            jsonObject.put("OrderId", orderSuccess!!.orderSuccessData!!.orderId)
            jsonObject.put("UserId", sharedPreferences.getString("USER_ID", null))
            Log.e(TAG, "$jsonObject")
            PaymentsFragment.paymentSuccess = true
            PaymentsFragment.afterRequest = false
            PaymentsFragment.jsonObjectData = jsonObject
            finish()
        } catch (e: Exception) {
            Log.e(TAG, "onPaymentSuccess: -- $e")
        }
    }

}