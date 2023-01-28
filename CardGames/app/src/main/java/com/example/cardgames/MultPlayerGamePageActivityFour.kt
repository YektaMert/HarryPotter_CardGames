package com.example.cardgames

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.os.Looper
import android.util.Base64
import android.widget.ImageView
import androidx.core.content.ContextCompat
import com.example.cardgames.databinding.ActivityGamePageFourBinding

import com.example.cardgames.databinding.ActivityGamePageSixBinding
import com.example.cardgames.databinding.ActivityMultPlayerGamePageBinding
import com.example.cardgames.databinding.ActivityMultPlayerGamePageFourBinding
import com.example.cardgames.databinding.ActivityMultPlayerGamePageSixBinding
import com.google.firebase.firestore.FirebaseFirestore
import java.util.*
import kotlin.concurrent.schedule

class MultPlayerGamePageActivityFour : AppCompatActivity() {
    private lateinit var database: FirebaseFirestore
    val cardImg  = arrayOf<Int>(R.drawable.letalestrange,R.drawable.andromedatonks,R.drawable.ernestmacmillan,
        R.drawable.helgahufflepuff,R.drawable.rubeushagrid,R.drawable.filiusflitwick,R.drawable.sybilltrelawney,
        R.drawable.silvanuskettleburn,R.drawable.tedlupin,R.drawable.evanrosier,R.drawable.nymphadoratonks,
        R.drawable.lilypotter,R.drawable.arthurweasley,R.drawable.quirinusquirrell,R.drawable.doloresumbridge,
        R.drawable.hermionegranger,R.drawable.myrtlewarren,R.drawable.luciusmalfoy,R.drawable.siriusblack,
        R.drawable.leanne,R.drawable.bellatrixlestrange,R.drawable.harrypotter,R.drawable.peterpettigrew,R.drawable.dracomalfoy,
        R.drawable.gilderoylockhart,R.drawable.chochang,R.drawable.rowenaravenclaw,R.drawable.newtscamander,R.drawable.marcusbelby,
        R.drawable.padmapatil,R.drawable.remuslupin,R.drawable.tomriddle,R.drawable.albusdumbledore,R.drawable.ronweasley,
        R.drawable.pomonasprout,R.drawable.minervamcgonagall,R.drawable.hannahabbott,R.drawable.cedricdiggory,R.drawable.garrickollivander,
        R.drawable.narcissamalfoy,R.drawable.horaceslughorn,R.drawable.fatfriar,R.drawable.severussnape,R.drawable.lunalovegood)
    var firstCardIndex : Cards? = null
    var firstCardImageView : ImageView? = null

    val millisInFuture:Long = 60000
    val countDownInterval:Long = 1000
    val resultName= mutableListOf<String>()
    val resultPoint = mutableListOf<String>()
    val listImgView = mutableListOf<ImageView>()
    val resultHome = mutableListOf<String>()
    val resultHpoint = mutableListOf<String>()
    val cardsArray = mutableListOf<Cards?>()
    val randomized = mutableListOf<Cards?>()
    var cards: Cards? = null
    val randomizing = mutableListOf<Cards?>()
    private lateinit var binding: ActivityMultPlayerGamePageFourBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMultPlayerGamePageFourBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        database = FirebaseFirestore.getInstance()
        listImgView.add(binding.imageView1)
        listImgView.add(binding.imageView2)
        listImgView.add(binding.imageView3)
        listImgView.add(binding.imageView4)
        listImgView.add(binding.imageView5)
        listImgView.add(binding.imageView6)
        listImgView.add(binding.imageView7)
        listImgView.add(binding.imageView8)
        listImgView.add(binding.imageView9)
        listImgView.add(binding.imageView10)
        listImgView.add(binding.imageView11)
        listImgView.add(binding.imageView12)
        listImgView.add(binding.imageView13)
        listImgView.add(binding.imageView14)
        listImgView.add(binding.imageView15)
        listImgView.add(binding.imageView16)

        var card1Int : Int? = null
        var card2Int : Int? = null
        var cardHoome : String? = null
        object: CountDownTimer(millisInFuture,countDownInterval){

            override fun onTick(millisUntilFinished: Long) {

                val timeRemaining = "${(millisUntilFinished / countDownInterval)+1} Saniye Kaldı!"

                /*if (isPause) {
                    binding.textView.text = "${textView.text}\nDurduruldu"
                    resumeFromMillis = millisUntilFinished
                    cancel()

                }*/

                //else {
                binding.textView22.setText(timeRemaining)
                //}
            }

