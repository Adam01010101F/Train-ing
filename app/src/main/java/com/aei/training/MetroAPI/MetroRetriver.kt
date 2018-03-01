package com.aei.training.MetroAPI

import com.aei.training.Objects.Color
import com.aei.training.Objects.EstimateList
import com.aei.training.Objects.StopList
import com.aei.training.Objects.TrainList
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
/**
 * Created by Adam F on 2/24/2018.
 */
class MetroRetriever {
    private val estimate: mGrabEst
    private val lines: mGetLine
    private val stops: mGetStops
    private val color: mGetColor


    init {
        val retrofit = Retrofit.Builder().baseUrl("http://api.metro.net/")   //base URL that all Metro calls will use
                .addConverterFactory(GsonConverterFactory.create())                              //Appending the Gson - JSON parser
                .build()
        estimate = retrofit.create(mGrabEst::class.java)
        lines = retrofit.create(mGetLine::class.java)
        stops = retrofit.create(mGetStops::class.java)
        color = retrofit.create(mGetColor::class.java)

    }

    fun getColor(route_tag:String, callback: Callback<Color>){
        val call = color.getColor(route_tag)
        call.enqueue(callback)
    }

    fun getEstimate(route_tag:String,stop_id:String,callback: Callback<EstimateList>){      //Callback Function when receiving a train estimate request from user
        val call = estimate.getEst(route_tag,stop_id)
        call.enqueue(callback)
    }


    fun getStops(route_tag:String,callback: Callback<StopList>){      //Callback Function when receiving a train stop request from user
        val call = stops.getStops(route_tag)
        call.enqueue(callback)
    }


    fun getLine(callback: Callback<TrainList>){            //Call back when receiving a Line request from users
        val call = lines.getLine()
        call.enqueue(callback)
    }

}