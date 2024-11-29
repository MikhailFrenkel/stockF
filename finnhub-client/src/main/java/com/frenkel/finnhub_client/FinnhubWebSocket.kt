package com.frenkel.finnhub_client

import com.frenkel.finnhub_client.models.MessageType
import com.frenkel.finnhub_client.models.RealTimeTradesMessage
import com.frenkel.finnhub_client.models.RealTimeTradesResponse
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.websocket.WebSockets
import io.ktor.client.plugins.websocket.webSocket
import io.ktor.websocket.Frame
import io.ktor.websocket.readText
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

interface FinnhubWebSocket {
    fun observeTrades(symbols: List<String>): Flow<RealTimeTradesResponse>
    fun close()
}

class FinnhubWebSocketImpl(
    private val baseUrl: String,
    private val apiKey: String,
    private val json: Json = Json
) : FinnhubWebSocket {

    private val lock = Any()

    private val httpClient = HttpClient(CIO) {
        install(WebSockets)
    }

    private var observeTradesFlow: Flow<RealTimeTradesResponse>? = null

    override fun observeTrades(symbols: List<String>): Flow<RealTimeTradesResponse> {
        if (symbols.isEmpty())
            error("symbols cannot be empty.")


        synchronized(lock) {
            return observeTradesFlow ?: flow {
                httpClient.webSocket(
                    "$baseUrl?token=$apiKey"
                ) {
                    symbols.forEach {
                        val message = RealTimeTradesMessage(
                            type = MessageType.SUBSCRIBE,
                            symbol = it
                        )

                        val messageJson = json.encodeToString(message)
                        send(Frame.Text(messageJson))
                    }

                    for (frame in incoming) {
                        if (frame is Frame.Text) {
                            val text = frame.readText()

                            try {
                                val response = json.decodeFromString<RealTimeTradesResponse>(text)
                                emit(response)
                            } catch (e: Exception) {
                                e.printStackTrace()
                            }
                        }
                    }
                }
            }
        }
    }

    override fun close() {
        httpClient.close()
        observeTradesFlow = null
    }
}