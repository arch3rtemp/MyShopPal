package eu.tutorials.myshoppal.utils

import android.net.Uri
import android.webkit.MimeTypeMap
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import eu.tutorials.myshoppal.R

fun Fragment.showSnackbar(message: String, status: String) {
    val color = when (status) {
        Constants.STATUS_ERROR -> {
            R.color.colorSnackBarError
        }
        Constants.STATUS_SUCCESS -> {
            R.color.colorSnackBarSuccess
        }
        else -> {
            throw IllegalArgumentException("Wrong snackbar status!")
        }
    }
    Snackbar.make(this.requireView(), message, Snackbar.LENGTH_SHORT).apply {
        animationMode = Snackbar.ANIMATION_MODE_SLIDE
        view.setBackgroundColor(ContextCompat.getColor(requireActivity(), color))
        show()

    }
}

fun Fragment.showToast(message: String) {
    Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
}

fun Fragment.getFileExtension(uri: Uri): String? {
    return MimeTypeMap.getSingleton().getExtensionFromMimeType(requireActivity().contentResolver.getType(uri))
}