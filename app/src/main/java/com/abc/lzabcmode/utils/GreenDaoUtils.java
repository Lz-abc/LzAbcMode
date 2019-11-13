package com.abc.lzabcmode.utils;

import com.abc.lzabcmode.config.App;
import com.abc.lzabcmode.greendao.LuckyNumberData;
import com.abc.lzabcmode.greendao.LuckyNumberDataDao;

import java.util.List;


/**
 * @name lz
 * @time 2019/8/13 10:18
 */
public class GreenDaoUtils {

    /**
     * 添加数据
     *
     * @param data 用户数据对象
     * @return 数据id
     */
    public static long addData(LuckyNumberData data) {
        LuckyNumberDataDao daoSession = App.StaticParams.getApp().getDaoSession().getLuckyNumberDataDao();
        return daoSession.insert(data);
    }

    /**
     * 更新/修改
     * @param userData
     * @return
     */
    public static long updateData(LuckyNumberData userData){
        LuckyNumberDataDao daoSession = App.StaticParams.getApp().getDaoSession().getLuckyNumberDataDao();
       return daoSession.insertOrReplace(userData);
    }

    /**
     * 查询所有数据
     *
     * @return
     */
    public static List<LuckyNumberData> queryAllData() {
        LuckyNumberDataDao daoSession = App.StaticParams.getApp().getDaoSession().getLuckyNumberDataDao();
        return daoSession.queryBuilder().list();
    }


    /**
     * 查询红球包含指定数值的数据
     * @return
     */
    public static List<LuckyNumberData> queryRedData(int number) {
        LuckyNumberDataDao daoSession = App.StaticParams.getApp().getDaoSession().getLuckyNumberDataDao();
        return daoSession.queryBuilder().whereOr(LuckyNumberDataDao.Properties.Number1.eq(number),
                LuckyNumberDataDao.Properties.Number2.eq(number),
                LuckyNumberDataDao.Properties.Number3.eq(number),
                LuckyNumberDataDao.Properties.Number4.eq(number),
                LuckyNumberDataDao.Properties.Number5.eq(number),
                LuckyNumberDataDao.Properties.Number6.eq(number)
        ).list();
    }

    /**
     * 查询蓝球包含指定数值的数据
     * @return
     */
    public static List<LuckyNumberData> queryBuleData(int number) {
        LuckyNumberDataDao daoSession = App.StaticParams.getApp().getDaoSession().getLuckyNumberDataDao();
        return daoSession.queryBuilder().where(LuckyNumberDataDao.Properties.Number7.eq(number)).list();
    }


    /**
     *  删除
     * @param taskData
     */
    public static void deleteData(LuckyNumberData taskData) {
        LuckyNumberDataDao daoSession = App.StaticParams.getApp().getDaoSession().getLuckyNumberDataDao();
        daoSession.delete(taskData);
    }
}
