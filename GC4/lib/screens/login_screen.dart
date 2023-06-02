import 'package:flash_chat/screens/ForgetPassword_screen.dart';

import 'package:flash_chat/screens/registration_screen.dart';
import 'package:flutter/material.dart';
import '../utils/colors.dart';
import '../utils/constant.dart';
import '../widgets/ButtonWidget.dart';
import 'frontscreen.dart';
import 'user_main.dart';

class LoginScreen extends StatefulWidget {
  static const String id = 'login_screen';
  const LoginScreen({super.key});

  @override
  State<LoginScreen> createState() => _LoginScreenState();
}

class _LoginScreenState extends State<LoginScreen> {
  final _formkey = GlobalKey<FormState>();

  late var email;
  late var password;

  final emailController = TextEditingController();
  final passwordController = TextEditingController();

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      backgroundColor: Colors.white,
      body: Form(
          key: _formkey,
          child: Padding(
            padding: const EdgeInsets.symmetric(horizontal: kdefaultPadding),
            child: Column(
              mainAxisAlignment: MainAxisAlignment.center,
              crossAxisAlignment: CrossAxisAlignment.stretch,
              children: [
                const Hero(
                  tag: 'logo',
                  child: Icon(
                    Icons.flash_on,
                    color: flashColor,
                    size: 100,
                  ),
                ),
                const SizedBox(
                  height: ksmallSpacing,
                ),
                TextFormField(
                  keyboardType: TextInputType.emailAddress,
                  onChanged: (value) {},
                  controller: emailController,
                  validator: (value) {
                    if (value == null || value.isEmpty) {
                      return 'Please Enter Email';
                    } else if (!value.contains('@')) {
                      return 'Please Provide valid Email';
                    }
                    return null;
                  },
                  decoration: const InputDecoration(
                    border: OutlineInputBorder(
                        borderRadius: BorderRadius.all(
                          Radius.circular(kdefaultPadding),
                        ),
                        borderSide:
                            BorderSide(width: 1, color: Colors.blueAccent)),
                    hintText: 'xyz@gmail.com',
                    contentPadding: EdgeInsets.symmetric(
                        vertical: knormalSpacing, horizontal: ksmallSpacing),
                    label: Text('enter your email'),
                  ),
                ),
                const SizedBox(
                  height: ksmallSpacing,
                ),
                TextField(
                  obscureText: true,
                  controller: passwordController,
                  onChanged: (value) {},
                  decoration: const InputDecoration(
                    filled: true,
                    fillColor: Colors.white,
                    border: OutlineInputBorder(
                        borderRadius: BorderRadius.all(
                          Radius.circular(kdefaultPadding),
                        ),
                        borderSide:
                            BorderSide(width: 1, color: Colors.blueAccent)),
                    hintText: 'xyz@12345',
                    label: Text('enter your password'),
                  ),
                ),
                const SizedBox(
                  height: kdefaultPadding,
                ),
                ButtonWidget(
                  onpress: () {
                    if (_formkey.currentState!.validate()) {
                      email = emailController.text;
                      password = passwordController.text;
                      Navigator.push(context, MaterialPageRoute(
                        builder: (context) {
                          return FrontScreen();
                        },
                      ));
                    }
                  },
                  text: 'login',
                  color: Colors.blueAccent,
                ),
                Align(
                  alignment: Alignment.bottomRight,
                  child: TextButton(
                      onPressed: () {
                        Navigator.push(context, MaterialPageRoute(
                          builder: (context) {
                            return ForgetPassword();
                          },
                        ));
                      },
                      child: Text('Forget password ?')),
                ),
                Row(
                  mainAxisAlignment: MainAxisAlignment.center,
                  children: [
                    Text("don't have an account"),
                    SizedBox(
                      width: 20,
                    ),
                    TextButton(
                        onPressed: () {
                          Navigator.pushNamed(context, RegistrationScreen.id);
                        },
                        child: Text('sign up')),
                  ],
                )
              ],
            ),
          )),
    );
  }
}
