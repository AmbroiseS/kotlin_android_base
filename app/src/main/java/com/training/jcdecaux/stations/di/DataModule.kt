package com.training.jcdecaux.stations.di

import android.app.Application
import android.content.Context
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.training.jcdecaux.stations.BuildConfig
import com.training.jcdecaux.stations.R
import com.training.jcdecaux.stations.data.jcdecaux.RequestInterceptor
import com.training.jcdecaux.stations.di.qualifier.ApiKey
import com.training.jcdecaux.stations.di.qualifier.ApplicationQualifier
import com.training.jcdecaux.stations.rest.clients.JCDRestClient
import com.training.jcdecaux.stations.rest.mapper.PositionMapper
import com.training.jcdecaux.stations.rest.mapper.StationMapper
import com.training.jcdecaux.stations.ui.utils.LocationUtils
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class DataModule {

    @Provides
    @Singleton
    fun provideOkHttpCache(application: Application): Cache {
        val cacheSize: Long = 10 * 1024 * 1024
        return Cache(application.cacheDir, cacheSize)
    }

    @Provides
    @Singleton
    @ApiKey
    fun provideApiKey(@ApplicationQualifier context: Context): String = context.getString(R.string.api_key)

    @Provides
    @Singleton
    fun provideOkHttpClient(cache: Cache, requestInterceptor: RequestInterceptor): OkHttpClient = OkHttpClient()
            .newBuilder()
            .addInterceptor(requestInterceptor)
            .cache(cache)
            .build()

    @Provides
    @Singleton
    fun provideRequestInterceptor(@ApiKey apiKey: String) = RequestInterceptor(apiKey)


    @Provides
    @Singleton
    fun provideGson(): Gson = GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create()

    @Provides
    @Singleton
    fun provideRetrofit(gson: Gson, okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .baseUrl(BuildConfig.JCDECAUX_API_URL)
            .client(okHttpClient)
            .build()

    @Provides
    @Singleton
    fun provideJCDRestClient(retrofit: Retrofit): JCDRestClient = retrofit.create(JCDRestClient::class.java)


}