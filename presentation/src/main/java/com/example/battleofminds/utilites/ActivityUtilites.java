package com.example.battleofminds.utilites;

import android.app.Activity;
import android.content.Intent;

public class ActivityUtilites {

    private static ActivityUtilites activityUtilites = null;

    public static ActivityUtilites getInstance(){

        if (activityUtilites == null){
            activityUtilites = new ActivityUtilites();
        }

        return activityUtilites;
    }

    public void invokeNewActivity(Activity activity, Class<?> tClass, boolean shouldFinish){

        Intent intent = new Intent(activity, tClass);
        activity.startActivity(intent);

        if (shouldFinish){
            activity.finish();
        }
    }
}
