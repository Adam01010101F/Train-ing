package com.aei.training.MetroAPI

import retrofit2.Callback
import retrofit2.http.GET

/**
 * Created by Adam F on 2/24/2018.
 */



interface mGrabEst{
    @GET("/stops/80101/predictions/")       //Eventually change 80101 to $stop_id
    fun getEst() : Call<Estimate>
}