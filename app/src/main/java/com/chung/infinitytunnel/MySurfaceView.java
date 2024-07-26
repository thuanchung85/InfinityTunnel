package com.chung.infinitytunnel;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.Point;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.ArrayList;
import java.util.Random;

public class MySurfaceView extends SurfaceView implements SurfaceHolder.Callback , TimerTickerListener{
    private final Paint paint;
    private boolean running = true;

    Point centerOfCanvas;

    private MyTimerTicker myTimerTicker;

    private ArrayList<Bitmap> array_bitmaps = new ArrayList<>() ;
    private int nextIndex = 0;
    Bitmap currentBMP ;
    public MySurfaceView(Context context) {
        super(context);
        getHolder().addCallback(this);
        paint = new Paint();
        paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.FILL);
        setZOrderOnTop(true); // Đặt SurfaceView lên trên VideoView
        getHolder().setFormat(PixelFormat.TRANSLUCENT); // Đặt nền của SurfaceView là trong suốt




        // Nạp hình ảnh từ tệp vào Bitmap
        BitmapFactory BitmapFactory;
        Bitmap bitmap = android.graphics.BitmapFactory.decodeResource(getResources(), R.drawable.cat);
        array_bitmaps.add(bitmap);
        Bitmap bitmap2 = android.graphics.BitmapFactory.decodeResource(getResources(), R.drawable.cat2);
        array_bitmaps.add(bitmap2);
        Bitmap bitmap3 = android.graphics.BitmapFactory.decodeResource(getResources(), R.drawable.cat3);
        array_bitmaps.add(bitmap3);
        currentBMP= array_bitmaps.get(0);


        //active timer
        myTimerTicker = new MyTimerTicker();
        myTimerTicker.setListener(this);
        myTimerTicker.start(100); // Ticker mỗi 1 giây
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder)
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

                        int rectW = 1000;
                        int rectH = 1000;
                        int left = centerOfCanvas.x - (rectW / 2);
                        int top = centerOfCanvas.y - (rectH / 2);
                        int right = centerOfCanvas.x + (rectW / 2);
                        int bottom = centerOfCanvas.y + (rectH / 2);
                        Rect rect = new Rect(left, top, right, bottom);
                        
                       // canvas.drawRect(rect, paint); // Vẽ một hình chữ nhật đỏ
                        canvas.drawBitmap(currentBMP, null, rect, paint);
                    } finally {
                        holder.unlockCanvasAndPost(canvas);
                    }
                }
                try {
                    Thread.sleep(16); // Khoảng 60 FPS
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        // Xử lý thay đổi kích thước hoặc định dạng nếu cần
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        running = false; // Dừng luồng vẽ khi SurfaceView bị hủy
        if (myTimerTicker != null) {
            myTimerTicker.stop();
        }
    }

    @Override
    public void onTick() {
        this.currentBMP = array_bitmaps.get(nextIndex);
         nextIndex += 1;
         if(nextIndex >= array_bitmaps.size()){
             nextIndex = 0;
         }

    }
}