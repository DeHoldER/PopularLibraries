package com.geekbrains.popularlibraries.utils

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.provider.MediaStore
import com.geekbrains.popularlibraries.app.GeekBrainsApp
import io.reactivex.rxjava3.core.Single
import java.io.FileOutputStream

const val REQUEST_CODE_GET_CONTENT = 123
const val REQUEST_CODE_PERMISSION_WRITE_EXTERNAL_STORAGE = 124
const val KEY_PATH_IMAGE_PICKED = "pathImagePicked"
const val KEY_PATH_IMAGE_CONVERTED = "pathImageConverted"
const val KEY_IS_CONVERTING = "isConverting"

object ImageConverter {

    fun convertJpgToPng(bitmap: Bitmap, pathToBitmap: String): Single<Pair<String, Bitmap>> {
        val (pathImagePickedDir, nameImagePicked) = splitPathToBitmap(pathToBitmap)
        return Single.fromCallable {
            val pathImageOutput = "$pathImagePickedDir/$nameImagePicked.png"
            val imageOutputStream = FileOutputStream(pathImageOutput)

            if (bitmap.compress(Bitmap.CompressFormat.PNG, 100, imageOutputStream)) {
                return@fromCallable (pathImageOutput to BitmapFactory.decodeFile(pathImageOutput))
            } else {
                throw Exception("Conversion problem")
            }
        }
    }

    private fun splitPathToBitmap(pathToBitmap: String): Pair<String, String> {
        val pathImagePickedParts = pathToBitmap.split("/")
        val pathImagePickedDir = pathImagePickedParts
            .subList(1, pathImagePickedParts.size - 1)
            .joinToString(prefix = "/", separator = "/")
        val nameImagePicked = pathImagePickedParts[pathImagePickedParts.size - 1]
            .split(".")[0]

        return pathImagePickedDir to nameImagePicked
    }

    fun getPathFromUri(contentUri: Uri): String? {
        var res: String? = null
        val projection = arrayOf(MediaStore.Images.Media.DATA)
        val cursor =
            GeekBrainsApp.instance.contentResolver.query(contentUri, projection, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst()
            val columnIndex = cursor.getColumnIndex(projection[0])
            columnIndex.let {
                res = cursor.getString(columnIndex)
            }
            cursor.close()
        }
        return res
    }

}