import 'package:flutter/material.dart';
import 'package:pigeon_chat/authentications/authentication_check.dart';
import 'package:pigeon_chat/provider/all_users_provider.dart';
import 'package:pigeon_chat/provider/app_providers.dart';
import 'package:pigeon_chat/provider/firebase_provider.dart';
import 'package:pigeon_chat/provider/group_provider.dart';
import 'package:pigeon_chat/utils/firebase_support.dart';

import 'package:provider/provider.dart';
import 'utils/routes/on_generate_routes.dart';

Future<void> main() async {
  WidgetsFlutterBinding.ensureInitialized();
  await FirebaseSupport().init();

  runApp(const MyApp());
}

class MyApp extends StatelessWidget {
  const MyApp({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return MultiProvider(
        providers: [
          ChangeNotifierProvider(create: ((context) => AppProvider())),
          ChangeNotifierProvider(create: ((context) => FirebaseProvider())),
          ChangeNotifierProvider(create: ((context) => GroupProvider())),
          ChangeNotifierProvider(create: ((context) => AllUsersProvider())),
        ],
        child: MaterialApp(
            onGenerateRoute: Routes.generateRoute,
            debugShowCheckedModeBanner: false,
            home: AuthenticationCheck()));
  }
}
