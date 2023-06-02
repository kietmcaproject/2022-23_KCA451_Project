import 'dart:math';
import 'package:carousel_slider/carousel_options.dart';
import 'package:carousel_slider/carousel_slider.dart';
import 'package:flash_chat/screens/frontscreen.dart';
import 'package:flash_chat/screens/screenclub.dart';
import 'package:flash_chat/screens/ProfileScreen.dart';
import 'package:flash_chat/screens/ActivityScreen.dart';
import 'package:flash_chat/screens/ChangePassword.dart';
import 'package:flash_chat/screens/Dashboard.dart';
import 'package:flash_chat/screens/EventScreen.dart';
import 'package:flash_chat/screens/signup.dart';
import 'package:flash_chat/screens/welcomescreen.dart';
import 'package:flash_chat/widgets/Profile.dart';
import 'package:flash_chat/screens/login_screen.dart';
import 'package:flash_chat/utils/constant.dart';
import 'package:flutter/material.dart';

import '../widgets/ContainerBox.dart';
import 'welcome_screen.dart';
import 'clubscreens.dart';

class UserMain extends StatefulWidget {
  const UserMain({super.key});

  @override
  State<UserMain> createState() => _UserMainState();
}

class _UserMainState extends State<UserMain> {
  double value = 0;
  double val = 0;

