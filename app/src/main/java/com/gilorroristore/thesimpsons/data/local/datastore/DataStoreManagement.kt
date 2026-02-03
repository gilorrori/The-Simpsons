package com.gilorroristore.thesimpsons.data.local.datastore

import kotlinx.coroutines.flow.Flow

interface DataStoreManagement {
    suspend fun saveString(key: String, dataString: String)
    suspend fun getString(key: String) : Flow<String>
    suspend fun saveInt(key: String, dataInt: Int)
    suspend fun getInt(key: String) : Flow<Int>
    suspend fun saveBoolean(key: String, dataBoolean: Boolean)
    suspend fun getBoolean(key: String) : Flow<Boolean>
}