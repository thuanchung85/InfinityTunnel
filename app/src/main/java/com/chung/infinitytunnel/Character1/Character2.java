package com.chung.infinitytunnel.Character1;

import static android.content.ContentValues.TAG;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.Point;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

import androidx.annotation.NonNull;

import com.chung.infinitytunnel.SharedCode.MyTimerTicker;
import com.chung.infinitytunnel.R;
import com.chung.infinitytunnel.SharedCode.TimerTickerListener;

import java.util.ArrayList;

public class Character2 extends CharacterGOC
{

    public Character2(Context context) {
        super(context);
        getHolder().addCallback(this);
        setZOrderOnTop(true); // Đặt SurfaceView lên trên VideoView
        getHolder().setFormat(PixelFormat.TRANSLUCENT); // Đặt nền của SurfaceView là trong suốt


        // Nạp hình ảnh từ tệp vào Bitmap
        Bitmap bitmap = android.graphics.BitmapFactory.decodeResource(getResources(), R.drawable.character_f2);
        array_bitmaps.add(bitmap);
        Bitmap bitmap2 = android.graphics.BitmapFactory.decodeResource(getResources(), R.drawable.character_f3);
        array_bitmaps.add(bitmap2);

        currentBMP= array_bitmaps.get(0);


        //active timer
        myTimerTicker = new MyTimerTicker();
        myTimerTicker.setListener(this);
    }

    @Override
    public void surfaceCreated(@NonNull SurfaceHolder holder)
    {


        //lấy trung tâm màn hình phone
        int canvasW = getWidth();
        int canvasH = getHeight();
        centerOfCanvas = new Point(canvasW / 2, canvasH / 2);

        //chạy update
        new Thread(() -> {
            while (running) {
                Canvas canvas = holder.lockCanvas();
                if (canvas != null) {
                    try {
                        canvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR); // Clear canvas với màu trong suốt

                        int rectW = 1200;
                        int rectH = 1200;
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
            myTimerTicker.stop();
        }
    }

    @Override
    public void onTick()
    {
        Log.d("Tick", "Tick Character 2");
        this.currentBMP = array_bitmaps.get(nextIndex);
        nextIndex += 1;
        if(nextIndex >= array_bitmaps.size()){
            nextIndex = 0;
        }

    }


}