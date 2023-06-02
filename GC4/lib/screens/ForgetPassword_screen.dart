import 'package:flash_chat/screens/login_screen.dart';
import 'package:flash_chat/screens/registration_screen.dart';
import 'package:flash_chat/utils/constant.dart';
import 'package:flutter/material.dart';

class ForgetPassword extends StatefulWidget {
  const ForgetPassword({super.key});

  @override
  State<ForgetPassword> createState() => _ForgetPasswordState();
}

class _ForgetPasswordState extends State<ForgetPassword> {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        leading: IconButton(onPressed: (){}, icon: const Icon(Icons.arrow_back)),
        title:const Text('Reset Password'),
      ),
      body: Form(child: Column(
        mainAxisAlignment: MainAxisAlignment.start,
        crossAxisAlignment: CrossAxisAlignment.start,
        children: [
         Padding(
           padding: const EdgeInsets.only(left: kdefaultPadding,top: kdefaultPadding),
           child: const  Text('Reset Link will send to your email id !',
           style: TextStyle(color: Colors.black87,
           fontSize: 20,fontWeight: FontWeight.w500),),
         ),
         Padding(padding: const EdgeInsets.all(kdefaultPadding),
         child:  TextFormField(
            controller: TextEditingController(),
            decoration: InputDecoration(
            labelText: 'enter your email',
              border: OutlineInputBorder(borderRadius:BorderRadius.circular(5) ),
              
            ),
          ),),
          Row(
            mainAxisAlignment: MainAxisAlignment.center,
            children: [
              ElevatedButton(style: ButtonStyle(backgroundColor: MaterialStatePropertyAll(Colors.blue)),
                onPressed: (){}, child:Padding(padding: EdgeInsets.all(ksmallSpacing),
                child:  Text('Send Email'),)),
              TextButton(onPressed: (){
                Navigator.pushAndRemoveUntil(
                  context,PageRouteBuilder(
                    pageBuilder: (context, animation, secondaryAnimation) 
                    => RegistrationScreen(),
                transitionDuration: const Duration(seconds:0 ),
                ),
                  (Route)=>false,
                );
              
              }, child: Text('Log in'))
            ],
          ),
          Row(
            mainAxisAlignment: MainAxisAlignment.center,
            children: [
                   Text("you don't have an account"),
                   SizedBox(width: ksmallSpacing,),
                   TextButton(onPressed: (){
                    Navigator.pushAndRemoveUntil(
                  context,PageRouteBuilder(
                    pageBuilder: (context, animation, secondaryAnimation) 
                    => LoginScreen(),
                transitionDuration: const Duration(seconds:0 ),
                ),
                  (Route)=>false,
                );
                   }, child: Text('sign up'))
            ],
          )
        ],
      ),)
    );
  }
}