import 'dart:async';

import 'package:flutter/material.dart';
import 'package:l/l.dart';
import 'package:pigeon_example/src/ticker_controller.dart';

void main() => runZonedGuarded(
      () => runApp(const App()),
      l.e,
    );

class App extends StatelessWidget {
  const App({Key? key}) : super(key: key);

  // This widget is the root of your application.
  @override
  Widget build(BuildContext context) => MaterialApp(
        title: 'Pigeon example',
        theme: ThemeData(
          primarySwatch: Colors.blue,
        ),
        home: const HomeScreen(),
      );
}

@immutable
class HomeScreen extends StatefulWidget {
  const HomeScreen({
    Key? key,
  }) : super(key: key);

  @override
  State<HomeScreen> createState() => _HomeScreenState();
}

class _HomeScreenState extends State<HomeScreen> {
  final ITickerController _controller = TickerController.instance();

  @override
  Widget build(BuildContext context) => Scaffold(
        body: SafeArea(
          child: Center(
            child: AnimatedBuilder(
              animation: _controller,
              builder: (context, _) => Text(
                _controller.date?.toIso8601String() ?? '<null>',
              ),
            ),
          ),
        ),
        floatingActionButton: FloatingActionButton(
          onPressed: () {
            if (_controller.isRunning) {
              _controller.stop();
            } else {
              _controller.start(
                const Duration(milliseconds: 250),
              );
            }
          },
        ),
      );
}
