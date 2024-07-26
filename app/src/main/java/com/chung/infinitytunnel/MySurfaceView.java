package com.chung.infinitytunnel;

import static android.content.ContentValues.TAG;

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
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class MySurfaceView extends SurfaceView implements SurfaceHolder.Callback , TimerTickerListener, View.OnClickListener {
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
        Bitmap bitmap = android.graphics.BitmapFactory.decodeResource(getResources(), R.drawable.f1);
        array_bitmaps.add(bitmap);
        Bitmap bitmap2 = android.graphics.BitmapFactory.decodeResource(getResources(), R.drawable.f2);
        array_bitmaps.add(bitmap2);
        Bitmap bitmap3 = android.graphics.BitmapFactory.decodeResource(getResources(), R.drawable.f3);
        array_bitmaps.add(bitmap3);
        Bitmap bitmap4 = android.graphics.BitmapFactory.decodeResource(getResources(), R.drawable.f4);
        array_bitmaps.add(bitmap4);
        Bitmap bitmap5 = android.graphics.BitmapFactory.decodeResource(getResources(), R.drawable.f5);
        array_bitmaps.add(bitmap5);
        Bitmap bitmap6 = android.graphics.BitmapFactory.decodeResource(getResources(), R.drawable.f6);
        array_bitmaps.add(bitmap6);
        Bitmap bitmap7 = android.graphics.BitmapFactory.decodeResource(getResources(), R.drawable.f7);
        array_bitmaps.add(bitmap7);
        Bitmap bitmap8 = android.graphics.BitmapFactory.decodeResource(getResources(), R.drawable.f8);
        array_bitmaps.add(bitmap8);
        Bitmap bitmap9 = android.graphics.BitmapFactory.decodeResource(getResources(), R.drawable.f9);
        array_bitmaps.add(bitmap9);
        Bitmap bitmap10 = android.graphics.BitmapFactory.decodeResource(getResources(), R.drawable.f10);
        array_bitmaps.add(bitmap10);
        Bitmap bitmap11 = android.graphics.BitmapFactory.decodeResource(getResources(), R.drawable.f11);
        array_bitmaps.add(bitmap11);
        Bitmap bitmap12 = android.graphics.BitmapFactory.decodeResource(getResources(), R.drawable.f12);
        array_bitmaps.add(bitmap12);
        Bitmap bitmap13 = android.graphics.BitmapFactory.decodeResource(getResources(), R.drawable.f13);
        array_bitmaps.add(bitmap13);
        Bitmap bitmap14 = android.graphics.BitmapFactory.decodeResource(getResources(), R.drawable.f14);
        array_bitmaps.add(bitmap14);
        Bitmap bitmap15 = android.graphics.BitmapFactory.decodeResource(getResources(), R.drawable.f15);
        array_bitmaps.add(bitmap15);
        Bitmap bitmap16 = android.graphics.BitmapFactory.decodeResource(getResources(), R.drawable.f16);
        array_bitmaps.add(bitmap16);
        Bitmap bitmap17 = android.graphics.BitmapFactory.decodeResource(getResources(), R.drawable.f17);
        array_bitmaps.add(bitmap17);
        Bitmap bitmap18 = android.graphics.BitmapFactory.decodeResource(getResources(), R.drawable.f18);
        array_bitmaps.add(bitmap18);
        Bitmap bitmap19 = android.graphics.BitmapFactory.decodeResource(getResources(), R.drawable.f19);
        array_bitmaps.add(bitmap19);
        Bitmap bitmap20 = android.graphics.BitmapFactory.decodeResource(getResources(), R.drawable.f20);
        array_bitmaps.add(bitmap20);
        Bitmap bitmap21 = android.graphics.BitmapFactory.decodeResource(getResources(), R.drawable.f21);
        array_bitmaps.add(bitmap21);
        Bitmap bitmap22 = android.graphics.BitmapFactory.decodeResource(getResources(), R.drawable.f22);
        array_bitmaps.add(bitmap22);
        Bitmap bitmap23 = android.graphics.BitmapFactory.decodeResource(getResources(), R.drawable.f23);
        array_bitmaps.add(bitmap23);
        Bitmap bitmap24 = android.graphics.BitmapFactory.decodeResource(getResources(), R.drawable.f24);
        array_bitmaps.add(bitmap24);
        Bitmap bitmap25 = android.graphics.BitmapFactory.decodeResource(getResources(), R.drawable.f25);
        array_bitmaps.add(bitmap25);
        Bitmap bitmap26 = android.graphics.BitmapFactory.decodeResource(getResources(), R.drawable.f26);
        array_bitmaps.add(bitmap26);
        Bitmap bitmap27 = android.graphics.BitmapFactory.decodeResource(getResources(), R.drawable.f27);
        array_bitmaps.add(bitmap27);
        Bitmap bitmap28 = android.graphics.BitmapFactory.decodeResource(getResources(), R.drawable.f28);
        array_bitmaps.add(bitmap28);
        Bitmap bitmap29 = android.graphics.BitmapFactory.decodeResource(getResources(), R.drawable.f29);
        array_bitmaps.add(bitmap29);
        Bitmap bitmap30 = android.graphics.BitmapFactory.decodeResource(getResources(), R.drawable.f30);
        array_bitmaps.add(bitmap30);
        Bitmap bitmap31 = android.graphics.BitmapFactory.decodeResource(getResources(), R.drawable.f31);
        array_bitmaps.add(bitmap31);
        Bitmap bitmap32 = android.graphics.BitmapFactory.decodeResource(getResources(), R.drawable.f32);
        array_bitmaps.add(bitmap32);
        currentBMP= array_bitmaps.get(0);


        //active timer
        myTimerTicker = new MyTimerTicker();
        myTimerTicker.setListener(this);
        myTimerTicker.start(150); // Ticker mỗi 1 giây
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

    @Override
    public void onClick(View v) {
        Log.d(TAG, "onClick: ");
    }
}