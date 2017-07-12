package com.example.android.miwok;

/**
 * Created by CodeTride on 2017/07/10.
 */

public class Word {

    private String mEnglishTranslation, mMiwokTranslation;
    private int mImageResourceId = NO_IMAGE;
    private static final int NO_IMAGE = -1;
    private int mAudioResourceId;

    public Word(String englishTranslation, String miwokTranslation, int imageResourceId, int audioResourceId) {
        mEnglishTranslation = englishTranslation;
        mMiwokTranslation = miwokTranslation;
        mImageResourceId = imageResourceId;
        mAudioResourceId = audioResourceId;
    }

    public Word(String englishTranslation, String miwokTranslation, int AudioResourceId) {
        mEnglishTranslation = englishTranslation;
        mMiwokTranslation = miwokTranslation;
        mAudioResourceId = AudioResourceId;
    }

    //get english translation of the word
    public String getEnglishTranslation() {
        return mEnglishTranslation;
    }

    //get miwok translation of the word
    public String getMiwokTranslation() {
        return mMiwokTranslation;
    }

    public int getImageResourceId() {
        return mImageResourceId;
    }


    public boolean hasImage(){
        return mImageResourceId != NO_IMAGE;
    }

    public int getAudioResourceId() {
        return mAudioResourceId;
    }
}
