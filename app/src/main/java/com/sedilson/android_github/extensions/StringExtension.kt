package com.sedilson.android_github.extensions

fun String.setStringMaxLength(maxLengthCharacters: Int): String {
    return if (this.length > maxLengthCharacters) {
        "${this.substring(0, maxLengthCharacters)}..."
    } else {
        this
    }
}