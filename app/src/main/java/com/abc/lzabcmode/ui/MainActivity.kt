package com.abc.lzabcmode.ui

import android.content.Intent
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import com.abc.baseloadview.utils.ScreenUtils
import com.abc.lzabcmode.R
import com.abc.lzabcmode.base.BaseActivity
import com.abc.lzabcmode.databinding.ActivityMainBinding
import com.abc.lzabcmode.ui.adapter.TabListAdapter
import com.abc.lzabcmode.ui.lucky.LuckyMainActivity
import com.abc.lzabcmode.ui.media.MediaPlayerListActivity
import com.chad.library.adapter.base.BaseQuickAdapter
import com.gyf.immersionbar.ImmersionBar
import kotlinx.android.synthetic.main.base_title_layout.barView

class MainActivity : BaseActivity<ActivityMainBinding>() {

    override fun setLayoutId(): Int {
        return R.layout.activity_main
    }

    override fun init(dataBinding: ActivityMainBinding) {
        ImmersionBar.with(this).init()
        var adapter = TabListAdapter(listOf("MediaPlayerList", "LuckyNumber", "3", "4"))
        dataBinding.tabAdapter = adapter
        adapter.onItemClickListener =
            BaseQuickAdapter.OnItemClickListener { _, _, position ->
                when (position) {
                    0 -> {
                        toActivity(MediaPlayerListActivity::class.java)
                    }
                    1 -> {
                        toActivity(LuckyMainActivity::class.java)
                    }
                    2 -> {

                    }
                }
            }
        barView.visibility = View.VISIBLE
        setStatusBarHeight()
    }

    private fun toActivity(t: Class<*>) {
        startActivity(Intent(this, t))
    }

    /**
     * 設置填充状态栏高度
     */
    private fun setStatusBarHeight() {
        barView.layoutParams = ConstraintLayout.LayoutParams(
            ConstraintLayout.LayoutParams.MATCH_PARENT,
            ScreenUtils.getStatusBarHeight(this) + ScreenUtils.getBarHeight(
                this
            )
        )
    }
}
