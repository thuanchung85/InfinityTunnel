package com.chung.infinitytunnel.BackGroundLoop;

import android.content.Context;
import android.net.Uri;
import android.util.AttributeSet;
import android.widget.VideoView;

import com.chung.infinitytunnel.R;

import java.util.Random;

public class BackgroundVideo extends VideoView {

    public BackgroundVideo(Context context) {
        super(context);
        init();
    }

    public BackgroundVideo(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public BackgroundVideo(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init() {
        // Customize your initialization here if needed
    }

    @Override
    public void setVideoURI(Uri uri) {
        super.setVideoURI(uri);
        // Mute the video when URI is set
        setOnPreparedListener(mp -> {
            mp.setVolume(0f, 0f); // Mute the video
        });
    }

    public void ShowRandom(int max, String getPackageName){
        Uri videoUri;
        // Đường dẫn đến video trong thư mục res/raw
        int randomIndex = new Random().nextInt(max);
        switch (randomIndex){
            case 1:
                videoUri = Uri.parse("android.resource://" + getPackageName + "/" + R.raw.bg1);
                break;
            case 2:
                videoUri = Uri.parse("android.resource://" + getPackageName + "/" + R.raw.bg2);
                break;
            case 3:
                videoUri = Uri.parse("android.resource://" + getPackageName + "/" + R.raw.bg3);
                break;
            case 4:
                videoUri = Uri.parse("android.resource://" + getPackageName + "/" + R.raw.bg4);
                break;
            case 5:
                videoUri = Uri.parse("android.resource://" + getPackageName + "/" + R.raw.bg5);
                break;
            case 6:
                videoUri = Uri.parse("android.resource://" + getPackageName + "/" + R.raw.bg6);
                break;
            case 7:
                videoUri = Uri.parse("android.resource://" + getPackageName + "/" + R.raw.bg7);
                break;

            default:
                videoUri = Uri.parse("android.resource://" + getPackageName + "/" + R.raw.bg1);
                break;
        }


        // Thiết lập MediaController để điều khiển video (tùy chọn)
        this.setMediaController(null);

        // Thiết lập URI của video
        this.setVideoURI(videoUri);

        // Thiết lập khi video kết thúc, sẽ phát lại từ đầu
        this.setOnCompletionListener(mp -> this.start());

        // Bắt đầu phát video
        this.start();

    }
}