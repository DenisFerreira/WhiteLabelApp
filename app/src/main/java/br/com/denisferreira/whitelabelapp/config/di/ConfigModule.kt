package br.com.denisferreira.whitelabelapp.config.di

import br.com.denisferreira.whitelabelapp.config.Config
import br.com.denisferreira.whitelabelapp.config.ConfigImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
interface ConfigModule {
    @Binds
    fun getConfig(configImpl: ConfigImpl): Config
}