package com.example.golfmap;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.maps.CameraUpdateFactory;


public class Fragment extends AppCompatActivity {
    private Button zoomInButton;
    private Button zoomOutButton;

    MapsFragment mf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        this.InitializeView();
        this.SetListener();
    }


    public void InitializeView(){

        zoomInButton = (Button) findViewById(R.id.zoomInButton);
        zoomOutButton = (Button) findViewById(R.id.zoomOutButton);
        ///GoogleMap에서 확대, 축소 기능을 지원하는것은 알고 있지만 fragment를 control해보기 위해 만들었습니다.

        mf = (MapsFragment) getSupportFragmentManager().findFragmentById(R.id.map);


    }

    public void SetListener() { //재사용성을 위해 리스너 구현.
        View.OnClickListener Listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (view.getId() == zoomInButton.getId()) {
                    int temp = mf.getZoomCount();
                    mf.setZoomCount(++temp);
                    mf.mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(mf.cameraPosition, mf.getZoomCount()));

                } else if(view.getId() == zoomOutButton.getId()) {
                    int temp = mf.getZoomCount();
                    mf.setZoomCount(--temp);
                    mf.mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(mf.cameraPosition, mf.getZoomCount()));
                }

            }
        };

        zoomInButton.setOnClickListener(Listener);
        zoomOutButton.setOnClickListener(Listener);
    }




}
