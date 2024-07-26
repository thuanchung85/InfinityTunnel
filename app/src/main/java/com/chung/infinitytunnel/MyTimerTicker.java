package com.chung.infinitytunnel;
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

    public void setListener(MySurfaceView mySurfaceView) {
        this.listener = mySurfaceView;
    }
}