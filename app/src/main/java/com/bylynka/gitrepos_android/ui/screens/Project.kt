package com.bylynka.gitrepos_android.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.bylynka.gitrepos_android.ui.components.TopBar
import com.bylynka.gitrepos_android.viewModels.ProjectViewModel

@Composable
fun ProjectScreen(navController: NavController, viewModel: ProjectViewModel = hiltViewModel()) {
    val project = viewModel.project.collectAsState()
    project.value?.apply {
        Column {
            TopBar(title = name ?: "Unknown", navController = navController, true)
            Column(Modifier.padding(16.dp)) {
                description?.also { Text(text = it) }
                html_url?.also {
                    val annotatedString = buildAnnotatedString {
                        pushStringAnnotation(tag = "url", annotation = it)
                        withStyle(style = SpanStyle(color = MaterialTheme.colors.primary)) {
                            append("Open project web site")
                        }
                        pop()
                    }
                    val uriHandler = LocalUriHandler.current

                    ClickableText(
                        text = annotatedString,
                        style = MaterialTheme.typography.body1,
                        modifier = Modifier.padding(top = 16.dp),
                        onClick = { offset ->
                            annotatedString.getStringAnnotations(
                                tag = "url", start = offset, end = offset
                            ).firstOrNull()?.let {
                                uriHandler.openUri(it.item)
                            }
                        })
                }
            }
        }
    }
}