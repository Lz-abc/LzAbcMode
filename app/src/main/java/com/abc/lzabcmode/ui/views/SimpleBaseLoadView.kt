package com.abc.lzabcmode.ui.views

import android.annotation.SuppressLint
import android.content.Context
import android.content.LocusId
import com.abc.baseloadview.R
import com.abc.baseloadview.views.IBaseLoadView

/**
 * @name lz
 * @time 2019/10/16 9:30
 */
class SimpleBaseLoadView(context: Context,layoutId: Int) : IBaseLoadView(context,layoutId) {

    override fun getLoadingViewId(): Int {
        return R.id.loadingView
    }

    override fun getLoadLayoutId(): Int {
        return R.layout.layout
    }

    override fun getErrorViewId(): Int {
        return R.id.errorView
    }

    override fun getBarViewId(): Int {
        return R.id.barView
    }

    override fun getContentViewId(): Int {
        return R.id.contentView
    }

    override fun isCloseLoadVew(): Boolean {
        return false
    }

    override fun getEmptyViewId(): Int {
        return R.id.emptyView
    }
}