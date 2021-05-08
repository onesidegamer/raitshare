package com.onesidegamer.raitshare.app;

import android.content.SharedPreferences;

import com.onesidegamer.raitshare.database.AccessDatabase;
import com.onesidegamer.raitshare.util.AppUtils;
import com.onesidegamer.raitshare.util.NotificationUtils;
abstract public class Service extends android.app.Service
{
    private NotificationUtils mNotificationUtils;

    public AccessDatabase getDatabase()
    {
        return AppUtils.getDatabase(this);
    }

    public SharedPreferences getDefaultPreferences()
    {
        return AppUtils.getDefaultPreferences(getApplicationContext());
    }

    public NotificationUtils getNotificationUtils()
    {
        if (mNotificationUtils == null)
            mNotificationUtils = new NotificationUtils(getApplicationContext(), getDatabase(), getDefaultPreferences());

        return mNotificationUtils;
    }
}
