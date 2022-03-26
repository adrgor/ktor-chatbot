package com.example

import io.ktor.server.routing.*
import io.ktor.http.*
import io.ktor.server.auth.*
import io.ktor.util.*
import io.ktor.server.auth.jwt.*
import com.auth0.jwt.JWT
import com.auth0.jwt.JWTVerifier
import com.auth0.jwt.algorithms.Algorithm
import io.ktor.server.auth.ldap.*
import io.ktor.server.locations.*
import io.ktor.client.*
import io.ktor.client.engine.apache.*
import io.ktor.server.sessions.*
import io.ktor.server.plugins.*
import io.ktor.server.http.content.*
import io.ktor.server.webjars.*
import java.time.*
import io.ktor.http.content.*
import org.slf4j.event.*
import io.ktor.server.request.*
import io.micrometer.prometheus.*
import io.ktor.server.metrics.micrometer.*
import io.ktor.server.html.*
import kotlinx.html.*
import kotlinx.css.*
import freemarker.cache.*
import io.ktor.server.freemarker.*
import com.github.mustachejava.DefaultMustacheFactory
import io.ktor.server.mustache.Mustache
import io.ktor.server.mustache.MustacheContent
import com.mitchellbosecke.pebble.loader.ClasspathLoader
import io.ktor.server.pebble.*
import io.ktor.server.thymeleaf.Thymeleaf
import io.ktor.server.thymeleaf.ThymeleafContent
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver
import io.ktor.server.velocity.*
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader
import org.apache.velocity.runtime.RuntimeConstants

import io.ktor.serialization.kotlinx.json.*
import io.ktor.serialization.gson.*
import io.ktor.serialization.jackson.*
import com.fasterxml.jackson.databind.*
import io.ktor.network.selector.*
import io.ktor.network.sockets.*
import io.ktor.utils.io.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.io.InputStream
import java.util.*
import io.ktor.network.tls.*
import io.ktor.utils.io.core.*
import io.ktor.server.websocket.*
import io.ktor.websocket.*
import java.time.Duration
import io.ktor.server.engine.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import kotlin.test.*
import io.ktor.server.testing.*
import com.example.plugins.*

class ApplicationTest {
    @Test
    fun testRoot() = testApplication {
        application {
            configureRouting()
        }
        client.get("/").apply {
            assertEquals(HttpStatusCode.OK, status)
            assertEquals("Hello World!", bodyAsText())
        }
    }
}