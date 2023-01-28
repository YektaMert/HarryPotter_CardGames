package com.example.cardgames

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.media.MediaPlayer
import android.os.*
import androidx.appcompat.app.AppCompatActivity
import android.util.Base64
import android.widget.ImageView
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import com.example.cardgames.databinding.ActivityGamePageBinding
import com.google.firebase.firestore.FirebaseFirestore
import java.util.Timer
import kotlin.concurrent.schedule


class GamePageActivity : AppCompatActivity() {
    private lateinit var binding: ActivityGamePageBinding
    private lateinit var database: FirebaseFirestore
    val millisInFuture:Long = 45000
    var resumeFromMillis:Int=0
    val timeRemaining:Int =0
    val countDownInterval:Long = 1000
    val cardsArray = mutableListOf<Cards>()
    val card2 : List<Cards>? = null
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
    var randomIndexImg : Int = 0
    val index = mutableListOf<Cards?>()
    val resultName= mutableListOf<String>()
    val resultPoint = mutableListOf<String>()
    val resultHome = mutableListOf<String>()
    val resultHpoint = mutableListOf<String>()
    val resultImg = mutableListOf<String>()
    var cards: Cards? = null
    val randomized = mutableListOf<Cards?>()
    val randomizing = mutableListOf<Cards?>()

// ...

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {

        var dongu = 0
        binding = ActivityGamePageBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        var card1Int:Int? = null
        var card2Int:Int? = null
        var cardImgs:Int? = null
        var cardHoome:String? = null

        object: CountDownTimer(millisInFuture,countDownInterval){

                override fun onTick(millisUntilFinished: Long) {

                    val timeRemaining = "${(millisUntilFinished / countDownInterval)+1} Saniye Kaldı!"

                    /*if (isPause) {
                        binding.textView.text = "${textView.text}\nDurduruldu"
                        resumeFromMillis = millisUntilFinished
                        cancel()

                    }*/
                    resumeFromMillis = millisUntilFinished.toInt()
                    //else {
                        binding.textView.setText(timeRemaining)
                    //}
                }

                override fun onFinish() {
                    val intent = Intent(applicationContext,FinishScreen::class.java)
                    startActivity(intent)
                }
            }.start()


        //startService(Intent(this,MusicService::class.java))

        database = FirebaseFirestore.getInstance()
        database.collection("home").get().addOnCompleteListener {

            var i : Int = 0
            var a : Int = 0
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





                        //while (i<1) {
                        //array.add(resultName.toString())
                        //arraytwo.add(index = i, element = resultPoint.toString())


                            //i++
                    //}

                        //println(resultName)
                        //println(resultPoint)
                        //println(resultHome)
                        //println(resultHpoint)






                }
                //println(cardsArray[0])
                //println(cardsArray[1].cardsImg)
                //val randomIndex = kotlin.random.Random.nextInt(resultName.size)
                //val randomIndex2 = kotlin.random.Random.nextInt(resultName.size)
                //println(resultName[randomIndex]+resultName[randomIndex2])
                randomizing.let { list1 -> cardsArray.shuffled().let (list1 :: addAll) }
                var dongu = 0
                while (dongu<2) {
                    randomized.add(randomizing[dongu])
                    dongu++
                }




                randomized.let { list1 -> randomized.shuffled().let (list1 :: addAll) }


                println(randomized)
                /*var j =0
                var n = 0
                while(j<2) {

                    if (index.isEmpty()) {
                        index.add(randomized[j])
                        //index.add(randomIndexImg)
                        //println(index[0])
                    } else {
                        while(n<index.size){
                            println("sa")
                            if (randomized[n] != index[n]) {
                                index.add(randomized[n])
                                //index.add(randomIndexImg)
                                //println(index[3])
                            }
                            n++
                        }
                    }
                    j++
                }
                index.add(index[0])
            index.add(index[1])
            println(index)*/

                println(randomized)
                println(randomized[0]!!.cardsImg)

                //binding.textView5.setText(array[0])
                //binding.textView6.setText(array[0])
            }
        }



        var sayac = 0
        cards
        binding.imageView1.setOnClickListener{
            binding.imageView1.setImageDrawable(ContextCompat.getDrawable(this,cardImg[randomized[0]!!.cardsImg]))
            if(sayac == 0){
                card1Int = randomized[0]!!.cardsPoint
                card2Int = randomized[0]!!.cardsHpoint
                cardHoome = randomized[0]!!.cardsHome
                cardImgs = randomized[0]!!.cardsImg
                firstCardIndex = randomized[0]
                firstCardImageView = binding.imageView1
                sayac++
            }else{
                sayac=0
                cardMatch(randomized[0]!!.cardsImg,firstCardIndex!!.cardsImg,binding.imageView1, firstCardImageView!!)
                val a=wrongCalculate(card2Int!!,
                    randomized[0]!!.cardsHpoint,card1Int!!,randomized[0]!!.cardsPoint,cardHoome!!,randomized[0]!!.cardsHome)
                println(a)
            }
        }
        binding.imageView4.setOnClickListener{
            binding.imageView4.setImageDrawable(ContextCompat.getDrawable(this,cardImg[randomized[1]!!.cardsImg]))
            if(sayac == 0){
                card1Int = randomized[1]!!.cardsPoint
                card2Int = randomized[1]!!.cardsHpoint
                cardHoome = randomized[1]!!.cardsHome
                cardImgs = randomized[1]!!.cardsImg
                firstCardIndex = randomized[1]
                firstCardImageView = binding.imageView4
                sayac++
            }else{
                sayac=0
                cardMatch(randomized[1]!!.cardsImg,firstCardIndex!!.cardsImg,binding.imageView4, firstCardImageView!!)
                val a=wrongCalculate(card2Int!!,randomized[1]!!.cardsHpoint,card1Int!!,randomized[1]!!.cardsPoint,cardHoome!!,randomized[1]!!.cardsHome)
                println(a)
            }

        }
        binding.imageView.setOnClickListener{
            binding.imageView.setImageDrawable(ContextCompat.getDrawable(this,cardImg[randomized[2]!!.cardsImg]))
            if(sayac == 0){
                card1Int = randomized[2]!!.cardsPoint
                card2Int = randomized[2]!!.cardsHpoint
                cardHoome = randomized[2]!!.cardsHome
                cardImgs = randomized[2]!!.cardsImg
                firstCardIndex = randomized[2]
                firstCardImageView = binding.imageView
                sayac++
            }else{
                sayac=0
                cardMatch(randomized[2]!!.cardsImg,firstCardIndex!!.cardsImg,binding.imageView, firstCardImageView!!)
                val a=wrongCalculate(card2Int!!,randomized[2]!!.cardsHpoint,card1Int!!,randomized[2]!!.cardsPoint,cardHoome!!,randomized[2]!!.cardsHome)
                println(a)
            }
        }
        binding.imageView3.setOnClickListener{
            binding.imageView3.setImageDrawable(ContextCompat.getDrawable(this,cardImg[randomized[3]!!.cardsImg]))
            if(sayac == 0){
                card1Int = randomized[3]!!.cardsPoint
                card2Int = randomized[3]!!.cardsHpoint
                cardHoome = randomized[3]!!.cardsHome
                cardImgs = randomized[3]!!.cardsImg
                firstCardIndex = randomized[3]
                firstCardImageView = binding.imageView3
                sayac++
            }else{
                sayac=0
                cardMatch(randomized[3]!!.cardsImg,firstCardIndex!!.cardsImg,binding.imageView3, firstCardImageView!!)
                val a=wrongCalculate(card2Int!!,randomized[3]!!.cardsHpoint,card1Int!!,randomized[3]!!.cardsPoint,cardHoome!!,randomized[3]!!.cardsHome)
                println(a)
            }
        }




        decoder()



    }
    fun cardMatch(int: Int, int2: Int, imageView: ImageView, imageView2: ImageView){
        if(cardsArray[int].cardsImg.equals(cardsArray[int2].cardsImg)){
            var mediaPlayer: MediaPlayer? = null
            mediaPlayer = MediaPlayer.create(this,R.raw.find)
            mediaPlayer?.start()
            imageView.isClickable = false
            imageView2.isClickable = false
            trueCalcute(cardsArray[int]!!.cardsHpoint, cardsArray[int]!!.cardsPoint )
        }else{
            Timer().schedule(1000) {
                Handler(Looper.getMainLooper()).post(Runnable {
                    imageView.setImageBitmap(decodedImage)
                    imageView2.setImageBitmap(decodedImage) })
            }
            wrongCalculate(cardsArray[int]!!.cardsHpoint,cardsArray[int2]!!.cardsHpoint,cardsArray[int]!!.cardsPoint,cardsArray[int2]!!.cardsPoint,cardsArray[int]!!.cardsHome,cardsArray[int2]!!.cardsHome)

        }


    }
    fun wrongCalculate(homepoint : Int,homepoint2 : Int, cardPoint:Int,cardPoint2:Int,cardHome:String,cardHome2: String ): Int {
        if(cardHome.equals(cardHome2)){

            return (-(cardPoint + cardPoint2) / homepoint*((45-resumeFromMillis)/10000))

        }

        return (-((cardPoint+cardPoint2)/2)*homepoint*homepoint2*((45-resumeFromMillis)/10000))




    }
    fun trueCalcute(homepoint: Int,cardPoint: Int): Int {
        return  (2*(cardPoint)*(homepoint)*((resumeFromMillis)/10000))
    }

    var decodedImage : Bitmap? = null
    fun decoder(){
        database.collection("image").get().addOnCompleteListener {
            val result: StringBuffer = StringBuffer()
            var i = 0

            if (it.isSuccessful) {

                for (document in it.result!!) {

                    result.append(document.data.getValue("hog").toString())



                }
                val imageBytes = Base64.decode(result.toString(), Base64.DEFAULT)
                decodedImage = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.size)

                /*while (i<2) {

                    i++
                }*/

                binding.imageView1.setImageBitmap(decodedImage)
                binding.imageView3.setImageBitmap(decodedImage)
                binding.imageView4.setImageBitmap(decodedImage)
                binding.imageView.setImageBitmap(decodedImage)


            }


        }

    }
    /*fun myShuffle(index:Int): MutableList<Int> {
        var j = 0
        var i = 0
        var k = 0
        val randomList = mutableListOf<Cards?>()
        var newList = mutableListOf<Cards?>()
        var indexsize = index

        while(j<3000) {
            k=0
            val randomIndex = kotlin.random.Random.nextInt(indexsize)
            val c = randomList
            if (randomList.isEmpty()) {
                // println("sa")
                randomList.add(randomIndex)
            } else {
                //println("rndind"+randomIndex)
                var a = randomList.size

                val b = index
                i=0
                while(i<a){
                    if (randomIndex == c[i]) {
                        //  println("tatlısu")
                        k++
                        break
                    }
                    i++
                }

                if (k == 0){
                    // println("girişçıkış")
                    randomList.add(randomIndex)
                    newList.add(index[randomIndex])
                    println("asjldfaf"+randomList)
                }

                j++
            }
        }
        /* i = 0
         j = 0
         while(j<randomList.size){

             j++
         }*/
        return newList

    }*/
    data class Cards(val cardsName : String , val cardsPoint : Int, val cardsHome: String, val cardsHpoint:Int, val cardsImg : Int )

}





