package com.example.myprofile

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun Navigation(){
    val navController= rememberNavController() // central API for the Navigation component
    NavHost(navController = navController, startDestination = Screen.MainScreen.route ){

        //adding composables
        composable(route = Screen.MainScreen.route){
            ProfilePage(navController = navController)
        }
        composable(route = Screen.MessageScreen.route){
            messagePage()
        }
    }



    }

