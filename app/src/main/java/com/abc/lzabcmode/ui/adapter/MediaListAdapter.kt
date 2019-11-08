package com.abc.lzabcmode.ui.adapter

import com.abc.lzabcmode.R
import com.abc.lzabcmode.ui.bean.MediaData
import com.abc.lzabcmode.utils.MediaPlayerTools
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import pl.droidsonroids.gif.GifDrawable

/**
 * @name lz
 * @time 2019/10/16 11:54
 */
class MediaListAdapter(datas: List<MediaData>?) :
    BaseQuickAdapter<MediaData, BaseViewHolder>(R.layout.item_media, datas) {

    override fun convert(helper: BaseViewHolder?, item: MediaData?) {
        helper?.let {
            item?.let {data->
                it.setText(R.id.talent_news_name,data.name)
                it.setText(R.id.talent_duration,"${data.time}s")
                it.addOnClickListener(R.id.talent_news_record_ll)

                var viewModel = MediaPlayerTools.ViewModel()
                viewModel.data = item
                viewModel.bar = it.getView(R.id.talent_bar)
                viewModel.iv = it.getView(R.id.gifImgView)
                viewModel.tv = it.getView(R.id.talent_duration)
                MediaPlayerTools.getInstance().updateViewModel("${item.id}",it.adapterPosition, viewModel)
            }
        }
    }

}