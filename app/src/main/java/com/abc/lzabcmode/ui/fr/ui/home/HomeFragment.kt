package com.abc.lzabcmode.ui.fr.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.abc.lzabcmode.R
import com.jkb.fragment.rigger.annotation.LazyLoad
import com.jkb.fragment.rigger.annotation.Puppet


@LazyLoad
@Puppet
class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
            ViewModelProviders.of(this).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)
        val textView: TextView = root.findViewById(R.id.text_home)
        homeViewModel.text.observe(this, Observer {
            textView.text = it
        })
        return root
    }

    fun onLazyLoadViewCreated(savedInstanceState: Bundle) {
        Toast.makeText(activity,"HomeFragment onLazyLoadViewCreated",Toast.LENGTH_LONG).show()
    }
}