package com.willianhdz.permisoscamara;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity2 extends AppCompatActivity {

    private static final int REQUEST_VIDEO_CAPTURE = 100;
    private static final int REQUEST_PERMISSION_CAPTURE = 101;

    Uri uri;
    Button btnGrabar, btnPlay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        btnGrabar = findViewById(R.id.btnGrabar);
        btnPlay = findViewById(R.id.btnPlay);

        btnGrabar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    if (ActivityCompat.checkSelfPermission(MainActivity2.this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
                        recordVideo();

                    } else {
                        ActivityCompat.requestPermissions(MainActivity2.this, new String[]{Manifest.permission.CAMERA}, REQUEST_PERMISSION_CAPTURE);

                    }

                }else{
                    recordVideo();
                }
            }
        });



        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (uri != null){
                    irVideoActivity(uri.toString());
                }else {
                    Toast.makeText(MainActivity2.this, "Tiene que grabar un video", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == REQUEST_PERMISSION_CAPTURE){
            if (permissions.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                recordVideo();
            }else {
                Toast.makeText(this, "Necesitas habilitar los permisos", Toast.LENGTH_SHORT).show();
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == REQUEST_VIDEO_CAPTURE){
            if (resultCode == Activity.RESULT_OK && data!= null){
                uri = data.getData();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void recordVideo(){
        Intent intent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
        if (intent.resolveActivity(getPackageManager()) != null){
            //Tiempo de duracion que va tener el video
            intent.putExtra(MediaStore.EXTRA_DURATION_LIMIT, 8);
            startActivityForResult(intent, REQUEST_VIDEO_CAPTURE);
        }
    }

    private void irVideoActivity(String uriString){
        Intent intent = new Intent(this, VideoActivity.class);
        intent.putExtra("VIDEO", uriString);
        startActivity(intent);
    }
}