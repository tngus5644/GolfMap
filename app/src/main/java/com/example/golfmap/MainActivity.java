package com.example.golfmap;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    private Button button;
    private String urlString = "http://api.vworld.kr/req/data?service=data&request=GetFeature&data=LT_P_SGISGOLF&key=925A60ED-DDFB-3A08-9005-D4DA2EDF302C&domain=925A60ED-DDFB-3A08-9005-D4DA2EDF302C URL&[요청파라미터]";

//    private String urlString = "m.naver.com";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = new Intent(this, Fragment.class);

        button = (Button) findViewById(R.id.login); //페이지 이동 버튼
        button.setOnClickListener(new View.OnClickListener() {   //익명함수로 이벤트리스너 생성
            @Override
            public void onClick(View view) {
                startActivity(intent);
//                new RequestHttpURLConnection(urlString).execute();

            }
        });


    }


}

//AIzaSyC0A2b32bxWei1PmNZ2GlBPx4HlshZEiHc API_KEY
//AIzaSyAweEGxCNqA55VDc3TO9UXBTsM3dXJG8_o MAPS_API_KEY
