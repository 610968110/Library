package com.lbx.library;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.lbx.library.ui.activity.LoginActivity;

/**
 * Copyright © 2013-2019 Worktile. All Rights Reserved.
 * Author: liboxin
 * Email: liboxin@worktile.com
 * Date: 2019/5/28
 * Time: 09:50
 * Desc:
 */
public class BootReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        LoginActivity.getIntent(context).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK).start();
    }
}
