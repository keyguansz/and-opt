package k.opt.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Trace;
import android.util.ArrayMap;
import android.view.View;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import k.opt.R;
import k.opt.ch2Draw.LayoutPerActivity;
import k.opt.monitor.time.TimeMonitorConfig;
import k.opt.monitor.time.TimeMonitorManager;


public class MainActivity extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //  Debug.startMethodTracing("kopt");

        try{
            Trace.beginSection("t1");
            setContentView(R.layout.activity_main);
        }finally {
            Trace.endSection();
        }


       // Debug.stopMethodTracing();
        testLeak();
        testAutoBoxing();
    }

    private void testAutoBoxing() {
        TimeMonitorManager.getInstance().getTimeMonitor(TimeMonitorConfig.TIME_MONITOR_ID_JAVA)
                .recodingTimeTag("test1 start");
        int test1 = 0;
        for (int i = 0;i<1000*1000;i++){
            test1 += i;
        }
        TimeMonitorManager.getInstance().getTimeMonitor(TimeMonitorConfig.TIME_MONITOR_ID_JAVA)
                .recodingTimeTag("test1 end,test2 start");
        Integer test2 = 0;
        for (int i = 0;i<1000*1000;i++){
            test2 += i;
        }
        TimeMonitorManager.getInstance().getTimeMonitor(TimeMonitorConfig.TIME_MONITOR_ID_JAVA)
                .recodingTimeTag("test2 end");
        TimeMonitorManager.getInstance().getTimeMonitor(TimeMonitorConfig.TIME_MONITOR_ID_JAVA)
                .end(false);
    }

    private void testLeak() {findViewById(R.id.test_leak).setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            startActivity(new Intent(MainActivity.this, LayoutPerActivity.class));
            getLevel(0);
        }
    });
    }
    public static final int UI_PERF_LEVEL_0 = 0;
    public static final int UI_PERF_LEVEL_1 = 2;
    @IntDef({UI_PERF_LEVEL_0,UI_PERF_LEVEL_1})
    @Retention(RetentionPolicy.SOURCE)
    public @interface PERF_LEVEL {
    }
    public int getLevel(@PERF_LEVEL  int level){
        switch (level){
            case UI_PERF_LEVEL_0:
                return 0;
            case UI_PERF_LEVEL_1:
                return  2;
            default:
                throw new IllegalAccessException("unkwon");
        }
    }
    public enum UI_PERF{
        LEVEL_0,
        LEVEL_1
    }
    public int getLevel(UI_PERF level){
        switch (level){
            case LEVEL_0:
                return 0;
            case LEVEL_1:
                return  2;
            default:
                throw new IllegalAccessException("unkwon");
        }
    }

}
