package com.mamon.pixe.utils

import android.app.DownloadManager
import android.content.Context
import android.net.Uri
import android.os.Environment
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import java.util.*


const val STARTING_PAGE_INDEX = 1
const val PER_PAGE = 10 // number of items per page
const val PREFETCH_DIST = 10
const val MAX_SIZE = PER_PAGE + 2 * PREFETCH_DIST


enum class FileFormat{JPEG,PNG,JPG,MP4}




fun Fragment.download(url: Uri,description: String, format: String): Long? {
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
    return downloadManager?.enqueue(request) // enqueue puts the download request in the
}

