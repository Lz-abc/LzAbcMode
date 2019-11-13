package com.abc.lzabcmode.ui.lucky

import android.app.Notification
import android.content.Intent
import com.abc.lzabcmode.R
import com.abc.lzabcmode.base.BaseActivity
import com.abc.lzabcmode.databinding.ActivityLuckyMainBinding
import com.abc.lzabcmode.greendao.LuckyNumberData
import com.abc.lzabcmode.ui.adapter.LuckyListAdapter
import com.abc.lzabcmode.utils.BadgeUtil
import com.abc.lzabcmode.utils.GreenDaoUtils

import kotlinx.android.synthetic.main.activity_lucky_main.*
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

class LuckyMainActivity : BaseActivity<ActivityLuckyMainBinding>() {

    var adapter: LuckyListAdapter? = null

    override fun setLayoutId(): Int {
        return R.layout.activity_lucky_main
    }

    override fun init(dataBinding: ActivityLuckyMainBinding) {
        val list = GreenDaoUtils.queryAllData()
        adapter = LuckyListAdapter(list)
        dataBinding.luckyAdapter = adapter
        fabAdd.setOnClickListener {
            startActivity(Intent(this, LuckyNumberActivity::class.java))
        }
    }

    private val redLuckyList =ArrayList<Int>()
    private val blueLuckyList = ArrayList<Int>()

    private fun initMap() {
        val redArrays=GreenDaoUtils.queryRedData(1)
        for (key in 1..33) {
            redLuckyList.add(key)
        }
        for (key in 1..15) {
            blueLuckyList.add(key)
        }
    }

    private fun addRedLucky(value: Int) {
        redLuckyList[redLuckyList.size + 1] = value
    }

    private fun addBlueLucky(value: Int) {
        blueLuckyList[blueLuckyList.size + 1] = value
    }

    private fun createLucky(): String {
        val redLuckys = ArrayList<Int>()
        val blueLuckys =  ArrayList<Int>()
        redLuckys.addAll(redLuckyList)
        blueLuckys.addAll(blueLuckyList)

        val random = Random()
        var lucky = ""
        for (i in 1..6) {
            val index = random.nextInt(redLuckys.size)
            val value=redLuckys[index]
            lucky += value
            lucky += ","
            redLuckys.remove(value)
        }
        return "$lucky${blueLuckys[random.nextInt(blueLuckys.size)]}"
    }

}
