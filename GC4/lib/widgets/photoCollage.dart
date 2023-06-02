import 'package:flutter/material.dart';

class PhotoCollage extends StatelessWidget {
  const PhotoCollage({super.key,this.image1,this.image2,this.image3});
  final String ?image1;
   final String ?image2;
   final String ?image3;
  


  @override
  Widget build(BuildContext context) {
     Size size=MediaQuery.of(context).size;
    return  Row(
    children: [
      Container(
        height: 170,
        width: size.width*3/4-40,

        decoration: BoxDecoration(
          borderRadius: BorderRadius.circular(20),
          image: DecorationImage(image: AssetImage(image1!),
          fit: BoxFit.cover)
        ),
      ),
     const SizedBox(width: 7,),
      Column(
        children: [
          Container(
        height: 82,
        width: size.width*1/4-20,

        decoration: BoxDecoration(
          borderRadius: BorderRadius.circular(20),
          image: DecorationImage(image: AssetImage(image2!),
          fit: BoxFit.cover),

        ),
      ),
     const SizedBox(height: 6,),
        Container(
        height: 82,
        width: size.width*1/4-20,

        decoration: BoxDecoration(
          borderRadius: BorderRadius.circular(20),
          image: DecorationImage(image: AssetImage(image3!),
          fit: BoxFit.cover),

        ),
      ),

        ],
      )
    ],
   )
         ;
  }
}

