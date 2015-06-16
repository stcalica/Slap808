package com.example.kyle.slap08;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Drum extends AppCompatActivity {
    Button bass;
    Button snare;
    Button kick;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drum);
        bass = (Button) findViewById(R.id.bass);
        snare = (Button) findViewById(R.id.snare);
        kick = (Button) findViewById(R.id.kick);
        final MediaPlayer mp = new MediaPlayer();
        mp.setAudioStreamType(AudioManager.STREAM_MUSIC);
        mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                mp.reset();
                mp.release();

            }
        });
        bass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mp.create(getApplicationContext(), R.raw.bass).start();
                mp.release();

                //sounds.play(bassID, 100, 100, 1, 0, 1f);
              //  bassplay.reset();

            }
        });
        snare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

              //  SoundUtility.PlaySound(mp, R.raw.snares, getApplicationContext());
                mp.create(getApplicationContext(), R.raw.snare).start();


                //snareplay.start(); // no need to call prepare(); create() does that for you
                //snareplay.reset();
            }
        });
        kick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mp.create(getApplicationContext(), R.raw.kick).start();
                //kickplay.start(); // no need to call prepare(); create() does that for you
                //kickplay.reset();

            }
        });
    } //END OF ONCREATE

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_drum, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}//end of activity
