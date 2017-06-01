package k.opt.activity;

import android.app.Activity;
import android.os.Bundle;
import android.os.Debug;
import android.os.Trace;

import k.opt.R;


public class DemoActivity extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
      //  Debug.startMethodTracing("kopt");

        try{
            Trace.beginSection("t1");
            setContentView(R.layout.activity_demo);
        }finally {
            Trace.endSection();
        }


       // Debug.stopMethodTracing();
    }
}
