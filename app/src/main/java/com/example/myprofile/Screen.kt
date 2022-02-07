package com.example.myprofile

sealed class  Screen (var route:String) {

  //screens to show  ,,define objects
  object MainScreen : Screen("profile_page")
  object MessageScreen : Screen("message_page")

}