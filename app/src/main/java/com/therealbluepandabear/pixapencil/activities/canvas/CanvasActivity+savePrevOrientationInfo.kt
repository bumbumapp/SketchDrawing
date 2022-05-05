package com.therealbluepandabear.pixapencil.activities.canvas

import androidx.lifecycle.lifecycleScope
import com.therealbluepandabear.pixapencil.R
import com.therealbluepandabear.pixapencil.converters.BitmapConverter
import com.therealbluepandabear.pixapencil.converters.JsonConverter
import com.therealbluepandabear.pixapencil.database.BrushesDatabase
import com.therealbluepandabear.pixapencil.enums.SymmetryMode
import com.therealbluepandabear.pixapencil.enums.Tools
import com.therealbluepandabear.pixapencil.extensions.enable
import com.therealbluepandabear.pixapencil.fragments.canvas.pixelGridViewInstance
import com.therealbluepandabear.pixapencil.fragments.outercanvas.OuterCanvasFragment
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

fun CanvasActivity.savePrevOrientationInfo() {

    if (prevOrientation != resources.configuration.orientation) {
        lifecycleScope.launch {
            delay(200)
            onOrientationChanged()
            delay(200)

            if (prevPrimaryColor != null && prevSecondaryColor != null) {
                setPrimaryPixelColor(prevPrimaryColor!!)
                setSecondaryPixelColor(prevSecondaryColor!!)
            }

            if (prevToolStr != null) {
                currentTool = if (Tools.findToolByName(prevToolStr!!) != null) {
                    Tools.findToolByName(prevToolStr!!)!!
                } else {
                    Tools.PencilTool
                }

                toolsFragmentInstance?.tapOnToolByName(prevToolStr!!)
            }

            if (prevBrushStr != null) {
                pixelGridViewInstance.currentBrush =
                    BrushesDatabase.toList().find { it.brushName == prevBrushStr }
            }

            if (prevTab != 0) {
                binding.activityCanvasTabLayout.getTabAt(prevTab)?.select()
            }

            if (prevUndoToolbarButtonDisabledEnabledState) {
                menu.findItem(R.id.activityCanvasTopAppMenu_undo).enable()
            }

            if (prevRedoToolbarButtonDisabledEnabledState) {
                menu.findItem(R.id.activityCanvasTopAppMenu_redo_item).enable()
            }

            if (prevUndoStackJsonStr != null) {
                pixelGridViewInstance.bitmapActionData =
                    JsonConverter.convertJsonStringToListOfBitmapAction(
                        prevUndoStackJsonStr!!
                    ).toMutableList()
            }

            if (prevRedoStackJsonStr != null) {
                pixelGridViewInstance.undoStack =
                    JsonConverter.convertJsonStringToListOfBitmapAction(
                        prevRedoStackJsonStr!!
                    ).toMutableList()
            }

            if (prevSymmetryModeStr != null) {
                pixelGridViewInstance.symmetryMode =
                    SymmetryMode.values().first { it.symmetryName == prevSymmetryModeStr }

                when (pixelGridViewInstance.symmetryMode) {
                    SymmetryMode.Horizontal -> {
                        menu.findItem(R.id.appMenu_symmetry_horizontal_subItem).isChecked = true
                    }

                    SymmetryMode.Vertical -> {
                        menu.findItem(R.id.appMenu_symmetry_vertical_subItem).isChecked = true
                    }

                    SymmetryMode.Quad -> {
                        menu.findItem(R.id.appMenu_symmetry_quad_subItem).isChecked = true
                    }

                    else -> {}
                }
            }

            if (prevRotation != 0) {
                outerCanvasInstance.rotate(prevRotation, animate = false)
            }

            replacedBMP = false
            prevOrientation = resources.configuration.orientation
        }
    }
}