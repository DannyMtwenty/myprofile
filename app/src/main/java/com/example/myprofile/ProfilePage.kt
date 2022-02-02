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
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.Dimension

import com.example.myprofile.ui.theme.MyProfileTheme

@Composable
fun  ProfilePage(){
    Card(modifier = Modifier
        .padding(top = 100.dp, bottom = 100.dp, start = 20.dp, end = 20.dp)
        .fillMaxSize()
        .border(width = 3.dp, color = Color.White, shape = RoundedCornerShape(30.dp)),
    elevation = 7.dp) //card shadow effect
    {
        //place constraint layout in boxlayoutwithconstraints
        BoxWithConstraints() {
            val constraints = if (minWidth < 600.dp) {
                portraitConstraints(16.dp)
            } else {
                portraitConstraints(16.dp)
            }


        //card content includiing profile,followere,post etc
        //constraint layout
        ConstraintLayout(constraints) {   //use declared constraint variables
            // Create references for the composables to constrain


            Image(
                painter = painterResource(id = R.drawable.cynthia), contentDescription = "The cat",
                modifier = Modifier
                    .size(150.dp)
                    .clip(CircleShape)
                    .border(width = 3.dp, color = Color.Red, shape = CircleShape)
                    .layoutId("image"),
                contentScale = ContentScale.Crop  //fill the img within border
            )

            Text(
                text = "Danny Wanyoike", fontWeight = FontWeight.Bold,modifier = Modifier.layoutId("username")
            )
            Text(text = "Kenya,Lvl 19",modifier = Modifier.layoutId("country"))

            Row(
                modifier = Modifier
                    .padding(top = 5.dp)
                    .fillMaxWidth()
                    .layoutId("rowstats"),

                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                ProfileStatistics(count = "12k", desc = "Followers")
                ProfileStatistics(count = "467", desc = "Following")
                ProfileStatistics(count = "253", desc = "Posts")

            }

            Button(onClick = { },modifier = Modifier.layoutId(
                "btnfollow")) {
                Text(text = "Follow")
            }
            Button(onClick = { },modifier = Modifier.layoutId("btnmsg")) {
                Text(text = "Message",)
            }


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

//define constrainsets,,for potrait and landscape

private fun portraitConstraints(margin:Dp) : androidx.constraintlayout.compose.ConstraintSet {
    return ConstraintSet{
        //constraint items
        val image=createRefFor("image")
        val username=createRefFor("username")
        val country=createRefFor("country")
        val rowstats=createRefFor("rowstats")
        val btnfollow=createRefFor("btnfollow")
        val btnmsg=createRefFor("btnmsg")
        
        
        constrain(image){
            top.linkTo(parent.top,margin = margin)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        }
        constrain(username){
            top.linkTo(image.bottom)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        }
        constrain(country){
            top.linkTo(username.bottom)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        }
        constrain(rowstats){
            top.linkTo(country.bottom)
        }
        constrain(btnfollow){
            top.linkTo(rowstats.bottom,margin = margin)
            start.linkTo(parent.start)
            end.linkTo(btnmsg.start)
            bottom.linkTo(parent.bottom,margin = margin)
            width= Dimension.wrapContent //btn as wide as content
        }
        constrain(btnmsg){
            top.linkTo(rowstats.bottom,margin =margin)
            start.linkTo(btnfollow.end)
            end.linkTo(parent.end)
            bottom.linkTo(parent.bottom,margin = margin)
            width= Dimension.wrapContent //btn as wide as content
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProfilePagePreview() {
    ProfilePage()
}