package extensions

import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.res.loadImageBitmap
import java.net.URL

fun String.carregarBitmap(): ImageBitmap {
    return URL(this)
        .openStream()
        .buffered()
        .use(::loadImageBitmap)
}
