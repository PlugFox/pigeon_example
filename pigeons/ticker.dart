// ignore_for_file: one_member_abstracts
import 'package:pigeon/pigeon.dart';

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
