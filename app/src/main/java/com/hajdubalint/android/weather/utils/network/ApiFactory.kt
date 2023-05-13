package com.hajdubalint.android.weather.utils.network

import com.google.gson.GsonBuilder
import com.hajdubalint.android.weather.BuildConfig
import com.hajdubalint.android.weather.utils.model.base.AppConstant
import com.hajdubalint.android.weather.utils.network.service.WeatherService
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object ApiFactory {

    private val loggingInterceptor = HttpLoggingInterceptor().apply {
        this.level = HttpLoggingInterceptor.Level.BODY
    }

    private val appIdInterceptor = Interceptor { chain ->
        val original: Request = chain.request()
        val originalHttpUrl: HttpUrl = original.url
        val url = originalHttpUrl.newBuilder()
            .addQueryParameter("appid", BuildConfig.WEATHER_API_KEY)
            .build()
        val requestBuilder: Request.Builder = original.newBuilder()
            .url(url)
        val request: Request = requestBuilder.build()
        chain.proceed(request)
    }

    private val unitInterceptor = Interceptor { chain ->
        val original: Request = chain.request()
        val originalHttpUrl: HttpUrl = original.url
        val url = originalHttpUrl.newBuilder()
            .addQueryParameter("units", "metric")
            .build()
        val requestBuilder: Request.Builder = original.newBuilder()
            .url(url)
        val request: Request = requestBuilder.build()
        chain.proceed(request)
    }

    private val appClient = OkHttpClient().newBuilder()
        .addInterceptor(loggingInterceptor)
        .addInterceptor(appIdInterceptor)
        .addInterceptor(unitInterceptor)
        .readTimeout(30, TimeUnit.SECONDS)
        .callTimeout(30, TimeUnit.SECONDS)
        .connectTimeout(30, TimeUnit.SECONDS)
        .writeTimeout(30, TimeUnit.SECONDS)
        .build()

    private val gson = GsonBuilder().setLenient().create()

    private fun retrofit(): Retrofit = Retrofit.Builder()
        .client(appClient)
        .baseUrl(AppConstant.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .build()

    val api: WeatherService = retrofit().create(WeatherService::class.java)

}