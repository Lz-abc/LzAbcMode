package com.abc.lzabcmode.ui.adapter

import android.graphics.Color
import android.widget.TextView
import com.abc.lzabcmode.R
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder

/**
 * @name lz
 * @time 2019/10/15 17:38
 */
class TabListAdapter(tabList: List<String>?) :
    BaseQuickAdapter<String, BaseViewHolder>(R.layout.item_tab_main, tabList) {

    override fun convert(helper: BaseViewHolder?, item: String?) {
        helper?.let {
            var tvTab=it.getView<TextView>(R.id.tvTab)
            tvTab.text = item
            if (it.adapterPosition%2==0){
                tvTab.setBackgroundColor(Color.WHITE)
            }else{
                tvTab.setBackgroundColor(Color.LTGRAY)
            }
        }
    }

}