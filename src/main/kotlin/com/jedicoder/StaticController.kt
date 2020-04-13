package com.jedicoder

import com.jedicoder.Consts.ANSWER
import com.jedicoder.Consts.GIF
import com.jedicoder.Consts.INDEX
import com.jedicoder.GifService.getGif
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.servlet.ModelAndView
import kotlin.random.Random

@RestController
class StaticController {

    @RequestMapping("/playGw2")
    fun playGw2(): ModelAndView {
        val embedUrl = getGif("not today")
        val answer = "Not today!"
        return ModelAndView(INDEX, mapOf(GIF to embedUrl, ANSWER to answer))
    }

    @RequestMapping("/")
    fun random(): ModelAndView {
        return if (Random.nextBoolean()) playGw2() else toDrinkBeer()
    }

    @RequestMapping("/toDrinkBeer")
    fun toDrinkBeer(): ModelAndView {
        val embedUrl = getGif("beer")
        val answer = "Drink beer!"
        return ModelAndView(INDEX, mapOf(GIF to embedUrl, ANSWER to answer))
    }
}