            override fun onFinish() {
                val intent = Intent(applicationContext,FinishScreen::class.java)
                startActivity(intent)
            }
        }.start()
        database.collection("home").get().addOnCompleteListener {

            var a = 0
            var i = 0
            //val randomIndex = kotlin.random.Random(10)
            if (it.isSuccessful) {

                for (document in it.result!!) {

                    var string : String = document.data.getValue("name").toString()
                    var value : String =  document.data.getValue("point").toString()
                    var home : String = document.data.getValue("home").toString()
                    var hpoint : String =  document.data.getValue("hpoint").toString()
                    resultName.add(string)
                    resultPoint.add(value)
                    resultHome.add(home)
                    resultHpoint.add(hpoint)
                    while (i<resultHpoint.size){
                        a = i
                        i++
                    }

                    cards = Cards(
                        string,
                        value.toInt(),
                        home,
                        hpoint.toInt(),
                        a
                    )
                    cardsArray.add(cards!!)


                    println(cardsArray)

                    println(resultName)
                    println(resultPoint)
                    println(resultHome)
                    println(resultHpoint)

                }
                randomizing.let { list1 -> cardsArray.shuffled().let (list1 :: addAll) }
                var dongu = 0
                while (dongu<8) {
                    randomized.add(randomizing[dongu])
                    dongu++
                }




                randomized.let { list1 -> randomized.shuffled().let (list1 :: addAll) }


                println(randomized)


            }
        }
        /*var j =0
        var n = 0
        while(j<18) {
            randomIndexImg = kotlin.random.Random.nextInt(cardImg.size)
            if (index.isEmpty()) {
                index.add(randomIndexImg)
                //index.add(randomIndexImg)
                //println(index[0])
            } else {
                while(n<index.size){
                    println("sa")
                    if (randomIndexImg != index[n]) {
                        index.add(randomIndexImg)
                        //index.add(randomIndexImg)
                        //println(index[3])
                    }
                    n++
                }
            }
            j++
        }
        j = 0
        n = index.size
        while(j<n) {
            index.add(index[j])
            j++
        }
        val indexx = index.shuffled()
        */
        var sayac = 0

