package com.example.advance.view

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.example.advance.R
import com.example.advance.model.Activity
import com.example.advance.model.DummyActivity
import com.example.advance.navigation.Screen

@Composable
fun Home(
    navController: NavController,
    viewModel: ViewModel
) {
    val context = LocalContext.current
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
                backgroundColor = colorResource(id = R.color.green),
                onClick = {
                    Toast.makeText(context, "Navigating...", Toast.LENGTH_LONG).show()
                    navController.navigate(Screen.AdjustScreen.route)
                }) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "")
            }
        }
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            items(DummyActivity.activityList) { activity ->
                ActivityItem(actItem = activity) {

                }
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
                Text(text = actItem.name, fontWeight = FontWeight.ExtraBold)
                Text(text = actItem.description)
            }
        }
    }
}