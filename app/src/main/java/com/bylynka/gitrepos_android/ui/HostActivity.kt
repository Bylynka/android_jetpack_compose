package com.bylynka.gitrepos_android.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.*
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.bylynka.gitrepos_android.ui.theme.GitRepos_androidTheme
import com.bylynka.gitrepos_android.navigation.Screens
import com.bylynka.gitrepos_android.ui.screens.*
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HostActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GitRepos_androidTheme(darkTheme = false) {
                Scaffold {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = Screens.projectsList
                    ) {
                        composable(
                            Screens.projectsList
                        ) { ProjectsScreen(navController) }
                        composable(
                            route = "${Screens.project}/{id}",
                            arguments = listOf(navArgument("id") {
                                type = NavType.IntType
                            })
                        ) {
                            ProjectScreen(navController)
                        }
                    }
                }
            }
        }
    }
}