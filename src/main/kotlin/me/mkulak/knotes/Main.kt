package me.mkulak.knotes

import io.vertx.core.Vertx
import io.vertx.ext.web.Router
import io.vertx.kotlin.coroutines.await

suspend fun main() {
    val vertx = Vertx.vertx()
    val server = vertx.createHttpServer()
    val router = Router.router(vertx)
    router.get("/").handler { ctx ->
        ctx.response().setStatusCode(200).end("Hello")
    }
    server.requestHandler(router).listen(8080).await()
    println("Server started on http://0.0.0.0:8080")
}