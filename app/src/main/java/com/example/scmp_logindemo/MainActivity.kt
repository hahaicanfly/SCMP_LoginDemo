package com.example.scmp_logindemo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.scmp_logindemo.ui.fragment.HomePageFragment
import com.example.scmp_logindemo.ui.fragment.LoginFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            toLoginPage()
        }
    }

    private fun toLoginPage() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, LoginFragment.newInstance())
            .commitNow()
    }

    fun toLoginSuccess(token: String?) {
        val homePageFragment = HomePageFragment.newInstance(token)
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, homePageFragment)
            .commitNow()
    }
}