package dev.plugfox.ticker

import android.os.Handler
import android.os.Looper
import android.util.Log
import io.flutter.embedding.engine.plugins.FlutterPlugin

import kotlin.concurrent.timerTask

class TickerPlugin : FlutterPlugin, Ticker.TickerSender {
    private companion object {
        /// Тэг плагина
        private const val TAG = "TickerPlugin"

        /// Взаимодействие с подключенным Flutter Engine
        private var sink : Ticker.TickerReceiver? = null

        private var timer : java.util.Timer? = null
    }

    override fun onAttachedToEngine(binding: FlutterPlugin.FlutterPluginBinding) {
        try {
            // Зарегистрируем метод ченелы
            Ticker.TickerSender.setup(binding.binaryMessenger, this)
            sink = Ticker.TickerReceiver(binding.binaryMessenger)
            timer?.cancel()
        }  catch (error: Throwable) {
            Log.e(TAG, "Произошло непредвиденное исключение при подключении TickerPlugin.")
        }
    }

    override fun onDetachedFromEngine(binding: FlutterPlugin.FlutterPluginBinding) {
        try {
            timer?.cancel()
            Ticker.TickerSender.setup(binding.binaryMessenger, null)
            sink = null
        }  catch (error: Throwable) {
            Log.e(TAG, "Произошло непредвиденное исключение при отключении TickerPlugin.")
        }
    }

    override fun start(tickerDelay: Ticker.TickerDelay?): Ticker.TickerResult {
        try {
            timer?.cancel()
            val handler = Handler(Looper.getMainLooper())
            timer = java.util.Timer().apply {
                scheduleAtFixedRate(
                    timerTask {
                        handler.post(Runnable() {
                            run {
                                Log.i(TAG, "TICK НА ПЛАТФОРМЕ")
                                sink?.onTick(Ticker.TickerMessage().apply {
                                    this.timestamp = System.currentTimeMillis()
                                }) {}
                            }
                        })
                    }, 0, tickerDelay?.delay ?: 1000
                )
            }
            return Ticker.TickerResult().apply {
                successful = true
                hasError = false
                error = null
            }
        } catch (platformError: Throwable) {
            return Ticker.TickerResult().apply {
                successful = false
                hasError = true
                error = platformError.message
            }
        }
    }

    override fun stop(): Ticker.TickerResult {
        return try {
            timer?.cancel()
            Ticker.TickerResult().apply {
                successful = true
                hasError = false
                error = null
            }
        } catch (platformError: Throwable) {
            Ticker.TickerResult().apply {
                successful = false
                hasError = true
                error = platformError.message
            }
        }
    }
}