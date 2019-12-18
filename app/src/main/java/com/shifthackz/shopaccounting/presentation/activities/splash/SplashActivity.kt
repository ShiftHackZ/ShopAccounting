package com.shifthackz.shopaccounting.presentation.activities.splash

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.shifthackz.shopaccounting.R
import com.shifthackz.shopaccounting.presentation.activities.main.MainActivity

class SplashActivity : AppCompatActivity() {

    private val timer = 3000L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        Handler().postDelayed({
            startActivity(MainActivity.newInstance(this))
            finish()
        }, timer)
    }
}
