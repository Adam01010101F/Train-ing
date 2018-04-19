package com.aei.training.Objects

import com.google.gson.annotations.SerializedName

/**
 * Created by Adam F on 2/28/2018.
 */
class TrainList {
    @SerializedName("items")
    var trainLine: List<TrainLine>? = null
}