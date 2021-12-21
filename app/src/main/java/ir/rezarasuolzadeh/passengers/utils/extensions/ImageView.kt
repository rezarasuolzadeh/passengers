package ir.rezarasuolzadeh.passengers.utils.extensions

import android.widget.ImageView
import coil.load
import coil.size.Scale

fun ImageView.loadImage(uri: String) {
    this.load(uri) {
        crossfade(2000)
        scale(Scale.FILL)
    }
}