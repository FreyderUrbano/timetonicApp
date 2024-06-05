package com.example.timitonicapp.view

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AlertDialog
import com.example.timitonicapp.databinding.ActivityMainBinding
import com.example.timitonicapp.view.service.AuthenticationService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var retrofit: Retrofit

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        retrofit = getRetrofit()
        validation()
    }

    private fun validation() {
        //USER VALIDATION
        CoroutineScope(Dispatchers.IO).launch {
            val authenticationService = retrofit.create(AuthenticationService::class.java)
            val emailVal = binding.tietEmail
            val passVal = binding.tietPass
            val btnLogin = binding.btnLogin

            val appkeyResponse =
                authenticationService.createAppKey("1.47", "createAppkey", "android")
            Log.i("Frey", "Appkey Response: $appkeyResponse")
            val appkey = appkeyResponse

            val pauthkeyResponse = authenticationService.createOAuthKey(
                "1.47",
                "createOauthkey",
                "androiddeveloper",
                "Android.developer1",
                "Y3kU-Ai5e-MMta-Uxba-GgF1-ftBJ-es1u"
            )
            Log.i("Frey", "pauthkeyResponse $pauthkeyResponse")
            val pauthkey = pauthkeyResponse

            val sesskeyResponse = authenticationService.createSesskey(
                "1.47",
                "createSesskey",
                "androiddeveloper",
                "androiddeveloper",
                "Jym3-m5rm-UIQx-14AT-9J9D-g7Ht-qITl"
            )
            val sesskey = sesskeyResponse
            val sesskeyString = sesskey.string()
            if (sesskeyString != null) {
                Log.i("Frey", "Es esta $sesskeyString")
            }


            btnLogin.setOnClickListener {
                val email = emailVal.text.toString()
                val pass = passVal.text.toString()
                val sessionKey = sesskey
                val keyApi = "7iim-Kmlj-YIsS-Pbfc-iKTd-CwNe-QnyK"
                val user = "android.developer@timetonic.com"
                val pwd = "Android.developer1"

                if (email.isNotEmpty() || pass.isNotEmpty() && sessionKey.toString() == keyApi && email == user || pass == pwd) {
                    val intent = Intent(this@MainActivity, BooksActivity::class.java)
                    startActivity(intent)
                } else {
                    alert_1()
                }

//                if (email.isEmpty() || pass.isEmpty()) {
//                    alert()
//                } else if (sessionKey.toString() != keyApi && email != user || pass != pwd) {
//                    alert_1()
//                } else if (!isValidEmail(email) || !isValidPassword(pass)) {
//                    alert_1()
//                } else {
//                    val intent = Intent(this@MainActivity, BooksActivity::class.java)
//                    startActivity(intent)
//                }

//                when {
//                    email.isEmpty() || pass.isEmpty() -> {
//                        alert()
//                    }
//
//                    !isValidEmail(email) || !isValidPassword(pass) -> {
//                        alert_1()
//                    }
//
//                    sessionKey.toString() != keyApi -> {
//                        alert_1()
//                    }
//
//                    email != user || pass != pwd -> {
//                        alert_1()
//                    }
//
//                    else -> {
//                        val intent = Intent(this@MainActivity, BooksActivity::class.java)
//                        startActivity(intent)
//                    }
//                }
            }

        }

    }

    private fun alert_1() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Wrong Credentials")
        builder.setMessage("Try Again")

        builder.setPositiveButton("OK", DialogInterface.OnClickListener { dialog, id ->
            // Acciones a realizar cuando el usuario presiona el botón "OK"
        })
        builder.show()
    }

    private fun alert() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Empty Credencials")
        builder.setMessage("Try Again")

        builder.setPositiveButton("OK", DialogInterface.OnClickListener { dialog, id ->
            // Acciones a realizar cuando el usuario presiona el botón "OK"
        })
        builder.show()
    }

}

private fun getRetrofit(): Retrofit {
    return Retrofit
        .Builder()
        .baseUrl("https://timetonic.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}

fun isValidEmail(email: String): Boolean {
    val emailRegex = "^A-Za-z([@]{1})(.{1,})(\\.)(.{1,})".toRegex()
    return emailRegex.matches(email)
}

fun isValidPassword(password: String): Boolean {
    val passwordRegex =
        "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$".toRegex()
    return passwordRegex.matches(password)
}


