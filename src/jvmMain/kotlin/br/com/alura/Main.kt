package br.com.alura

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Snackbar
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import br.com.alura.ui.content.MovieList
import br.com.alura.ui.fallback.Message
import br.com.alura.ui.layout.App
import br.com.alura.webclient.MovieWebClient
import br.com.alura.webclient.Status

fun main() = application {
    val client = MovieWebClient()
    val request = client
        .findTop250Movies()
        .collectAsState(Status.Loading)
        .value
    Window(
        onCloseRequest = ::exitApplication,
        title = "IMDB"
    ) {
        App {
            when (request) {
                is Status.Success -> {
                    val movies = request.data
                    if (movies.isNotEmpty()) {
                        MovieList(movies)
                    } else {
                        Message("Sem filmes")
                    }
                }
                is Status.Error -> {
                    val error = request.exception
                    var showSnackbar by remember { mutableStateOf(true) }
                    if (showSnackbar) {
                        Snackbar(
                            modifier = Modifier.padding(all = 8.dp),
                            action = {
                                Button(onClick = {
                                    showSnackbar = false
                                }) {
                                    Text("OK")
                                }
                            }
                        ) {
                            Text("Falha ao buscar filmes")
                            error.printStackTrace()
                        }
                    }
                }
                is Status.Loading -> {
                    Message("Carregando filmes...")
                }
            }
        }
    }
}
