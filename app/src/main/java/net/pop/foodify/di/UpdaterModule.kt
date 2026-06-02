package net.pop.foodify.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import net.pop.foodify.domain.updater.AppUpdater
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UpdaterModule {

    @Provides
    @Singleton
    fun provideAppUpdater(@ApplicationContext context: Context): AppUpdater {
        return AppUpdater(context)
    }
}