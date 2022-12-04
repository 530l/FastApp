package com.fastapp.ui.activity


import androidx.activity.viewModels
import com.drake.logcat.LogCat
import com.drake.net.Get
import com.drake.net.utils.scopeNetLife
import com.drake.serialize.serialize.serial
import com.drake.serialize.serialize.serialLazy
import com.drake.serialize.serialize.serialLiveData
import com.fastapp.R
import com.fastapp.base.BaseBindingActivity
import com.fastapp.databinding.CopyActivityBinding
import com.fastapp.entity.DataDTO
import com.fastapp.entity.Game
import com.fastapp.vm.MyViewModel
import com.fastapp.entity.SerializableModel
import com.fastapp.ui.fragment.CopyFragment
import com.hjq.bar.TitleBar
import com.therouter.router.Route
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
@Route(path = "http://fastapp/copy", action = "abc")
class CopyActivity : BaseBindingActivity<CopyActivityBinding>() {

//    lateinit var viewModel: TaskViewModel

    override fun initView() {
        val copyTitleFragment = CopyFragment.newInstance()
        val mTransaction = supportFragmentManager.beginTransaction()
        mTransaction.add(R.id.mContainerView, copyTitleFragment)
        mTransaction.commit()
    }

    /** 每次都读写磁盘 */
    private var name: String by serial()

    /** 懒读取, 每次写入都会更新内存/磁盘, 但是读取仅第一次会读取磁盘, 后续一直使用内存中, 有效减少ANR */
    private var model: SerializableModel? by serialLazy()

    private var simple: String by serial("默认值", "自定义键名")

    private val viewModel: MyViewModel by viewModels()

    private val liveData by serialLiveData("默认值")

    override fun initData() {
        //viewModel = ViewModelProvider(this)[TaskViewModel::class.java]

//        name = "我把530写入本地磁盘中。"
        LogCat.e(name)

        scopeNetLife { // 创建作用域
            // 这个大括号内就属于作用域内部
            Get<DataDTO?>("/article/list/0/json") {
                param("u_name", "drake")
                param("pwd", "123456")
            }.await()?.let {
                LogCat.e("#￥￥#" + it.pageCount)
            }
        }
    }

    override fun onLeftClick(titleBar: TitleBar?) {
        finish()
    }
}