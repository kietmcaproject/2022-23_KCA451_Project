import 'dart:developer';

import 'package:cloud_firestore/cloud_firestore.dart';
import 'package:firebase_auth/firebase_auth.dart';
import 'package:flutter/material.dart';
import 'package:google_sign_in/google_sign_in.dart';
import 'package:pigeon_chat/model/resposnse_model.dart';
import 'package:pigeon_chat/provider/app_providers.dart';
import 'package:pigeon_chat/utils/app_strings/app_strings.dart';
import 'package:provider/provider.dart';

// Account create Function
Future<User> createAccount(String email, String name, String password) async {
  FirebaseAuth auth = FirebaseAuth.instance;
  FirebaseFirestore firestore = FirebaseFirestore.instance;

  try {
    UserCredential userCredential = await auth.createUserWithEmailAndPassword(
      email: email,
      password: password,
    );
    if (userCredential.user != null) {
      log(AppStrings.accountCreatedSuccesfully);

      userCredential.user!.updateDisplayName(name);
      await firestore
          .collection(AppStrings.usersKey)
          .doc(auth.currentUser?.uid)
          .set(
        {
          AppStrings.namekey: name,
          AppStrings.emailkey: email,
          AppStrings.uidKey: auth.currentUser!.uid,
          AppStrings.imageKey: AppStrings.placeHolderLink
        },
      );
      return userCredential.user!;
    } else {
      log(AppStrings.accountCreatinFailed);
      return userCredential.user!;
    }
  } catch (e) {
    throw (e.toString());
  }
}

Future<User> logIn(BuildContext context, String email, String password) async {
  FirebaseAuth auth = FirebaseAuth.instance;
  try {
    User? user = (await auth.signInWithEmailAndPassword(
            email: email, password: password))
        .user;
    if (user != null) {
      log(AppStrings.loginSuccessfully);
      return user;
    } else {
      log(AppStrings.loginFailed);
    }
    return user!;
  } catch (e) {
    Provider.of<AppProvider>(context, listen: false).setLoaderValue(false);
    ScaffoldMessenger.of(context).showSnackBar(SnackBar(
      duration: const Duration(seconds: 2),
      content: Text(AppStrings.invalidcredetial),
    ));
  }

  throw ("");
}
//Account LogOut Function

Future<void> logOut() async {
  FirebaseAuth auth = FirebaseAuth.instance;
  try {
    await auth.signOut();
  } catch (e) {
    throw (AppStrings.blankString);
  }
}

// Profile Picture Update Function

Future<User> updateProfilePicture(String imageUrl) async {
  FirebaseAuth auth = FirebaseAuth.instance;
  FirebaseFirestore firestore = FirebaseFirestore.instance;
  final User user = auth.currentUser!;

  // ignore: unnecessary_null_comparison
  if (user != null) {
    log(AppStrings.blankString);

    user.updatePhotoURL(imageUrl);
    await firestore
        .collection(AppStrings.usersKey)
        .doc(auth.currentUser?.uid)
        .update({
      AppStrings.uidKey: auth.currentUser!.uid,
      AppStrings.imageKey: imageUrl,
    }).whenComplete(() {
      log(AppStrings.profilePictureUpdateSuccesfully);
    });
    return user;
  } else {
    log(AppStrings.uploadFailed);
    return user;
  }
}

// Google Sign In function

Future<ResponseModel> signInWithGoogle() async {
  FirebaseAuth auth = FirebaseAuth.instance;
  FirebaseFirestore firestore = FirebaseFirestore.instance;
  GoogleSignIn googleSignIn = GoogleSignIn();

  try {
    GoogleSignInAccount? googleAccount = await googleSignIn.signIn();
    if (googleAccount == null) {
      return ResponseModel();
    }

    AuthCredential credential = GoogleAuthProvider.credential(
      idToken: (await googleAccount.authentication).idToken,
      accessToken: (await googleAccount.authentication).accessToken,
    );
    UserCredential userCredential = await auth.signInWithCredential(credential);
    await firestore
        .collection(AppStrings.usersKey)
        .doc(auth.currentUser!.uid)
        .set({
      AppStrings.namekey: userCredential.user!.displayName,
      AppStrings.emailkey: userCredential.user!.email,
      AppStrings.uidKey: auth.currentUser!.uid,
      AppStrings.imageKey: userCredential.user!.photoURL!,
    });
    return ResponseModel(user: userCredential.user);
  } catch (e) {
    return Future.error(
      ResponseModel(
        error: AppStrings.unknownError,
      ),
    );
  }
}
