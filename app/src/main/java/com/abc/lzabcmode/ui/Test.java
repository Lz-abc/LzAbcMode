package com.abc.lzabcmode.ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;

import com.abc.lzabcmode.utils.MediaPlayerTools;

/**
 * @name lz
 * @time 2019/10/16 11:25
 */
public class Test {
    public void toActivity(Activity context, Class t){
        context.startActivity(new Intent(context,t));
    }

    public void set(){
        MediaPlayerTools.getInstance().setViewModel(1,new MediaPlayerTools.ViewModel())
                .setListener("tag",
                        new MediaPlayerTools.OnMediaPlayerListener() {
                            @Override
                            public void onPrepare(Object o) {

                            }

                            @Override
                            public void onPlay(Object o) {

                            }

                            @Override
                            public void onPause(Object o) {

                            }

                            @Override
                            public void onProgress(MediaPlayer mp, Object o) {

                            }

                            @Override
                            public void onStop(Object o) {

                            }
                        }).setAudioPath("","").play();

    }
}
