package com.example.myprofile

sealed class BottomNavItem(var route: String, var icon: Int, var title: String) {
    object Home : BottomNavItem("home", R.drawable.ic_home_white_24dp, "Home")
    object Music : BottomNavItem("music", R.drawable.ic_audiotrack_white_24dp, "Music")
    object Movies : BottomNavItem("movies", R.drawable.ic_tv_white_24dp, "Movies")
    object Books : BottomNavItem("books", R.drawable.ic_assignment_white_24dp, "Books")
    object Profile : BottomNavItem("profile", R.drawable.ic_profile, "Profile")
}
