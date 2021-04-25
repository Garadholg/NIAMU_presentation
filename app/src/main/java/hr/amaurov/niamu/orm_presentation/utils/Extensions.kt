package hr.amaurov.niamu.orm_presentation.utils

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.widget.Toast

inline fun<reified T: Activity> Context.startActivity() = startActivity(Intent(this,T::class.java).apply {
    addFlags(Intent.FLAG_ACTIVITY_NEW_TASK) // very important for the new versions of Android
})

fun Context.showToast(text: String, length: Int) = Toast.makeText(this, text, length).show()
