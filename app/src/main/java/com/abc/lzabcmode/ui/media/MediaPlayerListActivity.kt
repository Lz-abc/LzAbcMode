package com.abc.lzabcmode.ui.media

import android.media.MediaPlayer
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.ExpandableListView
import com.abc.lzabcmode.R
import com.abc.lzabcmode.base.BaseActivity
import com.abc.lzabcmode.databinding.ActivityMediaPlayerListBinding
import com.abc.lzabcmode.ui.adapter.MediaListAdapter
import com.abc.lzabcmode.ui.bean.MediaData
import com.abc.lzabcmode.ui.views.LoadMoreViews
import com.abc.lzabcmode.utils.MediaPlayerTools
import com.chad.library.adapter.base.BaseQuickAdapter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_media_player_list.*
import pl.droidsonroids.gif.GifDrawable
import java.io.IOException

class MediaPlayerListActivity : BaseActivity<ActivityMediaPlayerListBinding>() {

    override fun setLayoutId(): Int {
        return R.layout.activity_media_player_list
    }

    var gifDrawable: GifDrawable? = null

    override fun init(dataBinding: ActivityMediaPlayerListBinding) {
        var mediaAdapter = MediaListAdapter(
            arrayListOf(
                MediaData(
                    0,
                    "http://music.163.com/song/media/outer/url?id=1396377448.mp3",
                    318,
                    "我怀疑你曾来过"
                ),
                MediaData(
                    1,
                    "http://music.163.com/song/media/outer/url?id=1396973729.mp3",
                    182,
                    "大田後生仔"
                ),
                MediaData(
                    2,
                    "http://music.163.com/song/media/outer/url?id=255294.mp3",
                    213,
                    "我很快乐"
                ),
                MediaData(
                    3,
                    "http://music.163.com/song/media/outer/url?id=1339023244.mp3",
                    295,
                    "探清水河"
                ),
                MediaData(
                    4,
                    "http://music.163.com/song/media/outer/url?id=1340543218.mp3",
                    285,
                    "人间不值得"
                ),
                MediaData(
                    5,
                    "http://music.163.com/song/media/outer/url?id=1396377448.mp3",
                    318,
                    "我怀疑你曾来过"
                ),
                MediaData(
                    6,
                    "http://music.163.com/song/media/outer/url?id=1396973729.mp3",
                    182,
                    "大田後生仔"
                ),
                MediaData(
                    7,
                    "http://music.163.com/song/media/outer/url?id=255294.mp3",
                    213,
                    "我很快乐"
                ),
                MediaData(
                    8,
                    "http://music.163.com/song/media/outer/url?id=1339023244.mp3",
                    295,
                    "探清水河"
                ),
                MediaData(
                    9,
                    "http://music.163.com/song/media/outer/url?id=1340543218.mp3",
                    285,
                    "人间不值得"
                ),
                MediaData(
                    10,
                    "http://music.163.com/song/media/outer/url?id=1396377448.mp3",
                    318,
                    "我怀疑你曾来过"
                ),
                MediaData(
                    11,
                    "http://music.163.com/song/media/outer/url?id=1396973729.mp3",
                    182,
                    "大田後生仔"
                ),
                MediaData(
                    12,
                    "http://music.163.com/song/media/outer/url?id=255294.mp3",
                    213,
                    "我很快乐"
                ),
                MediaData(
                    13,
                    "http://music.163.com/song/media/outer/url?id=1339023244.mp3",
                    295,
                    "探清水河"
                ),
                MediaData(
                    14,
                    "http://music.163.com/song/media/outer/url?id=1340543218.mp3",
                    285,
                    "人间不值得"
                )
            )
        )
        mediaAdapter.setOnLoadMoreListener(
            {
              addMode(mediaAdapter)
            },
            rvMedia
        )
        mediaAdapter.setLoadMoreView(LoadMoreViews())
        mediaAdapter.disableLoadMoreIfNotFullPage()

        mediaAdapter.setOnItemChildClickListener { adapter, view, position ->
            if (view.id == R.id.talent_news_record_ll) {
                var mediaData = adapter.data[position] as MediaData
                var viewModel = MediaPlayerTools.ViewModel()
                viewModel.data = mediaData
                viewModel.bar = view.findViewById(R.id.talent_bar)
                viewModel.iv = view.findViewById(R.id.gifImgView)
                viewModel.tv = view.findViewById(R.id.talent_duration)
                MediaPlayerTools.getInstance()
                    .setListener("${mediaData.id}",
                        object :
                            MediaPlayerTools.OnMediaPlayerListener<MediaPlayerTools.ViewModel> {
                            override fun onPrepare(t: MediaPlayerTools.ViewModel?) {
                                t?.let {
                                    it.tv.visibility = View.GONE
                                    it.bar.visibility = View.VISIBLE
                                }
                            }

                            override fun onPlay(t: MediaPlayerTools.ViewModel?) {
                                t?.let {
                                    it.tv.visibility = View.VISIBLE
                                    it.bar.visibility = View.GONE
                                    try {
                                        gifDrawable = GifDrawable(
                                            resources,
                                            R.drawable.item_record
                                        )//设置资源对象
                                        it.iv.background = gifDrawable
                                        gifDrawable!!.start()
                                    } catch (e: IOException) {
                                        e.printStackTrace()
                                    }

                                }
                            }

                            override fun onPause(t: MediaPlayerTools.ViewModel?) {
                                t?.let {
                                    gifDrawable?.let { gif ->
                                        gif.stop()
                                    }
                                    it.iv.setBackgroundResource(0)
                                }
                            }

                            override fun onProgress(
                                mp: MediaPlayer?,
                                t: MediaPlayerTools.ViewModel?
                            ) {
                                t?.let { t ->
                                    mp?.let {
                                        val time = t.data.time - mp.currentPosition / 1000
                                        t.tv.text = (if (time < 0) 0 else time).toString() + "s"
                                    }
                                }
                            }

                            override fun onStop(t: MediaPlayerTools.ViewModel?) {
                                t?.let {
                                    it.tv.text = "${t.data.time}s"
                                    gifDrawable?.let { gif ->
                                        gif.stop()
                                    }
                                    it.iv.setBackgroundResource(0)
                                    if (it.bar.visibility === View.VISIBLE) {
                                        it.bar.visibility = View.GONE
                                    }
                                    if (it.tv.visibility !== View.VISIBLE) {
                                        it.tv.visibility = View.VISIBLE
                                    }
                                }
                            }
                        }).setViewModel(position, viewModel)
                    .setAudioPath("${mediaData.id}", mediaData.path).play()
            }
        }

        dataBinding.mediaAdapter = mediaAdapter
    }

    fun addMode(mediaAdapter:MediaListAdapter){
        Thread(Runnable {
            Thread.sleep(2000)
            Handler(Looper.getMainLooper()).post {
                mediaAdapter.addData(mediaAdapter.data)
                mediaAdapter.loadMoreComplete()
            }
        }).start()
    }

    override fun onDestroy() {
        super.onDestroy()
        MediaPlayerTools.getInstance().stop()
    }

}
