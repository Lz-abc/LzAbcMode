package com.abc.lzabcmode.icallback

import androidx.databinding.ViewDataBinding


/**
 * @name lz
 * @time 2019/10/15 17:11
 */
interface IBaseActivity<T:ViewDataBinding>{
    fun setLayoutId():Int
    fun init(dataBinding: T)
}