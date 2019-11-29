package com.abc.lzabcmode.ui.fr.ui.notifications

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
class NotificationsFragment : Fragment() {

    private lateinit var notificationsViewModel: NotificationsViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        notificationsViewModel =
            ViewModelProviders.of(this).get(NotificationsViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_notifications, container, false)
        val textView: TextView = root.findViewById(R.id.text_notifications)
        notificationsViewModel.text.observe(this, Observer {
            textView.text = it
        })
        return root
    }

    fun onLazyLoadViewCreated(savedInstanceState: Bundle) {
        Toast.makeText(activity,"NotificationsFragment onLazyLoadViewCreated", Toast.LENGTH_LONG).show()
    }
}