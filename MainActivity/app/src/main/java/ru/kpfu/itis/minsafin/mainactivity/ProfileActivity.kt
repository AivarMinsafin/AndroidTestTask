package ru.kpfu.itis.minsafin.mainactivity

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View


class ProfileActivity : AppCompatActivity() {
    val APP_PREF = MainActivity.APP_PREF
    val SAVED_EMAIL = MainActivity.SAVED_EMAIL
    val SAVED_PASSWORD = MainActivity.SAVED_PASSWORD
    lateinit var pref: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        pref = getSharedPreferences(APP_PREF, Context.MODE_PRIVATE)
    }

    fun toLoginPage(view: View) {
        val loginPageIntent = Intent(this, MainActivity::class.java)
        var editor = pref.edit()
        editor.putString(SAVED_EMAIL, null)
        editor.putString(SAVED_PASSWORD, null)
        editor.apply()
        startActivity(loginPageIntent)
    }
}
