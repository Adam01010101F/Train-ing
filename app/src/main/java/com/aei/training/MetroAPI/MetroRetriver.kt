package com.aei.training.MetroAPI

import com.aei.training.Objects.Estimate
import com.aei.training.Objects.TrainLine
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
/**
 * Created by Adam F on 2/24/2018.
 */
class MetroRetriever {
    private val estimate: mGrabEst
    private val lines: mGetLine

    init {
        val retrofit = Retrofit.Builder().baseUrl("http://api.metro.net/")   //base URL that all Metro calls will use
                .addConverterFactory(GsonConverterFactory.create())                              //Appending the Gson - JSON parser
                .build()
        estimate = retrofit.create(mGrabEst::class.java)
        lines = retrofit.create(mGetLine::class.java)

    }

    fun getEstimate(callback: Callback<Estimate>){      //Callback Function when receiving a train estimate request from user
        val call = estimate.getEst()
        call.enqueue(callback)
    }
    //getLine is an interface
//TrainLine is an object
//lines is a value
    fun getLine(callback: Callback<TrainLine>){            //Call back when receiving a Line request from users
        val call = lines.getLine()
        call.enqueue(callback)
    }
}