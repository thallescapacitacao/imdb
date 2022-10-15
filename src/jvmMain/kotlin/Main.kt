// Copyright 2000-2021 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import models.Filme
import utils.MovieWebClient

@Composable
@Preview
fun App() {
    MaterialTheme(
        colors = darkColors()
    ) {
        Surface {
            Box(modifier = Modifier.fillMaxSize()) {
                val filmes = listOf(
                    Filme(
                        titulo = "The Shawshank Redemption",
                        imagem = "shawshank.jpg",
                        nota = 9.3,
                        ano = 1994
                    ),
                    Filme(
                        titulo = "The Godfather",
                        imagem = "thegodfather.jpg",
                        nota = 9.2,
                        ano = 1972
                    ),
                    Filme(
                        titulo = "12 Angry Men",
                        imagem = "12angrymen.jpg",
                        nota = 9.0,
                        ano = 1957
                    )
                )
                LazyColumn {
                    items(filmes) { filme ->
                        Detalhe(filme)
                    }
                }
            }
        }
    }
}

@Composable
fun Detalhe(filme: Filme) {
    Column(
        modifier = Modifier
            .width(200.dp)
            .padding(16.dp)
    ) {
        Image(
            painter = painterResource(filme.imagem),
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
                    text = filme.nota.toString(),
                    modifier = Modifier.padding(start = 2.dp),
                    color = Color(0xffeeeeee),
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold
                )
            }
            Text(
                text = filme.ano.toString(),
                color = Color(0xffeeeeee),
                fontSize = 14.sp
            )
        }
        Text(
            text = filme.titulo,
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
    with(MovieWebClient()) {
        findTop250Movies()
    }
    Window(onCloseRequest = ::exitApplication, title = "IMDB") {
        App()
    }
}
