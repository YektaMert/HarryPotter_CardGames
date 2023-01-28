package com.example.cardgames

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.cardgames.databinding.ActivityResetPasswordBinding
import com.example.cardgames.databinding.ActivitySettingsBinding
import com.example.cardgames.databinding.ActivitySignUpBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference

class SettingsActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySettingsBinding
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var storage: StorageReference
    private lateinit var database: DatabaseReference
    val array = mutableListOf<String>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()

        database = FirebaseDatabase.getInstance().getReference("HUFFLEPUFF")
        array.add(index = 0, element = database.toString())
        //com.google.firebase.firestore.CollectionReference@6e6e1057

        println(array)



        binding.checkBox.setOnClickListener{
            if(binding.checkBox.isChecked){
                binding.checkBox2.isChecked = false
                binding.checkBox3.isClickable = true
                binding.checkBox4.isChecked = false
                binding.checkBox5.isChecked = false
            }
        }
        binding.checkBox2.setOnClickListener {
            if(binding.checkBox2.isChecked){
                binding.checkBox.isChecked = false
                binding.checkBox3.isChecked = false
                binding.checkBox4.isChecked = false
                binding.checkBox5.isChecked = false
            }
            
        }
        binding.checkBox3.setOnClickListener{
            if(binding.checkBox3.isChecked){
                binding.checkBox4.isChecked = false
                binding.checkBox5.isChecked = false
            }
        }
        binding.checkBox4.setOnClickListener{
            if(binding.checkBox4.isChecked){
                binding.checkBox3.isChecked = false
                binding.checkBox5.isChecked = false
            }
        }
        binding.checkBox5.setOnClickListener{
            if(binding.checkBox5.isChecked){
                binding.checkBox3.isChecked = false
                binding.checkBox4.isChecked = false
            }
        }
       //binding.button2.setOnClickListener{

         //   val intent = Intent(applicationContext,GamePageActivity::class.java)

           // startActivity(intent)

        //}
        binding.button2.setOnClickListener{
            if(binding.checkBox.isChecked ){
            if(binding.checkBox3.isChecked) {
                val intent = Intent(applicationContext, GamePageActivity::class.java)
                startActivity(intent)
            }
            if(binding.checkBox4.isChecked) {
                val intent = Intent(applicationContext, GamePageActivityFour::class.java)
                startActivity(intent)
            }
            if(binding.checkBox5.isChecked) {
                val intent = Intent(applicationContext, GamePageActivitySix::class.java)
                startActivity(intent)
            }
        }
            if(binding.checkBox2.isChecked ){
                if(binding.checkBox3.isChecked) {
                    val intent = Intent(applicationContext, MultPlayerGamePageActivity::class.java)
                    startActivity(intent)
                }
                if(binding.checkBox4.isChecked) {
                    val intent = Intent(applicationContext, MultPlayerGamePageActivityFour::class.java)
                    startActivity(intent)
                }
                if(binding.checkBox5.isChecked) {
                    val intent = Intent(applicationContext, MultPlayerGamePageActivitySix::class.java)
                    startActivity(intent)
                }
            }
        }


    }
}

