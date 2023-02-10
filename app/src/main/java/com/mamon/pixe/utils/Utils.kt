package com.mamon.pixe.utils

import android.Manifest
import android.annotation.SuppressLint
import android.app.DownloadManager
import android.content.Context
import android.database.Cursor
import android.net.Uri
import android.os.Environment
import android.util.Log
import androidx.core.content.ContextCompat
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import com.karumi.dexter.Dexter
import com.karumi.dexter.MultiplePermissionsReport
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.multi.MultiplePermissionsListener
import kotlinx.coroutines.*
import java.util.*


const val STARTING_PAGE_INDEX = 1
const val PER_PAGE = 10 // number of items per page
const val PREFETCH_DIST = 10
const val MAX_SIZE = PER_PAGE + 2 * PREFETCH_DIST


enum class FileFormat{JPEG,PNG,JPG,MP4,MP3}




fun Fragment.download(url: Uri,description: String, format: String): Long {
    var fileName = url.toString().substring(url.toString().lastIndexOf('/') + 1)
    fileName = fileName.substring(0, 1).uppercase(Locale.getDefault()) + fileName.substring(1)
    val request  = DownloadManager.Request(url)
        .apply {
            setTitle(fileName)
            setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI or DownloadManager.Request.NETWORK_MOBILE)
            setDescription(description)
            allowScanningByMediaScanner()
            setAllowedOverMetered(true)
            setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
            setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS,"${System.currentTimeMillis()}.$format")
        }
    val downloadManager = ContextCompat.getSystemService(requireContext(), DownloadManager::class.java)
    return downloadManager?.enqueue(request) ?: 0L // enqueue puts the download request in the
}


// you can insert list of permissions
fun Fragment.checkPermissions(onApprove:()-> Unit,onDecline:() -> Unit){
    Dexter.withContext(requireContext() )
        .withPermissions(Manifest.permission.WRITE_EXTERNAL_STORAGE)
        .withListener(object : MultiplePermissionsListener {
            override fun onPermissionsChecked(p0: MultiplePermissionsReport?) { onApprove() }
            override fun onPermissionRationaleShouldBeShown(
                p0: MutableList<PermissionRequest>?,
                p1: PermissionToken?) { onDecline() }
        }).check()
}


@SuppressLint("Range")
fun Fragment.getDownloadStatus
            (downloadId: Long,
               onProgress:(Int)-> Unit,
               onComplete:() -> Unit,
             onFailed:() -> Unit) {

  Thread{

      var downloading = true
      while (downloading) {
          val manager = try{ContextCompat.getSystemService(requireContext(), DownloadManager::class.java) }catch (ex: Exception) {null}
          val q = DownloadManager.Query()
          q.setFilterById(downloadId)
          val cursor: Cursor? = try{ manager?.query(q)} catch (ex: Exception){null}
          if ( cursor != null){
              cursor.moveToFirst()


              MainScope().launch {

                  val bytes_downloaded: Int = try {
                      cursor.getInt(cursor.getColumnIndex(DownloadManager.COLUMN_BYTES_DOWNLOADED_SO_FAR))
                  } catch (ex: Exception) {
                      0
                  }

                  val bytes_total: Int = try {
                      cursor.getInt(cursor.getColumnIndex(DownloadManager.COLUMN_TOTAL_SIZE_BYTES))
                  } catch (ex: Exception) {
                      0
                  }
//            val bytes_total: Int =
//                cursor.getInt(cursor.getColumnIndex(DownloadManager.COLUMN_TOTAL_SIZE_BYTES))




                  val status: Int = try {
                      cursor.getInt(cursor.getColumnIndex(DownloadManager.COLUMN_STATUS))
                  }catch (ex: Exception){
                      0
                  }
                  when (status){
                      DownloadManager.STATUS_SUCCESSFUL ->{
                          downloading = false
                          onComplete()
                          Log.d("DownloadManger","Download Success")
                      }
                      DownloadManager.STATUS_FAILED ->{
                          downloading = false
                          onFailed()
                          Log.d("DownloadManger","Download Failed")
                      }

                      else -> {
                          downloading = false
                          onFailed()
                          Log.d("DownloadManger","Download Failed")
                      }


                  }
//            if (status === DownloadManager.STATUS_SUCCESSFUL) {
//                downloading = false
//                onComplete()
//            }


                  if (bytes_total > 0){
                      val progress = (bytes_downloaded * 100L / bytes_total)
                      onProgress(progress.toInt())
                  }

              }
          }

          }


      }.start()


  }






