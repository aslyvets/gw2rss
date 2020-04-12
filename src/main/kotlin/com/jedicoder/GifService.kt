package com.jedicoder

import com.google.gson.Gson
import com.squareup.okhttp.HttpUrl
import com.squareup.okhttp.OkHttpClient
import com.squareup.okhttp.Request

object GifService {
    private val client = OkHttpClient()

    fun getGif(param: String): String {
        val urlBuilder = HttpUrl.parse("https:/api.giphy.com/v1/gifs/random").newBuilder()
        urlBuilder.addQueryParameter("api_key", "sXpGFDGZs0Dv1mmNFvYaGUvYwKX0PWIh")
        urlBuilder.addQueryParameter("tag", param)
        val url = urlBuilder.build().toString()

        val request = Request.Builder()
            .url(url)
            .build()
        client.newCall(request).execute().also { response ->
            val json = response.body().string()
            val gson = Gson()
            val data = gson.fromJson(json, Data::class.java)
            return data.data.image_original_url
        }
    }

    data class Data(val data: Gif = Gif())
    data class Gif(val image_original_url: String = "")
}
