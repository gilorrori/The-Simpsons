package com.gilorroristore.thesimpsons.data.local.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

/**
 * Clase encargada de gestionar los datos para almacenar en DataStore por medio de funciones
 * genericas
 * @author gil_orrori_94@hotmail.com
 */
class DataStoreManager @Inject constructor(
    private val dataStore: DataStore<Preferences>
) : DataStoreManagement {

    /**
     * Almacena clave valor para String
     * @author gil_orrori_94@hotmail.com
     */
    override suspend fun saveString(key: String, dataString: String) {
        dataStore.edit { preference ->
            preference[stringPreferencesKey(key)] = dataString
        }
    }

    /**
     * Obtiene valor para String
     * @author gil_orrori_94@hotmail.com
     */
    override suspend fun getString(key: String): Flow<String> {
        val pref: Flow<String> = dataStore.data.map { preference ->
            preference[stringPreferencesKey(key)].orEmpty()
        }
        return pref
    }

    /**
     * Almacena clave valor para Int
     * @author gil_orrori_94@hotmail.com
     */
    override suspend fun saveInt(key: String, dataInt: Int) {
        dataStore.edit { preference ->
            preference[intPreferencesKey(key)] = dataInt
        }
    }

    /**
     * Obtiene valor para Int
     * @author gil_orrori_94@hotmail.com
     */
    override suspend fun getInt(key: String): Flow<Int> {
        val pref: Flow<Int> = dataStore.data.map { preference ->
            preference[intPreferencesKey(key)] ?: -1
        }
        return pref
    }

    /**
     * Almacena clave valor para Boolean
     * @author gil_orrori_94@hotmail.com
     */
    override suspend fun saveBoolean(key: String, dataBoolean: Boolean) {
        dataStore.edit { preference ->
            preference[booleanPreferencesKey(key)] = dataBoolean
        }
    }

    /**
     * Obtiene valor para Boolean
     * @author gil_orrori_94@hotmail.com
     */
    override suspend fun getBoolean(key: String): Flow<Boolean> {
        val pref: Flow<Boolean> = dataStore.data.map { preference ->
            preference[booleanPreferencesKey(key)] ?: false
        }
        return pref
    }
}