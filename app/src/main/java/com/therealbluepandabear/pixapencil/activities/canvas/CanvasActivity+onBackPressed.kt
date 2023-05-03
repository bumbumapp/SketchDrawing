package com.therealbluepandabear.pixapencil.activities.canvas

import android.content.Intent
import com.therealbluepandabear.pixapencil.activities.main.MainActivity

fun CanvasActivity.extendedOnBackPressed(canvasActivity: CanvasActivity) {
    if (supportFragmentManager.backStackEntryCount > 0) {
        supportFragmentManager.popBackStackImmediate()

        binding.root.post {
            judgeUndoRedoStacks()
        }
    } else if (!viewModel.saved) {
        viewModel.unsavedChangesDialogShown = true
        showUnsavedChangesDialog(canvasActivity)
    } else {
        startActivity(Intent(this, MainActivity::class.java))
        finishAffinity()
    }
}