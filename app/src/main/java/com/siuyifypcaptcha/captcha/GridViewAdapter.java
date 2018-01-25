package com.siuyifypcaptcha.captcha;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class GridViewAdapter extends BaseAdapter {
    private String[] strings;
    public List selectedPositions;
    private Activity activity;

    // arrays of image drawables
    private int [] imgset1 = new int[]{R.drawable.m_1, R.drawable.m_2, R.drawable.m_3, R.drawable.m_4, R.drawable.m_5,
            R.drawable.m_6, R.drawable.m_7, R.drawable.m_8, R.drawable.m_9};

    private int [] imgset2 = new int[]{R.drawable.n1, R.drawable.n2, R.drawable.n3, R.drawable.n4, R.drawable.n5, 
            R.drawable.n6, R.drawable.n7, R.drawable.n8, R.drawable.n9 };
    
    private int [] imgset3 = new int[]{R.drawable.o1, R.drawable.o2, R.drawable.o3, R.drawable.o4, R.drawable.o5,
            R.drawable.o6, R.drawable.o7, R.drawable.o8, R.drawable.o9};



    public GridViewAdapter(String[] strings, Activity activity) {
        this.strings = strings;
        this.activity = activity;
        selectedPositions = new ArrayList<>();
    }

    @Override
    // return number of item in the array
    public int getCount() {
        return strings.length;
    }

    @Override
    // to generate item's view
    public Object getItem(int position) {
        return strings[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //this is to create different views, if convertView is null create new  else reuse it.
        GridItemView customView = (convertView == null) ? new GridItemView(activity) : (GridItemView) convertView;
        ImageView imageView = (ImageView)  customView.findViewById(R.id.image);

        // to check which String [] was generated from arrayRnd
        List valid = Arrays.asList("m_1","m_2","m_3","m_4","m_5","m_6","m_7","m_8","m_9");
        List valid1 = Arrays.asList("n1", "n2", "n3", "n4", "n5", "n6", "n7", "n8", "n9");

        // based on the generated string[] from arrayRnd, the set the imageView with the relevant image drawable arrays.
        if (Arrays.asList(strings).containsAll(valid)){
            for ( int i = 0; i < strings.length; i++) {
                imageView.setImageResource(imgset1[position]);
            }
        }
        else if (Arrays.asList(strings).containsAll(valid1)){
            for (int i = 0; i < strings.length; i++) {
                imageView.setImageResource(imgset2[position]);
            }
        }
        else{
            for (int i = 0; i < strings.length; i++) {
                imageView.setImageResource(imgset3[position]);
            }
        }
        customView.display(selectedPositions.contains(position));
        return customView;
    }
}

