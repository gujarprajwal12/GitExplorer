package com.psg.gitexplorer.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorites")
data class FavoriteEntity(
    @PrimaryKey val id: Long,
    val name: String,
    val fullName: String,
    val description: String?,
    val ownerLogin: String,
    val ownerAvatar: String,
    val stars: Int,
    val forks: Int,
    val language: String?
)
