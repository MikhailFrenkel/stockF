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
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
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
    private val observedSymbols = mutableListOf<String>()
    private val subscribeSymbolChannel: Channel<String> = Channel()
    private val tradesChannel: Channel<RealTimeTradesResponse> = Channel()
    private val scope = CoroutineScope(Dispatchers.IO)

    private val httpClient = HttpClient(CIO) {
        install(WebSockets)
    }

    private var observeTradesFlow: Flow<RealTimeTradesResponse>? = null

    override fun observeTrades(symbols: List<String>): Flow<RealTimeTradesResponse> {
        if (symbols.isEmpty())
            error("symbols cannot be empty.")

        synchronized(lock) {
            if (observeTradesFlow == null) {
                startWebSocket()
                observeTradesFlow = flow {
                    for (trade in tradesChannel) {
                        emit(trade)
                    }
                }
            }
        }

        for (symbol in symbols) {
            if (!observedSymbols.contains(symbol)) {
                scope.launch {
                    subscribeSymbolChannel.send(symbol)
                }
            }
        }

        return observeTradesFlow!!
    }

    private fun startWebSocket() {
        scope.launch {
            httpClient.webSocket(
                "$baseUrl?token=$apiKey"
            ) {
                launch {
                    for (symbol in subscribeSymbolChannel) {
                        if (!observedSymbols.contains(symbol)) {
                            val message = RealTimeTradesMessage(
                                type = MessageType.SUBSCRIBE,
                                symbol = symbol
                            )

                            val messageJson = json.encodeToString(message)
                            send(Frame.Text(messageJson))
                            observedSymbols.add(symbol)
                        }
                    }
                }

                for (frame in incoming) {
                    if (frame is Frame.Text) {
                        val text = frame.readText()
                        println(text)

                        try {
                            val response = json.decodeFromString<RealTimeTradesResponse>(text)
                            tradesChannel.send(response)
                        } catch (e: Exception) {
                            e.printStackTrace()
                        }
                    }
                }
            }
        }
    }

    override fun close() {
        httpClient.close()
        subscribeSymbolChannel.close()
        observeTradesFlow = null
    }
}