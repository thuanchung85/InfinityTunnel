package com.chung.infinitytunnel.SharedCode;
import com.chung.infinitytunnel.Character1.Character1;

import java.util.Timer;
import java.util.TimerTask;

public class MyTimerTicker {
    private Timer timer;
    private TimerTickerListener listener;
    public MyTimerTicker() {
        timer = new Timer();
    }

    public void start(long intervalMillis) {
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                // Code thực hiện theo định kỳ
                onTick();
            }
        };
        timer.scheduleAtFixedRate(task, 0, intervalMillis);
    }

    public void stop() {
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
    }

    private void onTick() {
        // Implement your periodic task here
        //System.out.println("Tick");
        listener.onTick();
    }

    public void setListener(Character1 character1) {
        this.listener = character1;
    }
}