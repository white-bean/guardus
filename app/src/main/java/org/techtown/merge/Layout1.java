package org.techtown.merge;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

public class Layout1 extends LinearLayout {
    ImageView imageView;
    ImageView imageView2;
    TextView textView;
    TextView textView2;
    TextView textView3;
    TextView textView4;

    public Layout1(Context context) {
        super(context);

        init(context);
    }

    public Layout1(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        init(context);
    }
    public void init(Context context){
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.layout1_main, this, true);
        imageView = findViewById(R.id.imageView);
        imageView2 = findViewById(R.id.imageView2);

        textView = findViewById(R.id.textView);
        textView2 = findViewById(R.id.textView2);
        textView3 = findViewById(R.id.text_fail);
        textView4 = findViewById(R.id.textView4);
    }

    public void setImage(int resId){
        imageView.setImageResource(resId);
    }
    public void setTitle(String title){
        textView.setText(title);
    }
    public void setPosition(String address){
        textView2.setText(address);
    }
    public void setTime(String time){
        textView3.setText(time + "분전");
    }
    public void setNice(int nice){
        textView4.setText(nice);
    }
}
