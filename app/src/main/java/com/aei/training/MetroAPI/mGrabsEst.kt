package com.aei.training.MetroAPI

import com.aei.training.Objects.EstimateList
import com.aei.training.Objects.StopList

import com.aei.training.Objects.TrainLine
import com.aei.training.Objects.TrainList
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Created by Adam F on 2/24/2018.
 */

//getStop
//TODO: FIX UrL && rm hardcode
interface mGrabEst{
    @GET("agencies/lametro-rail/routes/{route_tag}/stops/{stop_id}/predictions/")       //Eventually change 80101 to $stop_id
    fun getEst(@Path("route_tag") routeTag: String,@Path("stop_id") stop_id: String) : Call<EstimateList>
}

interface mGetStops{
    @GET("agencies/lametro-rail/routes/{route_tag}/stops")       //Eventually change 80101 to $stop_id
    fun getStops(@Path("route_tag") routeTag: String) : Call<StopList>
}


//fun is function interface getLine
interface mGetLine {
    @GET("agencies/lametro-rail")
    fun getLine() : Call<TrainList>
}
//interface mGetStops{
//    @GET("agencies/lametro-rail/routes/801/stops/80101/predictions/")       //Eventually change 80101 to $stop_id
//    fun getEst() : Call<EstimateList>
//}
//interface FooService {
//    @GET("/maps/api/geocode/json?address={zipcode}&sensor=false")
//    fun getPositionByZip(@Path("zipcode") zipcode: Int)
//}

