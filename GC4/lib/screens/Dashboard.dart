import 'package:flutter/material.dart';

class Dashboard extends StatefulWidget {
  const Dashboard({super.key});

  @override
  State<Dashboard> createState() => _DashboardState();
}

class _DashboardState extends State<Dashboard> {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body:Container(child: Column(
        mainAxisAlignment: MainAxisAlignment.center,
        crossAxisAlignment: CrossAxisAlignment.center,
        children: [
          Text('User Id'),
          SizedBox(height: 20,),
          Row(mainAxisAlignment: MainAxisAlignment.center,
            children: [
                        Text('Email id'),
                        SizedBox(width: 5,),
                        TextButton(onPressed: (){}, child: Text('Verified'))
          ],),
            SizedBox(height: 20,),

            Text('Login date'),
         
        ],
      )),
    );
  }
}