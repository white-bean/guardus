package org.techtown.merge;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class LoginRequest extends StringRequest {

    final static private String URL = "https://e278ab235596.ngrok.io/accounts/rest-auth/registration/";
    private Map<String, String> map;

    public LoginRequest(String userID, String userPW, Response.Listener<String> listener){
        super(Method.POST, URL, listener, null);

        map = new HashMap<>();
        map.put("username", userID);
        map.put("password1", userPW);
    }

    @Override
    public Map<String, String> getParams(){
        return map;
    }
}
