package com.jkds.iospopup;
import android.app.Application;

import com.jkds.dialog.util.BaseDialog;
import com.jkds.dialog.util.DialogSettings;
public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        DialogSettings.init();
        DialogSettings.autoShowInputKeyboard=false;
    }


    @Override
    public void onTerminate() {
        BaseDialog.unload();
        super.onTerminate();
    }
}
