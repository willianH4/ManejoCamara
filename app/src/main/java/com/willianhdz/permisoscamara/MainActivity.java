package com.willianhdz.permisoscamara;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.github.fafaldo.fabtoolbar.widget.FABToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.security.PrivateKey;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    modal_Toast_Custom modal = new modal_Toast_Custom(); //instancia de la clase

    private FABToolbarLayout morph; //agregado para la toolbar
    Button capturar, guardar;
    ImageView foto;
    Bitmap bitmap;
    View uno, dos, tres, cuatro, cinco;

    private static final int REQUEST_PERMISSION_CAMERA = 100;
    private static final int TAKE_PICTURE = 101;

    private static final int REQUEST_PERMISSION_WRITE_STORAGE = 200;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //Inicia codigo para la Toolbar
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        morph = (FABToolbarLayout) findViewById(R.id.fabtoolbar);

        uno = findViewById(R.id.capturar);
        dos = findViewById(R.id.guardar);
        tres = findViewById(R.id.tres);
        cuatro = findViewById(R.id.cuatro);
        cinco = findViewById(R.id.cinco);

        fab.setOnClickListener(this);
        //uno.setOnClickListener(this);
        //dos.setOnClickListener(this);
        tres.setOnClickListener(this);
        cuatro.setOnClickListener(this);
        cinco.setOnClickListener(this);
//Finaliza codigo para la Toolbar


        //UI
        initUI();
        //capturar.setOnClickListener(this);
        //guardar.setOnClickListener(this);
        uno.setOnClickListener(this);
        dos.setOnClickListener(this);
    }

    private void initUI() {
        //capturar = findViewById(R.id.capturar);
        //guardar = findViewById(R.id.guardar);
        uno = findViewById(R.id.capturar);
        dos = findViewById(R.id.guardar);
        foto = findViewById(R.id.foto);
    }

    /*
    @Override
    public void onClick(View v) {
        int id = v.getId();

        if (id == R.id.capturar) {
            checkPermissionCamera();
        } else if (id == R.id.guardar) {
            checkPermissionStorage();
        }
    }


     */

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == TAKE_PICTURE){
            if (resultCode == Activity.RESULT_OK && data != null){
                bitmap = (Bitmap) data.getExtras().get("data");
                foto.setImageBitmap(bitmap);
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == REQUEST_PERMISSION_CAMERA) {
            if (permissions.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                takePicture();
            }
        }else if (requestCode == REQUEST_PERMISSION_WRITE_STORAGE){
            if (permissions.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                guardarImagen();
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }


        private void checkPermissionCamera () {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
                    takePicture();
                } else {
                    ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA},
                            REQUEST_PERMISSION_CAMERA
                    );
                }

            } else {
                takePicture();
            }
        }

    private void checkPermissionStorage() {
        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.P){
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                if (ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED){
                    guardarImagen();
                }else {
                    ActivityCompat.requestPermissions(
                            this,
                            new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                            REQUEST_PERMISSION_WRITE_STORAGE
                    );
                }

            }else {
                guardarImagen();
            }

        }else {
            guardarImagen();
        }
    }

        private void takePicture () {
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            if (intent.resolveActivity(getPackageManager()) != null){
                startActivityForResult(intent,TAKE_PICTURE);
            }
        }

        private void guardarImagen(){
            OutputStream fos = null;
            File file = null;

            //este metodo es lo esencial de la app
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q){
                ContentResolver resolver = getContentResolver();
                ContentValues values = new ContentValues();

                String fileName = System.currentTimeMillis() + "image_example";

                values.put(MediaStore.Images.Media.DISPLAY_NAME,fileName);
                values.put(MediaStore.Images.Media.MIME_TYPE, "image/jpg");
                values.put(MediaStore.Images.Media.RELATIVE_PATH, "Picture/Myapp");
                values.put(MediaStore.Images.Media.IS_PENDING,1);

                Uri collection = MediaStore.Images.Media.getContentUri(MediaStore.VOLUME_EXTERNAL_PRIMARY);
                Uri imageUri = resolver.insert(collection,values);

                try {
                    fos = resolver.openOutputStream(imageUri);
                }catch (FileNotFoundException e){
                    e.printStackTrace();
                }

                values.clear();
                values.put(MediaStore.Images.Media.IS_PENDING,0);
                resolver.update(imageUri,values,null,null);

            }else {
                String imageDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).toString();

                String fileName = System.currentTimeMillis() + ".jpg";

                file = new File(imageDir,fileName);

                try {
                    fos = new FileOutputStream(file);
                }catch (FileNotFoundException e){
                    e.printStackTrace();
                }
            }

            boolean save = bitmap.compress(Bitmap.CompressFormat.JPEG, 100,fos);
            if (save){
                Toast.makeText(this, "Imagen guardada exitosamente", Toast.LENGTH_SHORT).show();
            }

            if (fos != null){
                try {
                    fos.flush();
                    fos.close();
                }catch (IOException e){
                    e.printStackTrace();
                }
            }

            if (file != null){ //Esto es para las apis menores a las 29
                MediaScannerConnection.scanFile(this, new String[]{file.toString()}, null, null);
            }
        }


    //metodo de la toolbarr
    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.fab) {
            morph.show();
        }
        morph.hide();
        if (v.getId() == R.id.capturar){
            checkPermissionCamera();
            Toast.makeText(getApplicationContext(),"Accediendo a la camara",Toast.LENGTH_SHORT).show();

        }else if (v.getId() == R.id.guardar){
           /* Intent listViewActivity = new Intent(MainActivity.this, List_view_articulos.class);
            startActivity(listViewActivity);

            */
            checkPermissionStorage();
            Toast.makeText(getApplicationContext(),"Registro guardado correctamente :)",Toast.LENGTH_SHORT).show();

        }else if (v.getId() == R.id.tres){
           Intent intent = new Intent(MainActivity.this, MainActivity2.class);
            startActivity(intent);

            Toast.makeText(getApplicationContext(),"Prueba para video",Toast.LENGTH_SHORT).show();
            //ventanas.Search(MainActivity.this);

        }else if (v.getId() == R.id.cuatro){
            Intent intent = new Intent(MainActivity.this, Acerca.class);
            startActivity(intent);

            //Toast.makeText(getApplicationContext(),"Prueba para video",Toast.LENGTH_SHORT).show();
            //ventanas.Search(MainActivity.this);

        }else if (v.getId() == R.id.cinco){
          Toast.makeText(getApplicationContext(),"Cerrando app",Toast.LENGTH_SHORT).show();
          finishAffinity();
           // Intent listViewActivity = new Intent(MainActivity.this, Consulta_spinner.class);
           // startActivity(listViewActivity);
        }
    }

}