import 'package:carousel_slider/carousel_slider.dart';
import 'package:flutter/material.dart';

class FrontScreen extends StatelessWidget {
  const FrontScreen({super.key});

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        automaticallyImplyLeading: false,
        title: Center(child: Text("Welcome Student")),
      ),
      body: Column(
        crossAxisAlignment: CrossAxisAlignment.start,
        children: [
          Container(
            margin: const EdgeInsets.only(left: 30, right: 20, top: 20),
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
                                color: Colors.grey
                                    .withOpacity(0.2), //color of shadow
                                spreadRadius: 3, //spread radius
                                blurRadius: 7, // blur radius
                                offset:
                                    Offset(2, 2), // changes position of shadow
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
                                color: Colors.grey
                                    .withOpacity(0.2), //color of shadow
                                spreadRadius: 3, //spread radius
                                blurRadius: 7, // blur radius
                                offset:
                                    Offset(2, 2), // changes position of shadow
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
                                color: Colors.grey
                                    .withOpacity(0.2), //color of shadow
                                spreadRadius: 3, //spread radius
                                blurRadius: 7, // blur radius
                                offset:
                                    Offset(2, 2), // changes position of shadow
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
                                color: Colors.grey
                                    .withOpacity(0.2), //color of shadow
                                spreadRadius: 3, //spread radius
                                blurRadius: 7, // blur radius
                                offset:
                                    Offset(2, 2), // changes position of shadow
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
                      autoPlayAnimationDuration: Duration(milliseconds: 800),
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
              style: TextStyle(fontWeight: FontWeight.bold, fontSize: 20),
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
                                color: Colors.grey
                                    .withOpacity(0.2), //color of shadow
                                spreadRadius: 3, //spread radius
                                blurRadius: 7, // blur radius
                                offset:
                                    Offset(2, 2), // changes position of shadow
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
                                color: Colors.grey
                                    .withOpacity(0.2), //color of shadow
                                spreadRadius: 3, //spread radius
                                blurRadius: 7, // blur radius
                                offset:
                                    Offset(2, 2), // changes position of shadow
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
                                color: Colors.grey
                                    .withOpacity(0.2), //color of shadow
                                spreadRadius: 3, //spread radius
                                blurRadius: 7, // blur radius
                                offset:
                                    Offset(2, 2), // changes position of shadow
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
                                color: Colors.grey
                                    .withOpacity(0.2), //color of shadow
                                spreadRadius: 3, //spread radius
                                blurRadius: 7, // blur radius
                                offset:
                                    Offset(2, 2), // changes position of shadow
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
                      autoPlayAnimationDuration: Duration(milliseconds: 4000),
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
    );
  }
}
