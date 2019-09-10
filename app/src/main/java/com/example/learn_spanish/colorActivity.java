package com.example.learn_spanish;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class colorActivity extends AppCompatActivity {

    MediaPlayer mediaPlayer;

    private MediaPlayer.OnCompletionListener listener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mp) {
            releaseMediaPlayer();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_color);

        final ArrayList<Word> color = new ArrayList<Word>();

        color.add(new Word(getString(R.string.red),"rojo",R.drawable.color_red,R.raw.color_red));
        color.add(new Word(getString(R.string.green),"verde",R.drawable.color_green,R.raw.color_green));
        color.add(new Word(getString(R.string.brown),"marron",R.drawable.color_brown,R.raw.color_brown));
        color.add(new Word(getString(R.string.gray),"gris",R.drawable.color_gray,R.raw.color_gray));
        color.add(new Word(getString(R.string.black),"negro",R.drawable.color_black,R.raw.color_black));
        color.add(new Word(getString(R.string.white),"blanco",R.drawable.color_white,R.raw.color_white));
        color.add(new Word(getString(R.string.dusty_yellow),"polvo amarillo",R.drawable.color_dusty_yellow,R.raw.color_dusty_yellow));
        color.add(new Word(getString(R.string.mustard_yellow),"mostaza amarilla",R.drawable.color_mustard_yellow,R.raw.color_mustard_yellow));

        WordAdapter adapter = new WordAdapter(this,color,R.color.colorColors);
        ListView lists = (ListView) findViewById(R.id.color);
        lists.setAdapter(adapter);

        lists.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Word word = color.get(position);
                releaseMediaPlayer();
                mediaPlayer = MediaPlayer.create(colorActivity.this,word.getaudio());
                mediaPlayer.start();
                mediaPlayer.setOnCompletionListener(listener);
            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        releaseMediaPlayer();
    }

    private void releaseMediaPlayer() {
        // If the media player is not null, then it may be currently playing a sound.
        if (mediaPlayer != null) {
            // Regardless of the current state of the media player, release its resources
            // because we no longer need it.
            mediaPlayer.release();

            // Set the media player back to null. For our code, we've decided that
            // setting the media player to null is an easy way to tell that the media player
            // is not configured to play an audio file at the moment.
            mediaPlayer = null;
        }
    }
}
