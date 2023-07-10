package com.trodev.medicarepro.activities;

import static android.os.Build.VERSION_CODES.O;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.timepicker.MaterialTimePicker;
import com.google.android.material.timepicker.TimeFormat;
import com.trodev.medicarepro.R;

import java.util.Calendar;

public class MakeAlarmActivity extends AppCompatActivity {

    private Button setAlarm, selectTime, cencelAlarm;
    private TextView timer;
    MaterialTimePicker picker;
    Calendar calendar;
    AlarmManager alarmManager;
    PendingIntent pendingIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_alarm);

        createNotificationChannel();

        final Ringtone r = RingtoneManager.getRingtone(getApplicationContext(), RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE));

        setAlarm = findViewById(R.id.setAlarmBtn);
        selectTime = findViewById(R.id.selectTimeBtn);
        cencelAlarm = findViewById(R.id.cencelBtn);
        timer = findViewById(R.id.timer);


        selectTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                showTimePicker();

            }
        });


        setAlarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                setAlarms();

            }
        });

        cencelAlarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                cencel();

            }
        });

    }

    private void cencel() {


        Intent intent = new Intent(this, AlarmRecevier.class);

        pendingIntent = PendingIntent.getBroadcast(this, 0, intent, 0);

        if (alarmManager == null) {
            alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        }

        alarmManager.cancel(pendingIntent);
        Toast.makeText(this, "Alarm Canceled", Toast.LENGTH_SHORT).show();

    }

    private void setAlarms() {

        alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);


       // int i = Integer.parseInt(timer.getText().toString());

        Intent intent = new Intent(this, AlarmRecevier.class);

        pendingIntent = PendingIntent.getBroadcast(this, 0, intent, 0);

        alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_FIFTEEN_MINUTES, pendingIntent);

        Toast.makeText(this, "Alarm Set Successful", Toast.LENGTH_SHORT).show();

    }

    private void showTimePicker() {

        picker = new MaterialTimePicker.Builder()
                .setTimeFormat(TimeFormat.CLOCK_12H)
                .setHour(12)
                .setMinute(0)
                .setTitleText("Select Alarm Time")
                .build();

        picker.show(getSupportFragmentManager(), "medicarepro");
/////
        picker.addOnPositiveButtonClickListener(new View.OnClickListener() {
            @SuppressLint("DefaultLocale")
            @Override
            public void onClick(View v) {


                if (picker.getHour() >= 12) {
                    timer.setText(
                            String.format("%02d", (picker.getHour() - 12)) + " : " + String.format("%02d", picker.getMinute()) + " PM"
                    );
                } else {
                    timer.setText(picker.getHour() + " : " + picker.getMinute() + " AM");
                }

                calendar = Calendar.getInstance();
                calendar.set(Calendar.HOUR_OF_DAY, picker.getHour());
                calendar.set(Calendar.MINUTE, picker.getMinute());
                calendar.set(Calendar.SECOND, 0);
                calendar.set(Calendar.MILLISECOND, 0);

            }
        });

    }

    private void createNotificationChannel() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "MedicareproChannel";
            String description = "Channel for Alarm Manager";
            int impontance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel channel = null;
            if (Build.VERSION.SDK_INT >= O) {
                channel = new NotificationChannel("medicarepro", name, impontance);
            }
            if (Build.VERSION.SDK_INT >= O) {
                channel.setDescription(description);
            }

            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            if (Build.VERSION.SDK_INT >= O) {
                notificationManager.createNotificationChannel(channel);
            }
        }
    }
}