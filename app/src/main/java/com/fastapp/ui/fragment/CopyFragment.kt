package com.fastapp.ui.fragment

import android.os.Bundle
import android.widget.TextView
import androidx.fragment.app.viewModels
import com.drake.brv.utils.*
import com.drake.logcat.LogCat
import com.fast.base.BaseActivity
import com.fastapp.R
import com.fastapp.base.BaseBindingFragment
import com.fastapp.config.glide.GlideApp
import com.fastapp.databinding.CopyFragmentBinding
import com.fastapp.entity.Game
import com.fastapp.entity.Student
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

    private val viewModel: TaskViewModel by viewModels() // 创建ViewModel

    override fun initView() {
        //共享viewModel
//        val viewModel: TaskViewModel by activityViewModels()
        viewModel.mutableLiveData.observe(viewLifecycleOwner) {
            ToastUtils.show(it)
        }
        viewModel.show()
        //view-model，mutableLiveData 没有回收，viewLifecycleOwner 回收了，
        LogCat.i("viewModel add = $viewModel ")
        LogCat.i("viewLifecycleOwner add = $viewLifecycleOwner")
        LogCat.i("mutableLiveData add = ${viewModel.mutableLiveData}")
    }

    private val list: MutableList<Student> = ArrayList()
    override fun initData() {
        repeat(10) {
            list.add(Student())
        }
        GlideApp.with(this)
            .load("https://img.zcool.cn/community/01233056fb62fe32f875a9447400e1.jpg")
//            .transform(MultiTransformation(CenterCrop(), CircleCrop()))
            .into(binding.image)

        binding.image.setOnClickListener {
//            if (binding.recyclerView.models.isNullOrEmpty()) {
//                repeat(10) {
//                    list.add(Student())
//                }
//            } else {
//                list.clear()
//            }
            repeat(10) {
                list.add(Student())
            }
            // 覆盖原来的列表,里面已经notifyDataSetChanged,
            // 并且判断了是否是首次加载的 isFirst lastPosition itemCount
            binding.recyclerView.models = list
        }

        binding.recyclerView.linear().setup {
            addType<Student>(R.layout.item_simple)
            onBind {
                findView<TextView>(R.id.tv_simple).text = bindingAdapterPosition.toString()
            }
            R.id.tv_simple.onClick {
                com.drake.tooltip.toast("点击文本")
            }
        }.models = list
    }
}