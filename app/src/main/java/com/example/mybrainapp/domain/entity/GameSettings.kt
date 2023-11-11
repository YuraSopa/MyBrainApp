package com.example.mybrainapp.domain.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class GameSettings(
    val maxSumValue: Int,
    val maxCountOfRightAnswers: Int,
    val maxPercentOfRightAnswers: Int,
    val gameTimeInSeconds: Int
) : Parcelable