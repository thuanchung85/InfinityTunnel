package com.chung.infinitytunnel.Characters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.PixelFormat;
import android.util.Log;

import com.chung.infinitytunnel.R;
import com.chung.infinitytunnel.SharedCode.MyTimerTicker;

public class Character2 extends CharacterGOC
{

    public Character2(int rectWidth, int rectHeight, Context context) {
        super(context);
        getHolder().addCallback(this);
        setZOrderOnTop(true); // Đặt SurfaceView lên trên VideoView
        getHolder().setFormat(PixelFormat.TRANSLUCENT); // Đặt nền của SurfaceView là trong suốt

        this.rectW = rectWidth;
        this.rectH = rectHeight;

        // Nạp hình ảnh từ tệp vào Bitmap
        Bitmap bitmap = android.graphics.BitmapFactory.decodeResource(getResources(), R.drawable.character_f2);
        array_bitmaps.add(bitmap);
        Bitmap bitmap2 = android.graphics.BitmapFactory.decodeResource(getResources(), R.drawable.character_f3);
        array_bitmaps.add(bitmap2);

        currentBMP= array_bitmaps.get(0);


        //active timer
        myTimerTicker = new MyTimerTicker();
        myTimerTicker.setListener(this);

        this.setClickable(true);
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

    public void clickAction(){
        Log.d("TAG", "onClick character2: ");
    }
}