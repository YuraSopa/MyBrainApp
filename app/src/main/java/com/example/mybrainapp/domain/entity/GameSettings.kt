package com.example.mybrainapp.domain.entity

data class GameSettings(
    val maxSumValue: Int,
    val maxCountOfRightAnswers: Int,
    val maxPercentOfRightAnswers: Int,
    val gameTimeInSeconds: Int
)