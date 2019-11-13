package com.abc.lzabcmode.utils

import android.app.Notification
import android.content.Context
import android.content.pm.PackageManager
import android.content.Intent
import android.widget.Toast
import android.os.Build



/**
 * @name lz
 * @time 2019/11/13 15:23
 */
class BadgeUtil {

    companion object Util{
        /**
         * Set badge count<br></br>
         * 针对 Samsung / xiaomi / sony 手机有效
         * @param context The context of the application package.
         * @param count Badge count to be set
         */
        fun setBadgeCount(notification: Notification, context: Context, count: Int) {
            var count = count
            if (count <= 0) {
                count = 0
            } else {
                count = Math.max(0, Math.min(count, 99))
            }

            if (Build.MANUFACTURER.equals("Xiaomi", ignoreCase = true)) {
                sendToXiaoMi(notification, context, count)
            } else if (Build.MANUFACTURER.equals("sony", ignoreCase = true)) {
                sendToSony(context, count)
            } else if (Build.MANUFACTURER.toLowerCase().contains("samsung")) {
                sendToSamsumg(context, count)
            } else {
                Toast.makeText(context, "Not Support", Toast.LENGTH_LONG).show()
            }
        }

        /**
         * 向小米手机发送未读消息数广播
         * @param count
         */
        fun sendToXiaoMi(notification: Notification, context: Context, count: Int) {
            try {
                //            Class miuiNotificationClass = Class.forName("android.app.MiuiNotification");
                //            Object miuiNotification = miuiNotificationClass.newInstance();
                //            Field field = miuiNotification.getClass().getDeclaredField("messageCount");
                //            field.setAccessible(true);
                //            field.set(miuiNotification, String.valueOf(count == 0 ? "" : count));  // 设置信息数-->这种发送必须是miui 6才行

                val field = notification.javaClass.getDeclaredField("extraNotification")

                val extraNotification = field.get(notification)

                val method = extraNotification.javaClass.getDeclaredMethod(
                    "setMessageCount",
                    Int::class.javaPrimitiveType
                )

                method.invoke(extraNotification, count)

            } catch (e: Exception) {
                e.printStackTrace()
                // miui 6之前的版本
                val localIntent = Intent(
                    "android.intent.action.APPLICATION_MESSAGE_UPDATE"
                )
                localIntent.putExtra(
                    "android.intent.extra.update_application_component_name",
                    context.getPackageName() + "/" + getLauncherClassName(context)
                )
                localIntent.putExtra(
                    "android.intent.extra.update_application_message_text",
                    (if (count == 0) "" else count).toString()
                )
                context.sendBroadcast(localIntent)
            }

        }


        /**
         * 向索尼手机发送未读消息数广播<br></br>
         * 据说：需添加权限：<uses-permission android:name="com.sonyericsson.home.permission.BROADCAST_BADGE"></uses-permission> [未验证]
         * @param count
         */
        fun sendToSony(context: Context, count: Int) {
            val launcherClassName = getLauncherClassName(context) ?: return

            var isShow = true
            if (count == 0) {
                isShow = false
            }
            val localIntent = Intent()
            localIntent.action = "com.sonyericsson.home.action.UPDATE_BADGE"
            localIntent.putExtra("com.sonyericsson.home.intent.extra.badge.SHOW_MESSAGE", isShow)//是否显示
            localIntent.putExtra(
                "com.sonyericsson.home.intent.extra.badge.ACTIVITY_NAME",
                launcherClassName
            )//启动页
            localIntent.putExtra(
                "com.sonyericsson.home.intent.extra.badge.MESSAGE",
                count.toString()
            )//数字
            localIntent.putExtra(
                "com.sonyericsson.home.intent.extra.badge.PACKAGE_NAME",
                context.getPackageName()
            )//包名
            context.sendBroadcast(localIntent)
        }


        /**
         * 向三星手机发送未读消息数广播
         * @param count
         */
        fun sendToSamsumg(context: Context, count: Int) {
            val launcherClassName = getLauncherClassName(context) ?: return
            val intent = Intent("android.intent.action.BADGE_COUNT_UPDATE")
            intent.putExtra("badge_count", count)
            intent.putExtra("badge_count_package_name", context.getPackageName())
            intent.putExtra("badge_count_class_name", launcherClassName)
            context.sendBroadcast(intent)
        }


        /**
         * 重置、清除Badge未读显示数<br></br>
         * @param context
         */
        fun resetBadgeCount(notification: Notification, context: Context) {
            setBadgeCount(notification, context, 0)
        }
        /**
         * Retrieve launcher activity name of the application from the context
         *
         * @param context The context of the application package.
         * @return launcher activity name of this application. From the
         * "android:name" attribute.
         */
        fun getLauncherClassName(context: Context): String? {
            val packageManager = context.getPackageManager()

            val intent = Intent(Intent.ACTION_MAIN)
            // To limit the components this Intent will resolve to, by setting an
            // explicit package name.
            intent.setPackage(context.getPackageName())
            intent.addCategory(Intent.CATEGORY_LAUNCHER)

            // All Application must have 1 Activity at least.
            // Launcher activity must be found!
            var info = packageManager
                .resolveActivity(intent, PackageManager.MATCH_DEFAULT_ONLY)

            // get a ResolveInfo containing ACTION_MAIN, CATEGORY_LAUNCHER
            // if there is no Activity which has filtered by CATEGORY_DEFAULT
            if (info == null) {
                info = packageManager.resolveActivity(intent, 0)
            }

            return info!!.activityInfo.name
        }
    }
}