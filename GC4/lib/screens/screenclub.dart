import 'package:flutter/material.dart';

class ClubScreen extends StatefulWidget {
  const ClubScreen({super.key});

  @override
  State<ClubScreen> createState() => _ClubScreenState();
}

class _ClubScreenState extends State<ClubScreen> {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: SafeArea(
        child: SingleChildScrollView(
          scrollDirection: Axis.vertical,
          child: Column(
            children: [
              Container(
                padding: EdgeInsets.all(10),
                height: 90,
                width: double.maxFinite,
                margin: EdgeInsets.only(bottom: 10),
                decoration: BoxDecoration(
                    color: Color.fromARGB(255, 7, 24, 54),
                    borderRadius: BorderRadius.only(
                        bottomRight: Radius.circular(20),
                        bottomLeft: Radius.circular(20))),
                child: Column(
                  mainAxisAlignment: MainAxisAlignment.spaceBetween,
                  children: [
                    Row(
                      mainAxisAlignment: MainAxisAlignment.end,
                      children: [
                        Icon(Icons.notifications,
                            color: Color.fromARGB(255, 204, 232, 246)),
                        SizedBox(
                          width: 25,
                        ),
                        Icon(
                          Icons.search,
                          color: Color.fromARGB(255, 204, 232, 246),
                        )
                      ],
                    ),
                    Text(
                      "KIET-CLUB",
                      style: TextStyle(
                          fontSize: 28,
                          color: Color.fromARGB(255, 204, 232, 246),
                          fontWeight: FontWeight.w500),
                    )
                  ],
                ),
              ),
              ContainerBox(
                club: 'Innogeeks',
                description: 'Innogeeks is an appreciable initiative.',
                image: 'Assets/images/club1.jpg',
                location: 'E-Block – IT Department, E-311 & E-401',
                onpress: () {
                  Navigator.push(context, MaterialPageRoute(
                    builder: (context) {
                      return ClubContainerScreen();
                    },
                  ));
                },
              ),
              ContainerBox(
                club: 'Innogeeks',
                description: 'Innogeeks is an appreciable initiative.',
                image: 'Assets/images/club2.jpg',
                location: 'E-Block – IT Department, E-311 & E-401',
                onpress: () {
                  Navigator.push(context, MaterialPageRoute(
                    builder: (context) {
                      return ClubContainerScreen();
                    },
                  ));
                },
              ),
              ContainerBox(
                club: 'Innogeeks',
                description: 'Innogeeks is an appreciable initiative.',
                image: 'Assets/images/club3.jpg',
                location: 'E-Block – IT Department, E-311 & E-401',
                onpress: () {
                  Navigator.push(context, MaterialPageRoute(
                    builder: (context) {
                      return ClubContainerScreen();
                    },
                  ));
                },
              ),
              ContainerBox(
                club: 'Innogeeks',
                description: 'Innogeeks is an appreciable initiative.',
                image: 'Assets/images/club4.jpg',
                location: 'E-Block – IT Department, E-311 & E-401',
                onpress: () {
                  Navigator.push(context, MaterialPageRoute(
                    builder: (context) {
                      return ClubContainerScreen();
                    },
                  ));
                },
              ),
              ContainerBox(
                club: 'Innogeeks',
                description: 'Innogeeks is an appreciable initiative.',
                image: 'Assets/images/club5.jpg',
                location: 'E-Block – IT Department, E-311 & E-401',
                onpress: () {
                  Navigator.push(context, MaterialPageRoute(
                    builder: (context) {
                      return ClubContainerScreen();
                    },
                  ));
                },
              ),
              ContainerBox(
                club: 'Innogeeks',
                description: 'Innogeeks is an appreciable initiative.',
                image: 'Assets/images/club5.jpg',
                location: 'E-Block – IT Department, E-311 & E-401',
                onpress: () {
                  Navigator.push(context, MaterialPageRoute(
                    builder: (context) {
                      return ClubContainerScreen();
                    },
                  ));
                },
              ),
              ContainerBox(
                club: 'Innogeeks',
                description: 'Innogeeks is an appreciable initiative.',
                image: 'Assets/images/club6.jpg',
                location: 'E-Block – IT Department, E-311 & E-401',
                onpress: () {
                  Navigator.push(context, MaterialPageRoute(
                    builder: (context) {
                      return ClubContainerScreen();
                    },
                  ));
                },
              ),
              ContainerBox(
                club: 'Innogeeks',
                description: 'Innogeeks is an appreciable initiative.',
                image: 'Assets/images/club7.jpg',
                location: 'E-Block – IT Department, E-311 & E-401',
                onpress: () {
                  Navigator.push(context, MaterialPageRoute(
                    builder: (context) {
                      return const ClubContainerScreen();
                    },
                  ));
                },
              ),
              ContainerBox(
                club: 'Innogeeks',
                description: 'Innogeeks is an appreciable initiative.',
                image: 'Assets/images/club8.jpg',
                location: 'E-Block – IT Department, E-311 & E-401',
                onpress: () {
                  Navigator.push(context, MaterialPageRoute(
                    builder: (context) {
                      return const ClubContainerScreen();
                    },
                  ));
                },
              ),
              ContainerBox(
                club: 'Innogeeks',
                description: 'Innogeeks is an appreciable initiative.',
                image: 'Assets/images/club9.jpg',
                location: 'E-Block – IT Department, E-311 & E-401',
                onpress: () {
                  Navigator.push(context, MaterialPageRoute(
                    builder: (context) {
                      return ClubContainerScreen();
                    },
                  ));
                },
              ),
              ContainerBox(
                club: 'Innogeeks',
                description: 'Innogeeks is an appreciable initiative.',
                image: 'Assets/images/club10.jpg',
                location: 'E-Block – IT Department, E-311 & E-401',
                onpress: () {
                  Navigator.push(context, MaterialPageRoute(
                    builder: (context) {
                      return ClubContainerScreen();
                    },
                  ));
                },
              )
            ],
          ),
        ),
      ),
    );
  }
}

