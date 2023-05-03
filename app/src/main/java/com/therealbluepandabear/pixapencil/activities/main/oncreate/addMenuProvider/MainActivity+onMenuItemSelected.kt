package com.therealbluepandabear.pixapencil.activities.main.oncreate.addMenuProvider

import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.InsetDrawable
import android.net.Uri
import android.view.MenuItem
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.Button
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatDelegate
import com.therealbluepandabear.pixapencil.R
import com.therealbluepandabear.pixapencil.activities.main.MainActivity
import com.therealbluepandabear.pixapencil.enums.SnackbarDuration
import com.therealbluepandabear.pixapencil.extensions.showDialog
import com.therealbluepandabear.pixapencil.extensions.showSnackbar
import com.therealbluepandabear.pixapencil.utility.constants.StringConstants

fun MainActivity.onMenuItemSelected(item: MenuItem): Boolean {
    when (item.itemId) {


        R.id.activityMainTopAppMenu_dark_light_mode_item -> {
            darkMode = !darkMode

            with(sharedPreferenceObject.edit()) {
                putBoolean(StringConstants.Identifiers.SHARED_PREFERENCE_DARK_LIGHT_MODE_IDENTIFIER, darkMode)
                putBoolean(StringConstants.Identifiers.SHARED_PREFERENCE_DARK_LIGHT_MODE_CHANGED_IDENTIFIER, true)
                apply()
            }

            if (darkMode) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
        }

       R.id.activityMainTopAppMenu_info -> {
               val dialog = Dialog(this)
               dialog.requestWindowFeature(Window.FEATURE_NO_TITLE) // before
               dialog.setContentView(R.layout.dialog_about)
               dialog.setCancelable(true)
               val lp = WindowManager.LayoutParams()
               lp.copyFrom(dialog.window!!.attributes)
               lp.width = WindowManager.LayoutParams.MATCH_PARENT
               lp.height = WindowManager.LayoutParams.WRAP_CONTENT
               val back = ColorDrawable(Color.TRANSPARENT)
               val margin = 20
               val inset = InsetDrawable(back, margin)
               dialog.window!!.setBackgroundDrawable(inset)
               (dialog.findViewById<View>(R.id.bt_close) as ImageButton).setOnClickListener { if (dialog != null) dialog.dismiss() }
               (dialog.findViewById<View>(R.id.bt_licence) as Button).setOnClickListener {
                   val url = "https://github.com/therealbluepandabear/PixaPencil/blob/master/LICENSE"
                   val urlIntent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                   startActivity(urlIntent)
               }
               (dialog.findViewById<View>(R.id.app_source_code) as Button).setOnClickListener {
                   val url = "https://github.com/bumbumapp/SketchDrawing"
                   val urlIntent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                   startActivity(urlIntent)
               }
               dialog.show()
               dialog.window!!.attributes = lp

       }

        R.id.activityMainTopAppMenu_delete_all_item -> {
            showDialog(
                getString(R.string.generic_warning),
                getString(R.string.dialog_delete_all_projects),
                getString(R.string.generic_ok), { _, _ ->
                    val size = pixelArtData.size

                    pixelArtViewModel.deleteAll()
                    binding.activityMainCoordinatorLayout.showSnackbar(getString(R.string.snackbar_deleted_projects, size.toString()), SnackbarDuration.Medium)
                    binding.activityMainNewProjectButton.show()
                },  getString(R.string.generic_cancel), null)
        }

        R.id.activityMainTopAppMenu_open_image_item -> {
            galleryActivityLauncher.launch(arrayOf ("image/*"))
        }
    }

    return true
}
