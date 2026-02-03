package com.gilorroristore.thesimpsons.core.util

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore

const val DATA_STORE_NAME = "dataStoreName"

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = DATA_STORE_NAME)