class ContainerBox extends StatelessWidget {
  ContainerBox(
      {super.key,
      this.club,
      this.description,
      this.image,
      this.location,
      this.onpress});
  final String? image;
  final String? club;
  final String? description;
  final String? location;
  final VoidCallback? onpress;

  @override
  Widget build(BuildContext context) {
    return GestureDetector(
      onTap: onpress,
      child: Container(
        margin: EdgeInsets.only(bottom: 10, left: 10, right: 10),
        padding: EdgeInsets.all(6),
        decoration: BoxDecoration(
            borderRadius: BorderRadius.circular(12),
            color: Colors.grey.withOpacity(0.2)),
        child: Row(
          children: [
            Container(
              height: 150,
              width: 130,
              decoration: BoxDecoration(
                  borderRadius: BorderRadius.circular(12),
                  image: DecorationImage(
                      image: AssetImage(image!), fit: BoxFit.fill)),
            ),
            SizedBox(
              width: 20,
            ),
            Expanded(
                child: Column(
              mainAxisAlignment: MainAxisAlignment.spaceBetween,
              crossAxisAlignment: CrossAxisAlignment.start,
              children: [
                Text(
                  club!,
                  style: TextStyle(
                      fontSize: 22,
                      fontWeight: FontWeight.bold,
                      color: Color.fromARGB(255, 19, 14, 47)),
                ),
                Padding(
                  padding: EdgeInsets.symmetric(vertical: 6),
                  child: Text(
                    description!,
                    style: TextStyle(
                        fontSize: 16,
                        fontWeight: FontWeight.w500,
                        color: Color.fromARGB(255, 19, 14, 47)),
                  ),
                ),
                Text(
                  location!,
                  style: TextStyle(
                      fontSize: 12, color: Color.fromARGB(255, 93, 91, 91)),
                )
              ],
            ))
          ],
        ),
      ),
    );
  }
}

class ClubContainerScreen extends StatefulWidget {
  const ClubContainerScreen({super.key});

  @override
  State<ClubContainerScreen> createState() => _ClubContainerScreenState();
}

