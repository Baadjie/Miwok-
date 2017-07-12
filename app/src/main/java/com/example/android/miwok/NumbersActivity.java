/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.android.miwok;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class NumbersActivity extends AppCompatActivity {

    private MediaPlayer mMediaPlayer;
    private AudioManager mAudioManager;

    AudioManager.OnAudioFocusChangeListener mOnAudioFocusChangeListener = new AudioManager.OnAudioFocusChangeListener() {
        @Override
        public void onAudioFocusChange(int focusChange) {
           if (focusChange== AudioManager.AUDIOFOCUS_LOSS_TRANSIENT||focusChange== AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK){
               mMediaPlayer.pause();
               mMediaPlayer.seekTo(0);
           }else if (focusChange== AudioManager.AUDIOFOCUS_GAIN){
               mMediaPlayer.start();
           }else if (focusChange==AudioManager.AUDIOFOCUS_LOSS){
               releaseMediaPlayer();
           }
        }
    };

    private MediaPlayer.OnCompletionListener mCompletionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mp) {
            releaseMediaPlayer();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);

        mAudioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);

        //Adding the list in the array list
     final ArrayList<Word> arrayList = new ArrayList<Word>();
        arrayList.add(new Word("One","lutti",R.drawable.number_one,R.raw.number_one));
        arrayList.add(new Word("Two","otiiko",R.drawable.number_two,R.raw.number_two));
        arrayList.add(new Word("Three","tolookosu",R.drawable.number_three,R.raw.number_three));
        arrayList.add(new Word("Four","oyyisa",R.drawable.number_four,R.raw.number_four));
        arrayList.add(new Word("Five","massokka",R.drawable.number_five,R.raw.number_five));
        arrayList.add(new Word("Six","temmokka",R.drawable.number_six,R.raw.number_six));
        arrayList.add(new Word("Seven","kenekaku",R.drawable.number_seven,R.raw.number_seven));
        arrayList.add(new Word("Eight","kawinta",R.drawable.number_eight,R.raw.number_eight));
        arrayList.add(new Word("Nine","wo'e",R.drawable.number_nine,R.raw.number_nine));
        arrayList.add(new Word("Ten","na'aacha",R.drawable.number_ten,R.raw.number_ten));


        WordAdapter arrayAdapter = new WordAdapter(this,arrayList,R.color.category_numbers);

        ListView listView = (ListView) findViewById(R.id.NumberList);

        listView.setAdapter(arrayAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Word word=arrayList.get(position);

                releaseMediaPlayer();

                int result = mAudioManager.requestAudioFocus(mOnAudioFocusChangeListener,AudioManager.STREAM_MUSIC,AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);

                if (result== AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                    releaseMediaPlayer();


                    mMediaPlayer = MediaPlayer.create(NumbersActivity.this, word.getAudioResourceId());
                    mMediaPlayer.start();

                    mMediaPlayer.setOnCompletionListener(mCompletionListener);
                }
            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        releaseMediaPlayer();
    }

    private void releaseMediaPlayer(){
        if (mMediaPlayer!=null){

            mMediaPlayer.release();

            mMediaPlayer=null;
            mAudioManager.abandonAudioFocus(mOnAudioFocusChangeListener);
        }
    }
}
