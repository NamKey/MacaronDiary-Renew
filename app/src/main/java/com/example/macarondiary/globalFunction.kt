package com.example.macarondiary

import android.content.Context
import android.database.Cursor
import android.net.Uri
import android.provider.MediaStore

fun getPathFromUri(uri: Uri,context: Context):String?{
    val cursor:Cursor? = context.contentResolver.query(uri, null, null, null, null )
    cursor?.moveToNext()
    cursor?.close()
    return cursor?.getString( cursor?.getColumnIndex( "_data" ) )
}

// fun InputStream.toFile(filePath: String) {
//        File(filePath).outputStream().use { fileOutput ->
//        this.copyTo(fileOutput)
//    }

fun getImagePath(uri: Uri,context:Context): String? {
    var cursor: Cursor? = context.contentResolver.query(uri, null, null, null, null)
    cursor?.moveToFirst()
    var document_id = cursor?.getString(0)
    document_id = document_id?.substring(document_id.lastIndexOf(":") + 1)
    cursor?.close()
    cursor = context.contentResolver.query(
        MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
        null, MediaStore.Images.Media._ID + " = ? ", arrayOf(document_id), null
    )
    cursor?.moveToFirst()
    val path = cursor?.getString(cursor?.getColumnIndex(MediaStore.Images.Media.DATA))
    cursor?.close()
    return path
}







