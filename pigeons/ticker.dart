// ignore_for_file: one_member_abstracts
import 'package:pigeon/pigeon.dart';

@ConfigurePigeon(
  PigeonOptions(
    copyrightHeader: 'pigeons/copyright_header.txt',
    dartOut: 'lib/src/ticker.g.dart',
    javaOut: 'android/app/src/main/java/dev/plugfox/ticker/Ticker.java',
    javaOptions: JavaOptions(package: 'dev.plugfox.ticker'),
    objcHeaderOut: 'ios/Runner/Pigeons/Ticker.h',
    objcSourceOut: 'ios/Runner/Pigeons/Ticker.m',
  ),
)
class TickerDelay {
  int? delay;
}

class TickerResult {
  bool? successful;
  bool? hasError;
  String? error;
}

class TickerMessage {
  int? timestamp;
}

/// FLUTTER FRAMEWORK -> FLUTTER ENGINE
@HostApi()
abstract class TickerSender {
  TickerResult start(TickerDelay tickerDelay);
  TickerResult stop();
}

/// FLUTTER FRAMEWORK <- FLUTTER ENGINE
@FlutterApi()
abstract class TickerReceiver {
  void onTick(TickerMessage tickerMessage);
}
