package com.example

import io.ktor.server.engine.*
import io.ktor.server.netty.*
import com.example.plugins.*
import com.example.services.ChatBot
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.json.serializer.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.client.request.*
import io.ktor.client.request.forms.*
import io.ktor.server.plugins.*
import io.ktor.util.*
import kotlinx.serialization.json.Json

import io.ktor.client.engine.apache.*
import kotlinx.coroutines.*
import kotlinx.serialization.*
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.jsonArray
import kotlinx.serialization.json.jsonObject

@OptIn(InternalAPI::class)
suspend fun main() {
    embeddedServer(Netty, port = 8080, host = "0.0.0.0") {
        configureRouting()
//        configureSecurity()
        configureHTTP()
        configureMonitoring()
        configureTemplating()
        configureSerialization()
        configureSockets()
        configureAdministration()
    }.start(wait = false)


    val chatBot = ChatBot("955905042597167135", "OTU1OTA2MjkwOTQ3ODYyNTY4.YjofGw.JPBVTpxT7lP1l0Ta0wdDALuwLgc")
        var lastMessage = chatBot.getLastMessage()
        lastMessage = Json.parseToJsonElement(lastMessage).jsonArray.get(0).jsonObject.get("id").toString()
        lastMessage = lastMessage.substring(1, lastMessage.length-1)
    while(true) {
        delay(1000)
        val response = chatBot.getMessagesAfter(lastMessage)

        if(response.size > 0) {
            lastMessage = response.get(0).jsonObject.get("id").toString()
            lastMessage = lastMessage.substring(1, lastMessage.length - 1)
        }
        val commandExecution = chatBot.respondToCommands(response)
        commandExecution.forEach { command ->
            chatBot.postMessage(command)
        }
    }

}