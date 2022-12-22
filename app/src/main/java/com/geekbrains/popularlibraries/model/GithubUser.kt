package com.geekbrains.popularlibraries.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.net.IDN

@Parcelize
data class GithubUser(
    val id: Long,
    val login: String,
    val avatarUrl: String?
) : Parcelable