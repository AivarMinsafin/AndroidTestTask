package ru.kpfu.itis.minsafin.mainactivity

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    var correctEmail = "itis@itis.kpfu.ru"
    var correctPass = "1234Itis"
    lateinit var pref: SharedPreferences


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        pref = getSharedPreferences(Companion.APP_PREF, Context.MODE_PRIVATE)
        if (pref.contains(Companion.SAVED_EMAIL) && pref.contains(Companion.SAVED_PASSWORD)) {
            if (pref.getString(Companion.SAVED_EMAIL, null).equals(correctEmail) && pref.getString(
                    Companion.SAVED_PASSWORD, null).equals(correctPass)) {
                var editor = pref.edit()
                editor.putString(Companion.SAVED_EMAIL, null)
                editor.putString(Companion.SAVED_PASSWORD, null)
                editor.apply()
                startActivity(Intent(this, ProfileActivity::class.java))
            } else {
                Toast.makeText(this, "Invalid saved data!",Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun toProfile(view: View) {
        val profileIntent = Intent(this, ProfileActivity::class.java)
        var email = findViewById<EditText>(R.id.email).text.toString()
        var password = findViewById<EditText>(R.id.password).text.toString()
        if (email.equals(correctEmail) && password.equals(correctPass)) {
            var editor = pref.edit()
            editor.putString(Companion.SAVED_EMAIL, email)
            editor.putString(Companion.SAVED_PASSWORD, password)
            editor.apply()
            startActivity(profileIntent)
        } else {
            Toast.makeText(this, "Sorry, wrong data!", Toast.LENGTH_SHORT).show()
        }
    }

    companion object {
        const val APP_PREF = "mysettings"
        const val SAVED_EMAIL = "savedEmail"
        const val SAVED_PASSWORD = "savedPassword"
    }
}
