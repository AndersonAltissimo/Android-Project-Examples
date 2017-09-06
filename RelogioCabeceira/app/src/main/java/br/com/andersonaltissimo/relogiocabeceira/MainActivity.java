package br.com.andersonaltissimo.relogiocabeceira;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private ViewHolder vh = new ViewHolder();
    private Handler handler = new Handler();
    private Runnable runnable;
    private boolean runnableStopped = false;
    private boolean isBatteryOn = true;

    private BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            int level = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, 0);
            vh.txtBatteryLevel.setText(String.valueOf(level) + "%");
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        this.vh.txtHourMinute = (TextView) findViewById(R.id.txt_hour_minute);
        this.vh.txtSeconds = (TextView) findViewById(R.id.txt_seconds);
        this.vh.chkBattery = (CheckBox) findViewById(R.id.chkBattery);
        this.vh.txtBatteryLevel = (TextView) findViewById(R.id.txt_battery_level);
        this.vh.imgOption = (ImageView) findViewById(R.id.imgOptions);
        this.vh.imgClose = (ImageView) findViewById(R.id.imgClose);
        this.vh.linearOptions = (LinearLayout) findViewById(R.id.linearOption);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        this.registerReceiver(this.receiver, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));

        this.vh.chkBattery.setChecked(true);
        this.vh.linearOptions.animate().translationY(500);
        this.setListener();
    }

    private void setListener() {
        this.vh.chkBattery.setOnClickListener(this);
        this.vh.imgOption.setOnClickListener(this);
        this.vh.imgClose.setOnClickListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        this.InitBedside();
        this.runnableStopped = false;
    }

    @Override
    protected void onStop() {
        super.onStop();
        this.runnableStopped = true;
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.chkBattery){
            this.toggleCheckBattery();
        } else if (v.getId() == R.id.imgOptions){
            this.vh.linearOptions.animate()
                    .translationY(0)
                    .setDuration(getResources().getInteger(android.R.integer.config_mediumAnimTime));
        } else if (v.getId() == R.id.imgClose){

            this.vh.linearOptions.setVisibility(View.GONE);
            this.vh.linearOptions.animate()
                    .translationY(this.vh.linearOptions.getMeasuredHeight())
                    .setDuration(getResources().getInteger(android.R.integer.config_mediumAnimTime));
        }
    }

    private void toggleCheckBattery() {
        if (this.isBatteryOn){
            this.isBatteryOn = false;
            this.vh.txtBatteryLevel.setVisibility(View.GONE);
        } else{
            this.isBatteryOn = true;
            this.vh.txtBatteryLevel.setVisibility(View.VISIBLE);
        }
    }

    private void InitBedside() {

        final Calendar calendar = Calendar.getInstance();

        this.runnable = new Runnable() {
            @Override
            public void run() {

                if (runnableStopped){
                    return;
                }

                calendar.setTimeInMillis(System.currentTimeMillis());

                String hourMinFormat = String.format("%02d:02d",calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE));
                String secondsFormat = String.format("%02d",calendar.get(Calendar.SECOND));

                vh.txtHourMinute.setText(hourMinFormat);
                vh.txtSeconds.setText(secondsFormat);

                long now = SystemClock.uptimeMillis();
                long next = now + (1000 - (now % 1000));
                handler.postAtTime(runnable, next);
            }
        };
        this.runnable.run();
    }

    private static class ViewHolder{
        TextView txtHourMinute;
        TextView txtSeconds;
        CheckBox chkBattery;
        TextView txtBatteryLevel;
        ImageView imgOption;
        ImageView imgClose;
        LinearLayout linearOptions;
    }
}
