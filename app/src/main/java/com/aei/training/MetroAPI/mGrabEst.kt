package com.aei.training.MetroAPI

import com.aei.training.Objects.Estimate
import retrofit2.Call
import retrofit2.http.GET

/**
 * Created by Adam F on 2/24/2018.
 */



interface mGrabEst{
    @GET("/stops/80101/predictions/")       //Eventually change 80101 to $stop_id
    fun getEst() : Call<Estimate>
}

//fun is functioninterface getLine
interface getLine 
{    @GET("agencies/lametro-rail")   
     fun getEst() : Call<TrainLines>
}
