package com.ekas.finhealkotlin.network

import android.content.Context
import com.ekas.finhealkotlin.Application.FinhealApp
import com.ekas.finhealkotlin.BuildConfig
import com.google.gson.GsonBuilder
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import com.readystatesoftware.chuck.ChuckInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class APIClient {

    companion object {

        private val CACHE_CONTROL = "Cache-Control"
        private val BASE_URL: String = "http://finhealcapital.in/app/admin/api/"

        fun getClient(context: Context?, tag: String?): Retrofit? {
            var retrofit: Retrofit? = null
            val builder = OkHttpClient.Builder()
            try {
                if (BuildConfig.DEBUG) {
                    val logging = HttpLoggingInterceptor()
                    logging.setLevel(HttpLoggingInterceptor.Level.HEADERS)
                            .setLevel(HttpLoggingInterceptor.Level.BODY)
                    builder.addInterceptor(logging)
                    ////comment this before release

                    //TODO: Hatana hai for chuck

                    //    builder.addInterceptor( ChuckInterceptor(context));
                }
                val interceptor = Interceptor { chain: Interceptor.Chain ->
                    val original = chain.request()
                    val request = original.newBuilder()
                            .addHeader(
                                    "Content-Type",
                                    "application/json"
                            ) //                        .addHeader(AppUrls.API_HEADER_VERSION, Helper.getAppVersionCode(FinhealApp.getInstance()) != null ? Helper.getAppVersionCode(FinhealApp.getInstance()) : "0")
                            .method(original.method, original.body)
                            .build()
                    chain.proceed(request)
                }
                builder.connectTimeout(60, TimeUnit.SECONDS)
                        .readTimeout(0, TimeUnit.NANOSECONDS)
                        .writeTimeout(35, TimeUnit.SECONDS)
                        .addInterceptor(interceptor)
                        .build()
                retrofit = Retrofit.Builder()
                        .baseUrl(BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .client(builder.build())
                        .build()
            } catch (e: Exception) {
                e.printStackTrace()
            }
            return retrofit
        }

        fun getClientHeader(context: Context?, tag: String?): Retrofit? {
            var retrofit: Retrofit? = null
            val builder = OkHttpClient.Builder()
            try {
                if (BuildConfig.DEBUG) {
                    val logging = HttpLoggingInterceptor()
                    logging.setLevel(HttpLoggingInterceptor.Level.HEADERS)
                            .setLevel(HttpLoggingInterceptor.Level.BODY)
                    builder.addInterceptor(logging)
                    //comment this before release
                    //TODO: Hatana hai for chuck
                    //    builder.addInterceptor(ChuckInterceptor(context));
                }
                val interceptor = Interceptor { chain: Interceptor.Chain ->
                    val original = chain.request()
                    val request = original.newBuilder()
                            .addHeader("Content-Type", "application/json")
                            .addHeader(
                                    "Authorization",
                                    "Bearer " + FinhealApp.getUserToken(context)
                            ) //                        .addHeader(AppUrls.API_HEADER_VERSION, Helper.getAppVersionCode(FinhealApp.getInstance()) != null ? Helper.getAppVersionCode(FinhealApp.getInstance()) : "0")
                            .method(original.method, original.body)
                            .build()
                    chain.proceed(request)
                }
                builder.connectTimeout(60, TimeUnit.SECONDS)
                        .readTimeout(0, TimeUnit.NANOSECONDS)
                        .writeTimeout(35, TimeUnit.SECONDS)
                        .addInterceptor(interceptor)
                        .build()
                val gson = GsonBuilder().setLenient().create()
                retrofit = Retrofit.Builder()
                        .baseUrl(BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create(gson))
                        .client(builder.build())
                        .build()
            } catch (e: Exception) {
                e.printStackTrace()
            }
            return retrofit
        }

        private val BASE_URL_EQUIFAX = "https://ists.equifax.co.in/cir360service/"

        fun getEquifaxClient(context: Context?, tag: String?): Retrofit? {
            var retrofit: Retrofit? = null
            val builder = OkHttpClient.Builder()
            try {
                if (BuildConfig.DEBUG) {
                    val logging = HttpLoggingInterceptor()
                    logging.setLevel(HttpLoggingInterceptor.Level.HEADERS)
                            .setLevel(HttpLoggingInterceptor.Level.BODY)
                    builder.addInterceptor(logging)
                    ///comment this before release
                    //TODO: Hatana hai for chuck
                    //    builder.addInterceptor(ChuckInterceptor(context));
                }
                val interceptor = Interceptor { chain: Interceptor.Chain ->
                    val original = chain.request()
                    val request = original.newBuilder()
                            .addHeader(
                                    "Content-Type",
                                    "application/json"
                            ) //    .addHeader(AppUrls.API_HEADER_VERSION, Helper.getAppVersionCode(FinhealApp.getInstance()) != null ? Helper.getAppVersionCode(FinhealApp.getInstance()) : "0")
                            .method(original.method, original.body)
                            .build()
                    chain.proceed(request)
                }
                builder.connectTimeout(60, TimeUnit.SECONDS)
                        .readTimeout(0, TimeUnit.NANOSECONDS)
                        .writeTimeout(35, TimeUnit.SECONDS)
                        .addInterceptor(interceptor)
                        .build()
                retrofit = Retrofit.Builder()
                        .baseUrl(BASE_URL_EQUIFAX)
                        .addConverterFactory(GsonConverterFactory.create())
                        .client(builder.build())
                        .build()
            } catch (e: Exception) {
                e.printStackTrace()
            }
            return retrofit
        }


        private val BASE_URL_EQUIFAX_PAN = "https://sm-kyc.scoreme.in/"

        fun getEquifaxClientpan(context: Context?, tag: String?): Retrofit? {
            var retrofit: Retrofit? = null
            val builder = OkHttpClient.Builder()
            try {
                if (BuildConfig.DEBUG) {
                    val logging = HttpLoggingInterceptor()
                    logging.setLevel(HttpLoggingInterceptor.Level.HEADERS)
                            .setLevel(HttpLoggingInterceptor.Level.BODY)
                    builder.addInterceptor(logging)
                    ///comment this before release
                    //TODO: Hatana hai for chuck
                    //   builder.addInterceptor( ChuckInterceptor(context));
                }
                val interceptor = Interceptor { chain: Interceptor.Chain ->
                    val original = chain.request()
                    val request = original.newBuilder()
                            .addHeader("Content-Type", "application/json")
                            .addHeader("Access-Type", "application/json")
                            .addHeader("ClientId", "d6eb78c9789197dfe3acad4bbd8c33bf")
                            .addHeader(
                                    "ClientSecret",
                                    "41e896aaaa23b9ba09b1ae5735b4bc83a1538bd103dd4a2525118297865f661f"
                            ) //.addHeader()
                            .method(original.method, original.body)
                            .build()
                    chain.proceed(request)
                }
                builder.connectTimeout(60, TimeUnit.SECONDS)
                        .readTimeout(0, TimeUnit.NANOSECONDS)
                        .writeTimeout(35, TimeUnit.SECONDS)
                        .addInterceptor(interceptor)
                        .build()
                retrofit = Retrofit.Builder()
                        .baseUrl(BASE_URL_EQUIFAX_PAN)
                        .addConverterFactory(GsonConverterFactory.create())
                        .client(builder.build())
                        .build()
            } catch (e: Exception) {
                e.printStackTrace()
            }
            return retrofit
        }

    }


}