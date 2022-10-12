// Copyright 2000-2021 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import extensions.carregarBitmap

data class Filme(
    val titulo: String,
    val poster: String,
    val nota: Double,
    val ano: Int
)

@Composable
@Preview
fun App() {
    val filme = Filme(
        titulo = "The Shawshank Redemption",
        poster = "poster_shawshank.jpg",
        nota = 9.2,
        ano = 1994
    )
    val imagem =
        "https://raw.githubusercontent.com/thallescapacitacao/imdb/master/src/jvmMain/resources/poster_shawshank.jpg"
    MaterialTheme {
        Column {
            Text(text = filme.titulo)
            Image(
                bitmap = imagem.carregarBitmap(),
                contentDescription = "Pôster",
                modifier = Modifier.height(200.dp)
            )
            Text(text = "Nota: ${filme.nota} - Ano: ${filme.ano}")
        }
    }
}

fun main() = application {
    Window(onCloseRequest = ::exitApplication, title = "IMDB") {
        App()
    }
}
