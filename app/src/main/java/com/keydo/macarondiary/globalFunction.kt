package com.keydo.macarondiary

import android.content.Context
import android.database.Cursor
import android.net.Uri
import android.provider.MediaStore
import com.keydo.macarondiary.dataset.DiaryDataset
import com.keydo.macarondiary.retrofitdataset.ResponseDiaryList

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

fun responsetodataset(responsediarylist: List<ResponseDiaryList>): List<DiaryDataset> {
    val datasetlist = mutableListOf<DiaryDataset>()

    for (i in 1..responsediarylist.count()){
        val diarydataset = DiaryDataset(
            responsediarylist[i-1].respdiarypk
            ,responsediarylist[i-1].respdiarytitle
            ,responsediarylist[i-1].respdiarycontent
            ,responsediarylist[i-1].respdiarydate
            ,responsediarylist[i-1].respdiaryshopname
            ,responsediarylist[i-1].respthumbnailpath
        )
        datasetlist.add(diarydataset)
    }

    return datasetlist
}









