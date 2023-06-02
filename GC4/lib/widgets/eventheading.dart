import 'package:flutter/material.dart';

class Eventheading extends StatelessWidget {
  const Eventheading({
    super.key,
  });

  @override
  Widget build(BuildContext context) {
    return Padding(
      padding: const EdgeInsets.only(top: 10),
      child: Column(
        crossAxisAlignment: CrossAxisAlignment.start,
        children: [
          Text(
            'Event 2023',
            style: TextStyle(
                color: Colors.black, fontSize: 24, fontWeight: FontWeight.w500),
          ),
          Text(
            'KIET Group of Institution',
            style: TextStyle(
                color: Colors.grey, fontSize: 12, fontWeight: FontWeight.w500),
          )
        ],
      ),
    );
  }
}
