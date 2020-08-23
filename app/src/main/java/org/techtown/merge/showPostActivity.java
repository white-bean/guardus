package org.techtown.merge;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class showPostActivity extends AppCompatActivity {
    TextView textView;
    TextView textView10;

    int thumbup=0;
    int count=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_post);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("");
        actionBar.setDisplayHomeAsUpEnabled(true);

        textView = findViewById(R.id.textView);
        textView10 = findViewById(R.id.textView10);

        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                thumbup++;
                if(thumbup%2==1){
                    count++;
                    textView.setText("1");
                    textView10.setText(""+count);
                }else{
                    count--;
                    textView.setText("0");
                    textView10.setText(""+count);
                }

            }
        });

        final RecyclerView recyclerView = findViewById(R.id.recyclerview);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        final CommentAdapter adapter = new CommentAdapter();

        adapter.addItem(new Comment("류상연", "우와 정말 도움이 되었어요!"));
        adapter.addItem(new Comment("이고은", "감사합니다!"));
        adapter.addItem(new Comment("신현주", "정말 감사합니다!!!"));
        adapter.addItem(new Comment("이지원", "Thank you~!"));

        Button button2 = findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText editText = (EditText)findViewById(R.id.editText);
                String value = editText.getText().toString();
                adapter.addItem(new Comment("가오리", value));
                editText.setText("");
            }
        });

        recyclerView.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.showpostactionbar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:{
                finish();
                return true;
            }
            case R.id.share:
                Toast.makeText(this, "공유 버튼 선택됨", Toast.LENGTH_LONG).show();
                return true;

            case R.id.declare:
                Toast.makeText(this, "신고 버튼 선택됨", Toast.LENGTH_LONG).show();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
