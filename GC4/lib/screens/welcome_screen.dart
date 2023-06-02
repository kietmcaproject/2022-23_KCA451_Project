// import 'package:flash_chat/screens/login_screen.dart';
// import 'package:flash_chat/screens/registration_screen.dart';
// import 'package:flash_chat/utils/colors.dart';
// import 'package:flash_chat/utils/constant.dart';
// import 'package:flutter/material.dart';
// import 'package:animated_text_kit/animated_text_kit.dart';

// import '../widgets/ButtonWidget.dart';

// class WelcomeScreen extends StatefulWidget {
//   static const String id = 'welcome_screen';

//   const WelcomeScreen({super.key});

//   @override
//   State<WelcomeScreen> createState() => _WelcomeScreenState();
// }

// class _WelcomeScreenState extends State<WelcomeScreen>
//     with SingleTickerProviderStateMixin {
//   @override
//   void dispose() {
//     super.dispose();
//   }

//   @override
//   Widget build(BuildContext context) {
//     return Scaffold(
//       backgroundColor: Color.fromARGB(255, 255, 255, 255),
//       body: Padding(
//         padding: EdgeInsets.symmetric(horizontal: kdefaultPadding),
//         child: Column(
//           crossAxisAlignment: CrossAxisAlignment.stretch,
//           mainAxisAlignment: MainAxisAlignment.center,
//           children: [
//             Row(
//               mainAxisAlignment: MainAxisAlignment.center,
//               children: [
//                 Hero(
//                   tag: 'logo',
//                   child: Icon(Icons.flash_on, color: flashColor, size: 40),
//                 ),
//                 SizedBox(
//                   width: 12,
//                 ),
//                 AnimatedTextKit(animatedTexts: [
//                   TypewriterAnimatedText(
//                     'Kiet Event',
//                     textStyle: TextStyle(
//                         fontSize: 30,
//                         fontWeight: FontWeight.w700,
//                         color: Colors.black54),
//                   )
//                 ]),
//               ],
//             ),
//             SizedBox(
//               height: kdefaultPadding + ksmallSpacing,
//             ),
//             ButtonWidget(
//               onpress: () {
//                 Navigator.pushNamed(context, LoginScreen.id);
//               },
//               text: 'log in',
//               color: Colors.lightBlue,
//             ),
//             SizedBox(
//               height: kdefaultPadding,
//             ),
//             ButtonWidget(
//               onpress: () {
//                 Navigator.pushNamed(context, RegistrationScreen.id);
//               },
//               text: 'registration',
//               color: Colors.blueAccent,
//             )
//           ],
//         ),
//       ),
//     );
//   }
// }
