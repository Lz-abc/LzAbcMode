package com.abc.lzabcmode.ui.fr

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentActivity
import com.abc.lzabcmode.R
import com.abc.lzabcmode.ui.fr.ui.home.HomeFragment
import com.jkb.fragment.rigger.annotation.Puppet
import com.jkb.fragment.rigger.rigger.Rigger

@Puppet(containerViewId = R.id.frame_layout)
class FrActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fr)
    }

    fun btn(view:View){
        Rigger.getRigger(this).startFragment(HomeFragment())
    }
}
