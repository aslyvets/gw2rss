package com.jedicoder

import com.google.gson.Gson
import com.jedicoder.Consts.ANSWER
import com.jedicoder.Consts.GIF
import com.jedicoder.Consts.INDEX
import com.squareup.okhttp.HttpUrl
import com.squareup.okhttp.OkHttpClient
import com.squareup.okhttp.Request
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.servlet.ModelAndView


@RestController
class StaticController {

    var client = OkHttpClient()

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

    @RequestMapping("/playGw2", "/")
    fun playGw2(): ModelAndView {
        val embedUrl = getGif("not today")
        val answer = "Not today!"
        return ModelAndView(INDEX, mapOf(GIF to embedUrl, ANSWER to answer))
    }

    @RequestMapping("/toDrinkBeer")
    fun toDrinkBeer(): ModelAndView {
        val embedUrl = getGif("beer")
        val answer = "Yes!"
        return ModelAndView(INDEX, mapOf(GIF to embedUrl, ANSWER to answer))
    }
}

data class Data(val data: Gif = Gif())

data class Gif(val image_original_url: String = "")
