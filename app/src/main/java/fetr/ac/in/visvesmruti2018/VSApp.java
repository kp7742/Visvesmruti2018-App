package fetr.ac.in.visvesmruti2018;

import android.app.Application;
import android.content.Context;

public class VSApp extends Application {

    private static Context ctx;

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        ctx = base;
    }

    public static Context getContext(){
        return ctx;
    }
}
