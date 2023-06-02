import 'dart:ffi';

import 'package:flutter/material.dart';

import 'welcomescreen.dart';

class NewRegistration extends StatelessWidget {
  const NewRegistration({super.key});

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
                  height: 30,
                ),
                Padding(
                  padding: const EdgeInsets.only(left: 20),
                  child: Text(
                    "Signup",
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
                    "Register your account",
                    style: TextStyle(color: Colors.grey, fontSize: 20),
                  ),
                ),
                SizedBox(
                  height: 30,
                ),
                Padding(
                  padding: const EdgeInsets.only(left: 20),
                  child: Text(
                    "Name",
                    style: TextStyle(
                        color: Colors.black,
                        fontWeight: FontWeight.bold,
                        fontSize: 15),
                  ),
                ),
                Padding(
                  padding: const EdgeInsets.only(left: 20, top: 10),
                  child: Row(
                    children: [Icon(Icons.person)],
                  ),
                ),
                SizedBox(
                  height: 20,
                ),
                Padding(
                  padding: const EdgeInsets.only(left: 20),
                  child: Text(
                    "Email ID",
                    style: TextStyle(
                        color: Colors.black,
                        fontWeight: FontWeight.bold,
                        fontSize: 15),
                  ),
                ),
                Padding(
                  padding: const EdgeInsets.only(left: 20, top: 10),
                  child: Row(
                    children: [Icon(Icons.person)],
                  ),
                ),
                SizedBox(
                  height: 20,
                ),
                Padding(
                  padding: const EdgeInsets.only(left: 20),
                  child: Text(
                    "Mobile No",
                    style: TextStyle(
                        color: Colors.black,
                        fontWeight: FontWeight.bold,
                        fontSize: 15),
                  ),
                ),
                Padding(
                  padding: const EdgeInsets.only(left: 20, top: 10),
                  child: Row(
                    children: [Icon(Icons.person)],
                  ),
                ),
                SizedBox(
                  height: 20,
                ),
                Padding(
                  padding: const EdgeInsets.only(left: 20),
                  child: Text(
                    "Password",
                    style: TextStyle(
                        color: Colors.black,
                        fontWeight: FontWeight.bold,
                        fontSize: 15),
                  ),
                ),
                Padding(
                  padding: const EdgeInsets.only(left: 20, top: 10),
                  child: Row(
                    children: [Icon(Icons.person)],
                  ),
                ),
                SizedBox(
                  height: 25,
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
                  child: Container(
                    decoration: BoxDecoration(
                        color: Color.fromARGB(255, 2, 58, 104),
                        borderRadius: BorderRadius.circular(30)),
                    height: MediaQuery.of(context).size.height * 0.070,
                    width: MediaQuery.of(context).size.height * 0.35,
                    child: Center(
                      child: Text(
                        "Signup",
                        style: TextStyle(
                            color: Colors.white,
                            fontWeight: FontWeight.w500,
                            fontSize: 30),
                      ),
                    ),
                  ),
                ),
                SizedBox(
                  height: 20,
                ),
              ],
            ),
          ),
          Spacer(),
          Center(
            child: GestureDetector(
              onTap: () {
                Navigator.push(context,
                    MaterialPageRoute(builder: (context) => WelcomeScreen()));
              },
              child: Text.rich(TextSpan(children: [
                TextSpan(
                    text: "Already have an account ? ",
                    style: TextStyle(
                        color: Colors.white,
                        fontSize: 20,
                        fontWeight: FontWeight.w400)),
                TextSpan(
                    text: "Login",
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
