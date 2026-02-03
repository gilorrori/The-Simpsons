package com.gilorroristore.thesimpsons.di.modules

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.gilorroristore.thesimpsons.core.util.dataStore
import com.gilorroristore.thesimpsons.data.local.datastore.DataStoreManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocalAppModule {

    @Provides
    @Singleton
    fun provideDataStore(@ApplicationContext context: Context): DataStore<Preferences> {
        return context.dataStore
    }

    @Provides
    @Singleton
    fun provideDataStoreManager(dataStore: DataStore<Preferences>) : DataStoreManager{
        return DataStoreManager(dataStore)
    }
}