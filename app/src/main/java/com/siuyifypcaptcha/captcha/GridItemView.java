package com.siuyifypcaptcha.captcha;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.ImageView;


// this is for gridview changable background during selection and deselection
public class GridItemView extends FrameLayout {
    private ImageView imageView;

    public GridItemView(Context context) {
        super(context);
        LayoutInflater.from(context).inflate(R.layout.item_grid,this);
        imageView = (ImageView) getRootView().findViewById(R.id.image);

    }

    public void display(int image, boolean isSelected) {
        imageView.setImageResource(image);
        display(isSelected);
    }

    public void display(boolean isSelected) {
       imageView.setBackgroundResource(isSelected ? R.drawable.orange : R.drawable.transparent);

    }

}
