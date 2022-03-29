package com.therealbluepandabear.pixapencil.fragments.newcolorpalette

fun setOnClickListeners() {
    binding.fragmentNewColorPaletteDoneButton.setOnClickListener {
        val extractColorPaletteFromCanvasCheckboxState = binding.fragmentNewColorPaletteExtractFromCanvasCheckBox.isChecked
        caller.onDoneButtonPressed(binding.fragmentNewColorPaletteColorPaletteNameTextInputEditText.text.toString(), extractColorPaletteFromCanvasCheckboxState)
    }
}