package com.jbkalit.usersearch.presentation.ext

import android.view.View
import android.view.ViewGroup
import android.view.animation.TranslateAnimation
import com.google.android.material.snackbar.Snackbar

fun View.setVisible() {
    this.visibility = View.VISIBLE
}

fun View.showIf(condition: Boolean) {
    if (condition) setVisible()
    else setGone()
}

fun View.setGone() {
    this.visibility = View.GONE
}

fun View.showSlideUp() {
    setVisible()
    val bottomMargin = (layoutParams as ViewGroup.MarginLayoutParams).bottomMargin
    startAnimation(TranslateAnimation(0f, 0f, height.toFloat() + bottomMargin.toFloat(), 0f).apply {
        duration = 300
        fillAfter = true
    })
}

fun View.hideSlideDown() {
    if (this.visibility == View.VISIBLE) {
        val bottomMargin = (layoutParams as ViewGroup.MarginLayoutParams).bottomMargin
        startAnimation(
            TranslateAnimation(
                0f,
                0f,
                0f,
                height.toFloat() + bottomMargin.toFloat()
            ).apply {
                duration = 300
                fillAfter = true
            })
        setGone()
    }
}

fun View.showSnackbar(
    message: String = "",
    actionMessage: String = "",
    anchorView: View? = null,
    length: Int = Snackbar.LENGTH_LONG,
    action: (() -> Unit)? = null
): Snackbar {
    val snackbar = Snackbar.make(this, message, length)
    if (action != null) snackbar.setAction(actionMessage) { action.invoke() }
    if (anchorView != null) snackbar.anchorView = anchorView
    snackbar.show()
    return snackbar
}
