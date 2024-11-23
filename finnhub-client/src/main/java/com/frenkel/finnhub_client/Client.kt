package com.frenkel.finnhub_client

import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.websocket.WebSockets
import io.ktor.client.plugins.websocket.webSocket
import io.ktor.websocket.Frame
import io.ktor.websocket.readText
import kotlinx.coroutines.runBlocking

private val client = HttpClient(CIO) {
    install(WebSockets)
}

// fun sendWebsocketMessage() channel.send

fun startWebSocket() {
    runBlocking {
        client.webSocket(
            "wss://ws.finnhub.io?token=csu9tnpr01qgo8ni1chgcsu9tnpr01qgo8ni1ci0"
        ) {
            // channel.read ()
            send(Frame.Text("{\"type\":\"subscribe\",\"symbol\":\"AAPL\"}"))
            send(Frame.Text("{\"type\":\"subscribe\",\"symbol\":\"AMZN\"}"))
            //send(Frame.Text("{\"type\":\"subscribe\",\"symbol\":\"BINANCE:BTCUSDT\"}"))

            while (true) {
                val message = incoming.receive() as? Frame.Text
                message?.let {
                    println(message.readText())
                }
            }
        }
    }
}