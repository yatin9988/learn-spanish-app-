package com.example.learn_spanish;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class phrasesActivity extends AppCompatActivity {

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
        setContentView(R.layout.activity_phrases);
        final ArrayList<Word> phrases = new ArrayList<Word>();
        phrases.add(new Word(getString(R.string.a),"A dónde vas",R.raw.phrase_where_are_you_going));
        phrases.add(new Word(getString(R.string.b),"cuál es tu nombre",R.raw.phrase_what_is_your_name));
        phrases.add(new Word(getString(R.string.c),"mi nombre es",R.raw.phrase_my_name_is));
        phrases.add(new Word(getString(R.string.d),"como te sientes",R.raw.phrase_how_are_you_feeling));
        phrases.add(new Word(getString(R.string.e),"me siento bien",R.raw.phrase_im_feeling_good));
        phrases.add(new Word(getString(R.string.f),"vienes",R.raw.phrase_are_you_coming));
        phrases.add(new Word(getString(R.string.g),"Si, voy para allá",R.raw.phrase_yes_im_coming));
        phrases.add(new Word(getString(R.string.h),"ya voy",R.raw.phrase_yes_im_coming));
        phrases.add(new Word(getString(R.string.i),"vamonos",R.raw.phrase_lets_go));
        phrases.add(new Word(getString(R.string.j),"ven aca",R.raw.phrase_come_here));

        WordAdapter adapter = new WordAdapter(this,phrases,R.color.colorPhrases);
        ListView lists = (ListView) findViewById(R.id.phrases);
        lists.setAdapter(adapter);

        lists.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Word word = phrases.get(position);
                releaseMediaPlayer();
                mediaPlayer = MediaPlayer.create(phrasesActivity.this,word.getaudio());
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