        binding.imageView1.setOnClickListener{
            binding.imageView1.setImageDrawable(ContextCompat.getDrawable(this,cardImg[randomized[0]!!.cardsImg]))
            if(sayac == 0){
                card1Int = randomized[0]!!.cardsPoint
                card2Int = randomized[0]!!.cardsHpoint
                cardHoome = randomized[0]!!.cardsHome
                firstCardIndex = randomized[0]
                firstCardImageView = binding.imageView1
                sayac++
            }else{
                sayac=0
                cardMatch(randomized[0]!!.cardsImg,firstCardIndex!!.cardsImg,binding.imageView1, firstCardImageView!!)
                val a=wrongCalculate(card2Int!!,resultHpoint[0].toInt(),card1Int!!,resultPoint[0].toInt(),cardHoome!!,resultHome[0])
                println(a)
            }
        }
        binding.imageView2.setOnClickListener{
            binding.imageView2.setImageDrawable(ContextCompat.getDrawable(this,cardImg[randomized[1]!!.cardsImg]))
            if(sayac == 0){
                card1Int = randomized[1]!!.cardsPoint
                card2Int = randomized[1]!!.cardsHpoint
                cardHoome = randomized[1]!!.cardsHome
                firstCardIndex = randomized[1]
                firstCardImageView = binding.imageView2
                sayac++
            }else{
                sayac=0
                cardMatch(randomized[1]!!.cardsImg,firstCardIndex!!.cardsImg,binding.imageView2, firstCardImageView!!)
                val a=wrongCalculate(card2Int!!,randomized[1]!!.cardsHpoint,card1Int!!,randomized[1]!!.cardsPoint,cardHoome!!,randomized[1]!!.cardsHome)
                println(a)
            }
        }
        binding.imageView3.setOnClickListener{
            binding.imageView3.setImageDrawable(ContextCompat.getDrawable(this,cardImg[randomized[2]!!.cardsImg]))
            if(sayac == 0){
                card1Int = randomized[2]!!.cardsPoint
                card2Int = randomized[2]!!.cardsHpoint
                cardHoome = randomized[2]!!.cardsHome
                firstCardIndex = randomized[2]
                firstCardImageView = binding.imageView3
                sayac++
            }else{
                sayac=0
                cardMatch(randomized[2]!!.cardsImg,firstCardIndex!!.cardsImg,binding.imageView3, firstCardImageView!!)
                val a=wrongCalculate(card2Int!!,randomized[2]!!.cardsHpoint,card1Int!!,randomized[2]!!.cardsPoint,cardHoome!!,randomized[2]!!.cardsHome)
                println(a)
            }
        }
        binding.imageView4.setOnClickListener{
            binding.imageView4.setImageDrawable(ContextCompat.getDrawable(this,cardImg[randomized[3]!!.cardsImg]))
            if(sayac == 0){
                card1Int = randomized[3]!!.cardsPoint
                card2Int = randomized[3]!!.cardsHpoint
                cardHoome = randomized[3]!!.cardsHome
                firstCardIndex = randomized[3]
                firstCardImageView = binding.imageView4
                sayac++
            }else{
                sayac=0
                cardMatch(randomized[3]!!.cardsImg,firstCardIndex!!.cardsImg,binding.imageView3, firstCardImageView!!)
                val a=wrongCalculate(card2Int!!,randomized[3]!!.cardsHpoint,card1Int!!,randomized[3]!!.cardsPoint,cardHoome!!,randomized[3]!!.cardsHome)
                println(a)
            }

        }
        binding.imageView5.setOnClickListener{
            binding.imageView5.setImageDrawable(ContextCompat.getDrawable(this,cardImg[randomized[4]!!.cardsImg]))
            if(sayac == 0){
                card1Int = randomized[4]!!.cardsPoint
                card2Int = randomized[4]!!.cardsHpoint
                cardHoome = randomized[4]!!.cardsHome
                firstCardIndex = randomized[4]
                firstCardImageView = binding.imageView5
                sayac++
            }else{
                sayac=0
                cardMatch(randomized[4]!!.cardsImg,firstCardIndex!!.cardsImg,binding.imageView3, firstCardImageView!!)
                val a=wrongCalculate(card2Int!!,randomized[4]!!.cardsHpoint,card1Int!!,randomized[4]!!.cardsPoint,cardHoome!!,randomized[4]!!.cardsHome)
                println(a)
            }
        }
        binding.imageView6.setOnClickListener{
            binding.imageView6.setImageDrawable(ContextCompat.getDrawable(this,cardImg[randomized[5]!!.cardsImg]))
            if(sayac == 0){
                card1Int = randomized[5]!!.cardsPoint
                card2Int = randomized[5]!!.cardsHpoint
                cardHoome = randomized[5]!!.cardsHome
                firstCardIndex = randomized[5]
                firstCardImageView = binding.imageView6
                sayac++
            }else{
                sayac=0
                cardMatch(randomized[5]!!.cardsImg,firstCardIndex!!.cardsImg,binding.imageView3, firstCardImageView!!)
                val a=wrongCalculate(card2Int!!,randomized[5]!!.cardsHpoint,card1Int!!,randomized[5]!!.cardsPoint,cardHoome!!,randomized[5]!!.cardsHome)
                println(a)
            }
        }
        binding.imageView7.setOnClickListener{
            binding.imageView7.setImageDrawable(ContextCompat.getDrawable(this,cardImg[randomized[6]!!.cardsImg]))
            if(sayac == 0){
                card1Int = randomized[6]!!.cardsPoint
                card2Int = randomized[6]!!.cardsHpoint
                cardHoome = randomized[6]!!.cardsHome
                firstCardIndex = randomized[6]
                firstCardImageView = binding.imageView7
                sayac++
            }else{
                sayac=0
                cardMatch(randomized[6]!!.cardsImg,firstCardIndex!!.cardsImg,binding.imageView3, firstCardImageView!!)
                val a=wrongCalculate(card2Int!!,randomized[6]!!.cardsHpoint,card1Int!!,randomized[6]!!.cardsPoint,cardHoome!!,randomized[6]!!.cardsHome)
                println(a)
            }
        }
        binding.imageView8.setOnClickListener{
            binding.imageView8.setImageDrawable(ContextCompat.getDrawable(this,cardImg[randomized[7]!!.cardsImg]))
            if(sayac == 0){
                card1Int = randomized[7]!!.cardsPoint
                card2Int = randomized[7]!!.cardsHpoint
                cardHoome = randomized[7]!!.cardsHome
                firstCardIndex = randomized[7]
                firstCardImageView = binding.imageView8
                sayac++
            }else{
                sayac=0
                cardMatch(randomized[7]!!.cardsImg,firstCardIndex!!.cardsImg,binding.imageView3, firstCardImageView!!)
                val a=wrongCalculate(card2Int!!,randomized[7]!!.cardsHpoint,card1Int!!,randomized[7]!!.cardsPoint,cardHoome!!,randomized[7]!!.cardsHome)
                println(a)
            }
        }
        binding.imageView9.setOnClickListener{
            binding.imageView9.setImageDrawable(ContextCompat.getDrawable(this,cardImg[randomized[8]!!.cardsImg]))
            if(sayac == 0){
                card1Int = randomized[8]!!.cardsPoint
                card2Int = randomized[8]!!.cardsHpoint
                cardHoome = randomized[8]!!.cardsHome
                firstCardIndex = randomized[8]
                firstCardImageView = binding.imageView9
                sayac++
            }else{
                sayac=0
                cardMatch(randomized[8]!!.cardsImg,firstCardIndex!!.cardsImg,binding.imageView3, firstCardImageView!!)
                val a=wrongCalculate(card2Int!!,randomized[8]!!.cardsHpoint,card1Int!!,randomized[8]!!.cardsPoint,cardHoome!!,randomized[8]!!.cardsHome)
                println(a)
            }
        }
        binding.imageView10.setOnClickListener{
            binding.imageView10.setImageDrawable(ContextCompat.getDrawable(this,cardImg[randomized[9]!!.cardsImg]))
            if(sayac == 0){
                card1Int = randomized[9]!!.cardsPoint
                card2Int = randomized[9]!!.cardsHpoint
                cardHoome = randomized[9]!!.cardsHome
                firstCardIndex = randomized[9]
                firstCardImageView = binding.imageView10
                sayac++
            }else{
                sayac=0
                cardMatch(randomized[9]!!.cardsImg,firstCardIndex!!.cardsImg,binding.imageView3, firstCardImageView!!)
                val a=wrongCalculate(card2Int!!,randomized[9]!!.cardsHpoint,card1Int!!,randomized[9]!!.cardsPoint,cardHoome!!,randomized[9]!!.cardsHome)
                println(a)
            }
        }
        binding.imageView11.setOnClickListener{
            binding.imageView11.setImageDrawable(ContextCompat.getDrawable(this,cardImg[randomized[10]!!.cardsImg]))
            if(sayac == 0){
                card1Int = randomized[10]!!.cardsPoint
                card2Int = randomized[10]!!.cardsHpoint
                cardHoome = randomized[10]!!.cardsHome
                firstCardIndex = randomized[10]
                firstCardImageView = binding.imageView11
                sayac++
            }else{
                sayac=0
                cardMatch(randomized[10]!!.cardsImg,firstCardIndex!!.cardsImg,binding.imageView3, firstCardImageView!!)
                val a=wrongCalculate(card2Int!!,randomized[10]!!.cardsHpoint,card1Int!!,randomized[10]!!.cardsPoint,cardHoome!!,randomized[10]!!.cardsHome)
                println(a)
            }
        }
        binding.imageView12.setOnClickListener{
            binding.imageView12.setImageDrawable(ContextCompat.getDrawable(this,cardImg[randomized[11]!!.cardsImg]))
            if(sayac == 0){
                card1Int = randomized[11]!!.cardsPoint
                card2Int = randomized[11]!!.cardsHpoint
                cardHoome = randomized[11]!!.cardsHome
                firstCardIndex = randomized[11]
                firstCardImageView = binding.imageView12
                sayac++
            }else{
                sayac=0
                cardMatch(randomized[11]!!.cardsImg,firstCardIndex!!.cardsImg,binding.imageView3, firstCardImageView!!)
                val a=wrongCalculate(card2Int!!,randomized[11]!!.cardsHpoint,card1Int!!,randomized[11]!!.cardsPoint,cardHoome!!,randomized[11]!!.cardsHome)
                println(a)
            }
        }
        binding.imageView13.setOnClickListener{
            binding.imageView13.setImageDrawable(ContextCompat.getDrawable(this,cardImg[randomized[12]!!.cardsImg]))
            if(sayac == 0){
                card1Int = randomized[12]!!.cardsPoint
                card2Int = randomized[12]!!.cardsHpoint
                cardHoome = randomized[12]!!.cardsHome
                firstCardIndex = randomized[12]
                firstCardImageView = binding.imageView13
                sayac++
            }else{
                sayac=0
                cardMatch(randomized[12]!!.cardsImg,firstCardIndex!!.cardsImg,binding.imageView3, firstCardImageView!!)
                val a=wrongCalculate(card2Int!!,randomized[12]!!.cardsHpoint,card1Int!!,randomized[12]!!.cardsPoint,cardHoome!!,randomized[12]!!.cardsHome)
                println(a)
            }
        }
        binding.imageView14.setOnClickListener{
            binding.imageView14.setImageDrawable(ContextCompat.getDrawable(this,cardImg[randomized[13]!!.cardsImg]))
            if(sayac == 0){
                card1Int = randomized[13]!!.cardsPoint
                card2Int = randomized[13]!!.cardsHpoint
                cardHoome = randomized[13]!!.cardsHome
                firstCardIndex = randomized[13]
                firstCardImageView = binding.imageView14
                sayac++
            }else{
                sayac=0
                cardMatch(randomized[13]!!.cardsImg,firstCardIndex!!.cardsImg,binding.imageView3, firstCardImageView!!)
                val a=wrongCalculate(card2Int!!,randomized[13]!!.cardsHpoint,card1Int!!,randomized[13]!!.cardsPoint,cardHoome!!,randomized[13]!!.cardsHome)
                println(a)
            }
        }
        binding.imageView15.setOnClickListener{
            binding.imageView15.setImageDrawable(ContextCompat.getDrawable(this,cardImg[randomized[14]!!.cardsImg]))
            if(sayac == 0){
                card1Int = randomized[14]!!.cardsPoint
                card2Int = randomized[14]!!.cardsHpoint
                cardHoome = randomized[14]!!.cardsHome
                firstCardIndex = randomized[14]
                firstCardImageView = binding.imageView7
                sayac++
            }else{
                sayac=0
                cardMatch(randomized[14]!!.cardsImg,firstCardIndex!!.cardsImg,binding.imageView3, firstCardImageView!!)
                val a=wrongCalculate(card2Int!!,randomized[14]!!.cardsHpoint,card1Int!!,randomized[14]!!.cardsPoint,cardHoome!!,randomized[14]!!.cardsHome)
                println(a)
            }
        }
        binding.imageView16.setOnClickListener{
            binding.imageView16.setImageDrawable(ContextCompat.getDrawable(this,cardImg[randomized[15]!!.cardsImg]))
            if(sayac == 0){
                card1Int = randomized[15]!!.cardsPoint
                card2Int = randomized[15]!!.cardsHpoint
                cardHoome = randomized[15]!!.cardsHome
                firstCardIndex = randomized[15]
                firstCardImageView = binding.imageView16
                sayac++
            }else{
                sayac=0
                cardMatch(randomized[15]!!.cardsImg,firstCardIndex!!.cardsImg,binding.imageView3, firstCardImageView!!)
                val a=wrongCalculate(card2Int!!,randomized[15]!!.cardsHpoint,card1Int!!,randomized[15]!!.cardsPoint,cardHoome!!,randomized[15]!!.cardsHome)
                println(a)
            }
        }

