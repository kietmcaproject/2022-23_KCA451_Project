import 'package:flutter/material.dart';

class activityheading extends StatelessWidget {
  const activityheading({
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
            'Prastuti 2023',
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
