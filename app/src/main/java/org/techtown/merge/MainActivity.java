package org.techtown.merge;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    List<Person> posts;
    private static String JSON_URL = " https://ba782cd7df41.ngrok.io/post ";
    PersonAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerview);
        posts = new ArrayList <>();
        extractPosts();


/*
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        PersonAdapter adapter = new PersonAdapter();

        adapter.addItem(new Person("연남동에 화재가 났어요", "서울 마포구 동교로32길 3","3분전","4"));
        adapter.addItem(new Person("잠수교가 통제됐어요", "서울 서초구 반포동","4분전","12"));
        adapter.addItem(new Person("월계2교 공사중입니다", "서울 노원구 월계동","15분전","2"));
        adapter.addItem(new Person("선곡초등학교 앞 통제됐습니다", "서울시 노원구 광운로2길","18분전","4"));
        adapter.addItem(new Person("홍대 앞 치한나타났습니다", "서울 마포구 홍익로 18","20분전","12"));
        adapter.addItem(new Person("홍길동", "010-3000-3000","15","2"));
        adapter.addItem(new Person("김민수", "010-1000-1000","3","4"));
        adapter.addItem(new Person("김하늘", "010-2000-2000","4","12"));
        adapter.addItem(new Person("홍길동", "010-3000-3000","15","2"));
        adapter.addItem(new Person("김민수", "010-1000-1000","3","4"));
        adapter.addItem(new Person("김하늘", "010-2000-2000","4","12"));
        adapter.addItem(new Person("홍길동", "010-3000-3000","15","2"));
        recyclerView.setAdapter(adapter);*/
    }
    private void extractPosts(){
        RequestQueue queue = Volley.newRequestQueue(this);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.POST, JSON_URL, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject postObject = response.getJSONObject(i);

                        Person post = new Person();
                        post.setTitle(postObject.getString("title"));
                        post.setAddress(postObject.getString("address"));
                        post.setCoverImage(postObject.getString("image"));
                        posts.add(post);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                adapter = new PersonAdapter(getApplicationContext(), posts);
                recyclerView.setAdapter(adapter);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("tag", "onErrorResponse: " + error.getMessage());
            }
        });

        queue.add(jsonArrayRequest);
    }
}