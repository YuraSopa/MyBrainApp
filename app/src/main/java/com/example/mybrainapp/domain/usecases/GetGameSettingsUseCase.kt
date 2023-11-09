package com.example.mybrainapp.domain.usecases

import com.example.mybrainapp.domain.entity.GameSettings
import com.example.mybrainapp.domain.entity.Level
import com.example.mybrainapp.domain.repository.GameRepository

class GetGameSettingsUseCase(private val repository: GameRepository) {

    operator fun invoke(level: Level): GameSettings {
        return repository.getGameSettings(level)
    }
}