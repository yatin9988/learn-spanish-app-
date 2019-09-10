package com.example.learn_spanish;

import java.util.*;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

public class numberActivity extends AppCompatActivity {

    private MediaPlayer.OnCompletionListener listener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mp) {
            releaseMediaPlayer();
        }
    };

    MediaPlayer mMediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_number);
        final ArrayList<Word> words = new ArrayList<Word>();

        words.add(new Word(getString(R.string.one),"uno",R.drawable.number_one,R.raw.number_one));
        words.add(new Word(getString(R.string.two),"dos",R.drawable.number_two,R.raw.number_two));
        words.add(new Word(getString(R.string.three),"tres",R.drawable.number_three,R.raw.number_three));
        words.add(new Word(getString(R.string.four),"cuatro",R.drawable.number_four,R.raw.number_four));
        words.add(new Word(getString(R.string.five),"cinco",R.drawable.number_five,R.raw.number_five));
        words.add(new Word(getString(R.string.six),"seis",R.drawable.number_six,R.raw.number_six));
        words.add(new Word(getString(R.string.seven),"siete",R.drawable.number_seven,R.raw.number_seven));
        words.add(new Word(getString(R.string.eight),"ocho",R.drawable.number_eight,R.raw.number_eight));
        words.add(new Word(getString(R.string.nine),"nueve",R.drawable.number_nine,R.raw.number_nine));
        words.add(new Word(getString(R.string.ten),"diez",R.drawable.number_ten,R.raw.number_ten));
        WordAdapter adapter = new WordAdapter(this,words,R.color.colorNumbers);
        ListView lists = (ListView) findViewById(R.id.list);
        lists.setAdapter(adapter);
        lists.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Word word = words.get(position);

                releaseMediaPlayer();

                mMediaPlayer = MediaPlayer.create(numberActivity.this,word.getaudio());

                mMediaPlayer.start();

                mMediaPlayer.setOnCompletionListener(listener);

            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        releaseMediaPlayer();
    }

    /**
     * Clean up the media player by releasing its resources.
     */
    private void releaseMediaPlayer() {
        // If the media player is not null, then it may be currently playing a sound.
        if (mMediaPlayer != null) {
            // Regardless of the current state of the media player, release its resources
            // because we no longer need it.
            mMediaPlayer.release();

            // Set the media player back to null. For our code, we've decided that
            // setting the media player to null is an easy way to tell that the media player
            // is not configured to play an audio file at the moment.
            mMediaPlayer = null;
        }
    }
}