class _ClubContainerScreenState extends State<ClubContainerScreen> {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
        body: Container(
      margin: EdgeInsets.all(8),
      decoration: BoxDecoration(
          borderRadius: BorderRadius.circular(12),
          color: Colors.grey.withOpacity(0.2)),
      child: Column(
        crossAxisAlignment: CrossAxisAlignment.start,
        children: [
          Container(
            margin: EdgeInsets.all(8),
            height: 180,
            decoration: BoxDecoration(
                borderRadius: BorderRadius.circular(12),
                image: DecorationImage(
                    image: AssetImage('Assets/images/even1.jpg'),
                    fit: BoxFit.fill)),
          ),
          Container(
            padding: EdgeInsets.all(12),
            margin: EdgeInsets.only(left: 10, right: 10, bottom: 10),
            width: double.maxFinite,
            decoration: BoxDecoration(
                borderRadius: BorderRadius.circular(12), color: Colors.white),
            child: Column(
              mainAxisAlignment: MainAxisAlignment.spaceBetween,
              crossAxisAlignment: CrossAxisAlignment.start,
              children: [
                Text(
                  'Innogeeks',
                  style: TextStyle(
                      fontSize: 22,
                      fontWeight: FontWeight.bold,
                      color: Color.fromARGB(255, 19, 14, 47)),
                ),
                Padding(
                  padding: EdgeInsets.symmetric(vertical: 6),
                  child: Text(
                    'Innogeeks is an appreciable initiative.',
                    style: TextStyle(
                        fontSize: 16,
                        fontWeight: FontWeight.w500,
                        color: Color.fromARGB(255, 19, 14, 47)),
                  ),
                ),
                Text(
                  'E-Block – IT Department, E-311 & E-401',
                  style: TextStyle(
                      fontSize: 12, color: Color.fromARGB(255, 93, 91, 91)),
                ),
              ],
            ),
          ),
          Padding(
            padding: const EdgeInsets.all(10.0),
            child: Row(
              children: [
                CircleAvatar(
                  backgroundColor: Colors.grey.withOpacity(0.5),
                  child: Icon(
                    Icons.phone,
                    color: Color.fromARGB(255, 52, 50, 50),
                  ),
                ),
                SizedBox(
                  width: 10,
                ),
                CircleAvatar(
                  backgroundColor: Colors.grey.withOpacity(0.5),
                  child: Icon(
                    Icons.email,
                    color: Color.fromARGB(255, 52, 50, 50),
                  ),
                ),
                SizedBox(
                  width: 10,
                ),
                CircleAvatar(
                  backgroundColor: Colors.grey.withOpacity(0.5),
                  child: Icon(
                    Icons.person,
                    color: Color.fromARGB(255, 52, 50, 50),
                  ),
                )
              ],
            ),
          ),
          Padding(
            padding: const EdgeInsets.symmetric(vertical: 10),
            child: Divider(
              height: 1,
              color: Color.fromARGB(255, 223, 212, 212),
            ),
          ),
          Padding(
            padding: const EdgeInsets.only(left: 10, bottom: 10),
            child: Text(
              'Information:-',
              style: TextStyle(
                  fontSize: 19,
                  fontWeight: FontWeight.bold,
                  color: Color.fromARGB(255, 19, 14, 47)),
            ),
          ),
          Expanded(
              child: Column(
            children: [
              Container(
                width: double.maxFinite,
                margin: EdgeInsets.only(left: 10, right: 10, bottom: 16),
                child: Text(
                  'we are proud to share that the members of INNOGEEKS club have participated and won in various National and International Level Hackathons & Competitions, including Smart India Hackathon, NASA International Space Apps Challenge, ACM ICPC, Virtual Hacks, Snapchat India Lensathons, and many more. Given below the links to club’s various achievements. ',
                  style: TextStyle(
                      fontSize: 16,
                      fontWeight: FontWeight.w500,
                      color: Color.fromARGB(255, 99, 99, 99)),
                ),
              ),
              Container(
                width: double.maxFinite,
                margin: EdgeInsets.only(left: 10, right: 10, bottom: 16),
                child: Text(
                  "Innogeeks has started launching its yearly Technical E-Magazine, Bits N’ Bytes from 2020, Here is the link to latest Editions:Edition 1: http://online.anyflip.com/oalvj/keql/mobile/index.html Edition 2: https://drive.google.com/file/d/1OLmaneCj6uqV52eND2pku1KDfiTbalcD/view?usp=drivesdk",
                  style: TextStyle(
                      fontSize: 16,
                      fontWeight: FontWeight.w500,
                      color: Color.fromARGB(255, 99, 99, 99)),
                ),
              ),
              // Container(
              //   width: double.maxFinite,
              //   margin: EdgeInsets.only(left: 10, right: 10, bottom: 16),
              //   child: Text(
              //     "Core Leads: Rohan Khurana(CSE), Kritika Singh(EIE), Uddeshya(CS), Naman Mehrotra(CSIT), Vedansh Kumar Gupta(IT), Anushka Sharma(ECE), Sakshmika Aggarwal(ECE), Harsh Gupta(CS), Prakhar Yadav(ECE), Ravi Kumar Vishwakarma(ECE), Shreya Asthana(IT), and Muskaan Mittal(ECE) Core Team: Aryan Tanwar(IT), Vanshika Singhal(IT), Tushar Gupta(CSIT), Chinmoy Chakraborty(IT), Shreyansh Tripathi(ECE), Vanshika Namdev(CS), Sakshi Singh(CSIT), Yashashvi Singh Bhaduria(CSIT), Deepak Chaturvedi(CSE), Kartik Gupta(CS), Vivek Kumar(IT), Ayushi Tyagi(CS), Vishwaas Saxena(CSE), and Kushagra Goel(CSE).",
              //     style: TextStyle(
              //         fontSize: 16,
              //         fontWeight: FontWeight.w500,
              //         color: Color.fromARGB(255, 99, 99, 99)),
              //   ),
              // )
            ],
          ))
        ],
      ),
    ));
  }
}
