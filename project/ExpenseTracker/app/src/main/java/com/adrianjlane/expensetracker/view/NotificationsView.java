package com.adrianjlane.expensetracker.view;

import android.content.Context;
import android.view.Gravity;
import android.widget.Toast;

public class NotificationsView {

    public static void showToast(Context context, String message) {

        Toast toast = Toast.makeText(context, message, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.TOP, 0, 0);
        toast.show();
    }
}
