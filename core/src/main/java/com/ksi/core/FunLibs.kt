package com.ksi.core

import android.Manifest
import android.net.Uri
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

import com.squareup.picasso.Picasso
import com.tbruyelle.rxpermissions2.RxPermissions
import gun0912.tedbottompicker.TedBottomPicker
import id.zelory.compressor.Compressor
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File


/**
 * Created by lenovo on 1/15/2019.
 */

public fun AppCompatActivity.loadImage(path: String? = null, img: ImageView) {

    Picasso.get().load(path)
            .into(img)

}

 fun AppCompatActivity.PermissionsCamera ( whenPermissionCamera: (isGranted: Boolean) -> Unit) {

    var rxPermissions: RxPermissions = RxPermissions(this)
    rxPermissions
            .request(Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE)
            .subscribe { granted ->

                    whenPermissionCamera(granted)

            }

}

public fun AppCompatActivity.OpenGallary( onSelectedImage: (uriImage: Uri) -> Unit) {

    val tedBottomPicker = TedBottomPicker.Builder(this)
            .setOnImageSelectedListener(object : TedBottomPicker.OnImageSelectedListener {
                override fun onImageSelected(uri: Uri) {
                    onSelectedImage(uri)
                   // setGallaryResultInActivity(uri)
                }
            })
            .create()

    tedBottomPicker.show(supportFragmentManager)

}

fun AppCompatActivity.setGallaryResultInActivity(uri: Uri) {
    val act: AppCompatActivity = this


}

fun AppCompatActivity.CompressImage(imageuri: Uri): MultipartBody.Part {
    val compressedImageFile: File = Compressor(this).compressToFile(File(imageuri.getPath()))
    return prepareFilePart("avatar",compressedImageFile)
}


private fun prepareFilePart(partName: String, file: File): MultipartBody.Part {

    // create RequestBody instance from file
    val requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file)
    // MultipartBody.Part is used to send also the actual file name
    return MultipartBody.Part.createFormData(partName, file.name, requestFile)
}

 fun createPartFromString(description: String): RequestBody {
    return RequestBody.create(okhttp3.MultipartBody.FORM, description)
}