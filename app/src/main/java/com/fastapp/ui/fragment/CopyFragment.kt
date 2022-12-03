package com.fastapp.ui.fragment

import android.os.Bundle
import androidx.fragment.app.viewModels
import com.fast.base.BaseActivity
import com.fastapp.base.BaseBindingFragment
import com.fastapp.databinding.CopyFragmentBinding
import com.fastapp.vm.DetailedTaskViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CopyFragment : BaseBindingFragment<BaseActivity, CopyFragmentBinding>() {

    companion object {
        fun newInstance(): CopyFragment {
            val args = Bundle()
            args.putString("name", "530")
            val fragment = CopyFragment()
            fragment.arguments = args
            return fragment
        }
    }

    private val viewModel : DetailedTaskViewModel by viewModels()


    override fun initView() {



    }

    override fun initData() {


    }
}