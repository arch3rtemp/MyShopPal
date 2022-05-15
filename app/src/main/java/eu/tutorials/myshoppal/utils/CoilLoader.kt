package eu.tutorials.myshoppal.utils

import android.content.Context
import android.net.Uri
import android.widget.ImageView
import coil.imageLoader
import coil.load
import coil.size.Scale
import eu.tutorials.myshoppal.R
import java.io.IOException

class CoilLoader(val context: Context) {
    fun loadUserPicture(imageURI: Uri, imageView: ImageView) {
        try {
            imageView.load(imageURI, context.imageLoader) {
                placeholder(R.drawable.ic_user_placeholder)
                scale(Scale.FILL)
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }
}