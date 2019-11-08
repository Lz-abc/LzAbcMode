package com.abc.lzabcmode.utils;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.abc.lzabcmode.ui.bean.MediaData;
import com.orhanobut.logger.Logger;

import pl.droidsonroids.gif.GifDrawable;

/**
 * @name lz
 * @time 2019/10/12 17:10
 */
public class MediaPlayerTools<T> implements MediaPlayer.OnCompletionListener, MediaPlayer.OnPreparedListener {


    public static class ViewModel {
        public TextView tv;
        public ImageView iv;
        public ProgressBar bar;
        public MediaData data;
    }


    private MediaPlayer mPlayer;
    private T mViewModel;
    private int mPosition = 0;
    private String mAudioPath = "-1";
    private String mTag = "-1";//唯一标记
    private String mKey;
    private boolean isLoadComplete = false;
    private boolean isPlay = false;
    private int mCurrType = TYPE_STOP;
    public static final int TYPE_PREPARE = 3;//准备中
    public static final int TYPE_RUNNING = 0;//播放中
    public static final int TYPE_PAUSE = 1;//暂停
    public static final int TYPE_STOP = 2;//结束
    public static final int TYPE_START = 4;//开始播放

    private OnMediaPlayerListener mMediaPlayerListener;

    private MediaPlayerTools() {
        initMediaPlayer();
    }

    private void initMediaPlayer() {
        mPlayer = new MediaPlayer();
        mPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
    }

    public static class Instance {
        public static MediaPlayerTools sPlayerTools = new MediaPlayerTools();
    }

    public static MediaPlayerTools getInstance() {
        return Instance.sPlayerTools;
    }


    /**
     * 该方法应在setListener 方法之后 避免上一个播放视图无法重置
     * @param position
     * @param t
     * @return
     */
    public MediaPlayerTools setViewModel(int position, T t) {
        mPosition = position;
        mViewModel = t;
        return Instance.sPlayerTools;
    }

    /**
     * 更新viewModel 多列表使用
     * @param tag 当前列表唯一值 建议设置model Id
     * @param key 列表唯一标记
     * @param position 当前列表下标
     * @param t viewModel 用于传递不同状态下需要用的view 或数据
     */
    public void updateViewModelKey(String tag, String key, int position, T t) {
        if (!key.equals(mKey)) {
            return;
        }
        updateViewModel(tag, position, t);
    }

    /**
     * 更新viewModel 单列表使用 需要在adapter 绘制里调用
     * @param tag 当前列表唯一值 建议设置model Id
     * @param position 当前列表下标
     * @param t viewModel 用于传递不同状态下需要用的view 或数据
     */
    public void updateViewModel(String tag, int position, T t) {
        if (tag.equals(mTag) && mPosition == position) {
            mViewModel = t;
            if (mMediaPlayerListener != null) {
                mMediaPlayerListener.onPlay(mViewModel);
            }
        } else if (Math.abs(position - mPosition) > 5) {
            if (mMediaPlayerListener != null && mViewModel != null) {
                mMediaPlayerListener.onStop(mViewModel);
            }
            mViewModel = null;
        }
    }

