package com.chung.infinitytunnel.Characters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.PixelFormat;
import android.util.Log;

import com.chung.infinitytunnel.R;

@SuppressLint("ViewConstructor")
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
        //array of bitmaps
        array_bitmaps.add(android.graphics.BitmapFactory.decodeResource(getResources(), R.drawable.character_f2));
        array_bitmaps.add(android.graphics.BitmapFactory.decodeResource(getResources(), R.drawable.character_f3));

        currentBMP= array_bitmaps.get(0);



    }


    @Override
    public void onTick()
    {
        Log.d("Tick", "Tick Character 2 animation");
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