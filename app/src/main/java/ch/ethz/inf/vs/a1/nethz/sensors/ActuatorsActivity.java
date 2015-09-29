package ch.ethz.inf.vs.a1.nethz.sensors;

import android.media.MediaPlayer;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;

public class ActuatorsActivity extends AppCompatActivity {

    private Vibrator vibrator;
    private int duration = 50;

    private MediaPlayer mp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actuators);


        // initialize the vibrator
        vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
        SeekBar seekDuration = (SeekBar) findViewById(R.id.vibration_length);
        seekDuration.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                // save the duration
                duration = progress;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        Button startVibration = (Button) findViewById(R.id.vibrate_button);
        startVibration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vibrator.vibrate(duration * 10);
            }
        });

        // initialize the player
        initPlayer(false);

        // set up the play button
        Button soundButton = (Button) findViewById(R.id.play_sound);
        soundButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // do stuff according to whether we are playing or not...
                if (!mp.isPlaying()) {
                    mp.start();
                    if (mp.isLooping()) {
                        ((Button) v).setText(R.string.actuators_sound_stop);
                    }
                } else {
                    mp.stop();
                    try {
                        mp.prepareAsync();
                    } catch (IllegalStateException e) {
                        // what should I do here?
                    }
                    ((Button) v).setText(R.string.actuators_sound_start);
                }

            }
        });

    }

    private void initPlayer(boolean loop){
        int sound;
        if(loop)
            sound = R.raw.loop;
        else
            sound = R.raw.sound;
        mp = MediaPlayer.create(this, sound);
        mp.setVolume(1.0f, 1.0f);
        mp.setLooping(loop);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_actuators, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_looping:
                initPlayer(true);
                return true;
            case R.id.menu_once:
                initPlayer(false);
                return true;
            case R.id.menu_back:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
