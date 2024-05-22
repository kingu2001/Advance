@file:OptIn(ExperimentalMaterialApi::class)

package com.example.advance.view

import android.widget.ProgressBar
import android.widget.Toast
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.DismissDirection
import androidx.compose.material.DismissState
import androidx.compose.material.DismissValue
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.FractionalThreshold
import androidx.compose.material.Icon
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.Scaffold
import androidx.compose.material.SwipeToDismiss
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.rememberDismissState
import androidx.compose.material.rememberSwipeableState
import androidx.compose.material.swipeable
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.advance.R
import com.example.advance.model.activity.Activity
import com.example.advance.navigation.Screen
import com.example.advance.viewModel.ActivityViewModel

@Composable
fun Home(
    navController: NavController,
    viewModel: ActivityViewModel
) {
    val context = LocalContext.current
    var progress by remember { mutableStateOf(0f) }
    var level by remember{mutableStateOf(1)}
    var tempLevel by remember{mutableStateOf(0)}
    var leveledUp by remember{ mutableStateOf(false)}


    Scaffold(
        topBar = {
            AppBarView(title = "A D V A N C E", {
                Toast.makeText(context, "Button clicked", Toast.LENGTH_LONG).show()
            })
        },
        floatingActionButton = {
            FloatingActionButton(
                modifier = Modifier.padding(all = 20.dp),
                contentColor = colorResource(id = R.color.white),
                backgroundColor = Color.Green,
                onClick = {
                    Toast.makeText(context, "Navigating...", Toast.LENGTH_LONG).show()
                    navController.navigate(Screen.AdjustScreen.route + "/0")
                }) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "")
            }
        }
    ) {

        /** Display user info. **/
        Text(modifier = Modifier.padding(start = 100.dp, top = 10.dp),
            text = "Kiet Tuan Nguyen",
            fontSize = 24.sp,
            color = colorResource(id = R.color.green))
        
        Box(
            modifier = Modifier
                .padding(top = 20.dp, start = 20.dp)
                .size(60.dp)
                .clip(CircleShape)
                .background(colorResource(id = R.color.green)),
            contentAlignment = Alignment.Center
        ) {

            Text(
                text = tempLevel.toString(),
                color = Color.White,
                fontWeight = FontWeight.ExtraBold
            )
        }

        /** Progress bar. **/
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .padding(50.dp)
        ) {
            LinearProgressIndicator(progress = progress, color = Color.Green)
            if (progress >= 1f) {
                Toast.makeText(context, "Congratulations. You have leveled up!", Toast.LENGTH_LONG).show()
                tempLevel += level
                leveledUp = true
                progress = 0f
            }
            else{
                tempLevel = tempLevel
                leveledUp = false
            }
        }


        val activityList = viewModel.getAllActivities.collectAsState(initial = listOf())
        LazyColumn(
            modifier = Modifier
                .padding(top = 150.dp)
                .fillMaxSize()
                .padding(it)
        ) {

            items(activityList.value, key = { activity -> activity.id }) { activity ->
                val dismissState = rememberDismissState(
                    confirmStateChange = {
                        if (it == DismissValue.DismissedToStart) {
                            viewModel.deleteActivity(activity)
                        } else if (it == DismissValue.DismissedToEnd) {
                            viewModel.doneActivity(activity)
                            progress = (progress + 0.5f).coerceAtMost(1f)
                        }
                        true
                    }
                )
                SwipeToDismiss(
                    state = dismissState,
                    background = {
                        val color by animateColorAsState(
                            when (dismissState.dismissDirection) {
                                DismissDirection.StartToEnd -> Color.Green
                                DismissDirection.EndToStart -> Color.Red
                                else -> Color.Transparent
                            }
                        )
                        val icon = when (dismissState.dismissDirection) {
                            DismissDirection.StartToEnd -> Icons.Default.Check
                            DismissDirection.EndToStart -> Icons.Default.Delete
                            else -> null
                        }

                        val alignment = when (dismissState.dismissDirection) {
                            DismissDirection.StartToEnd -> Alignment.CenterStart
                            DismissDirection.EndToStart -> Alignment.CenterEnd
                            else -> Alignment.CenterEnd
                        }


                        Box(
                            Modifier
                                .fillMaxSize()
                                .background(color)
                                .padding(horizontal = 20.dp),
                            contentAlignment = alignment
                        ) {
                            if (icon != null) {
                                Icon(
                                    icon,
                                    contentDescription =
                                    if (dismissState.dismissDirection == DismissDirection.StartToEnd)
                                        "Done activity"
                                    else "Delete activity",
                                    tint = Color.White
                                )
                            }
                        }
                    },
                    directions = setOf(DismissDirection.StartToEnd, DismissDirection.EndToStart),
                    dismissThresholds = { FractionalThreshold(0.25f) },
                    dismissContent = {
                        ActivityItem(actItem = activity) {
                            val id = activity.id
                            navController.navigate(Screen.AdjustScreen.route + "/$id")
                        }
                    }
                )

            }
        }
    }
}


@Composable
fun ActivityItem(
    actItem: Activity,
    onClick: () -> Unit
) {
    Box(
        contentAlignment = Alignment.Center
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    top = 8.dp,
                    start = 8.dp,
                    end = 8.dp,
                    bottom = 8.dp
                )
                .clickable {
                    onClick()
                },
            elevation = 10.dp,
            backgroundColor = Color.White,
            border = BorderStroke(
                width = 1.dp,
                color = Color.Green
            )
        ) {
            Column(
                modifier = Modifier
                    .padding(16.dp)
            ) {
                Text(text = actItem.title, fontWeight = FontWeight.ExtraBold)
                Text(text = actItem.description)
            }
        }
    }
}

