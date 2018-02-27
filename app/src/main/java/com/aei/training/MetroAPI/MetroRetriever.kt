package com.aei.training.MetroAPI

import com.aei.training.
import com.aei.training.Objects.Estimate
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
/**
 * Created by Adam F on 2/24/2018.
 */
class MetroRetriever {
    private val estimate: Estimate

    init {
        val retrofit = Retrofit.Builder.baseUrl("http://api.metro.net/agencies/lametro-rail")   //base URL that all Metro calls will use
                .addCoverterFactroy(GsonConverterFactory.create())                              //Appending the Gson - JSON parser
                .build()
        estimate = retrofit.create(mGrabEst::class.java)

    }

    fun getEstimate(callback: Callback<estimate>){      //Callback Function when receiving a train estimate request from user
        val call = service.getEstimate()
        call.enqueue(callback)
    }

    fun getLines(callback: Callback<Lines>){            //Call back when receiving a Line request from users
        val call = service.getLines()
        call.enqueue(callback)
    }
}