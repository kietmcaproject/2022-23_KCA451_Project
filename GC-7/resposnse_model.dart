import 'package:firebase_auth/firebase_auth.dart';

// Response Model for google signIn
class ResponseModel {
  User? user;
  String? error;

  ResponseModel({this.user, this.error});
}
