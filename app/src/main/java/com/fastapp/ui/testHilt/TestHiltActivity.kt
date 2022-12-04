package com.fastapp.ui.testHilt

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.fastapp.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TestHiltActivity :AppCompatActivity (){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.copy_activity)
        val copyTitleFragment = TestHiltFragment.newInstance()
        val mTransaction = supportFragmentManager.beginTransaction()
        mTransaction.add(R.id.mContainerView, copyTitleFragment)
        mTransaction.commit()
    }
}