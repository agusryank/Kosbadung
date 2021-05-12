package com.example.kosbadung;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.kosbadung.server.AppController;
import com.example.kosbadung.server.ServerAPI;
import com.example.kosbadung.session.SessionManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {

    EditText et_username,et_password;
    Button btn_login;
    TextView tv_regis;
    SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        tv_regis = findViewById(R.id.tv_regis);
        tv_regis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent);
            }
        });
        sessionManager = new SessionManager(this);

        et_username=(EditText)findViewById(R.id.et_username);
        et_password=(EditText)findViewById(R.id.et_password);

        btn_login=(Button)findViewById(R.id.btn_login);

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Susername = et_username.getText().toString();
                String Spassword = et_password.getText().toString();

                if (Susername.equals("")){
                    et_username.setError("Anda belum menginputkan username");
                    et_username.requestFocus();
                }
                if (Spassword.equals("")){
                    et_password.setError("Anda belum menginputkan password");
                    et_password.requestFocus();
                } else
                    kirim_data();
            }
        });

    }

    void kirim_data(){

        StringRequest selectUser = new StringRequest(Request.Method.POST, ServerAPI.URL_login,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("volley","response : " + response.toString());

                        try {
                            JSONObject data = new JSONObject(response);
                            String status_login = data.getString("status_login");

                            if (status_login.equals("berhasil")){

                                String id_user = data.getString("id");
                                String nama_user = data.getString("Nama");
                                String alamat = data.getString("Alamat");
                                String jenis_kelamin =data.getString("Jenis_kelamin");
                                String no_telp = data.getString("No_telp");
                                String username = data.getString("Username");
                                String password = data.getString("Password");

                                sessionManager.sessionUser(id_user, nama_user, alamat, no_telp, jenis_kelamin, username, password, status_login);
                                Toast.makeText(LoginActivity.this, "Login Berhasil", Toast.LENGTH_SHORT).show();
                                Intent intent=new Intent(getApplicationContext(),BerandaActivity.class);
                                startActivity(intent);
                            } else if (status_login.equals("gagal")){
                                Toast.makeText(LoginActivity.this, "Username Atau Password salah", Toast.LENGTH_SHORT).show();
                            }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    }
                },new Response.ErrorListener(){
                        @Override
                        public void onErrorResponse(VolleyError error){
                        Log.d ("volley", "Error : " + error.getMessage());
                        Toast.makeText(LoginActivity.this, "Login Gagal", Toast.LENGTH_SHORT).show();
                    }
                }) {
                        @Override
                        protected Map<String, String> getParams(){
                            Map<String, String> params = new HashMap<>();
                            params.put("Username",et_username.getText().toString());
                            params.put("Password", et_password.getText().toString());
                            return params;
                        }
                };
        AppController.getInstance().addToRequestQueue(selectUser);
    }


}
