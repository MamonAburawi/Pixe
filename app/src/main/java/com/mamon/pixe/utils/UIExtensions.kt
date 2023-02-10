@file:Suppress("DEPRECATION")

package com.mamon.pixe.utils

import android.app.Activity
import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.provider.MediaStore
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.airbnb.lottie.LottieAnimationView
import com.mamon.pixe.R
import java.io.File
import java.io.FileNotFoundException
import java.io.FileOutputStream
import java.io.IOException


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



fun LottieAnimationView.startAnimation(anim: Int ){
    setAnimation(anim)
    playAnimation()
}

fun LottieAnimationView.stopAnimation(){
    cancelAnimation()
    progress = 0F
}

fun LottieAnimationView.pauseAnimation(){
    pauseAnimation()
}

fun LottieAnimationView.resumeAnimation(p: Float){
    resumeAnimation()
    progress = p
}


fun Fragment.takeScreenShoot(rootView: View,name: String) {
    // Create a bitmap with the same dimensions as the view
    val bitmap = Bitmap.createBitmap(rootView.width, rootView.height, Bitmap.Config.ARGB_8888)

    // Draw the view into the bitmap
    val canvas = Canvas(bitmap)
    rootView.draw(canvas)

    // Create a file to save the screenshot to
    val file = File(requireContext().filesDir, "screenshot.png")
    val fos: FileOutputStream
    try {
        // Save the screenshot to the file
        fos = FileOutputStream(file)

        // Compress and write the bitmap to the output stream (file)
        bitmap.compress(Bitmap.CompressFormat.PNG, 90, fos)

        // Flush and close the output stream (file)
        fos.flush()
        fos.close()

        // Save the bitmap to Gallery
        MediaStore.Images.Media.insertImage(requireActivity().contentResolver, bitmap, name, "screenshot");

        showMessage("screen captured")
    } catch (e: FileNotFoundException) {
        e.printStackTrace()
    } catch (e: IOException) {
        e.printStackTrace()
    }
}
