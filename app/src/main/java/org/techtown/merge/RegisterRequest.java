package org.techtown.merge;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class RegisterRequest extends StringRequest {

    final static private String URL = "https://e278ab235596.ngrok.io/accounts/rest-auth/registration/";
    private Map<String, String> parameters;

    public RegisterRequest(String userID, String userPW, String nickName, Response.Listener<String> listener){
        super(Method.POST, URL, listener, null);
        parameters = new HashMap<>();
        parameters.put("username", userID);
        parameters.put("password1", userPW);
        parameters.put("nickName", nickName);
    }
    @Override
    public Map<String, String> getParams(){
        return parameters;
    }
}
