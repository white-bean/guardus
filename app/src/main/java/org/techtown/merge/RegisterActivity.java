package org.techtown.merge;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

public class RegisterActivity extends AppCompatActivity {

    private EditText idText;
    private EditText pwText;
    private EditText pwText2;
    private EditText nickNameText;
    private TextView text_fail;
    private TextView text_success;
    private AlertDialog dialog;
    private boolean validate = false;
    private boolean validate2 = false;

    Button btn_pw;

    Button registerButton;

    private Button btn_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        idText = (EditText) findViewById(R.id.userID);
        pwText = (EditText) findViewById(R.id.userPW1);
        pwText2 = (EditText) findViewById(R.id.userPW2);
        nickNameText = (EditText) findViewById(R.id.nickName);
        text_fail = findViewById(R.id.text_fail);
        text_success = findViewById(R.id.text_success);
        btn_pw = findViewById(R.id.btn_pw);

        final String pw1 = pwText.getText().toString();
        final String pw2 = pwText2.getText().toString();
        text_fail.setText("");
        text_success.setText("");
        btn_pw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!pw1.equals(pw2)){
                    text_fail.setText("비밀번호가 다릅니다");
                    text_success.setText("");
                }
                else{
                    text_success.setText("비밀번호 확인");
                    text_fail.setText("");
                }
            }
        });
        btn_back = findViewById(R.id.btn_back);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent registerIntent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(registerIntent);//액티비티 이동
            }
        });

        final Button validationID = (Button) findViewById(R.id.validateID);
        validationID.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userID = idText.getText().toString();
                if(validate)
                {
                    return;
                }
                if(userID.equals(""))
                {
                    AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                    dialog = builder.setMessage("사용자 ID를 입력하세요. ")
                        .setPositiveButton("확인",null)
                        .create();
                    dialog.show();

                }

                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try{
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");
                            if(success){
                                AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                                dialog = builder.setMessage("사용할 수 있는 아이디입니다. ")
                                        .setPositiveButton("확인",null)
                                        .create();
                                dialog.show();
                                idText.setEnabled(false);
                                validate = true;
                                idText.setBackgroundColor(getResources().getColor(R.color.colorGray));
                                validationID.setBackgroundColor(getResources().getColor(R.color.colorGray));

                            }
                            else{
                                AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                                dialog = builder.setMessage("사용할 수 없는 아이디입니다. ")
                                        .setNegativeButton("확인",null)
                                        .create();
                                dialog.show();
                            }
                        }
                        catch(Exception e){
                            e.printStackTrace();
                        }
                    }
                };
                ValidateRequest validateRequest = new ValidateRequest(userID,responseListener);
                RequestQueue queue = Volley.newRequestQueue(RegisterActivity.this);
                queue.add(validateRequest);
            }
        });

        final Button validationNN = (Button) findViewById(R.id.validateNN);
        validationNN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nickName = nickNameText.getText().toString();
                if(validate2)
                {
                    return;
                }
                if(nickName.equals(""))
                {
                    AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                    dialog = builder.setMessage("닉네임을 입력하세요. ")
                            .setPositiveButton("확인",null)
                            .create();
                    dialog.show();

                }

                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try{
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");
                            if(success){
                                AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                                dialog = builder.setMessage("사용할 수 있는 닉네임입니다. ")
                                        .setPositiveButton("확인",null)
                                        .create();
                                dialog.show();
                                nickNameText.setEnabled(false);
                                validate2 = true;
                                nickNameText.setBackgroundColor(getResources().getColor(R.color.colorGray));
                                validationNN.setBackgroundColor(getResources().getColor(R.color.colorGray));

                            }
                            else{
                                AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                                dialog = builder.setMessage("사용할 수 없는 닉네임입니다. ")
                                        .setNegativeButton("확인",null)
                                        .create();
                                dialog.show();
                            }
                        }
                        catch(Exception e){
                            e.printStackTrace();
                        }
                    }
                };
                ValidateRequest validateRequest = new ValidateRequest(nickName,responseListener);
                RequestQueue queue = Volley.newRequestQueue(RegisterActivity.this);
                queue.add(validateRequest);
            }
        });

        registerButton = (Button) findViewById(R.id.done);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userID = idText.getText().toString();
                String userPW = pwText.getText().toString();
                String userPW2 = pwText.getText().toString();
                String nickName = nickNameText.getText().toString();

                if(!validate){
                    AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                    dialog = builder.setMessage("ID 중복체크를 해주세요. ")
                            .setNegativeButton("확인",null)
                            .create();
                    dialog.show();
                    return;
                }
                if(!validate2){
                    AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                    dialog = builder.setMessage("닉네임 중복체크를 해주세요. ")
                            .setNegativeButton("확인",null)
                            .create();
                    dialog.show();
                    return;
                }

                if(userID.equals("")||userPW.equals("")||userPW2.equals("")||nickName.equals("")){
                    AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                    dialog = builder.setMessage("빈칸없이 입력해주세요. ")
                            .setNegativeButton("확인",null)
                            .create();
                    dialog.show();
                    return;
                }

                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try{
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");
                            if(success){
                                AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                                dialog = builder.setMessage("회원 등록에 성공했습니다. ")
                                        .setPositiveButton("확인",null)
                                        .create();
                                dialog.show();

                                Intent registerIntent = new Intent(RegisterActivity.this, LoginActivity.class);
                                startActivity(registerIntent);//액티비티 이동

                                finish();

                            }
                            else{
                                AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                                dialog = builder.setMessage("회원등록에 실패했습니다. ")
                                        .setNegativeButton("확인",null)
                                        .create();
                                dialog.show();
                            }
                        }
                        catch(Exception e){
                            e.printStackTrace();
                        }
                    }
                };
                RegisterRequest registerRequest = new RegisterRequest(userID, userPW, nickName, responseListener);
                RequestQueue queue = Volley.newRequestQueue(RegisterActivity.this);
                queue.add(registerRequest);


            }
        });


    }

        @Override
        protected void onStop(){
            super.onStop();
            if(dialog != null)
            {
                dialog.dismiss();
                dialog = null;
            }
        }

}