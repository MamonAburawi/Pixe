package com.mamon.pixe.utils

import android.app.Activity
import android.content.Context
import android.opengl.Visibility
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.fragment.app.Fragment


fun View.show(){ visibility = View.VISIBLE }
fun View.hide() {visibility = View.GONE}


fun Fragment.hideKeyboard() {
    view?.let { activity?.hideKeyboard(it) }
}

fun Activity.hideKeyboard() {
    hideKeyboard(currentFocus ?: View(this))
}

fun Context.hideKeyboard(view: View) {
    val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
}


fun Fragment.showMessage(message: String){
    Toast.makeText(requireContext(),message,Toast.LENGTH_SHORT).show()
}
