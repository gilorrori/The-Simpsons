package com.gilorroristore.thesimpsons.di.modules

import com.gilorroristore.thesimpsons.BuildConfig
import com.gilorroristore.thesimpsons.data.network.SimpsonsApiService
import com.gilorroristore.thesimpsons.data.repositories.CharacterRepositoryImpl
import com.gilorroristore.thesimpsons.domain.repositories.CharacterRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        val interceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

        return OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()


    @Provides
    @Singleton
    fun provideSimpsonApiInterface(retrofit: Retrofit): SimpsonsApiService {
        return retrofit.create(SimpsonsApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideCharacterRepository(simpsonsApiService: SimpsonsApiService): CharacterRepository {
        return CharacterRepositoryImpl(simpsonsApiService)
    }
}