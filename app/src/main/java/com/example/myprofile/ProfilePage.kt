package com.example.myprofile

import android.widget.Toast
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.example.myprofile.ui.theme.MyProfileTheme

@Composable
fun  ProfilePage(){
    Card(modifier = Modifier
        .padding(top = 100.dp, bottom = 100.dp, start = 20.dp, end = 20.dp)
        .border(width = 3.dp, color = Color.White, shape = RoundedCornerShape(3.dp)),
    elevation = 7.dp) //card shadow effect
    {
         //card content includiing profile,followere,post etc
        //constraint layout
ConstraintLayout{
    // Create references for the composables to constrain
    val (image,username,country,rowstats,btnfollow,btnmsg)=createRefs()

    val guideLine=createGuidelineFromTop(0.1f)  //invisible line for positioning elements on screen

            Image(painter = painterResource(id = R.drawable.catq), contentDescription = "The cat",
                modifier =  Modifier
                    .size(150.dp)
                    .clip(CircleShape)
                    .border(width = 3.dp, color = Color.Red, shape = CircleShape)
                    .constrainAs(image){
                        top.linkTo(guideLine)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
                   ,
                contentScale= ContentScale.Crop  //fill the img within border
            )
            Text(text = "Danny Wanyoike" ,fontWeight = FontWeight.Bold,modifier = Modifier
                .constrainAs(username){
                    top.linkTo(image.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                })
            Text(text = "Kenya",modifier = Modifier
                .constrainAs(country){
                    top.linkTo(username.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                })

            Row(modifier = Modifier
                .padding(top = 5.dp)
                .fillMaxWidth()
                .constrainAs(rowstats) {
                    top.linkTo(country.bottom)
                }
                ,
                horizontalArrangement = Arrangement.SpaceEvenly){
                ProfileStatistics(count = "40", desc ="Followers" )
                ProfileStatistics(count ="467" , desc ="Following" )
                ProfileStatistics(count = "253", desc ="Posts" )

            }

                Button(onClick = { },modifier = Modifier
                    .constrainAs(btnfollow){
                        top.linkTo(rowstats.bottom,margin = 16.dp)
                        start.linkTo(parent.start)
                        end.linkTo(btnmsg.start)
                        width= Dimension.wrapContent //btn as wide as content

                    }) {
                    Text(text = "Follow user")
                }
                Button(onClick = { },modifier = Modifier
                    .constrainAs(btnmsg){
                        top.linkTo(rowstats.bottom,margin = 16.dp)
                        start.linkTo(btnfollow.end)
                        end.linkTo(parent.end)
                        width= Dimension.wrapContent //btn as wide as content
                    }) {
                    Text(text = "Direct message")
                }



        }

    }
}

@Composable
fun ProfileStatistics(count:String,desc:String) {

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = count,fontWeight = FontWeight.Bold)
        Text(text = desc)

    }
}

@Preview(showBackground = true)
@Composable
fun ProfilePagePreview() {
    ProfilePage()
}