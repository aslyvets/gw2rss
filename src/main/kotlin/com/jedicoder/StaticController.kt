package com.jedicoder

import com.jedicoder.Consts.ANSWER
import com.jedicoder.Consts.BUTTON_TEXT
import com.jedicoder.Consts.GIF
import com.jedicoder.Consts.HB
import com.jedicoder.Consts.INDEX
import com.jedicoder.Consts.NAME
import com.jedicoder.GifService.getGif
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.servlet.ModelAndView

@RestController
class StaticController {
    private var user: User? = null
    private var counter = 0

    @RequestMapping("/")
    fun random(): ModelAndView {
        return ModelAndView(NAME)
    }

    @RequestMapping("/playGw2")
    fun playGw2(): ModelAndView {
        return getModelAndViewFor("not today", "Not today!")
    }

    @RequestMapping("/drink")
    fun drink(): ModelAndView {
        return getDrink("Another one?", "Get another one")
    }

    @RequestMapping("/toDrinkBeer")
    fun toDrinkBeer(): ModelAndView {
        return getModelAndViewFor("beer", "Drink beer!")
    }

    @RequestMapping("/name")
    fun add(@ModelAttribute user: User): ModelAndView {
        this.user = user
        val tina =
            setOf("tina", "valentina", "weingold", "валя", "вальчик", "вальхала", "вальхалла", "walhalla", "valhalla")
        val alex = setOf("alex", "leha", "lexa", "lelik", "леха", "лёха", "алекс", "лёлик")
        val marcus = setOf("marcus", "annocence")
        val isTina = tina.contains(user.name.toLowerCase())
        val isAlex = alex.contains(user.name.toLowerCase())
        val isMarcus = marcus.contains(user.name.toLowerCase())
        when {
            isTina -> return getHB()
            isAlex -> return getModelAndViewFor("whiskey", "С именнинецей тебя!")
            isMarcus -> return getModelAndViewFor("gaming", "Play GW2!")
        }
        return ModelAndView(NAME)
    }

    data class User(val name: String)

    private fun getModelAndViewFor(gifParam: String, headerText: String): ModelAndView {
        return ModelAndView(INDEX, mapOf(GIF to getGif(gifParam), ANSWER to headerText))
    }

    private fun getHB(): ModelAndView {
        counter++
        val headerText = "Happy birthday ${user?.name}"
        return ModelAndView(
            HB,
            mapOf(GIF to getGif("happy birthday"), ANSWER to headerText, BUTTON_TEXT to "Get drink!")
        )
    }

    private fun getDrink(headerText: String, buttonText: String): ModelAndView {
        counter++
        if (counter.rem(4) == 0) {
            return getModelAndViewFor("hide", "Время прятатся под стол!")
        }
        return ModelAndView(HB, mapOf(GIF to getGif("drink"), ANSWER to headerText, BUTTON_TEXT to buttonText))
    }
}
