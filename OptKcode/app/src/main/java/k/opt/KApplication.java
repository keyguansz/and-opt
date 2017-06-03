package k.opt;

import android.app.Application;
import android.content.Context;
import android.content.res.Configuration;

import k.opt.monitor.time.TimeMonitorConfig;
import k.opt.monitor.time.TimeMonitorManager;

/**
 * Created by yuchengluo on 2015/6/25.
 */
public class KApplication extends Application {

    private static Context mContext = null;

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        mContext = this;

    }

    public static Context getmContext() {
        return mContext;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        InitModule();
        TimeMonitorManager.getInstance().getTimeMonitor(TimeMonitorConfig.TIME_MONITOR_ID_APPLICATION_START)
                .recodingTimeTag("ApplicationCreate");
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }

    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
    }

    public static Context getContext() {
        return mContext;
    }

    private void InitModule() {
      /*  DBManager.InitDB(mContext);
        CrashHandler crashHandler = new CrashHandler();
        crashHandler.init(this);*/
      for (int i=0;i<1000*1000;i++)
          for (int j=0;j<1000*1000;j++){

          }
    }
}
