package com.chung.infinitytunnel.Characters;

import android.content.Context;
import android.widget.FrameLayout;

public class CharacterManager
{
    Context c;
    FrameLayout frameLayout;

    public CharacterManager(Context c, FrameLayout frameLayout)
    {
        this.c = c;
        this.frameLayout = frameLayout;
    }

    //=======================PRIVATE ZONE==================//
    public void MAKE_Character(int n)
    {
        switch (n){
            case 1:
                // Tạo character1
                Character1 character1 = new Character1(1200,1200,c);
                ADD_Character(character1);
                break;

            case 2:
                // Tạo character2
                Character2 character2 = new Character2(1200,1200,c);
                ADD_Character(character2);
                break;

            default:
                break;
        }

    }


    //hàm gắn character lên frame layout và run timer tick
    private void ADD_Character(CharacterGOC c)
    {
        //click tren nhan vat
        c.setOnClickListener(v -> c.clickAction());
        // Thêm MySurfaceView vào FrameLayout
        frameLayout.addView(c);
        //khoi dong update timer cho character 1
        c.start_UpdateLoopWithTimerTick();

    }
}
