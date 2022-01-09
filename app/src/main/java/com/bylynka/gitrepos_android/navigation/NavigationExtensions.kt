package com.bylynka.gitrepos_android.navigation

import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.NavOptionsBuilder

fun NavController.navigate(
    screen: String,
    params: Bundle?,
    builder: NavOptionsBuilder.() -> Unit = {}
) {
    this.currentBackStackEntry?.arguments?.putAll(params)
    navigate(screen, builder)
}