  @override
  Widget build(BuildContext context) {
    Size size = MediaQuery.of(context).size;
    return Scaffold(
      body: Stack(
        children: [
          Container(
            height: size.height,
            decoration: BoxDecoration(
                gradient: LinearGradient(colors: [
              Color.fromRGBO(23, 33, 42, 1),
              Color.fromARGB(255, 22, 141, 239)
            ], begin: Alignment.topCenter, end: Alignment.bottomCenter)),
          ),
          SafeArea(
              child: Container(
                  width: 200,
                  height: 500,
                  padding: const EdgeInsets.all(8),
                  child: GestureDetector(
                    onTap: () {
                      setState(() {
                        value == 0 ? value = 1 : value = 0;
                      });
                    },
                    child: Column(
                      children: [
                        DrawerHeader(
                            child: Column(
                          mainAxisAlignment: MainAxisAlignment.center,
                          children: [
                            CircleAvatar(
                              backgroundImage: AssetImage(
                                'Assets/images/profile.png',
                              ),
                              radius: 50,
                            ),
                            SizedBox(
                              height: ksmallSpacing,
                            ),
                            Text(
                              'USERNAME ',
                              style: TextStyle(
                                  color: Colors.white,
                                  fontSize: 18,
                                  fontWeight: FontWeight.w600),
                            ),
                          ],
                        )),
                        Expanded(
                            child: ListView(
                          scrollDirection: Axis.vertical,
                          children: [
                            GestureDetector(
                              onTap: () {
                                Navigator.push(context, MaterialPageRoute(
                                  builder: (context) {
                                    return ProfileScreen();
                                  },
                                ));
                              },
                              child: const ListTile(
                                leading: Icon(
                                  Icons.person,
                                  color: Colors.white,
                                ),
                                title: Text('My Profile',
                                    style: TextStyle(color: Colors.white)),
                              ),
                            ),
                            GestureDetector(
                              onTap: () {
                                Navigator.push(context, MaterialPageRoute(
                                  builder: (context) {
                                    return ClubScreen();
                                  },
                                ));
                              },
                              child: const ListTile(
                                leading: Icon(
                                  Icons.person_3_sharp,
                                  color: Colors.white,
                                ),
                                title: Text('Clubs',
                                    style: TextStyle(color: Colors.white)),
                              ),
                            ),
                            GestureDetector(
                              onTap: () {
                                Navigator.push(context, MaterialPageRoute(
                                  builder: (context) {
                                    return ActivityScreen();
                                  },
                                ));
                              },
                              child: const ListTile(
                                leading: Icon(
                                  Icons.local_activity_outlined,
                                  color: Colors.white,
                                ),
                                title: Text('Activity',
                                    style: TextStyle(color: Colors.white)),
                              ),
                            ),
                            GestureDetector(
                              onTap: () {
                                Navigator.push(context, MaterialPageRoute(
                                  builder: (context) {
                                    return EventScreen();
                                  },
                                ));
                              },
                              child: const ListTile(
                                leading: Icon(
                                  Icons.event_sharp,
                                  color: Colors.white,
                                ),
                                title: Text('Events',
                                    style: TextStyle(color: Colors.white)),
                              ),
                            ),
                            const ListTile(
                              leading: Icon(
                                Icons.settings,
                                color: Colors.white,
                              ),
                              title: Text('settings',
                                  style: TextStyle(color: Colors.white)),
                            ),
                            ListTile(
                              leading: const Icon(
                                Icons.exit_to_app_outlined,
                                color: Colors.white,
                              ),
                              title: const Text('Log out',
                                  style: TextStyle(color: Colors.white)),
                            ),
                          ],
                        ))
                      ],
                    ),
                  ))),
          TweenAnimationBuilder(
            tween: Tween(begin: 0, end: value),
            curve: Curves.easeIn,
            duration: const Duration(milliseconds: 500),
            builder: ((_, val, __) {
              return Transform(
                alignment: Alignment.center,
                transform: Matrix4.identity()
                  ..setEntry(3, 2, 0.001)
                  ..setEntry(0, 3, value * 200)
                  ..rotateY((pi / 6) * val),
                child: Scaffold(
                  appBar: AppBar(
                    leading: GestureDetector(
                      onTap: () {
                        setState(() {
                          value == 0 ? value = 1 : value = 0;
                        });
                      },
                      child: Icon(Icons.menu),
                    ),
                    title: Text('Welcome Student !'),
                    backgroundColor: Color.fromARGB(255, 8, 117, 185),
                    centerTitle: true,
                    elevation: 0,
                  ),
                  body: Column(
                    crossAxisAlignment: CrossAxisAlignment.start,
                    children: [
                      Container(
                        margin:
                            const EdgeInsets.only(left: 30, right: 20, top: 20),
                        child: Row(
                          children: const [],
                        ),
                      ),
                      SizedBox(
                          height: 250,
                          child: ListView(
                            children: <Widget>[
                              CarouselSlider(
                                items: [
                                  //1st Image of Slider
                                  Container(
                                    decoration: BoxDecoration(
                                        borderRadius: BorderRadius.circular(10),
                                        boxShadow: [
                                          BoxShadow(
                                            color: Colors.grey.withOpacity(
                                                0.2), //color of shadow
                                            spreadRadius: 3, //spread radius
                                            blurRadius: 7, // blur radius
                                            offset: Offset(2,
                                                2), // changes position of shadow
                                            //first paramerter of offset is left-right
                                            //second parameter is top to down
                                          ),
                                        ],
                                        color: Colors.white),
                                    //width: 200,
                                    padding: const EdgeInsets.all(5),
                                    margin: EdgeInsets.all(5),
                                    child: Column(
                                      children: <Widget>[
                                        Row(
                                          children: [
                                            Expanded(
                                              child: Column(
                                                crossAxisAlignment:
                                                    CrossAxisAlignment.start,
                                                children: <Widget>[
                                                  Image.asset(
                                                    'Assets/images/act1.jpg',
                                                    width: 250,
                                                    height: 195,
                                                  ),
                                                  const SizedBox(
                                                    height: 5,
                                                  ),
                                                ],
                                              ),
                                            ),
                                          ],
                                        ),
                                      ],
                                    ),
                                  ),

                                  //nextSlide
                                  Container(
                                    decoration: BoxDecoration(
                                        borderRadius: BorderRadius.circular(10),
                                        boxShadow: [
                                          BoxShadow(
                                            color: Colors.grey.withOpacity(
                                                0.2), //color of shadow
                                            spreadRadius: 3, //spread radius
                                            blurRadius: 7, // blur radius
                                            offset: Offset(2,
                                                2), // changes position of shadow
                                            //first paramerter of offset is left-right
                                            //second parameter is top to down
                                          ),
                                        ],
                                        color: Colors.white),
                                    //width: 200,
                                    padding: const EdgeInsets.all(5),
                                    margin: EdgeInsets.all(5),
                                    child: Column(
                                      children: <Widget>[
                                        Row(
                                          children: [
                                            Expanded(
                                              child: Column(
                                                crossAxisAlignment:
                                                    CrossAxisAlignment.start,
                                                children: <Widget>[
                                                  Image.asset(
                                                    'Assets/images/act1.jpg',
                                                    width: 250,
                                                    height: 195,
                                                  ),
                                                  const SizedBox(
                                                    height: 5,
                                                  ),
                                                ],
                                              ),
                                            ),
                                          ],
                                        ),
                                      ],
                                    ),
                                  ),
                                  //next slide
                                  Container(
                                    decoration: BoxDecoration(
                                        borderRadius: BorderRadius.circular(10),
                                        boxShadow: [
                                          BoxShadow(
                                            color: Colors.grey.withOpacity(
                                                0.2), //color of shadow
                                            spreadRadius: 3, //spread radius
                                            blurRadius: 7, // blur radius
                                            offset: Offset(2,
                                                2), // changes position of shadow
                                            //first paramerter of offset is left-right
                                            //second parameter is top to down
                                          ),
                                        ],
                                        color: Colors.white),
                                    //width: 200,
                                    padding: const EdgeInsets.all(5),
                                    margin: EdgeInsets.all(5),
                                    child: Column(
                                      children: <Widget>[
                                        Row(
                                          children: [
                                            Expanded(
                                              child: Column(
                                                crossAxisAlignment:
                                                    CrossAxisAlignment.start,
                                                children: <Widget>[
                                                  Image.asset(
                                                    'Assets/images/act1.jpg',
                                                    width: 250,
                                                    height: 195,
                                                  ),
                                                  const SizedBox(
                                                    height: 5,
                                                  ),
                                                ],
                                              ),
                                            ),
                                          ],
                                        ),
                                      ],
                                    ),
                                  ),
                                  //next Image of Slider
                                  Container(
                                    decoration: BoxDecoration(
                                        borderRadius: BorderRadius.circular(10),
                                        boxShadow: [
                                          BoxShadow(
                                            color: Colors.grey.withOpacity(
                                                0.2), //color of shadow
                                            spreadRadius: 3, //spread radius
                                            blurRadius: 7, // blur radius
                                            offset: Offset(2,
                                                2), // changes position of shadow
                                            //first paramerter of offset is left-right
                                            //second parameter is top to down
                                          ),
                                        ],
                                        color: Colors.white),
                                    //width: 200,
                                    padding: const EdgeInsets.all(5),
                                    margin: EdgeInsets.all(5),
                                    child: Column(
                                      children: <Widget>[
                                        Row(
                                          children: [
                                            Expanded(
                                              child: Column(
                                                crossAxisAlignment:
                                                    CrossAxisAlignment.start,
                                                children: <Widget>[
                                                  Image.asset(
                                                    'Assets/images/act1.jpg',
                                                    width: 250,
                                                    height: 195,
                                                  ),
                                                  const SizedBox(
                                                    height: 5,
                                                  ),
                                                ],
                                              ),
                                            ),
                                          ],
                                        ),
                                      ],
                                    ),
                                  ),
                                ],
                                //Slider Container properties
                                options: CarouselOptions(
                                  height: 320,
                                  //aspectRatio: 16 / 9,
                                  viewportFraction: 0.5,
                                  initialPage: 0,
                                  enableInfiniteScroll: true,
                                  reverse: false,
                                  autoPlay: true,
                                  autoPlayInterval: Duration(seconds: 3),
                                  autoPlayAnimationDuration:
                                      Duration(milliseconds: 800),
                                  autoPlayCurve: Curves.fastOutSlowIn,
                                  enlargeCenterPage: false,
                                  disableCenter: false,

                                  scrollDirection: Axis.horizontal,
                                ),
                              )
                            ],
                          )),
                      Padding(
                        padding: const EdgeInsets.only(bottom: 20, left: 10),
                        child: Text(
                          "Whats's New ?",
                          style: TextStyle(
                              fontWeight: FontWeight.bold, fontSize: 20),
                        ),
                      ),
                      SizedBox(
                          height: 320,
                          child: ListView(
                            children: <Widget>[
                              CarouselSlider(
                                items: [
                                  Container(
                                    decoration: BoxDecoration(
                                        borderRadius: BorderRadius.circular(10),
                                        boxShadow: [
                                          BoxShadow(
                                            color: Colors.grey.withOpacity(
                                                0.2), //color of shadow
                                            spreadRadius: 3, //spread radius
                                            blurRadius: 7, // blur radius
                                            offset: Offset(2,
                                                2), // changes position of shadow
                                            //first paramerter of offset is left-right
                                            //second parameter is top to down
                                          ),
                                        ],
                                        color: Colors.white),
                                    //width: 200,
                                    padding: const EdgeInsets.all(5),
                                    margin: EdgeInsets.all(5),
                                    child: Column(
                                      children: <Widget>[
                                        Row(
                                          children: [
                                            Text(
                                                "Lorem Ipsum is simply dummy text of the printing \n and typesetting industry.\n Lorem Ipsum has been the industry's\n standard dummy text ever since the 1500s, when an \nunknown printer took a galley of type and scrambled  ")
                                          ],
                                        ),
                                      ],
                                    ),
                                  ),

                                  //nextSlide
                                  Container(
                                    decoration: BoxDecoration(
                                        borderRadius: BorderRadius.circular(10),
                                        boxShadow: [
                                          BoxShadow(
                                            color: Colors.grey.withOpacity(
                                                0.2), //color of shadow
                                            spreadRadius: 3, //spread radius
                                            blurRadius: 7, // blur radius
                                            offset: Offset(2,
                                                2), // changes position of shadow
                                            //first paramerter of offset is left-right
                                            //second parameter is top to down
                                          ),
                                        ],
                                        color: Colors.white),
                                    //width: 200,
                                    padding: const EdgeInsets.all(5),
                                    margin: EdgeInsets.all(5),
                                    child: Column(
                                      children: <Widget>[
                                        Row(
                                          children: [
                                            Text(
                                                "Lorem Ipsum is simply dummy text of the printing \n and typesetting industry.\n Lorem Ipsum has been the industry's\n standard dummy text ever since the 1500s, when an \nunknown printer took a galley of type and scrambled  ")
                                          ],
                                        ),
                                      ],
                                    ),
                                  ),
                                  //next slide
                                  Container(
                                    decoration: BoxDecoration(
                                        borderRadius: BorderRadius.circular(10),
                                        boxShadow: [
                                          BoxShadow(
                                            color: Colors.grey.withOpacity(
                                                0.2), //color of shadow
                                            spreadRadius: 3, //spread radius
                                            blurRadius: 7, // blur radius
                                            offset: Offset(2,
                                                2), // changes position of shadow
                                            //first paramerter of offset is left-right
                                            //second parameter is top to down
                                          ),
                                        ],
                                        color: Colors.white),
                                    //width: 200,
                                    padding: const EdgeInsets.all(5),
                                    margin: EdgeInsets.all(5),
                                    child: Column(
                                      children: <Widget>[
                                        Row(
                                          children: [
                                            Text(
                                                "Lorem Ipsum is simply dummy text of the printing \n and typesetting industry.\n Lorem Ipsum has been the industry's\n standard dummy text ever since the 1500s, when an \nunknown printer took a galley of type and scrambled ")
                                          ],
                                        ),
                                      ],
                                    ),
                                  ),
                                  //next Image of Slider
                                  Container(
                                    decoration: BoxDecoration(
                                        borderRadius: BorderRadius.circular(10),
                                        boxShadow: [
                                          BoxShadow(
                                            color: Colors.grey.withOpacity(
                                                0.2), //color of shadow
                                            spreadRadius: 3, //spread radius
                                            blurRadius: 7, // blur radius
                                            offset: Offset(2,
                                                2), // changes position of shadow
                                            //first paramerter of offset is left-right
                                            //second parameter is top to down
                                          ),
                                        ],
                                        color: Colors.white),
                                    //width: 200,
                                    padding: const EdgeInsets.all(5),
                                    margin: EdgeInsets.all(5),
                                    child: Column(
                                      children: <Widget>[
                                        Row(
                                          children: [
                                            Text(
                                                "Lorem Ipsum is simply dummy text of the printing \n and typesetting industry.\n Lorem Ipsum has been the industry's\n standard dummy text ever since the 1500s, when an \nunknown printer took a galley of type and scrambled ")
                                          ],
                                        ),
                                      ],
                                    ),
                                  ),
                                ],
                                //Slider Container properties
                                options: CarouselOptions(
                                  height: 200,
                                  //aspectRatio: 16 / 9,
                                  viewportFraction: 0.5,
                                  initialPage: 0,
                                  enableInfiniteScroll: true,
                                  reverse: false,
                                  autoPlay: true,
                                  autoPlayInterval: Duration(seconds: 3),
                                  autoPlayAnimationDuration:
                                      Duration(milliseconds: 4000),
                                  autoPlayCurve: Curves.fastOutSlowIn,
                                  enlargeCenterPage: false,
                                  disableCenter: false,

                                  scrollDirection: Axis.vertical,
                                ),
                              )
                            ],
                          )),
                    ],
                  ),
                ),
              );
            }),
          ),
          // GestureDetector(

          //   // onHorizontalDragUpdate: (e){
          //   //   if(e.delta.dx>0){
          //   //     setState(() {
          //   //       value=1;
          //   //     });
          //   //   }
          //   //   else{
          //   //     setState(() {
          //   //       value=0;
          //   //     });
          //   //   }
          //   // },
          //      onTap: () {
          //        setState(() {
          //          value==0?value=1:value=0;
          //        });
          //      },
          // )
        ],
      ),
    );
  }
}


