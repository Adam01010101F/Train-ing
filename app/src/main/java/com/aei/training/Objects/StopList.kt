package com.aei.training.Objects

import com.google.gson.annotations.SerializedName

/**
 * Created by Adam F on 2/28/2018.
 */

class StopList(val hits: List<Stop>){
    @SerializedName("items")
    var stops: List<Stop>? = null
}