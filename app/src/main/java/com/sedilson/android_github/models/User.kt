package com.sedilson.android_github.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class User(
    val avatar_url: String,
    val login: String
) : Parcelable
