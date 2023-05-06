package com.example.mobileapps1

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.mobileapps1.databinding.ActivityMainBinding
import com.example.mobileapps1.models.Auth
import com.example.mobileapps1.models.Token
import com.google.android.material.snackbar.Snackbar
import com.google.gson.Gson
import okhttp3.*
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.IOException

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val client = OkHttpClient()
    private val gson = Gson()


    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(this.layoutInflater)
        setContentView(binding.root)

        binding.signIn.setOnClickListener {
            val usernameString = binding.username.text.toString()
            val passwordString = binding.password.text.toString()
            val auth = Auth(usernameString, passwordString)
            val request = getTokenAttachedRequestBuilder()
                .url("https://fakestoreapi.com/auth/Login")
                .post(gson.toJson(auth).toRequestBody())
                .header("Content-Type", "application/json")
                .build()

            client.newCall(request).enqueue(object : Callback {
                override fun onFailure(call: Call, e: IOException) {
                    Log.i("MAIN_ACT", "Failure: $e")
                }

                override fun onResponse(call: Call, response: Response) {
                    if (response.isSuccessful) {
                        val responseString = response.body?.string()
                        Log.i("MAIN_ACT", "Success: $responseString")
                        val tokenObject = gson.fromJson(responseString, Token::class.java)
                        Log.i("MAIN_ACT", "Token: ${tokenObject.token}")
                        val intent = Intent(this@MainActivity, Products::class.java)
                        startActivity(intent)


                    } else {
                        Handler(Looper.getMainLooper()).post {
                            Toast.makeText(this@MainActivity, "Unsuccessful ${response.code}", Toast.LENGTH_LONG)
                                .show()
                        }
                        Log.i("MAIN_ACT", "Unsuccessful ${response.code} ${response.body?.string()}")
                    }
                }

            })


            binding.signUp.setOnClickListener {
                val intent = Intent(this, SignUpActivity::class.java)
                startActivity(intent)

            }

        }



    }   fun getTokenAttachedRequestBuilder(): Request.Builder {
        val token = "Your shared preferences code"
        val requestBuilder = Request.Builder()
        if(token!= "")
            return requestBuilder.header("Authorization", "Bearer $token")
        else return requestBuilder
    } }