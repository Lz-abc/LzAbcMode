package com.abc.lzabcmode.base

import android.app.Activity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.abc.lzabcmode.R
import com.abc.lzabcmode.icallback.IBaseActivity
import com.abc.lzabcmode.ui.views.SimpleBaseLoadView
import com.gyf.immersionbar.ImmersionBar

/**
 * @name lz
 * @time 2019/10/15 17:09
 */
abstract class BaseActivity<T:ViewDataBinding> : Activity(), IBaseActivity<T> {

    lateinit var mDataBinding:T

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mDataBinding= DataBindingUtil.setContentView(this,setLayoutId())
        mDataBinding?.let {
            init(it)
        }
    }

//    var mLoadView: SimpleBaseLoadView? = null
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        ImmersionBar.with(this).init()
//        mLoadView = SimpleBaseLoadView(this, setLayoutId())
//        setContentView(get().layoutView)
//        get().showBar(true, true)
//            init()
//    }
//
//    fun get(): SimpleBaseLoadView {
//        return mLoadView!!
//    }
}