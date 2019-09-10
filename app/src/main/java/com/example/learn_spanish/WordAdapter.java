package com.example.learn_spanish;

import android.app.Activity;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class WordAdapter extends ArrayAdapter<Word> {

    private int mresource;

    public WordAdapter(Activity context, ArrayList<Word> words,int resource) {
        super(context,0,words);
        mresource = resource;
    }

    @Override
    public View getView(int position,View convertView,ViewGroup parent) {

        View view = convertView;
        if(view == null){
            view = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }

        Word currentObject = getItem(position);
        TextView text1 = (TextView) view.findViewById(R.id.spanish);
        text1.setText(currentObject.getSpanishTranslation());

        TextView text2 = (TextView) view.findViewById(R.id.english);
        text2.setText(currentObject.getDefaultTranslation());

        ImageView image = (ImageView) view.findViewById(R.id.picture);
        if(currentObject.getImageResouceId()!=0)
          image.setImageResource(currentObject.getImageResouceId());
        else
          image.setVisibility(View.GONE);

        LinearLayout textContainer =(LinearLayout) view.findViewById(R.id.container);
        int color = ContextCompat.getColor(getContext(),mresource);
        textContainer.setBackgroundColor(color);

        return view;
    }
}
