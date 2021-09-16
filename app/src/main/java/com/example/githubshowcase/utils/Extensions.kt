package com.example.githubshowcase.utils

import android.app.Activity
import android.content.Context
import android.view.inputmethod.InputMethodManager
import android.widget.ProgressBar
import androidx.appcompat.app.AlertDialog
import com.example.githubshowcase.R
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.textfield.TextInputLayout
import java.text.SimpleDateFormat
import java.util.*

private val locale by lazy { Locale.getDefault() }

fun Date.format() : String{
    return SimpleDateFormat("dd-MM-yyyy", locale).format(this)
}

var TextInputLayout.text: String
    get()= editText?.text?.toString() ?: ""
    set(value){
        editText?.setText(value)
    }

fun Context.createDialog(block: MaterialAlertDialogBuilder.()->Unit = {}): AlertDialog {
    val builder = MaterialAlertDialogBuilder(this)
    builder.setPositiveButton(android.R.string.ok, null)
    block(builder)
    return builder.create()
}

fun Context.createProgressDialog(): AlertDialog {
    return createDialog {
        val padding = this@createProgressDialog.resources.getDimensionPixelOffset(R.dimen.layout_padding)
        val progressBar = ProgressBar(this@createProgressDialog)
        progressBar.setPadding(padding, padding, padding, padding)
        setView(progressBar)
        setPositiveButton(null, null)
        setCancelable(false)
    }
}

fun Activity.hideKeyboard() {
    val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.hideSoftInputFromWindow(currentFocus?.windowToken , 0)
}