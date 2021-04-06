package com.example.golfmap;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

    private Button button;

    private EditText accountText;
    private EditText passwordText;

    private String shared = "file";
//    private String urlString = "m.naver.com";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        accountText = findViewById(R.id.accountText);
        passwordText = findViewById(R.id.passwordText);

        ///Password는 제외하고, account는 자동 저장.
        SharedPreferences sharedPreferences= getSharedPreferences(shared, 0);
        String value = sharedPreferences.getString("yoon", " ");
        accountText.setText(value);

        Intent intent = new Intent(this, Fragment.class);


        button = (Button) findViewById(R.id.login); //페이지 이동 버튼
        button.setOnClickListener(new View.OnClickListener() {   //일회성이기때문에 익명함수로 이벤트리스너 생성
            @Override
            public void onClick(View view) {
                Context context = getApplicationContext();
                CharSequence text = "Hello Bottle!";
                int duration = Toast.LENGTH_LONG;

                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
                startActivity(intent);
                finish();
//                new RequestHttpURLConnection(urlString).execute();
            }
        });


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        SharedPreferences sharedPreferences = getSharedPreferences(shared, 0);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        String value = accountText.getText().toString();
        editor.putString("yoon", value);
        editor.commit();


    }


}

//AIzaSyC0A2b32bxWei1PmNZ2GlBPx4HlshZEiHc API_KEY
//AIzaSyAweEGxCNqA55VDc3TO9UXBTsM3dXJG8_o MAPS_API_KEY
