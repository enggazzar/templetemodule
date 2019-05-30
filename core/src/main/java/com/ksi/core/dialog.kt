package com.ksi.core

import android.content.DialogInterface
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import libs.mjn.prettydialog.PrettyDialog

fun AppCompatActivity.dialogForgetPwd(onOkClicked: () -> Unit) {
    //==========================================//
    val alert = android.app.AlertDialog.Builder(this)
    val edittext = android.widget.EditText(this)
    alert.setMessage(getString(R.string.forget_pwd_enter_your_email))
    alert.setView(edittext)
    alert.setPositiveButton(getString(R.string.ok)) { dialog, whichButton ->
        val email = edittext.text.toString()
        //==================================================//
        if (!email.isEmpty()) {
            onOkClicked()
        }
        //===================================================//
    }
    alert.setNegativeButton(getString(R.string.cancel)) { dialog, whichButton ->
    }

    alert.show()
}

fun AppCompatActivity.dialogInfo(
    msg: String? = null, @ColorRes color: Int,
    @DrawableRes drawable: Int,
    onOkClicked: () -> Unit
)
{

    val dialog = PrettyDialog(this)
    dialog.setTitle(getString(R.string.app_name))
        .setTitleColor(color)
        .setMessage(msg)
        .setIcon(drawable)
        .addButton(
            getString(R.string.ok), // button text
            R.color.pdlg_color_white, // button text color
            R.color.pdlg_color_green // button background color
        )
        {

            dialog.dismiss()
            onOkClicked()
        }
        .show()

}

fun AppCompatActivity.dialogExitApp() {

    val alertDialog = AlertDialog.Builder(this)
        //set icon
        .setIcon(android.R.drawable.ic_dialog_alert)
        //set title
        .setTitle(getString(R.string.confirm_exit_app_title))
        //set message
        .setMessage(getString(R.string.confirm_exit_app_msg))
        //set positive button
        .setPositiveButton(getString(R.string.ok), DialogInterface.OnClickListener { dialog, i ->

            finish()
        })
        //set negative button
        .setNegativeButton(getString(R.string.cancel), DialogInterface.OnClickListener { dialogInterface, i ->


        })
        .show()
}

fun AppCompatActivity.dialogOkCansel(title: String, message: String, icon: Int, okPressed: () -> Unit) {

    val alertDialog = AlertDialog.Builder(this)
        //set icon
        .setIcon(icon)
        //set title
        .setTitle(title)
        //set message
        .setMessage(message)
        //set positive button
        .setPositiveButton(getString(R.string.ok), DialogInterface.OnClickListener { dialog, i ->
            okPressed()
        })
        //set negative button
        .setNegativeButton(getString(R.string.cancel), DialogInterface.OnClickListener { dialogInterface, i ->


        })
        .show()
}