package com.example.weatherzone.data

import com.example.myapplication.BuildConfig
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient : Interceptor {

    private val BASE_URL = "http://api.openweathermap.org/"

    private val retrofitLogin by lazy {
        val logging = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
        val client = OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        var original = chain.request()
        val url = original.url.newBuilder().addQueryParameter("apikey", BuildConfig.API_KEY).build()
        original = original.newBuilder().url(url).build()
        return chain.proceed(original)
    }

    val cityAPi: RestApi by lazy {
        retrofitLogin.create(RestApi::class.java)
    }
}