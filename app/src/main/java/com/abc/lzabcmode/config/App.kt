package com.abc.lzabcmode.config

import android.app.Application
import com.abc.lzabcmode.greendao.DaoMaster
import com.abc.lzabcmode.greendao.DaoSession
import org.greenrobot.greendao.query.QueryBuilder

/**
 * @name lz
 * @time 2019/11/7 17:46
 */
class App : Application() {
    companion object StaticParams {
        var sApp: App? = null
        fun getApp(): App? {
            return sApp
        }
    }

    private var daoSession: DaoSession? = null

    override fun onCreate() {
        super.onCreate()
        sApp = this
        initGreenDao()
    }

    /**
     * 初始化GreenDao,直接在Application中进行初始化操作
     */
    private fun initGreenDao() {
        val helper = DaoMaster.DevOpenHelper(this, "abcDao.db")
        val db = helper.writableDatabase
        val daoMaster = DaoMaster(db)
        daoSession = daoMaster.newSession()
        QueryBuilder.LOG_SQL = true
        QueryBuilder.LOG_VALUES = true
    }

    fun getDaoSession(): DaoSession? {
        return daoSession
    }

}