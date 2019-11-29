package com.abc.lzabcmode.ui.fr;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.abc.lzabcmode.R;
import com.abc.lzabcmode.ui.fr.ui.home.HomeFragment;
import com.jkb.fragment.rigger.annotation.Puppet;
import com.jkb.fragment.rigger.rigger.Rigger;

/**
 * @name lz
 * @time 2019/11/29 17:03
 */
@Puppet(containerViewId = R.id.frame_layout)
public class FrJavaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fr);
    }

    public void  btn(View view){
        Rigger.getRigger(this).startFragment(new HomeFragment());
    }
}
