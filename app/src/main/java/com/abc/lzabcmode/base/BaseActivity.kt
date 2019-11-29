package com.abc.lzabcmode.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.abc.lzabcmode.icallback.IBaseActivity

/**
 * @name lz
 * @time 2019/10/15 17:09
 */
abstract class BaseActivity<T:ViewDataBinding> : AppCompatActivity(), IBaseActivity<T> {

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