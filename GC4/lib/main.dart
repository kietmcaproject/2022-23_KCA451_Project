import 'package:flash_chat/screens/user_main.dart';
import 'package:flash_chat/screens/welcomescreen.dart';
import 'package:flutter/material.dart';

void main() {
  runApp(const KietEvent());
}

class KietEvent extends StatelessWidget {
  const KietEvent({super.key});

  @override
  Widget build(BuildContext context) {
    return const MaterialApp(
      debugShowCheckedModeBanner: false,
      home: WelcomeScreen(),
    );
  }
}
