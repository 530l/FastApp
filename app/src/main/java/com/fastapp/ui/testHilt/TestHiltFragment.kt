package com.fastapp.ui.testHilt

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.fastapp.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TestHiltFragment :Fragment(R.layout.copy_fragment) {

    companion object {
        fun newInstance(): TestHiltFragment {
            val args = Bundle()
            args.putString("name", "530")
            val fragment = TestHiltFragment()
            fragment.arguments = args
            return fragment
        }
    }
}