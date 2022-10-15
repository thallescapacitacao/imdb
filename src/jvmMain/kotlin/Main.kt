// Copyright 2000-2021 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import extensions.loadBitmap
import models.Movie
import webclient.MovieWebClient

@OptIn(ExperimentalFoundationApi::class)
@Composable
@Preview
fun App(movies: List<Movie>) {
    MaterialTheme(
        colors = darkColors()
    ) {
        Surface {
            Box(modifier = Modifier.fillMaxSize()) {
                LazyVerticalGrid(
                    cells = GridCells.Adaptive(minSize = 200.dp)
                ) {
                    items(movies) { movie ->
                        Detail(movie)
                    }
                }
            }
        }
    }
}

@Composable
fun Detail(movie: Movie) {
    Column(
        modifier = Modifier
            .width(200.dp)
            .padding(16.dp)
    ) {
        Image(
            bitmap = movie.image.loadBitmap(),
            contentDescription = "capa do filme",
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(4.dp))
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    top = 8.dp,
                    start = 8.dp,
                    end = 8.dp
                ),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = Icons.Default.Star,
                    contentDescription = "ícone de estrela para a nota",
                    tint = Color.Yellow,
                    modifier = Modifier.height(16.dp)
                )
                Text(
                    text = movie.rating.toString(),
                    modifier = Modifier.padding(start = 2.dp),
                    color = Color(0xffeeeeee),
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold
                )
            }
            Text(
                text = movie.year.toString(),
                color = Color(0xffeeeeee),
                fontSize = 14.sp
            )
        }
        Text(
            text = movie.title,
            modifier = Modifier
                .padding(
                    start = 16.dp,
                    top = 8.dp,
                    end = 16.dp
                ),
            fontSize = 12.sp,
            textAlign = TextAlign.Center,
        )
    }
}

fun main() = application {
    val client = MovieWebClient()
    var list by remember { mutableStateOf(listOf<Movie>()) }
    client.findTop250Movies() { movies ->
        movies?.let {
            list = it
        }
    }
    Window(onCloseRequest = ::exitApplication, title = "IMDB") {
        App(movies = list)
    }
}
