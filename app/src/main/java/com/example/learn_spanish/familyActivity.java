package com.example.learn_spanish;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class familyActivity extends AppCompatActivity {

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
        setContentView(R.layout.activity_family);

        final ArrayList<Word> family = new ArrayList<>();

        family.add(new Word(getString(R.string.father),"padre",R.drawable.family_father,R.raw.family_father));
        family.add(new Word(getString(R.string.mother),"madre",R.drawable.family_mother,R.raw.family_mother));
        family.add(new Word(getString(R.string.son),"hijo",R.drawable.family_son,R.raw.family_son));
        family.add(new Word(getString(R.string.daughter),"hija",R.drawable.family_daughter,R.raw.family_daughter));
        family.add(new Word(getString(R.string.older_brother),"hermano mayor",R.drawable.family_older_brother,R.raw.family_older_brother));
        family.add(new Word(getString(R.string.younger_brother),"hermano mas joven",R.drawable.family_younger_brother,R.raw.family_younger_brother));
        family.add(new Word(getString(R.string.older_sister),"hermana mayor",R.drawable.family_older_sister,R.raw.family_older_sister));
        family.add(new Word(getString(R.string.younger_sister),"hermana menor",R.drawable.family_younger_sister,R.raw.family_younger_sister));
        family.add(new Word(getString(R.string.grandmother),"abuela",R.drawable.family_grandmother,R.raw.family_grandmother));
        family.add(new Word(getString(R.string.grandfather),"abuelo",R.drawable.family_grandfather,R.raw.family_grandfather));

        WordAdapter adapter = new WordAdapter(this,family,R.color.colorFamily);
        ListView lists = (ListView) findViewById(R.id.family);
        lists.setAdapter(adapter);

        lists.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Word word = family.get(position);
                releaseMediaPlayer();
                mediaPlayer = MediaPlayer.create(familyActivity.this,word.getaudio());
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
