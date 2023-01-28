package com.example.cardgames

import android.content.Intent
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.cardgames.databinding.ActivitySignInBinding
import com.example.cardgames.databinding.ActivitySignUpBinding
import com.google.firebase.auth.FirebaseAuth
import java.util.*
import kotlin.concurrent.schedule

class SignInActivity : AppCompatActivity() {

    private lateinit var  binding: ActivitySignInBinding
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)
        var mediaPlayer:MediaPlayer? = null
        mediaPlayer = MediaPlayer.create(this,R.raw.prologue)
        mediaPlayer?.start()
        firebaseAuth = FirebaseAuth.getInstance()

        binding.textView.setOnClickListener{
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }

        binding.textViewReset.setOnClickListener{
            val intent = Intent(this, ResetPasswordActivity::class.java)
            startActivity(intent)
        }
        binding.button.setOnClickListener{
            val email = binding.emailEt.text.toString()
            val pass = binding.passET.text.toString()



            if (email.isNotEmpty() && pass.isNotEmpty() ){

                    firebaseAuth.signInWithEmailAndPassword(email,pass).addOnCompleteListener{
                        if(it.isSuccessful){
                            val intent = Intent(this, SettingsActivity::class.java)
                            startActivity(intent)
                        }else{
                            Toast.makeText(
                                applicationContext,
                                it.exception.toString(),
                                Toast.LENGTH_LONG
                            ).show()
                        }
                    }
            }
            else{

                Toast.makeText(
                    this,
                    "Alanların Doldurulması Zorunludur.",
                    Toast.LENGTH_LONG
                ).show()

            }

        }


    }

}
