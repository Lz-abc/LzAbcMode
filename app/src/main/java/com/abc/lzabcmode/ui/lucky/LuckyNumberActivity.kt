package com.abc.lzabcmode.ui.lucky

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.databinding.DataBindingUtil.setContentView
import com.abc.httplibs.utils.ToastUtils
import com.abc.lzabcmode.R
import com.abc.lzabcmode.base.BaseActivity
import com.abc.lzabcmode.databinding.ActivityLuckyNumberBinding
import com.abc.lzabcmode.greendao.LuckyNumberData
import com.abc.lzabcmode.ui.views.TEditText
import com.abc.lzabcmode.ui.views.TInputConnection
import com.abc.lzabcmode.utils.GreenDaoUtils
import kotlinx.android.synthetic.main.activity_lucky_number.*
import java.util.ArrayList

class LuckyNumberActivity : BaseActivity<ActivityLuckyNumberBinding>() {

    private val codeViewList = ArrayList<TEditText>()
    private var mIndex = 0
    private var isBack = false
    private val mMap = HashMap<Int, Int>()

    override fun init(dataBinding: ActivityLuckyNumberBinding) {

        etCode1.addTextChangedListener(textWatcher)
        etCode2.addTextChangedListener(textWatcher)
        etCode3.addTextChangedListener(textWatcher)
        etCode4.addTextChangedListener(textWatcher)
        etCode5.addTextChangedListener(textWatcher)
        etCode6.addTextChangedListener(textWatcher)
        etCode7.addTextChangedListener(textWatcher)

        etCode1.setBackSpaceLisetener(textBack)
        etCode2.setBackSpaceLisetener(textBack)
        etCode3.setBackSpaceLisetener(textBack)
        etCode4.setBackSpaceLisetener(textBack)
        etCode5.setBackSpaceLisetener(textBack)
        etCode6.setBackSpaceLisetener(textBack)
        etCode7.setBackSpaceLisetener(textBack)

        codeViewList.add(etCode1)
        codeViewList.add(etCode2)
        codeViewList.add(etCode3)
        codeViewList.add(etCode4)
        codeViewList.add(etCode5)
        codeViewList.add(etCode6)
        codeViewList.add(etCode7)

        viewId.setOnClickListener {
            //判断键盘是否显示，未显示显示软键盘
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS)
            when (mIndex) {
                0 -> etCode1.requestFocus()
                1 -> etCode2.requestFocus()
                2 -> etCode3.requestFocus()
                3 -> etCode4.requestFocus()
                4 -> etCode5.requestFocus()
                5 -> etCode6.requestFocus()
                6 -> etCode7.requestFocus()
            }
        }
        tvBtn.setOnClickListener {
            //输入完成，开始验证
            val code = StringBuilder()
            for (et in codeViewList) {
                code.append("${et.text.toString()},")
            }
            if (TextUtils.isEmpty(code.toString())) {
                return@setOnClickListener
            }
            val luckyData = LuckyNumberData()
            luckyData.time = "${System.currentTimeMillis()}"
            luckyData.issueNumber = "101010"
            luckyData.luckyArrays = "${code.toString().substring(0, code.toString().length - 1)}"
            GreenDaoUtils.addData(luckyData)
        }
    }

    override fun setLayoutId(): Int {
        return R.layout.activity_lucky_number
    }

    private var textWatcher: TextWatcher = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {

        }

        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
            if (isBack) {
                isBack = false
                return
            }
            if (mIndex >= codeViewList.size) {
                tvErrorMsg.text = "已达最大长度，无法输入"
                return
            }
            if (s.length == 1) {
                if (mIndex == 6 && s.toString().toInt() <= 1) {
                    return
                }
                if (s.toString().toInt() <= 3) {
                    return
                }
            } else if (s.length > 1){
                if (mIndex == 6 && s.toString().toInt() > 15) {
                    tvErrorMsg.text = "请输入正确的数字"
                    codeViewList[mIndex].setText("")
                    return
                } else if (s.toString().toInt() > 33) {
                    tvErrorMsg.text = "请输入正确的数字"
                    codeViewList[mIndex].setText("")
                    return
                }
            }else{
                return
            }
            val value = s.toString().toInt()
            if (mMap[value]!=null&&mMap[value]==value&&mIndex!=6) {
                tvErrorMsg.text = "不能输入相同数字"
                codeViewList[mIndex].setText("")
                return
            }
            if (mIndex<6) {
                mMap[value] = value
            }
            mIndex++
            if (mIndex < codeViewList.size) {
                codeViewList[mIndex].requestFocus()
            }
        }

        override fun afterTextChanged(s: Editable) {

        }
    }


    private var textBack: TInputConnection.BackspaceListener =
        TInputConnection.BackspaceListener {
            isBack = true
            mIndex--
            if (mIndex >= 0) {
                val txt=codeViewList[mIndex].text.toString().toInt()
                mMap.remove(txt)
                codeViewList[mIndex].setText("")
                codeViewList[mIndex].requestFocus()
            } else {
                mIndex = 0
                isBack = false
            }
            false
        }
}
