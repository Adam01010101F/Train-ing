package com.aei.training.MetroAPI

import com.aei.training.Objects.EstimateList
import com.aei.training.Objects.StopList
import com.aei.training.Objects.TrainLine
import retrofit2.Call
import retrofit2.http.GET

/**
 * Created by Adam F on 2/24/2018.
 */


//TODO: FIX UrL && rm hardcode
interface mGrabEst{
    @GET("agencies/lametro-rail/stops/80101/predictions/")       //Eventually change 80101 to $stop_id
    fun getEst() : Call<EstimateList>
}

//fun is functioninterface getLine
interface mGetLine {
    @GET("agencies/lametro-rail")
    fun getLine() : Call<TrainLine>
}

//TODO: FIX UrL && rm hardcode
interface mGetStops{
    @GET("agencies/lametro-rail/routes/801/sequence")
    fun getStops() : Call<StopList>
}