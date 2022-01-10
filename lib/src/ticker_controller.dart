// ignore_for_file: prefer_mixin

import 'dart:async';

import 'package:flutter/foundation.dart';
import 'package:pigeon_example/src/ticker.g.dart';

/// Контроллер управляющий тикером,
/// с определенной переодичностью уведомляющего подписчиков
abstract class ITickerController implements Listenable {
  /// Тикер запущен
  bool get isRunning;

  /// Тикер не запущен
  bool get isNotRunning;

  /// Дата последнего тика
  DateTime? get date;

  /// Запустить тикер
  Future<TickerResult> start(Duration period);

  /// Остановить тикер
  Future<TickerResult> stop();
}

class TickerController
    with ChangeNotifier
    implements ITickerController, TickerReceiver {
  static TickerController? _instance;
  factory TickerController.instance() => _instance ??= TickerController._();
  TickerController._() : _sink = TickerSender() {
    TickerReceiver.setup(this);
  }
  final TickerSender _sink;

  @override
  DateTime? get date => _date;
  DateTime? _date;

  @override
  bool get isRunning => _isRunning;
  @override
  bool get isNotRunning => !_isRunning;
  bool _isRunning = false;

  @override
  Future<TickerResult> start(Duration period) => _sink
          .start(TickerDelay()..delay = period.inMilliseconds)
          .then<TickerResult>((value) {
        _isRunning = value.successful ?? false;
        notifyListeners();
        return value;
      });

  @override
  Future<TickerResult> stop() => _sink.stop().then<TickerResult>((value) {
        _isRunning = !(value.successful ?? true);
        if (isNotRunning) {
          _date = null;
        }
        notifyListeners();
        return value;
      });

  @override
  @protected
  void onTick(TickerMessage tickerMessage) {
    final timestamp = tickerMessage.timestamp;
    if (timestamp != null) {
      _date = DateTime.fromMillisecondsSinceEpoch(timestamp);
    }
    notifyListeners();
  }
}
