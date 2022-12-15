package com.fastapp.ui.testHilt

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.fastapp.R
import dagger.hilt.android.AndroidEntryPoint
import net.lingala.zip4j.ZipFile
import java.util.zip.ZipInputStream
import javax.inject.Inject


@AndroidEntryPoint
class TestHiltActivity : AppCompatActivity() {

    //这个字段的值就是注入的
    @Inject
    lateinit var programmer: Programmer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.copy_activity)
        val copyTitleFragment = TestHiltFragment.newInstance()
        val mTransaction = supportFragmentManager.beginTransaction()
        mTransaction.add(R.id.mContainerView, copyTitleFragment)
        mTransaction.commit()

         ZipFile("filename.zip")

    }
}

//提供该依赖的地方，后面说
class Programmer @Inject constructor(){

    var name: String = "张三"
    var age: Int = 20

    override fun toString() : String{
        return "姓名:$name  年龄:$age"
    }
}

