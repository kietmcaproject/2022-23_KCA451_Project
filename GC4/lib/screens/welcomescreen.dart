import 'dart:ffi';

import 'package:flash_chat/screens/frontscreen.dart';
import 'package:flash_chat/screens/signup.dart';
import 'package:flash_chat/screens/user_main.dart';
import 'package:flutter/material.dart';

class WelcomeScreen extends StatefulWidget {
  const WelcomeScreen({super.key});

  @override
  State<WelcomeScreen> createState() => _WelcomeScreenState();
}

class _WelcomeScreenState extends State<WelcomeScreen> {
  late var email;
  late var password;

  final emailController = TextEditingController();
  final passwordController = TextEditingController();
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      backgroundColor: Color.fromARGB(255, 2, 58, 104),
      body: Column(
        children: [
          Container(
            width: double.maxFinite,
            height: MediaQuery.of(context).size.height * 0.9,
            decoration: BoxDecoration(
                color: Colors.white, borderRadius: BorderRadius.circular(40)),
            child: Column(
              crossAxisAlignment: CrossAxisAlignment.start,
              children: [
                SizedBox(
                  height: 100,
                ),
                Center(
                  child: Text.rich(TextSpan(children: [
                    TextSpan(
                        text: "KIET ",
                        style: TextStyle(
                            color: Colors.blueAccent,
                            fontSize: 40,
                            fontWeight: FontWeight.bold)),
                    TextSpan(
                        text: "EVENT",
                        style: TextStyle(
                          color: Colors.black,
                          fontWeight: FontWeight.bold,
                          fontSize: 40,
                        ))
                  ])),
                ),
                SizedBox(
                  height: 50,
                ),
                Padding(
                  padding: const EdgeInsets.only(left: 20),
                  child: Text(
                    "Login",
                    style: TextStyle(
                        letterSpacing: 2,
                        color: Colors.blueAccent,
                        fontSize: 40,
                        fontWeight: FontWeight.w600),
                  ),
                ),
                Padding(
                  padding: const EdgeInsets.only(left: 20),
                  child: Text(
                    "To Continue your account!",
                    style: TextStyle(color: Colors.grey, fontSize: 20),
                  ),
                ),
                SizedBox(
                  height: 50,
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
                          Radius.circular(18),
                        ),
                        borderSide:
                            BorderSide(width: 1, color: Colors.blueAccent)),
                    hintText: 'xyz@gmail.com',
                    contentPadding:
                        EdgeInsets.symmetric(vertical: 15, horizontal: 10),
                    label: Text('enter your email'),
                  ),
                ),
                SizedBox(
                  height: 10,
                ),
                TextFormField(
                  obscureText: true,
                  controller: passwordController,
                  onChanged: (value) {},
                  decoration: const InputDecoration(
                    filled: true,
                    fillColor: Colors.white,
                    border: OutlineInputBorder(
                      borderRadius: BorderRadius.all(
                        Radius.circular(18),
                      ),
                    ),
                    hintText: 'xyz@12345',
                    label: Text('enter your password'),
                  ),
                ),
                SizedBox(
                  height: 10,
                ),
                Padding(
                  padding: const EdgeInsets.only(right: 25),
                  child: Row(
                    mainAxisAlignment: MainAxisAlignment.end,
                    children: [
                      Text(
                        "Forgot Password ?",
                        style: TextStyle(
                            color: Colors.blueAccent,
                            fontWeight: FontWeight.w500),
                      ),
                    ],
                  ),
                ),
                SizedBox(
                  height: 40,
                ),
                Center(
                  child: GestureDetector(
                    onTap: () {
                      Navigator.push(context,
                          MaterialPageRoute(builder: (context) => UserMain()));
                    },
                    child: Container(
                      decoration: BoxDecoration(
                          color: Color.fromARGB(255, 2, 58, 104),
                          borderRadius: BorderRadius.circular(30)),
                      height: MediaQuery.of(context).size.height * 0.070,
                      width: MediaQuery.of(context).size.height * 0.35,
                      child: Center(
                        child: Text(
                          "Login",
                          style: TextStyle(
                              color: Colors.white,
                              fontWeight: FontWeight.w500,
                              fontSize: 30),
                        ),
                      ),
                    ),
                  ),
                ),
                SizedBox(
                  height: 20,
                ),
                Center(child: Text("new user"))
              ],
            ),
          ),
          Spacer(),
          Center(
            child: GestureDetector(
              onTap: () {
                Navigator.push(context,
                    MaterialPageRoute(builder: (context) => NewRegistration()));
              },
              child: Text.rich(TextSpan(children: [
                TextSpan(
                    text: "Didn't have an account ? ",
                    style: TextStyle(
                        color: Colors.white,
                        fontSize: 20,
                        fontWeight: FontWeight.w400)),
                TextSpan(
                    text: "Signup",
                    style: TextStyle(
                      color: Color.fromARGB(255, 161, 195, 255),
                      fontWeight: FontWeight.w400,
                      fontSize: 20,
                    ))
              ])),
            ),
          ),
          SizedBox(
            height: 20,
          )
        ],
      ),
    );
  }
}
