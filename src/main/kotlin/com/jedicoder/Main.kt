package com.jedicoder

import com.github.mustachejava.DefaultMustacheFactory
import com.jedicoder.Consts.ANSWER
import com.jedicoder.Consts.GIF
import com.jedicoder.Consts.INDEX
import com.jedicoder.GifService.getGif
import io.ktor.application.Application
import io.ktor.application.ApplicationCall
import io.ktor.application.call
import io.ktor.application.install
import io.ktor.mustache.Mustache
import io.ktor.mustache.MustacheContent
import io.ktor.response.respond
import io.ktor.routing.get
import io.ktor.routing.routing
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import io.ktor.util.pipeline.PipelineContext
import kotlin.random.Random

fun Application.main() {
    val port = System.getenv("PORT")?.toInt() ?: 8080
    val server = embeddedServer(Netty, port = port) {
        install(Mustache) {
            mustacheFactory = DefaultMustacheFactory("templates")
        }
        routing {
            get("/") {
                if (Random.nextBoolean()) playGw2() else toDrinkBeer()
            }
            get("/playGw2") {
                playGw2()
            }
            get("/toDrinkBeer") {
                toDrinkBeer()
            }
        }
    }
    server.start(wait = true)
}

private suspend fun PipelineContext<Unit, ApplicationCall>.toDrinkBeer() {
    val embedUrl = getGif("beer")
    val answer = "Drink beer!"
    call.respond(MustacheContent(INDEX, mapOf(GIF to embedUrl, ANSWER to answer)))
}

private suspend fun PipelineContext<Unit, ApplicationCall>.playGw2() {
    val embedUrl = getGif("not today")
    val answer = "Not today!"
    call.respond(MustacheContent(INDEX, mapOf(GIF to embedUrl, ANSWER to answer)))
}
