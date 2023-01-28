package com.example.cardgames

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.view.isVisible
import com.example.cardgames.databinding.ActivityResetPasswordBinding
import com.example.cardgames.databinding.ActivitySignInBinding
import com.example.cardgames.databinding.ActivitySignUpBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.storage.StorageReference
import java.net.URI

class ResetPasswordActivity : AppCompatActivity() {

    private lateinit var binding: ActivityResetPasswordBinding
    private lateinit var  firebaseAuth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResetPasswordBinding.inflate(layoutInflater)
        setContentView(binding.root)
        firebaseAuth = FirebaseAuth.getInstance()

        binding.btnSubmit.setOnClickListener{
            val email = binding.emailET.text.toString()

            if(email.isEmpty()){
                Toast.makeText(
                    this,
                    "Email giriniz.",
                    Toast.LENGTH_LONG
                ).show()
            }else{
                firebaseAuth.sendPasswordResetEmail(email).addOnCompleteListener{
                    task ->
                        if (task.isSuccessful){
                            Toast.makeText(
                                this,
                                "Sıfırlama Maili Gönderilmiştir.",
                                Toast.LENGTH_LONG
                            ).show()
                            finish()

                        }else{
                            Toast.makeText(
                                this,
                                task.exception!!.message.toString(),
                                Toast.LENGTH_LONG
                            ).show()

                        }


                }
            }


        }



    }
}