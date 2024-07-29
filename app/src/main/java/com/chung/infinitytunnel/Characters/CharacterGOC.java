package com.chung.infinitytunnel.Characters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.annotation.NonNull;

import com.chung.infinitytunnel.SharedCode.MyTimerTicker;
import com.chung.infinitytunnel.SharedCode.TimerTickerListener;

import java.util.ArrayList;

public abstract class CharacterGOC extends SurfaceView implements SurfaceHolder.Callback , TimerTickerListener
{
    boolean running = true;
    Point centerOfCanvas;

    MyTimerTicker myTimerTicker;

    ArrayList<Bitmap> array_bitmaps = new ArrayList<>() ;
    int nextIndex = 0;
    Bitmap currentBMP ;
    int rectW = 1;
    int rectH = 1;

    public CharacterGOC(Context context) {
        super(context);
        getHolder().addCallback(this);

        //active timer
        myTimerTicker = new MyTimerTicker();
        myTimerTicker.setListener(this);

        this.setClickable(true);

    }

    public void start_UpdateLoopWithTimerTick()
    {
        myTimerTicker.start(150); // Ticker mỗi 1 giây
    }
    public void stop_UpdateLoopWithTimerTick(){
        myTimerTicker.stop();
    }



    @Override
    public void surfaceCreated(@NonNull SurfaceHolder holder)
    {


        //lấy trung tâm màn hình phone
        int canvasW = getWidth();
        int canvasH = getHeight();
        centerOfCanvas = new Point(canvasW / 2, canvasH / 2);

        //chạy update show currentBMP theo timer tick để tạo hoạt cảnh
        new Thread(() -> {
            while (running) {
                Canvas canvas = holder.lockCanvas();
                if (canvas != null) {
                    try {
                        canvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR); // Clear canvas với màu trong suốt


                        int left = centerOfCanvas.x - (rectW / 2);
                        int top = centerOfCanvas.y - (rectH / 2);
                        int right = centerOfCanvas.x + (rectW / 2);
                        int bottom = centerOfCanvas.y + (rectH / 2);
                        Rect rect = new Rect(left, top, right, bottom);

                        canvas.drawBitmap(currentBMP, null, rect, null);
                    } finally {
                        holder.unlockCanvasAndPost(canvas);
                    }
                }

            }
        }).start();
    }

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder holder, int format, int width, int height) {
        // Xử lý thay đổi kích thước hoặc định dạng nếu cần
    }

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder holder) {
        running = false; // Dừng luồng vẽ khi SurfaceView bị hủy
        if (myTimerTicker != null) {
            currentBMP = null;
            centerOfCanvas = null;
            array_bitmaps.clear();
            myTimerTicker.stop();
        }
    }

    public abstract void clickAction();
}