package com.aei.training.MetroAPI

import com.aei.training.Objects.Color
import com.aei.training.Objects.EstimateList
import com.aei.training.Objects.StopList
import com.aei.training.Objects.TrainList
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Created by Adam F on 2/24/2018.
 */


interface mGrabEst{
    @GET("agencies/lametro-rail/routes/{route_tag}/stops/{stop_id}/predictions/")
    fun getEst(@Path("route_tag") routeTag: String,@Path("stop_id") stop_id: String) : Call<EstimateList>
}

interface mGetColor{
    @GET("agencies/lametro-rail/routes/{route_tag}/info")
    fun getColor(@Path("route_tag") routeTag: String) : Call<Color>
}

interface mGetStops{
    @GET("agencies/lametro-rail/routes/{route_tag}/stops")
    fun getStops(@Path("route_tag") routeTag: String) : Call<StopList>
}


interface mGetLine {
    @GET("agencies/lametro-rail")
    fun getLine() : Call<TrainList>
}


