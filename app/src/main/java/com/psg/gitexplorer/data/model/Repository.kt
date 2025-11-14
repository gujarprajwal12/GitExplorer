package com.psg.gitexplorer.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Repository(
    val id: Long,
    val name: String,
    val full_name: String,
    val description: String?,
    val stargazers_count: Int,
    val forks_count: Int,
    val language: String?,
    val owner: Owner
) : Parcelable
