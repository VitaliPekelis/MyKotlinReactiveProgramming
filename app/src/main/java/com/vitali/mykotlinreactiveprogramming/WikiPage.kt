package com.vitali.mykotlinreactiveprogramming

data class WikiPage(val pageid: Long? = null,
                    val title: String? = null,
                    val fullurl: String? = null,
                    val thumbnail: WikiThumbnail? = null)