    /**
     * 设置播放资源 并装载
     * 设置相同资源时不会重新装载(即key、tag、audioPath 都相同)
     * @param tag
     * @param audioPath
     * @return
     */
    public MediaPlayerTools setAudioPath(String tag, final String audioPath) {
        if (mAudioPath.equals(audioPath) && mTag.equals(tag)) {
            //与上次播放的一致  不做重新装载
            return Instance.sPlayerTools;
        }
        if (!TextUtils.isEmpty(mAudioPath)) {
            stop();
        }
        mAudioPath = audioPath;
        mTag = tag;
        mCurrType = TYPE_PREPARE;
        mHandler.sendEmptyMessage(mCurrType);
        ThreadManager.getInstance(1).excute(new Runnable() {
            @Override
            public void run() {
                try {
                    initMediaPlayer();//重新创建播放器；避免音视频穿插播放后卡死
                    mPlayer.reset();
                    mPlayer.setDataSource(audioPath);
                    mPlayer.setOnPreparedListener(MediaPlayerTools.this);
                    mPlayer.setOnCompletionListener(MediaPlayerTools.this);
                    mPlayer.setOnErrorListener(new MediaPlayer.OnErrorListener() {
                        @Override
                        public boolean onError(MediaPlayer mp, int what, int extra) {
                            Logger.d("播放失败");
                            return false;
                        }
                    });
                    mPlayer.prepareAsync();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        return Instance.sPlayerTools;
    }

    public MediaPlayerTools setAudioPathKey(String key, String tag, final String audioPath) {
        if (mAudioPath.equals(audioPath) && mTag.equals(tag) && key.equals(mKey)) {
            //与上次播放的一致  不做重新装载
            return Instance.sPlayerTools;
        }
        return setAudioPath(tag,audioPath);
    }

    /**
     * 该方法应在setViewModel 方法之前 避免上一个播放视图无法重置
     * @param key
     * @param tag
     * @param mediaPlayerListener
     * @return
     */
    public MediaPlayerTools setKeyListener(String key, String tag, OnMediaPlayerListener mediaPlayerListener) {
        if (mMediaPlayerListener != null && mViewModel != null && (!tag.equals(mTag) || !key.equals(mKey))) {
            mMediaPlayerListener.onStop(mViewModel);
        }
        mMediaPlayerListener = mediaPlayerListener;
        return Instance.sPlayerTools;
    }
    /**
     * 该方法应在setViewModel 方法之前 避免上一个播放视图无法重置
     * @param tag
     * @param mediaPlayerListener
     * @return
     */
    public MediaPlayerTools setListener( String tag, OnMediaPlayerListener mediaPlayerListener) {
        if (mMediaPlayerListener != null && mViewModel != null && !tag.equals(mTag)) {
            mMediaPlayerListener.onStop(mViewModel);
        }
        mMediaPlayerListener = mediaPlayerListener;
        return Instance.sPlayerTools;
    }
    /**
     * 有多个列表且存在相同tag时使用
     * 播放or暂停
     *
     * @param key 不同列表标签
     */
    public void playKey(String key) {
        mKey = key;
        play();
    }

    /**
     * 播放or暂停
     */
    public void play() {
        isPlay = true;
        if (mPlayer == null) {
            return;
        }
        if (mPlayer.isPlaying()) {
            pause();
            isLoadComplete = true;
            isPlay = false;
            return;
        }
        if (!isLoadComplete) {
            return;
        }
        isLoadComplete = false;
        mPlayer.start();

        mHandler.removeCallbacksAndMessages(null);
        mCurrType = TYPE_START;
        mHandler.sendEmptyMessage(mCurrType);

        isPlay = false;
    }

    /**
     * 单独暂停
     */
    public void pause() {
        if (mPlayer == null) {
            return;
        }
        if (mPlayer.isPlaying()) {
            mPlayer.pause();
        }
        mCurrType = TYPE_PAUSE;
        mHandler.removeCallbacksAndMessages(null);
        mHandler.sendEmptyMessage(mCurrType);
    }

    /**
     * 结束
     */
    public void stop() {
        if (mPlayer == null) {
            return;
        }
        mPlayer.seekTo(0);
        if (mPlayer.isPlaying()) {
            mPlayer.stop();
        }
        mCurrType = TYPE_STOP;
        mHandler.removeCallbacksAndMessages(null);
        mHandler.sendEmptyMessage(mCurrType);
        mPlayer.release();
        mPlayer = null;
        mAudioPath = "";
        mTag = "";
        mKey = "";
        System.gc();
    }

    /**
     * 获取当前播放器状态
     *
     * @return
     */
    public boolean isRunning() {
        if (mPlayer == null) {
            return false;
        }
        return mPlayer.isPlaying();
    }

    /**
     * 获取当前播放器的资源地址
     * @return
     */
    public String getAudioPath() {
        return mAudioPath;
    }

    @Override
    public void onCompletion(MediaPlayer mp) {
        stop();
    }

    @Override
    public void onPrepared(MediaPlayer mp) {
        isLoadComplete = true;
        if (isPlay) {
            play();
        }
    }

    private Handler mHandler = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case TYPE_PREPARE:
                    if (mMediaPlayerListener != null && mViewModel != null) {
                        mMediaPlayerListener.onPrepare(mViewModel);
                    }
                    break;
                case TYPE_START:
                    if (mMediaPlayerListener != null && mViewModel != null) {
                        mMediaPlayerListener.onPlay(mViewModel);
                    }
                    mCurrType = TYPE_RUNNING;
                    mHandler.sendEmptyMessage(mCurrType);
                    break;
                case TYPE_RUNNING://正常计时
                    if (mMediaPlayerListener != null && mPlayer != null && mViewModel != null) {
                        mMediaPlayerListener.onProgress(mPlayer, mViewModel);
                    }
                    mHandler.sendEmptyMessageDelayed(TYPE_RUNNING, 1000);
                    break;
                case TYPE_PAUSE://暂停
                    if (mMediaPlayerListener != null && mViewModel != null) {
                        mMediaPlayerListener.onPause(mViewModel);
                    }
                    break;
                case TYPE_STOP://结束
                    if (mMediaPlayerListener != null && mViewModel != null) {
                        mMediaPlayerListener.onStop(mViewModel);
                    }
                    break;
            }
        }
    };


    public interface OnMediaPlayerListener<T> {
        void onPrepare(T t);

        void onPlay(T t);

        void onPause(T t);

        void onProgress(MediaPlayer mp, T t);

        void onStop(T t);
    }
}
