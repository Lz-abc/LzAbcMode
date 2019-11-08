package com.abc.lzabcmode.ui.lucky

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.databinding.DataBindingUtil.setContentView
import com.abc.lzabcmode.R
import com.abc.lzabcmode.base.BaseActivity
import com.abc.lzabcmode.databinding.ActivityLuckyNumberBinding
import com.abc.lzabcmode.ui.views.TEditText
import com.abc.lzabcmode.ui.views.TInputConnection
import kotlinx.android.synthetic.main.activity_lucky_number.*
import java.util.ArrayList

class LuckyNumberActivity : BaseActivity<ActivityLuckyNumberBinding>() {

    private val codeViewList = ArrayList<TEditText>()
    private var mIndex = 0
    private var isBack = false
    private var codeResultListener: OnResultListener? = null

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
            mIndex++
            if (mIndex < codeViewList.size) {
                codeViewList[mIndex].requestFocus()
            } else {
                //输入完成，开始验证
                if (codeResultListener != null) {
                    val code = StringBuilder()
                    for (et in codeViewList) {
                        code.append(et.text.toString())
                    }
                    Handler().postDelayed({
                        codeResultListener!!.onCodeVerification(code.toString())
                    }, 200)
                }
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
                codeViewList[mIndex].setText("")
                codeViewList[mIndex].requestFocus()
            } else {
                mIndex = 0
                isBack = false
            }
            false
        }

    /**
     * 核对验证码
     */
    interface OnResultListener {
        fun onCodeVerification(code: String)

        fun onGetCode(phone: String)
    }
}
