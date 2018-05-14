package com.vitali.mykotlinreactiveprogramming

import io.reactivex.Observable

object NetworkHandler
{
    private val someNetworkApi by lazy {
        SomeNetworkApi.create()
    }


    fun getSearch(searchString: String, skip: Int = 0, pilimit: Int = 1, take: Int): Observable<WikiResult>
    {
        return someNetworkApi.getSearch(searchString, skip, pilimit, take)
    }

}