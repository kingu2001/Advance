package com.example.advance.view

import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.TopAppBar
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.example.advance.R


@Composable
fun AppBarView(
    title: String, onBackNavClicked: () -> Unit = {}
) {
    val navigationicon: (@Composable () -> Unit)? =
        if(!title.contains("A D V A N C E")) {
            {
                IconButton(onClick = { onBackNavClicked() }) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Default.ArrowBack,
                        tint = colorResource(id = R.color.green),
                        contentDescription = ""
                    )
                }
            }
        }
    else{
        null
        }


    TopAppBar(title = {
        Text(
            text = title,
            color = colorResource(id = R.color.green),
            modifier = Modifier
                .padding(start = 120.dp)
                .heightIn(max = 24.dp)
        )
    },
        elevation = 3.dp,
        backgroundColor = Color.Transparent,
        navigationIcon = navigationicon

    )
}