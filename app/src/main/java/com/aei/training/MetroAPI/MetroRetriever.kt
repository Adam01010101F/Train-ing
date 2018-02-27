package com.aei.training.MetroAPI

import com.aei.training.Objects.Estimate
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
/**
 * Created by Adam F on 2/24/2018.
 */
class MetroRetriever {
    private val estimate: mGrabEst

    init {
        val retrofit = Retrofit.Builder.baseUrl("http://api.metro.net/agencies/lametro-rail")   //base URL that all Metro calls will use
                .addCoverterFactroy(GsonConverterFactory.create())                              //Appending the Gson - JSON parser
                .build()
        estimate = retrofit.create(mGrabEst::class.java)

    }

    fun getEstimate(callback: Callback<Estimate>){      //Callback Function when receiving a train estimate request from user
        val call = estimate.getEstimate()
        call.enqueue(callback)
    }

    fun getLines(callback: Callback<Estimate>){            //Call back when receiving a Line request from users
        val call = estimate.getLines()
        call.enqueue(callback)
    }
}