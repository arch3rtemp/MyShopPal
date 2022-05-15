package eu.tutorials.myshoppal.utils

import android.net.Uri
import android.webkit.MimeTypeMap
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import eu.tutorials.myshoppal.R

fun Fragment.showSnackbar(message: String, error: Boolean) {
    val color = if (error) {
        R.color.colorSnackBarError
    } else {
        R.color.colorSnackBarSuccess
    }
    Snackbar.make(this.requireView(), message, Snackbar.LENGTH_LONG).apply {
        animationMode = Snackbar.ANIMATION_MODE_SLIDE
        view.setBackgroundColor(ContextCompat.getColor(requireActivity(), color))
        show()
    }
}

fun Fragment.getFileExtension(uri: Uri?): String? {
    return MimeTypeMap.getSingleton().getExtensionFromMimeType(requireActivity().contentResolver.getType(uri!!))
}