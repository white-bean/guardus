package org.techtown.merge;

import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.pedro.library.AutoPermissions;
import com.pedro.library.AutoPermissionsListener;

import java.io.IOException;
import java.util.List;

public class Map4writepostActivity extends AppCompatActivity implements AutoPermissionsListener {
    SupportMapFragment mapFragment;
    GoogleMap map;
    TextView textView5;
    Geocoder geocoder;
    Double latitude;
    Double longitude;
    String sendMsg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map4writepost);

        textView5 = findViewById(R.id.textView5);
        Button button = findViewById(R.id.button);



        geocoder = new Geocoder(this);

        mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(final GoogleMap googleMap) {
                map = googleMap;
                map.setMyLocationEnabled(true);


                map.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
                    @Override
                    public void onMapClick(LatLng point) {
                        MarkerOptions mOptions = new MarkerOptions();
                        mOptions.title("마커 좌표");
                        latitude = point.latitude;
                        longitude = point.longitude;
                        mOptions.snippet(latitude.toString()+", "+longitude.toString());
                        mOptions.position(new LatLng(latitude, longitude));
                        //mOptions.draggable(true);
                        googleMap.addMarker(mOptions);


                        List<Address> list = null;

                        try{
                            list = geocoder.getFromLocation(latitude, longitude, 5);
                        }catch (IOException e){
                            e.printStackTrace();
                            Log.e("test", "입출력 오류 - 서버에서 주소변환시 에러발생");
                        }
                        if(list != null){
                            if(list.size()==0){
                                textView5.setText("해당되는 주소 정보는 없습니다");
                            }else{
                                String cut[] = list.get(0).toString().split(" ");
                                for(int i=0; i<cut.length; i++){
                                    //System.out.println("cut["+i+"] : " + cut[i]);
                                } // cut[0] : Address[addressLines=[0:"대한민국
                                // cut[1] : 서울특별시  cut[2] : 송파구  cut[3] : 오금동
                                // cut[4] : cut[4] : 41-26"],feature=41-26,admin=null ~~~~
                                sendMsg = cut[1]+" "+cut[2] + " " + cut[3];
                                textView5.setText(sendMsg);
                            }
                        }
                    }
                });

            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), writepostActivity.class);
                intent.putExtra("msg", sendMsg);

                startActivity(intent);
            }
        });
/*
        List<Address> list = null;

        try{
            list = geocoder.getFromLocation(latitude, longitude, 5);
        }catch (IOException e){
            e.printStackTrace();
            Log.e("test", "입출력 오류 - 서버에서 주소변환시 에러발생");
        }
        if(list != null){
            if(list.size()==0){
                textView5.setText("해당되는 주소 정보는 없습니다");
            }else{
                textView5.setText(list.get(0).toString());
            }
        }

 */




        try{
            MapsInitializer.initialize(this);
        }catch (Exception e){
            e.printStackTrace();
        }


        AutoPermissions.Companion.loadAllPermissions(this, 101);
    }


    @Override
    protected void onPause() {
        super.onPause();
        if(map!=null){
            map.setMyLocationEnabled(false);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(map!=null){
            map.setMyLocationEnabled(true);
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        AutoPermissions.Companion.parsePermissions(this, requestCode, permissions, this);
    }

    @Override
    public void onDenied(int i, String[] strings) {

    }

    @Override
    public void onGranted(int i, String[] strings) {

    }

    class GPSListener implements LocationListener{
        public void onLocationChanged(Location location){
            Double latitude = location.getLatitude();
            Double longitude = location.getLongitude();

            showCurrentLocation(latitude, longitude);
        }

        private void showCurrentLocation(Double latitude, Double longitude){
            LatLng curPoint = new LatLng(latitude, longitude);
            map.animateCamera(CameraUpdateFactory.newLatLngZoom(curPoint, 15));
        }


        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {

        }

        @Override
        public void onProviderEnabled(String provider) {

        }

        @Override
        public void onProviderDisabled(String provider) {

        }

    }
}
