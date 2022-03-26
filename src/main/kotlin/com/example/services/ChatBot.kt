package com.example.services

import com.example.repositories.GlobalRepository
import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.client.request.forms.*
import io.ktor.client.statement.*
import io.ktor.http.*
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.jsonArray
import kotlinx.serialization.json.jsonObject

class ChatBot {
    val channel: String
    val bot: String
    val client: HttpClient
    val commands = listOf("!get_categories", "!get_products", "!contact")

    constructor(channel: String, bot: String) {
        this.channel = channel
        this.bot = bot
        this.client = HttpClient()
    }

    suspend fun getAllMessages(): JsonArray {
        val messages = client.get("https://discord.com/api/channels/$channel/messages") {
            headers {
                append(HttpHeaders.Authorization, "Bot $bot")
            }
        }.bodyAsText()
        return Json.parseToJsonElement(messages).jsonArray
    }

    suspend fun getLastMessage(): String {
        return client.get("https://discord.com/api/channels/$channel/messages?limit=1") {
            headers {
                append(HttpHeaders.Authorization, "Bot $bot")
            }
        }.bodyAsText()
    }

    suspend fun getMessagesAfter(messageId: String): JsonArray {
        val message = client.get("https://discord.com/api/channels/$channel/messages?after=${messageId}") {
            headers {
                append(HttpHeaders.Authorization, "Bot $bot")
            }
        }.bodyAsText()
        return Json.parseToJsonElement(message).jsonArray
    }

    suspend fun postMessage(messageContent: String) {
        client.submitForm (
            url = "https://discord.com/api/channels/$channel/messages",
            formParameters = Parameters.build {
                append("content", messageContent)
            }
        ) {
            headers {
                append(HttpHeaders.Authorization, "Bot $bot")
            }
        }
    }

    fun respondToCommands(messages: JsonArray): MutableSet<String> {
        val repository = GlobalRepository()
        var response = mutableSetOf<String>()
        for(message in messages) {
            var content = message.jsonObject.get("content").toString()
            var contentSplited: List<String> = content.substring(1, content.length - 1).split(" ")
            var potentialCommand = contentSplited[0]
            if (potentialCommand in commands) {
                when (potentialCommand) {
                    "!contact" -> response.add("chatbot.help@gmail.com")
                    "!get_categories" -> {
                        response.add(repository.products.keys.toString())
                    }
                    "!get_products" -> {
                        if (contentSplited.size < 2) {
                            response.add("!get_products: Podaj kategorię")
                        } else {
                            val category = contentSplited[1]
                            if(repository.products.contains(category)) {
                                response.add("$category: ${repository.products.get(category).toString()}")
                            } else {
                                response.add("Nie znaleziono kategorii $category, spróbuj ponownie")
                            }
                        }
                    }
                }
            }
        }

        return response
    }
}