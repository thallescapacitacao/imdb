package br.com.alura.extension

import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.res.loadImageBitmap
import java.net.URL

fun String.loadBitmap(): ImageBitmap =
    URL(this)
        .openStream()
        .buffered()
        .use(::loadImageBitmap)
