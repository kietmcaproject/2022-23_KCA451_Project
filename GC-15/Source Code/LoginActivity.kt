package com.app.pepens.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.app.pepens.R
import com.app.pepens.fragment.NumberFragment

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val fragment = NumberFragment()
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager!!.beginTransaction()
        fragmentTransaction.replace(R.id.frameLayout, fragment)
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()
    }

    override fun onBackPressed() {
        val fragmentManager = supportFragmentManager
        var fragment = supportFragmentManager.findFragmentById(R.id.frameLayout)
        when (fragment) {
            is NumberFragment -> {
                finish()
            }
            else -> {
                if (fragmentManager.backStackEntryCount > 0) {
                    fragmentManager.popBackStack()
                } else {
                    super.onBackPressed()
                }
            }
        }
    }

}