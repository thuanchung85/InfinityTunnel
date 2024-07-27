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

public abstract class CharacterGOC extends SurfaceView implements SurfaceHolder.Callback , TimerTickerListener, View.OnClickListener
{
    boolean running = true;
    Point centerOfCanvas;

    MyTimerTicker myTimerTicker;

    ArrayList<Bitmap> array_bitmaps = new ArrayList<>() ;
    int nextIndex = 0;
    Bitmap currentBMP ;


    public CharacterGOC(Context context) {
        super(context);
        getHolder().addCallback(this);



    }

    public void start_UpdateLoopWithTimerTick()
    {
        myTimerTicker.start(150); // Ticker mỗi 1 giây
    }
    public void stop_UpdateLoopWithTimerTick(){
        myTimerTicker.stop();
    }


}