package com.bylynka.gitrepos_android.ui.components

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp

import androidx.navigation.NavController

@Composable
fun TopBar(title: String, navController: NavController, canPop: Boolean = false) {
    if (canPop) {
        TopAppBar(
            elevation = 4.dp,
            title = { Text(text = title) },
            navigationIcon = {
                IconButton(onClick = {
                    navController.navigateUp()
                }) {
                    Icon(Icons.Rounded.ArrowBack, "")
                }
            },
            backgroundColor = MaterialTheme.colors.primary
        )
    } else {
        TopAppBar(
            elevation = 4.dp,
            title = { Text(text = title) },
            backgroundColor = MaterialTheme.colors.primary
        )
    }
}
