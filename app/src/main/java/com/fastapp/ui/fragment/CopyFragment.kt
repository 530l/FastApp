package com.fastapp.ui.fragment

import android.os.Bundle
import androidx.fragment.app.activityViewModels
import com.drake.logcat.LogCat
import com.fast.base.BaseActivity
import com.fastapp.base.BaseBindingFragment
import com.fastapp.databinding.CopyFragmentBinding
import com.fastapp.vm.TaskViewModel
import com.hjq.toast.ToastUtils

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


    override fun initView() {
        //共享viewModel
        val viewModel: TaskViewModel by activityViewModels()
        viewModel.mutableLiveData.observe(viewLifecycleOwner) {
            ToastUtils.show(it)
        }
        viewModel.show()
        //view-model，mutableLiveData 没有回收，viewLifecycleOwner 回收了，
        LogCat.i(
            "viewModel add = $viewModel  " +
                    "viewLifecycleOwner add = $viewLifecycleOwner" +
                    "mutableLiveData add = ${viewModel.mutableLiveData}"
        )
    }

    override fun initData() {


    }
}