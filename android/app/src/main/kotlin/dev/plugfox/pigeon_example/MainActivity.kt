package dev.plugfox.pigeon_example

import dev.plugfox.ticker.TickerPlugin
import io.flutter.embedding.android.FlutterActivity
import io.flutter.embedding.engine.FlutterEngine

class MainActivity: FlutterActivity() {
    override fun configureFlutterEngine(flutterEngine: FlutterEngine) {
        flutterEngine.plugins.add(TickerPlugin())
        super.configureFlutterEngine(flutterEngine)
    }

    @Suppress("RedundantOverride")
    override fun onDestroy() {
        super.onDestroy()
    }
}
