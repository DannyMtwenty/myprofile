package com.example.myprofile

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.Dimension
import androidx.navigation.NavController
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


@Composable
fun ProfilePage(navController: NavController) {

    val scaffoldState = rememberScaffoldState() // this contains the `SnackbarHostState`
    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        modifier = Modifier,
        scaffoldState = scaffoldState // attaching `scaffoldState` to the `Scaffold`
    ) {

        Card(
            modifier = Modifier
                .padding(top = 100.dp, bottom = 100.dp, start = 20.dp, end = 20.dp)
                .fillMaxSize()
                .border(width = 3.dp, color = Color.White, shape = RoundedCornerShape(30.dp)),
            elevation = 7.dp //card shadow effect
        )
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
                        painter = painterResource(id = R.drawable.cynthia),
                        contentDescription = "danny",
                        modifier = Modifier
                            .size(150.dp)
                            .clip(CircleShape)
                            .border(width = 3.dp, color = Color.DarkGray, shape = CircleShape)
                            .layoutId("image"),
                        contentScale = ContentScale.Crop  //fill the img within border
                    )

                    Text(
                        text = "Danny Wanyoike",
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.layoutId("username")
                    )
                    Text(text = "Kenya,Lvl 19", modifier = Modifier.layoutId("country"))

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

                    Button(
                        onClick = {
                            showSnackbar(
                                coroutineScope = coroutineScope,
                                scaffoldState = scaffoldState,
                                message = "Followed!", caption = "Unfollow?"
                            )

                        }, modifier = Modifier.layoutId(
                            "btnfollow"
                        )
                    ) {
                        Text(text = "Follow")
                    }
                    Button(onClick = {
                        /*
                        showSnackbar(
                            coroutineScope = coroutineScope,
                            scaffoldState = scaffoldState,
                            message = "Send Message!",
                            caption = "Undo?"
                        ) */
                        navController.navigate(Screen.MessageScreen.route)
                    }, modifier = Modifier.layoutId("btnmsg")) {
                        Text(text = "Message")
                    }


                }
            }

        }

    }

}

fun showSnackbar(
    coroutineScope: CoroutineScope,
    scaffoldState: ScaffoldState,
    message: String,
    caption: String
) {
    coroutineScope.launch { // using the `coroutineScope` to `launch` showing the snackbar
        // taking the `snackbarHostState` from the attached `scaffoldState`
        val snackbarResult = scaffoldState.snackbarHostState.showSnackbar(
            message = message,
            actionLabel = caption
        )
        when (snackbarResult) {
            SnackbarResult.Dismissed -> Log.d("SnackbarDemo", "Dismissed")
            SnackbarResult.ActionPerformed -> Log.d("SnackbarDemo", "Snackbar's button clicked")
        }
    }
}

@Composable
fun ProfileStatistics(count: String, desc: String) {

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = count, fontWeight = FontWeight.Bold)
        Text(text = desc)

    }
}

//define constrainsets,,for potrait and landscape

private fun portraitConstraints(margin: Dp): androidx.constraintlayout.compose.ConstraintSet {
    return ConstraintSet {
        //constraint items
        val image = createRefFor("image")
        val username = createRefFor("username")
        val country = createRefFor("country")
        val rowstats = createRefFor("rowstats")
        val btnfollow = createRefFor("btnfollow")
        val btnmsg = createRefFor("btnmsg")


        constrain(image) {
            top.linkTo(parent.top, margin = margin)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        }
        constrain(username) {
            top.linkTo(image.bottom)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        }
        constrain(country) {
            top.linkTo(username.bottom)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        }
        constrain(rowstats) {
            top.linkTo(country.bottom)
        }
        constrain(btnfollow) {
            top.linkTo(rowstats.bottom, margin = margin)
            start.linkTo(parent.start)
            end.linkTo(btnmsg.start)
            bottom.linkTo(parent.bottom, margin = margin)
            width = Dimension.wrapContent //btn as wide as content
        }
        constrain(btnmsg) {
            top.linkTo(rowstats.bottom, margin = margin)
            start.linkTo(btnfollow.end)
            end.linkTo(parent.end)
            bottom.linkTo(parent.bottom, margin = margin)
            width = Dimension.wrapContent //btn as wide as content
        }
    }
}

//@Preview(showBackground = true)
//@Composable
//fun ProfilePagePreview() {
//    ProfilePage(navController = NavController)
//}