package com.psg.gitexplorer.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Repository(
    val id: Long = 0,
    val name: String = "",
    val full_name: String = "",
    val description: String? = "",
    val stargazers_count: Int = 0,
    val forks_count: Int = 0,
    val language: String? = "",
    val owner: Owner = Owner()
) : Parcelable
