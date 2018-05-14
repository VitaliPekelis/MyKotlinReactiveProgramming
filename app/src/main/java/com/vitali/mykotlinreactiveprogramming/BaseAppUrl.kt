package com.vitali.mykotlinreactiveprogramming

import android.net.Uri

object BaseAppUrl
{
    private const val schema = "https"
    private const val host = "en.wikipedia.org"

    const val GPS_SEARCH = "gpssearch"
    const val GPS_LIMIT = "gpslimit"
    const val GPS_OFFSET = "gpsoffset"
    const val PI_LIMIT = "pilimit"

    fun getBaseUrl(): String
    {
        return Uri.Builder().scheme(schema).encodedAuthority(host).toString()
    }

}
