import 'package:flutter/material.dart';

import '../utils/constant.dart';

class CantainerBox extends StatelessWidget {
  const CantainerBox({super.key, this.image, this.onpress});
  final String? image;
  final VoidCallback? onpress;

  @override
  Widget build(BuildContext context) {
    Size size = MediaQuery.of(context).size;
    return Container(
        height: size.height / 3,
        width: double.maxFinite,
        decoration: BoxDecoration(
          borderRadius: BorderRadius.circular(kdefaultPadding),
          gradient: LinearGradient(
              colors: [Color.fromARGB(150, 7, 119, 123), Colors.blueAccent]),
          boxShadow: [
            BoxShadow(
                color: Color.fromARGB(255, 2, 19, 47).withOpacity(0.22),
                offset: const Offset(0, 10),
                blurRadius: 5)
          ],
        ),
        child: GestureDetector(
          onTap: onpress,
          child: Container(
            margin: const EdgeInsets.all(ksmallSpacing / 2),
            decoration: BoxDecoration(
                borderRadius: BorderRadius.circular(kdefaultPadding),
                image: DecorationImage(
                    image: AssetImage(image!), fit: BoxFit.fill)),
          ),
        ));
  }
}
