package com.example.golfmap;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;


public class Fragment extends AppCompatActivity {
    private Button zoomInButton;
    private Button zoomOutButton;

    MapsFragment mf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);

        this.InitializeView();
        this.SetListener();
    }


    public void InitializeView(){
        zoomInButton = (Button) findViewById(R.id.zoomInButton);
        zoomOutButton = (Button) findViewById(R.id.zoomOutButton);

        mf = (MapsFragment) getSupportFragmentManager().findFragmentById(R.id.map);

    }

    public void SetListener() { //재사용성을 위해 리스너 구현.
        View.OnClickListener Listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (view.getId() == zoomInButton.getId()) {
                    int temp = mf.getZoomCount();
                    mf.setZoomCount(++temp);

                } else if(view.getId() == zoomOutButton.getId()) {
                    int temp = mf.getZoomCount();
                    mf.setZoomCount(--temp);
                }

            }
        };

        zoomInButton.setOnClickListener(Listener);
        zoomOutButton.setOnClickListener(Listener);
    }




}
