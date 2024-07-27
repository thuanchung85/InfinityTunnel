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
import com.chung.infinitytunnel.Character1.Character2;
import com.chung.infinitytunnel.Character1.CharacterGOC;
import com.chung.infinitytunnel.SharedCode.CommonFunction;


public class MainActivity extends AppCompatActivity
{
    FrameLayout frameLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
         frameLayout = findViewById(R.id.frameLayout);
        //setup full screen game
        CommonFunction.FullScreencall( getWindow());



        //==hình nền chạy hiệu ứng tunnel loop infinity==//
        BackgroundVideo videoView = findViewById(R.id.videoView);
        videoView.ShowRandom(8,getPackageName());


        // Tạo character1
        Character1 character1 = new Character1(this);
        character1.setClickable(true);
        //click tren nhan vat
        character1.setOnClickListener(v -> Log.d("TAG", "onClick character1: "));

        // Tạo character2
        Character2 character2 = new Character2(this);
        character2.setClickable(true);
        //click tren nhan vat
        character2.setOnClickListener(v -> Log.d("TAG", "onClick character2: "));


        ADD_Character(character1);





    }

private void ADD_Character(CharacterGOC c){
    // Thêm MySurfaceView vào FrameLayout
    frameLayout.addView(c);
    //khoi dong update timer cho character 1
    c.start_UpdateLoopWithTimerTick();

}

}//end class