// Scaffold(
//                     appBar: AppBar(
//                       leading: GestureDetector(
//                         onTap: () {
//                           setState(() {
//                             value == 0 ? value = 1 : value = 0;
//                           });
//                         },
//                         child: Icon(Icons.menu),
//                       ),
//                       title: Text('Welcome Student !'),
//                       backgroundColor: Color.fromARGB(255, 8, 117, 185),
//                       centerTitle: true,
//                       elevation: 0,
//                     ),
//                     body: SingleChildScrollView(
//                       scrollDirection: Axis.vertical,
//                       child: Padding(
//                         padding: const EdgeInsets.all(ksmallSpacing),
//                         child: Column(
//                           crossAxisAlignment: CrossAxisAlignment.start,
//                           children: [
//                             const Text(
//                               'Activity',
//                               style: TextStyle(
//                                   color: Colors.blue,
//                                   fontSize: 25,
//                                   fontWeight: FontWeight.w400),
//                             ),
//                             const SizedBox(
//                               height: ksmallSpacing,
//                             ),
//                             CantainerBox(
//                               image: 'Assets/images/activity.jpeg',
//                               onpress: () {
//                                 Navigator.pushReplacement(context,
//                                     MaterialPageRoute(
//                                   builder: (context) {
//                                     return ActivityScreen();
//                                   },
//                                 ));
//                               },
//                             ),
//                             const SizedBox(height: knormalSpacing),
//                             const Text(
//                               'Club',
//                               style: TextStyle(
//                                   color: Colors.blueAccent,
//                                   fontSize: 25,
//                                   fontWeight: FontWeight.w400),
//                             ),
//                             const SizedBox(
//                               height: ksmallSpacing,
//                             ),
//                             CantainerBox(
//                               image: 'Assets/images/club.jpeg',
//                               onpress: () {
//                                 Navigator.push(context, MaterialPageRoute(
//                                   builder: (context) {
//                                     return ClubScreen();
//                                   },
//                                 ));
//                               },
//                             ),
//                             SizedBox(height: knormalSpacing),
//                             Text(
//                               'Event',
//                               style: TextStyle(
//                                   color: Colors.blue,
//                                   fontSize: 25,
//                                   fontWeight: FontWeight.w400),
//                             ),
//                             SizedBox(
//                               height: ksmallSpacing,
//                             ),
//                             CantainerBox(
//                               image: 'Assets/images/event.jpeg',
//                               onpress: () {
//                                 Navigator.push(context, MaterialPageRoute(
//                                   builder: (context) {
//                                     return EventScreen();
//                                   },
//                                 ));
//                               },
//                             ),
//                           ],
//                         ),
//                       ),
//                     )
//                     ),







