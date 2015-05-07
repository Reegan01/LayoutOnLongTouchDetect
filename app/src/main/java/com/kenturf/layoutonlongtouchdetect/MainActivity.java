package com.kenturf.layoutonlongtouchdetect;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;


public class MainActivity extends ActionBarActivity {


    RelativeLayout imgLayout;
    Bitmap bitmap;
    RelativeLayout.LayoutParams rparams;
    int i = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imgLayout = (RelativeLayout)findViewById(R.id.imgView);
        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.pinimgnew);


        /* Single Touch Listener */

        /* Bro  Bojan Kseneman I can do this single Instant touch using this setOnTouchListener But I want Long Touch to enable this -
        *  Bro I also want single touch and long touch
        *  Because the single touch is using zoom the image , long press is using set the pin image */

        imgLayout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int x = (int) event.getX();
                int y = (int) event.getY();

                rparams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,RelativeLayout.LayoutParams.WRAP_CONTENT);
                rparams.addRule(RelativeLayout.ACCESSIBILITY_LIVE_REGION_ASSERTIVE);
                rparams.leftMargin = x;
                rparams.topMargin = y;
                rparams.height = 48;
                rparams.width = 60;
                final ImageView btn = new ImageView(getBaseContext());
                btn.setId(i + 1);
                btn.setImageBitmap(bitmap);
                btn.setLayoutParams(rparams);
                imgLayout.addView(btn);
                i++;

                return false;
            }
        });


        /* Long Touch Listener */

        /* Bro This makes error Please help me */

        final Handler handler = new Handler();
        Runnable mLongPressed = new Runnable() {
            public void run() {
                Log.i("", "Long press!");
                if(myEvent!=null)
                {
                    int requiredXvalue=myEvent.getX();
                    int requiredYvalue=myEvent.getY();
                    rparams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,RelativeLayout.LayoutParams.WRAP_CONTENT);
                    rparams.addRule(RelativeLayout.ACCESSIBILITY_LIVE_REGION_ASSERTIVE);
                    rparams.leftMargin = requiredXvalue;
                    rparams.topMargin = requiredXvalue;
                    rparams.height = 48;
                    rparams.width = 60;
                    final ImageView btn = new ImageView(getBaseContext());
                    btn.setId(i + 1);
                    btn.setImageBitmap(bitmap);
                    btn.setLayoutParams(rparams);
                    imgLayout.addView(btn);
                    i++;
                }
            }
        };
        MotionEvent myEvent;
        @Override
        public boolean onTouchEvent(MotionEvent event, RelativeLayout layout){
            if(event.getAction() == MotionEvent.ACTION_DOWN)
            {
                handler.postDelayed(mLongPressed, 1000);
                myEvent=event;
            }
            if((event.getAction() == MotionEvent.ACTION_MOVE)||(event.getAction() == MotionEvent.ACTION_UP))
                handler.removeCallbacks(mLongPressed);
            return super.onTouchEvent(event, mapView);
        }

        /* error end */




    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
