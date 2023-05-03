package com.therealbluepandabear.pixapencil.activities.main.oncreate.addMenuProvider

import android.view.Menu
import com.therealbluepandabear.pixapencil.R
import com.therealbluepandabear.pixapencil.activities.main.MainActivity

fun MainActivity.onCreateMenu(_menu: Menu?): Boolean {
    val inflater = menuInflater
    inflater.inflate(R.menu.activity_main_top_app_menu, _menu)
    
    if (_menu != null) {
        menu = _menu

    }

    return true
}