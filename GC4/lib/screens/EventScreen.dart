import 'package:flash_chat/utils/constant.dart';
import 'package:flash_chat/widgets/eventheading.dart';
import 'package:flutter/material.dart';

import '../widgets/PhotoContainer.dart';
import '../widgets/photoCollage.dart';

class EventScreen extends StatefulWidget {
  const EventScreen({super.key});

  @override
  State<EventScreen> createState() => _EventScreenState();
}

class _EventScreenState extends State<EventScreen> {
  @override
  Widget build(BuildContext context) {
    Size size = MediaQuery.of(context).size;
    return Scaffold(
      appBar: AppBar(
        elevation: 0,
        title: const Text(
          'Events',
          style: TextStyle(color: Colors.white),
        ),
        backgroundColor: Colors.blueAccent,
        centerTitle: true,
      ),
      body: Padding(
        padding: EdgeInsets.symmetric(horizontal: knormalSpacing + 5),
        child: SingleChildScrollView(
          child: Column(
            crossAxisAlignment: CrossAxisAlignment.start,
            children: const [
              Eventheading(),
              Padding(
                padding: EdgeInsets.symmetric(vertical: ksmallSpacing),
                child: PhotoCollage(
                  image1: 'Assets/images/even1.jpg',
                  image2: 'Assets/images/even2.jpg',
                  image3: 'Assets/images/event3.jpg',
                ),
              ),
              Eventheading(),
              PhotoContainer(
                image: 'Assets/images/even4.jpg',
              ),
              PhotoContainer(
                image: 'Assets/images/even5.jpg',
              ),
              Eventheading(),
              Padding(
                padding: EdgeInsets.symmetric(vertical: ksmallSpacing),
                child: PhotoCollage(
                  image1: 'Assets/images/even1.jpg',
                  image2: 'Assets/images/even2.jpg',
                  image3: 'Assets/images/event3.jpg',
                ),
              ),
              Eventheading(),
              PhotoContainer(
                image: 'Assets/images/even4.jpg',
              ),
              Eventheading(),
              PhotoContainer(
                image: 'Assets/images/even5.jpg',
              ),
              Eventheading(),
              Padding(
                padding: EdgeInsets.symmetric(vertical: ksmallSpacing),
                child: PhotoCollage(
                  image1: 'Assets/images/even6.jpg',
                  image2: 'Assets/images/even2.jpg',
                  image3: 'Assets/images/even1.jpg',
                ),
              ),
            ],
          ),
        ),
      ),
    );
  }
}
