package com.therealbluepandabear.pixapencil.activities.canvas

import android.content.Intent
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.therealbluepandabear.pixapencil.R
import com.therealbluepandabear.pixapencil.activities.canvas.onoptionsitemselected.onSaveProjectOptionsItemSelected
//import com.therealbluepandabear.pixapencil.activities.canvas.onoptionsitemselected.onSaveProjectOptionsItemSelected
import com.therealbluepandabear.pixapencil.activities.main.MainActivity
import com.therealbluepandabear.pixapencil.extensions.showDialogWithNeutralButtonAndOnCancelListener

fun CanvasActivity.showUnsavedChangesDialog(canvasActivity: CanvasActivity) {
    val alertDialog = MaterialAlertDialogBuilder(this, R.style.AlertDialogTheme)
    alertDialog.apply {
        setTitle(getString(R.string.dialog_unsaved_changes_title))
        setMessage(getString(R.string.dialog_unsaved_changes_message, projectTitle))
        setNegativeButton(getString(R.string.dialog_unsaved_changes_negative_button_text)) { _, _ ->
            startActivity(Intent(canvasActivity, MainActivity::class.java))
            finishAffinity()
        }
        setPositiveButton(getString(R.string.activityCanvasTopAppMenu_save_text)) { _, _ ->
            onSaveProjectOptionsItemSelected()
            startActivity(Intent(canvasActivity, MainActivity::class.java))
            finishAffinity()
        }
        setNeutralButton(getString(R.string.generic_cancel)) { _, _ ->
            viewModel.unsavedChangesDialogShown = false
        }
        setOnCancelListener {
            viewModel.unsavedChangesDialogShown = false
        }
    }.show()
}
