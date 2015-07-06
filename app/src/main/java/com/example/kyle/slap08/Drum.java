package com.example.kyle.slap08;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.media.SoundPool;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.IOException;

public class Drum extends AppCompatActivity {
    MediaRecorder rec;

    //rec.setOutputFile(mFileName); //change to different output files
    String mFileName = Environment.getExternalStorageDirectory().getAbsolutePath();

    boolean recording = false;
    boolean loaded =false;
    Button bass;
    Button snare;
    Button kick;
    Button shake;
    Button clap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        rec = new MediaRecorder();
        rec.setAudioSource(MediaRecorder.AudioSource.DEFAULT);
        rec.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        rec.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
        rec.setOutputFile(mFileName + "/track1");
        Log.d("FilePath", mFileName + "/track1");
        final SoundPool sp = new SoundPool(10, AudioManager.STREAM_MUSIC, 0);
        final int bassID = sp.load(this, R.raw.deep, 1);
        final int snareID = sp.load(this, R.raw.snare, 1);
        final int kickID = sp.load(this, R.raw.kick, 1);
        final int shakeID = sp.load(this, R.raw.shake, 1);
        final int clapID = sp.load(this, R.raw.clap, 1);
        final int heyID = sp.load(this, R.raw.hey, 1);

        AudioManager am = (AudioManager) getSystemService(AUDIO_SERVICE);
        Float actVolume = (float) am.getStreamVolume(AudioManager.STREAM_MUSIC);
        Float maxVolume = (float) am.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        final Float volume = actVolume/maxVolume;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drum);
        sp.setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener() {
            @Override
            public void onLoadComplete(SoundPool soundPool, int sampleId, int status) {
                loaded = true;

            }
        });
        this.setVolumeControlStream(AudioManager.STREAM_MUSIC);


        bass = (Button) findViewById(R.id.bass);
        snare = (Button) findViewById(R.id.snare);
        kick = (Button) findViewById(R.id.kick);
        shake = (Button) findViewById(R.id.shake);
        clap = (Button) findViewById(R.id.clap);
        Button hey = (Button) findViewById(R.id.hey);
        bass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sp.play(bassID, volume, volume, 1, 1, 1f);
            }
        });
        snare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sp.play(snareID, volume, volume, 1, 1, 1f);
            }
        });
        kick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sp.play(kickID, volume, volume, 1, 1, 1f);
            }
        });
        shake.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sp.play(shakeID, volume, volume, 1, 1, 1f);
            }
        });
        clap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sp.play(clapID, volume, volume, 1, 1, 1f);

            }
        });
        hey.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sp.play(heyID, volume, volume, 1, 1, 1f);
            }
        });
    } //END OF ONCREATE

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_drum, menu);
        return true;
    }

    protected  void recordToggle(){
        if(recording){
            recording = false;
            try {
                rec.stop();
            } catch (RuntimeException stopException) {
                //handle cleanup here
                Toast t = Toast.makeText(getApplicationContext(), "Recoding Off", Toast.LENGTH_SHORT);
                t.show();
            }
            //rec.reset();
            //rec.release();

        } else {
            recording = true;
            try {
                rec.prepare();
                rec.start();

            } catch (IOException e) {
                e.printStackTrace();
            }
            Toast t = Toast.makeText(getApplicationContext(), "Recoding On", Toast.LENGTH_SHORT);
            t.show();
        }


    }

    protected  void playBack() throws IOException {

        MediaPlayer mp = new MediaPlayer();
        mp.setDataSource(mFileName + "/track1");
        mp.prepare();
        mp.start();
        Toast t = Toast.makeText(getApplicationContext(), "Playback", Toast.LENGTH_SHORT);
        t.show();


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        switch(id){
            case R.id.action_record:
                recordToggle();
                return true;
            case R.id.action_play:
                try {
                    playBack();
                } catch (IOException e) {
                    e.printStackTrace();
                    Log.d("Sounds", "Exception thrown");
                }
                return true;

        }
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}//end of activity
