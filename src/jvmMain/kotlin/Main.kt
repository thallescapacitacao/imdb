// Copyright 2000-2021 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import extensions.carregarBitmap

data class Filme(
    val titulo: String,
    val poster: String,
    val nota: Double,
    val ano: Int
)

val filme = Filme(
    titulo = "The Shawshank Redemption",
    poster = "poster_shawshank.jpg",
    nota = 9.2,
    ano = 1994
)

const val imagem =
    "https://raw.githubusercontent.com/thallescapacitacao/imdb/master/src/jvmMain/resources/poster_shawshank.jpg"

@Composable
@Preview
fun App() {
    MaterialTheme(
        colors = darkColors()
    ) {
        Surface {
            Box(
                modifier = Modifier
                    .padding(all = 8.dp)
                    .width(170.5.dp)
            ) {
                Column {
                    Image(
                        bitmap = imagem.carregarBitmap(),
                        contentDescription = "Pôster",
                        modifier = Modifier
                            .height(249.dp)
                            .clip(RoundedCornerShape(5.dp))
                    )
                    Row(
                        modifier = Modifier
                            .padding(
                                start = 6.dp,
                                end = 6.dp,
                                top = 6.dp,
                                bottom = 4.dp
                            )
                    ) {
                        Row(
                            modifier = Modifier
                                .padding(start = 1.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Filled.Star,
                                contentDescription = "Avaliação",
                                tint = Color.Yellow,
                                modifier = Modifier
                                    .size(20.dp)
                                    .padding(horizontal = 2.dp)
                            )
                            Text(
                                text = filme.nota.toString(),
                                color = Color.White,
                                fontSize = 14.sp,
                            )
                        }
                        Text(
                            text = filme.ano.toString(),
                            color = Color.White,
                            fontSize = 14.sp,
                            textAlign = TextAlign.End,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 4.dp)
                        )
                    }
                    Text(
                        text = filme.titulo,
                        color = Color.White,
                        fontSize = 12.sp,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .height(40.dp)
                    )
                }
            }
        }
    }
}

fun main() = application {
    Window(onCloseRequest = ::exitApplication, title = "IMDB") {
        App()
    }
}
