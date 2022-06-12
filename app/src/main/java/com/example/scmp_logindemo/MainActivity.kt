package com.example.scmp_logindemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.scmp_logindemo.ui.fragment.LoginFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            toLoginPage()
        }
    }

    private fun toLoginPage(){
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, LoginFragment.newInstance())
            .commitNow()
    }
}