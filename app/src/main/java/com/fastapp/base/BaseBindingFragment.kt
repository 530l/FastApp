package com.fastapp.base


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding

import com.fast.base.BaseFragment
import com.dylanc.viewbinding.base.FragmentBinding
import com.dylanc.viewbinding.base.FragmentBindingDelegate
import com.fast.base.BaseActivity
import com.fastapp.action.ToastAction

/**
Fragment 业务基类
 */
abstract class BaseBindingFragment<A:BaseActivity, VB : ViewBinding> : BaseFragment<A>(),
    FragmentBinding<VB> by FragmentBindingDelegate(), ToastAction {


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        rootView = createViewWithBinding(inflater, container)
        return super.onCreateView(inflater, container, savedInstanceState)
    }
}