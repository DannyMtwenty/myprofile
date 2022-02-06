package com.example.myprofile

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun messagePage(){
    var nameState by rememberSaveable() {
        mutableStateOf("")
    }
    var name by rememberSaveable() {   //rememberSaveable saves value when configuration changes e.g screen rotation
        mutableStateOf("")
    }

    Column(horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement= Arrangement.Center,
    modifier = Modifier
        .fillMaxSize()
        .padding(bottom = 20.dp)
        ) {
        Text(text =name, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(10.dp))
        TextField(value =nameState  , onValueChange = {
           nameState=it
        } ,label = { Text("Enter message") })

        Spacer(modifier = Modifier.height(15.dp))
        Button(onClick = { name =nameState }) {
         Text(text = "send")
        }

    }
}

@Preview(showBackground = true)
@Composable
fun MessagePagePreview() {
    messagePage()
}