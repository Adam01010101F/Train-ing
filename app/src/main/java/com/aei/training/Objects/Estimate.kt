package com.aei.training.Objects

import java.io.Serializable

/**
 * Created by Adam F on 2/25/2018.
 */
data class Estimate (val is_departing: Boolean, val run_id : String, val seconds : Int ) : Serializable{}