package com.abc.lzabcmode.ui.adapter

import android.graphics.Color
import android.widget.LinearLayout
import android.widget.TextView
import com.abc.lzabcmode.R
import com.abc.lzabcmode.greendao.LuckyNumberData
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder

/**
 * @name lz
 * @time 2019/10/15 17:38
 */
class LuckyListAdapter(tabList: List<LuckyNumberData>?) :
    BaseQuickAdapter<LuckyNumberData, BaseViewHolder>(R.layout.item_lucky, tabList) {

    private val viewList = arrayOf(
        R.id.etCode1,
        R.id.etCode2,
        R.id.etCode3,
        R.id.etCode4,
        R.id.etCode5,
        R.id.etCode6,
        R.id.etCode7
    )

    override fun convert(helper: BaseViewHolder?, item: LuckyNumberData?) {
        helper?.let {
            item?.luckyList?.let { array ->
                for ((index, viewId) in viewList.withIndex()) {
                    helper.setText(viewId, "${array[index]}")
                }
                helper.setText(R.id.tvIssueNumber,"${item.issueNumber}")
                helper.setText(R.id.tvTime,"${item.time}")

//                var tvTab=it.getView<LinearLayout>(R.id.layout)
//                if (it.adapterPosition%2==0){
//                    tvTab.setBackgroundColor(Color.WHITE)
//                }else{
//                    tvTab.setBackgroundColor(Color.LTGRAY)
//                }
            }
        }
    }

}