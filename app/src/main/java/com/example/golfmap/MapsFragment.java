package com.example.golfmap;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.VisibleRegion;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicReference;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class MapsFragment extends Fragment {
    private int zoomCount = 15;
    private GoogleMap mMap;


    private OnMapReadyCallback callback = new OnMapReadyCallback() {
        @Override
        public void onMapReady(GoogleMap googleMap) {
            AtomicReference<String> url = new AtomicReference<>("http://api.vworld.kr/req/data?service=data&request=GetFeature&data=LT_P_SGISGOLF&key=0E4100B2-CC9B-3652-A565-FBBE16E2E8A6&domain=www.tngus5644.com&size=10&geomFilter=POINT");

            mMap = googleMap;
            LatLng cameraPosition = new LatLng(37.413039996482894, 127.09956268470926);


            mMap.addMarker(new MarkerOptions().position(cameraPosition).title("Best Company, Bottle"));
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(cameraPosition, zoomCount));
            mMap.animateCamera(CameraUpdateFactory.zoomTo(17.0f));


            mMap.setOnCameraMoveListener(() -> {

                CameraPosition position = mMap.getCameraPosition();
                url.set(url + "(" + position.target.longitude + "," + position.target.latitude + ")");
                System.out.println(position);
                System.out.println(url);
                url.set("http://api.vworld.kr/req/data?service=data&request=GetFeature&data=LT_P_SGISGOLF&key=0E4100B2-CC9B-3652-A565-FBBE16E2E8A6&domain=www.tngus5644.com&size=10&geomFilter=POINT");
            });

//            new HttpAsyncTask().execute(url.toString());


        }


    };

    private void findMarker(double left, double top, double right, double bottom) {


    }

    private static class HttpAsyncTask extends AsyncTask<String, Void, String> {

        OkHttpClient client = new OkHttpClient();

        @Override
        protected String doInBackground(String... params) {
            String result = null;
            String strUrl = params[0];

            try {
                Request request = new Request.Builder()
                        .url(strUrl)
                        .build();
                Response response = client.newCall(request).execute();
                System.out.println(response.body().string());
                result = response.body().string();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {


        return inflater.inflate(R.layout.fragment_maps, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SupportMapFragment mapFragment =
                (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(callback);
        }
    }

    public int getZoomCount() {
        return zoomCount;
    }

    public void setZoomCount(int zoomCount) {
        this.zoomCount = zoomCount;
    }
}