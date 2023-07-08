package com.r4ziel.nycschools.utilities

import android.view.View
import android.view.View.OnClickListener
import com.google.android.material.snackbar.Snackbar

/**
 * Created by Jarvis Charles on 7/7/23.
 */
interface SnackBarHelper {
        fun createSnackBar(view: View, message: String?, clickListener: OnClickListener): Snackbar {
                val charSequence: CharSequence = message.toString()
                return Snackbar.make(
                        view,
                        charSequence,
                        Snackbar.LENGTH_LONG
                ).setAction("Dismiss", clickListener)
        }
}
