// import 'dart:convert';

// import 'package:flash_chat/model/apimodel.dart';
// import 'package:flutter/material.dart';
// import 'package:http/http.dart' as http;

// class ClubScreens extends StatefulWidget {
//   const ClubScreens({super.key});

//   @override
//   State<ClubScreens> createState() => _ClubScreensState();
// }

// class _ClubScreensState extends State<ClubScreens> {
//   List<SampleApi> sampleApi = [];

//   @override
//   Widget build(BuildContext context) {
//     return Scaffold(
//         appBar: AppBar(title: const Center(child: Text("API TESTING"))),
//         body: FutureBuilder(
//             future: getData(),
//             builder: (context, snapshot) {
//               if (snapshot.hasData) {
//                 return ListView.builder(
//                     itemCount: sampleApi.length,
//                     itemBuilder: (context, index) {
//                       return Container(
//                         padding:
//                             EdgeInsets.symmetric(vertical: 10, horizontal: 10),
//                         margin: EdgeInsets.all(10),
//                         height: 140,
//                         width: double.maxFinite,
//                         color: Color.fromARGB(255, 129, 170, 243),
//                         child: Column(
//                           crossAxisAlignment: CrossAxisAlignment.start,
//                           mainAxisAlignment: MainAxisAlignment.spaceBetween,
//                           children: [
//                             Text(
//                               "Title : ${sampleApi[index].clubName}",
//                               style: TextStyle(
//                                 fontSize: 20,
//                                 fontWeight: FontWeight.bold,
//                               ),
//                             ),
//                             Text(
//                               "Faculty : ${sampleApi[index].facultyCoordinator}",
//                               style: TextStyle(
//                                 fontSize: 20,
//                                 fontWeight: FontWeight.bold,
//                               ),
//                             ),
//                             Text(
//                               "Details : ${sampleApi[index].details}",
//                               maxLines: 1,
//                               style: TextStyle(
//                                 fontSize: 20,
//                                 fontWeight: FontWeight.bold,
//                               ),
//                             ),
//                             Image.asset("Image : ${sampleApi[index].image}")
//                             // Text(
//                             //   "image : ${sampleApi[index].image}",
//                             //   maxLines: 1,
//                             //   style: TextStyle(
//                             //     fontSize: 20,
//                             //     fontWeight: FontWeight.bold,
//                             //   ),
//                             // ),
//                           ],
//                         ),
//                       );
//                     });
//               }
//               return Center(child: CircularProgressIndicator());
//             }));
//   }

//   Future<List<SampleApi>> getData() async {
//     final response = await http.get(
//         Uri.parse("http://event-management-api.us-e2.cloudhub.io/getEvent"));
//     var data = jsonDecode(response.body.toString());

//     if (response.statusCode == 200) {
//       for (Map<String, dynamic> index in data) {
//         sampleApi.add(SampleApi.fromJson(index));
//       }
//       return sampleApi;
//     } else {
//       return sampleApi;
//     }
//   }
// }
