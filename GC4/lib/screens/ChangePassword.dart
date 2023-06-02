import 'package:flash_chat/screens/login_screen.dart';
import 'package:flutter/material.dart';

import '../utils/constant.dart';

class ChangePassword extends StatefulWidget {
  const ChangePassword({super.key});

  @override
  State<ChangePassword> createState() => _ChangePasswordState();
}

class _ChangePasswordState extends State<ChangePassword> {
  TextEditingController changePasswordController = TextEditingController();
  final _formkey = GlobalKey<FormState>();
  @override
  void dispose() {
    super.dispose();
    changePasswordController.dispose();
  }

  void ChangePassword() {
    Navigator.pushReplacement(context, MaterialPageRoute(
      builder: (context) {
        return LoginScreen();
      },
    ));
  }

  String newPassword = '';
  @override
  Widget build(BuildContext context) {
    return Scaffold(
        body: Form(
      key: _formkey,
      child: Column(
        children: [
          Padding(
            padding: const EdgeInsets.all(kdefaultPadding),
            child: TextFormField(
              controller: changePasswordController,
              validator: (value) {
                if (value == null || value.isEmpty) {
                  return 'Enter New Password';
                }
                return null;
              },
              decoration: InputDecoration(
                labelText: 'enter new email',
                border:
                    OutlineInputBorder(borderRadius: BorderRadius.circular(5)),
              ),
            ),
          ),
          Padding(
            padding: const EdgeInsets.all(kdefaultPadding),
            child: Row(
              mainAxisAlignment: MainAxisAlignment.center,
              children: [
                TextButton(
                    style: const ButtonStyle(
                        backgroundColor: MaterialStatePropertyAll(Colors.blue)),
                    onPressed: () {
                      if (_formkey.currentState!.validate()) {
                        setState(() {
                          newPassword = changePasswordController.text;
                        });
                        ChangePassword();
                      }
                    },
                    child: const Text(
                      'Change Password',
                      style: TextStyle(color: Colors.white),
                    )),
              ],
            ),
          )
        ],
      ),
    ));
  }
}
