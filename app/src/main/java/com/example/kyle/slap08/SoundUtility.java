package com.example.kyle.slap08;

import android.content.Context;
import android.media.MediaPlayer;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by kyle on 6/16/2015.
 */
public class SoundUtility {

    public static void PlaySound(final MediaPlayer mp, final int id, final Context context){
        new Thread(new Runnable() {
            @Override
            public void run() {
                mp.create(context, id);
                mp.start();
                Log.d("DebugThread", "Playing Sound");
            }
        }).start();

    } //end of play sound
}
