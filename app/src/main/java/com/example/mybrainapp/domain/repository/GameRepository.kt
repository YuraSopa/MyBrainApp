package com.example.mybrainapp.domain.repository

import com.example.mybrainapp.domain.entity.GameSettings
import com.example.mybrainapp.domain.entity.Level
import com.example.mybrainapp.domain.entity.Question

interface GameRepository {
    fun generateQuestion(
        maxSumValue: Int,
        countOfOptions: Int
    ): Question

    fun getGameSettings(level: Level): GameSettings
}