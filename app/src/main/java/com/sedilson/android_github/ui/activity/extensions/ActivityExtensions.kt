package com.sedilson.android_github.ui.activity.extensions

import android.app.Activity
import android.widget.Toast

fun Activity.showErrorMessage(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_LONG).show()
}