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
        retrofit = getRetrofit()
        setContentView(binding.root)
        validation()

//        val email = binding.tietEmail
//        val pass = binding.tietPass
//        val btnLogin = binding.btnLogin

//        val emailExample = "android.developer@timetonic.com"
//        val passExample = "Android.developer1"
//
//        btnLogin.setOnClickListener {
//            val emailText = email.text.toString()
//            val passText = pass.text.toString()
//
//            if (emailText.isEmpty() && passText.isEmpty()) {
//                alert()
//            } else if (emailText == emailExample && passText == passExample) {
//                val intent = Intent(this, BooksActivity::class.java)
//                startActivity(intent)
//            } else {
//                alert_1()
//            }
//
//        }
    }

    private fun validation() {
        //USER VALIDATION
        CoroutineScope(Dispatchers.IO).launch {
            val authenticationService = retrofit.create(AuthenticationService::class.java)
            val emailVal = binding.tietEmail
            val passVal = binding.tietPass
            val btnLogin = binding.btnLogin

            val appkeyResponse = authenticationService.createApikey(emailVal, passVal)
            val appkey = appkeyResponse.body()

            val pauthkeyResponse = authenticationService.createPauthkey(appkey)
            val pauthkey = pauthkeyResponse.body()

            val sesskeyResponse = authenticationService.createSesskey(pauthkey)
            val sesskey = sesskeyResponse.body()
            Log.i("Frey", "$sesskey")

            btnLogin.setOnClickListener {
                val email = emailVal.text.toString()
                val pass = passVal.text.toString()
                val key = sesskey
                val keyApi = "NN3s-tDmK-wNQm-S2J8-zytF-mYTN-2Wbt"

                if (email.isEmpty() && pass.isEmpty()) {
                    alert()
                } else {
                    if (isValidEmail(email) && isValidPassword(pass)) {
                        val intent = Intent(this@MainActivity, BooksActivity::class.java)
                        startActivity(intent)
                    } else if (email == "android.developer@timetonic.com" && pass == "Android.developer1") {
                        val intent = Intent(this@MainActivity, BooksActivity::class.java)
                        startActivity(intent)
                    } else {
                        if (key.toString() == keyApi) {
                            val intent = Intent(this@MainActivity, BooksActivity::class.java)
                            startActivity(intent)
                        } else {
                            alert_1()
                        }

                    }
                }

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


}