        decoder()
    }
    fun cardMatch(int: Int, int2: Int, imageView: ImageView, imageView2: ImageView){
        if(cardsArray[int]!!.cardsName.equals(cardsArray[int2]!!.cardsName)){
            var mediaPlayer: MediaPlayer? = null
            mediaPlayer = MediaPlayer.create(this,R.raw.find)
            mediaPlayer?.start()
            imageView.isClickable = false
            imageView2.isClickable = false
            trueCalcute(cardsArray[int]!!.cardsPoint, cardsArray[int]!!.cardsHpoint )
        }else{
            Timer().schedule(2000) {
                Handler(Looper.getMainLooper()).post(Runnable {
                    imageView.setImageBitmap(decodedImage)
                    imageView2.setImageBitmap(decodedImage) })

            }

        }


    }
    fun wrongCalculate(homepoint : Int,homepoint2 : Int, cardPoint:Int,cardPoint2:Int,cardHome:String,cardHome2: String): Int {
        if(cardHome.equals(cardHome2)){
            return (-(cardPoint+cardPoint2)/homepoint)
        }else{
            return (-((cardPoint+cardPoint2)/2)*homepoint*homepoint2)
        }

    }
    fun trueCalcute(homepoint: Int,cardPoint: Int): Int {
        return  (2*(cardPoint)*(homepoint))
    }
    data class Cards(val cardsName : String , val cardsPoint : Int, val cardsHome: String, val cardsHpoint:Int, val cardsImg : Int )


    var decodedImage : Bitmap? = null
    fun decoder(){
        database.collection("image").get().addOnCompleteListener {
            val result: StringBuffer = StringBuffer()
            var i = 0

            if (it.isSuccessful) {

                for (document in it.result!!) {

                    result.append(document.data.getValue("hog")).append(" ")

                }

                val imageBytes = Base64.decode(result.toString(), Base64.DEFAULT)
                decodedImage = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.size)

                var i= 0
                while(i<listImgView.size){
                    listImgView[i].setImageBitmap(decodedImage)
                    i++
                }



            }


        }

    }
}