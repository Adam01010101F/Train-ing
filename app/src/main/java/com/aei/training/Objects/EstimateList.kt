package com.aei.training.Objects

import com.google.gson.annotations.SerializedName

/**
 * Created by Null on 2/28/2018.
 */

class EstimateList {
    @SerializedName("items")
    var estimates: List<Estimate>? = null
}
