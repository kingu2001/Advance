@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.advance.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.advance.R
import com.example.advance.model.activity.Activity
import com.example.advance.viewModel.ActivityViewModel
import kotlinx.coroutines.launch

@Composable
fun Adjust(
    id: Int,
    viewModel: ActivityViewModel,
    navController: NavController
) {
    val snackMessage = remember {
        mutableStateOf("")
    }

    val scope = rememberCoroutineScope()
    val scaffoldState = rememberScaffoldState()
    if (id != 0) {
        val activity = viewModel.getActivityById(id).collectAsState(initial = Activity(0, "", ""))
        viewModel.activityTitleState = activity.value.title
        viewModel.activityDescriptionState = activity.value.description
    } else {
        viewModel.activityTitleState = ""
        viewModel.activityDescriptionState = ""
    }

    Scaffold(
        topBar = {
            AppBarView(
                title =
                if (id != 0) {
                    stringResource(id = R.string.update_activity)
                } else {
                    stringResource(id = R.string.add_activity)
                }
            ) { navController.navigateUp() }
        }, scaffoldState = scaffoldState

    ) {
        Column(
            modifier = Modifier
                .padding(it)
                .wrapContentSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Spacer(modifier = Modifier.height(10.dp))

            ActivityTextField(label = "Title",
                value = viewModel.activityTitleState,
                onValueChanged = { viewModel.onActivityTitleChanged(it) }
            )

            Spacer(modifier = Modifier.height(10.dp))

            ActivityTextField(label = "Description",
                value = viewModel.activityDescriptionState,
                onValueChanged = { viewModel.onActivityDescriptionChanged(it) }
            )

            Button(modifier = Modifier
                .padding(10.dp)
                .clip(CircleShape),
                colors = ButtonDefaults.buttonColors(Color.Green),
                onClick = {
                    if (viewModel.activityTitleState.isNotEmpty() &&
                        viewModel.activityDescriptionState.isNotEmpty()
                    ) {
                        if (id != 0) {
                            viewModel.updateActivity(
                                Activity(
                                    id = id,
                                    title = viewModel.activityTitleState.trim(),
                                    description = viewModel.activityDescriptionState.trim()
                                )
                            )
                        } else {
                            viewModel.addActivity(
                                Activity(
                                    title = viewModel.activityTitleState.trim(),
                                    description = viewModel.activityDescriptionState.trim()
                                )
                            )
                            snackMessage.value = "Activity has been created!"
                        }
                    } else {
                        snackMessage.value = "Enter fields to create an activity."
                    }
                    scope.launch {
                        scaffoldState.snackbarHostState.showSnackbar(snackMessage.value)
                        navController.navigateUp()
                    }

                }) {
                Text(
                    text =
                    if (id != 0) {
                        stringResource(id = R.string.update_activity)
                    } else {
                        stringResource(id = R.string.add_activity)
                    },
                    style = TextStyle(fontSize = 18.sp),
                    color = Color.White
                )
            }
        }
    }
}

@Composable
fun ActivityTextField(
    label: String,
    value: String,
    onValueChanged: (String) -> Unit
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChanged,
        label = { Text(text = label, color = Color.Black) },
        modifier = Modifier.fillMaxWidth(),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = colorResource(id = R.color.black),
            unfocusedBorderColor = colorResource(id = R.color.black),
            cursorColor = colorResource(id = R.color.black),
            focusedLabelColor = colorResource(id = R.color.black),
            unfocusedLabelColor = colorResource(id = R.color.black)
        )
    )
}


