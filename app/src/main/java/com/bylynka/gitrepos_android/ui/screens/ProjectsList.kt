package com.bylynka.gitrepos_android.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.bylynka.gitrepos_android.models.Project
import com.bylynka.gitrepos_android.providers.DataState
import com.bylynka.gitrepos_android.viewModels.ProjectsListViewModel
import androidx.compose.ui.graphics.Color
import com.bylynka.gitrepos_android.navigation.Screens
import com.bylynka.gitrepos_android.ui.components.TopBar


@Composable
fun ProjectsScreen(
    navController: NavController,
    viewModel: ProjectsListViewModel = hiltViewModel()
) {
    val uiState = viewModel.dataState.collectAsState()
    Column(Modifier.fillMaxWidth()) {
        TopBar(title = "Projects list", navController = navController)
        when (val dataState = uiState.value) {
            is DataState.Success<List<Project>> -> {
                ProjectsList(dataState.data, navController)
            }
            is DataState.Error -> {
                Error(viewModel)
            }
            is DataState.Loading -> {
                ProgressBar()
            }
        }
    }
}

@Composable
fun ProjectsList(projects: List<Project>, navController: NavController) {
    LazyColumn(Modifier.fillMaxWidth()) {
        items(projects.size, { i -> projects[i].id as Any }) { i ->
            val project = projects[i]
            Surface(
                elevation = 2.dp, modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
            ) {
                Surface(Modifier.clickable {
                    navController.navigate("${Screens.project}/${project.id}")
                }) {
                    Column(Modifier.padding(16.dp)) {
                        Text(
                            text = project.name?.uppercase() ?: "Unknown",
                            fontWeight = FontWeight.SemiBold
                        )
                        Text(text = project.description ?: "")
                    }
                }

            }

        }
    }
}

@Composable
fun ProgressBar() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.8f)
    ) {
        Text(
            text = "Loading...",
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.padding(bottom = 16.dp)
        )
        LinearProgressIndicator()
    }
}

@Composable
fun Error(viewModel: ProjectsListViewModel) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Text(
            text = "Something went wrong :(",
            color = Color.Red,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.padding(bottom = 16.dp)
        )
        Button(onClick = { viewModel.refreshProjectsList() }) {
            Text(text = "Try again")
        }
    }
}



