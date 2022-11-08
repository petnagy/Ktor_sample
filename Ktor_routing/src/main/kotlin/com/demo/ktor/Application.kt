package com.demo.ktor

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

fun Application.module() {
    routing {
        talkRoute()

        route("/hello", HttpMethod.Get) {
            handle {
                call.respondText("Hello")
            }
        }

        get("/say/hello/{name}") {
            val name = call.parameters["name"]
            call.respondText { "Hello $name" }
        }

        get("/test/{param...}") {
            val params = call.parameters.getAll("param")
            call.respondText { "Params caught: ${params?.joinToString(",")}" }
        }

        get("/example/*") {
            val path = call.request.path()
            call.respondText { "Hello, the path: $path" }
        }

        get("/") {
            call.respondText("Hello, world this is routing!")
        }

        get("/user/{login?}") {
            val param = call.parameters["login"]
            call.respondText("Hello: $param")
        }

        route("/customer") {
            get {

            }
            post {

            }
        }
        route("/order") {
            get {

            }
            get("/{id}") {

            }
        }
        route("/order") {
            route("/shipment") {
                get {

                }
                post {

                }
            }
        }
    }
}

fun Route.talkRoute() {
    get("/goodbye") {
        call.respondText("Bye-Bye")
    }
}