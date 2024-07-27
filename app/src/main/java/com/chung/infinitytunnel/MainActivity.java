package com.chung.infinitytunnel;

import android.os.Bundle;
import android.widget.FrameLayout;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import com.chung.infinitytunnel.BackGroundLoop.BackgroundVideo;
import com.chung.infinitytunnel.Characters.Character1;
import com.chung.infinitytunnel.Characters.Character2;
import com.chung.infinitytunnel.Characters.CharacterGOC;
import com.chung.infinitytunnel.Characters.CharacterManager;
import com.chung.infinitytunnel.SharedCode.CommonFunction;


public class MainActivity extends AppCompatActivity
{
    FrameLayout frameLayout;
    CharacterManager characterManager ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
         frameLayout = findViewById(R.id.frameLayout);
        CommonFunction.FullScreencall( getWindow());


        //==hình nền chạy hiệu ứng tunnel loop infinity==//
        BackgroundVideo videoView = findViewById(R.id.videoView);
        videoView.ShowRandom(8,getPackageName());

        //==tao character o giua===//
        characterManager = new CharacterManager(this,frameLayout);
        characterManager.MAKE_Character(2);







    }




}//end class