package com.ifeng_tech.spotmall.myapplication;

import android.content.Context;

import me.leolin.shortcutbadger.ShortcutBadger;

/**
 * Created by weiminze on 2018/5/29.
 */

public class BadgeCountUtils {
    //设置APP logo的角标
    public static void setBadgeCount(Context context, int badgeCount) {
        ShortcutBadger.applyCount(context, badgeCount);
    }

    //移除角标
    public static void removeBadge(Context context) {
        ShortcutBadger.removeCount(context);
    }
}
