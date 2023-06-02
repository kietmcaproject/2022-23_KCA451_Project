import 'package:firebase_auth/firebase_auth.dart';
import 'package:flutter/material.dart';

import 'package:pigeon_chat/screens/welcome_screen.dart';
import 'package:pigeon_chat/widgets/tab_bar_widget.dart';

// Authentication check for routing
class AuthenticationCheck extends StatelessWidget {
  final FirebaseAuth _auth = FirebaseAuth.instance;

  AuthenticationCheck({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    if (_auth.currentUser != null) {
      return const TabBarWidget();
    } else {
      return const WelComeScreen();
    }
  }
}
