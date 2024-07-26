package com.chung.infinitytunnel;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import com.chung.infinitytunnel.BackGroundLoop.BackgroundVideo;
import com.chung.infinitytunnel.Character1.Character1;
import com.chung.infinitytunnel.SharedCode.CommonFunction;


public class MainActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);


        //==hình nền chạy hiệu ứng tunnel loop infinity==//
        BackgroundVideo videoView = findViewById(R.id.videoView);
        videoView.ShowRandom(8,getPackageName());


        // Tạo một thể hiện của MySurfaceView
        Character1 character1 = new Character1(this);
        character1.setClickable(true);

        //click tren nhan vat
        character1.setOnClickListener(v -> Log.d("TAG", "onClick: "));

        // Lấy FrameLayout từ layout
        FrameLayout frameLayout = findViewById(R.id.frameLayout);
        // Thêm MySurfaceView vào FrameLayout
        frameLayout.addView(character1);

        //setup full screen game
        CommonFunction.FullScreencall( getWindow());
    }



}//end class