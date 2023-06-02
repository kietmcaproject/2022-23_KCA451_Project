import 'package:flutter/material.dart';

import '../utils/constant.dart';

class PhotoContainer extends StatelessWidget {
  const PhotoContainer({super.key,this.image});
  final String ?image;

  @override
  Widget build(BuildContext context) {
    return   Padding(
              padding: const EdgeInsets.symmetric(vertical: ksmallSpacing),
              child: Container(
                height:170 ,
                width: double.maxFinite,
                decoration:BoxDecoration(
                  color: Colors.amber,
                  borderRadius: BorderRadius.circular(knormalSpacing),
                  image:  DecorationImage(image: AssetImage(image!),
                  fit: BoxFit.fill),
                ) ,
              ),
            );
  }
}