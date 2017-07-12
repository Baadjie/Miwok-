package com.example.android.miwok;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by CodeTride on 2017/07/10.
 */



public class WordAdapter extends ArrayAdapter<Word>{

    private int mColorResourceId;

    public WordAdapter(Activity context, ArrayList<Word> Words,int colorResourceId) {
        super(context, 0, Words);
        mColorResourceId= colorResourceId;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
          /*first we making sure that we have the list item view even if it doesn't have the right data
        * so we use the recycle view that came in as a input para
        * */
        View listItemView = convertView;

        //if list item view is null then
        if (listItemView==null) {

            /*we use the layout inflater from android and call the inflate method passing in the layout
            resources id, the parent view which is the ListView and false at the end,
            false because we don't want to attach the ListItemView to the parent ListView just yet*/
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);

            //get the Word object located in at this position in the list
            Word currentWord = getItem(position);
            //find the textview in the list_item.xml with the id of miwok_textView
            TextView miwok_textView = (TextView) listItemView.findViewById(R.id.miwok_textView);
            //get the miwok translation from the currentWord object and set it to the miwok textview
            miwok_textView.setText(currentWord.getMiwokTranslation());
            //find the textview in the list_item.xml with the id of english_textView
            TextView english_textView = (TextView) listItemView.findViewById(R.id.english_textView);
            //get the english translation from the currentWord object and set it to the english textview
            english_textView.setText(currentWord.getEnglishTranslation());
            //Return the

            ImageView image = (ImageView) listItemView.findViewById(R.id.image);

            if (currentWord.hasImage()){
                image.setImageResource(currentWord.getImageResourceId());
                image.setVisibility(View.VISIBLE);
            }else {
                image.setVisibility(View.GONE);
            }

            View textContainer = listItemView.findViewById(R.id.text_container);

            int color = ContextCompat.getColor(getContext(), mColorResourceId);
            textContainer.setBackgroundColor(color);


        }
        return listItemView;
    }
}
