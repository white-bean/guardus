package org.techtown.merge;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class LocationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);

        Button button = findViewById(R.id.button);  //뒤로가기
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //첫 화면으로
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });

        Button button2 = findViewById(R.id.button2);    //위치설정하기
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Location2Activity.class);
                startActivity(intent);
            }
        });
    }
}
