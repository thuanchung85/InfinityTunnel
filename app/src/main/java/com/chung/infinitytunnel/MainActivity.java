package com.chung.infinitytunnel;

import android.net.Uri;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.FrameLayout;
import android.widget.MediaController;
import android.widget.VideoView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);



        //==hình nền chạy hiệu ứng tunnel loop infinity==//
        CustomVideoView videoView = findViewById(R.id.videoView);
        videoView.ShowRandom(8,getPackageName(),this);


        // Tạo một thể hiện của MySurfaceView
        MySurfaceView mySurfaceView = new MySurfaceView(this);
        // Lấy FrameLayout từ layout
        FrameLayout frameLayout = findViewById(R.id.frameLayout);
        // Thêm MySurfaceView vào FrameLayout
        frameLayout.addView(mySurfaceView);

    }



}//end class