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
    //  alert.setTitle("Enter Your Title");

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
        // what ever you want to do with No option.
    }

    alert.show()
}

fun AppCompatActivity.dialogInfo(msg: String? = null, @ColorRes color: Int, @DrawableRes drawable: Int, onOkClicked: () -> Unit) {

    val dialog = PrettyDialog(this)
    dialog.setTitle(getString(R.string.app_name))
        .setTitleColor(color)
        .setMessage(msg)
        .setIcon(drawable)
        .addButton(
            getString(R.string.ok), // button text
            R.color.pdlg_color_white, // button text color
            R.color.pdlg_color_green // button background color
        ) {

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
        .setTitle("Are you sure to Exit")
        //set message
        .setMessage("If yes then application will close")
        //set positive button
        .setPositiveButton("Yes", DialogInterface.OnClickListener { dialog, i ->

            finish()
        })
        //set negative button
        .setNegativeButton("No", DialogInterface.OnClickListener { dialogInterface, i ->
            //set what should happen when negative button is clicked

        })
        .show()
}