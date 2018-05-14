package com.vitali.mykotlinreactiveprogramming

import io.reactivex.Observable
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.concurrent.TimeUnit

interface SomeNetworkApi
{
    @GET("/w/api.php?format=json&action=query&formatversion=2&generator=prefixsearch&prop=pageimages|info&wbptterms=description&inprop=url&pithumbsize=200")
    fun getSearch(
            @Query(BaseAppUrl.GPS_SEARCH) term: String,
            @Query(BaseAppUrl.GPS_OFFSET) skip: Int,
            @Query(BaseAppUrl.PI_LIMIT) pilimit: Int,
            @Query(BaseAppUrl.GPS_LIMIT) take: Int): Observable<WikiResult>

    companion object
    {
        fun create(): SomeNetworkApi
        {
            val okHttpClient = OkHttpClient.Builder()
                    .connectTimeout(20000, TimeUnit.MILLISECONDS)
                    /*ADD MORE settings*/
                    .build()

            val retrofit = Retrofit.Builder()
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(okHttpClient)
                    .baseUrl(BaseAppUrl.getBaseUrl())
                    .build()

                    return retrofit.create(SomeNetworkApi::class.java)
        }
    }
}
