package com.gilorroristore.thesimpsons.ui.core

import android.content.Context
import android.widget.Toast
import androidx.fragment.app.Fragment


fun Fragment.showToast(context: Context, text: String) {
    Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
}
