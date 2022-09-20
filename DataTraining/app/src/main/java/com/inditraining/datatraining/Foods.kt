package com.inditraining.datatraining

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Foods (
    var name:String,
    var price: String,
    var pic: Int
) : Parcelable