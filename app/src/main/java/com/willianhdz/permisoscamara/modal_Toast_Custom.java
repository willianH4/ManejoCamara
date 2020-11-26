package com.willianhdz.permisoscamara;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

//import android.support.v7.app.AppCompatActivity;

//Toco aplicar herencia de la clase AppCompatActivity para que no diera problemas el mètodo getLayoutInflater()
//public class modal_Toast_Custom extends AppCompatActivity{
public class modal_Toast_Custom extends AppCompatActivity {
    Dialog myDialog;
    androidx.appcompat.app.AlertDialog.Builder dialogo;
    AlertDialog.Builder dialogo1;

    /*
    public void callModalBasic(final Context context){
        //Creo el dialogo con sus propiedad
        myDialog = new Dialog(context);
        myDialog.setContentView(R.layout.ventana1);
        myDialog.setTitle("Modal");
        myDialog.setCancelable(false);

        //Mapeo o referencia a los controles en el diseño/layout
        final EditText et_cod = (EditText)myDialog.findViewById(R.id.et_cod);
        Button btn_buscar = (Button)myDialog.findViewById(R.id.btn_buscar);
        TextView tv_close = (TextView)myDialog.findViewById(R.id.tv_close);
        //Fin del mapeo

        //Generando evento clic al control de nombre: tv_close
        tv_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Instrucciòn para cerrar dialogo
                myDialog.dismiss();
            }
        });

        //Generando evento clic al control de nombre: btn_buscar
        btn_buscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Acciones a realizar para evento clic boton buscar.
                Toast.makeText(context, "Mensaje personalizado Modal", Toast.LENGTH_SHORT).show();
            }
        });

        //Instruccion para dar transparencia al dialo
        myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        //Instruccion para mostrar el diagolo
        myDialog.show();

    }



    public void modalConfirm(final Context context){
        String mensaje = "¿Clic en Aceptar para Confirmar Accion?";
        //final android.app.AlertDialog.Builder mBuilder = new android.app.AlertDialog.Builder(context);
        //dialogo = new androidx.appcompat.app.AlertDialog.Builder(context);
        dialogo = new androidx.appcompat.app.AlertDialog.Builder(context);
        //dialogo = new AlertDialog.Builder(context);
        dialogo.setIcon(R.drawable.ic_borrar);
        dialogo.setTitle("Warning");
        dialogo.setMessage(mensaje);
        dialogo.setCancelable(true);

        dialogo.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogo, int id) {
                //Acciones a ejecutar al dar clic en eliminar
                Toast.makeText(context, "Clic Aceptar.", Toast.LENGTH_LONG).show();
                //showToast1(context, "Registro eliminado correctamente");
            }
        });

        dialogo.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogo, int id) {
                Toast.makeText(context, "Clic Cancelar.", Toast.LENGTH_LONG).show();
                //showToast1(context, "Operacion cancelada correctamente");
            }
        });
        dialogo.show();
    }


    public void dialogConfirm1(final Context context) {
        new androidx.appcompat.app.AlertDialog.Builder(context)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle("Warning")
                .setMessage("¿Clic en Aceptar para Confirmar Accion?")
                .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //Intentamos activarlo con el siguiente intent.
                        Toast.makeText(context, "Clic en Boton SI.", Toast.LENGTH_LONG).show();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //Salimos de la app
                        Toast.makeText(context, "Clic en Boton NO.", Toast.LENGTH_LONG).show();
                        finish();
                    }
                })
                .show();
    }



    public void dialogConfirm2(final Context context){

        new AlertDialog.Builder(context)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle("Salir")
                .setMessage("¿Esta seguro?\n\nClic en ok para confirmar")
                .setNegativeButton(android.R.string.cancel, null)
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {//un listener que al pulsar, cierre la aplicacion
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(context, "chi...", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                })
                .show();

    }


    public void dialgoConfirm3(final Context context){

        dialogo1 = new AlertDialog.Builder(context);
        dialogo1.setIcon(android.R.drawable.ic_dialog_alert);
        //dialog.setTitle("COCESNA.");
        dialogo1.setTitle("INFORMACION");
        dialogo1.setMessage("Contenido del mensaje");
        dialogo1.setCancelable(false);

        dialogo1.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogo, int id) {
                Toast toast = Toast.makeText(getApplicationContext(), "Aceptado.", Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER, 0, 0);
                toast.show();
            }
        });

        //dialogo1.setNegativeButton("Cancelar", null);
        dialogo1.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
        public void onClick(DialogInterface dialogo, int id) {
            Toast toast = Toast.makeText(getApplicationContext(), "Cancelado.", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();
            }
        });

        dialogo1.show();
    }


    public void dialogConfirm4(final Context context){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        //builder.setIcon(android.R.drawable.ic_dialog_alert);
        builder.setIcon(R.drawable.ic_error);
        builder.setMessage(getString(R.string.mensaje));
        builder.setCancelable(false);
        builder.setNeutralButton(R.string.ok, null);
        builder.setTitle(getString(R.string.error));
        builder.create().show();
    }



    public void dialogConfirmCustom1(final Context context) {
        myDialog = new Dialog(context);
        myDialog.setContentView(R.layout.confirmcustom);

        ImageView ivClose = (ImageView)myDialog.findViewById(R.id.ivClose);
        Button btnAccept = (Button)myDialog.findViewById(R.id.btnAccept);

        ivClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDialog.dismiss();
            }
        });

        btnAccept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Clic en Aceptar.", Toast.LENGTH_SHORT).show();
            }
        });

        myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        myDialog.show();
    }



    public void dialogConfirmCustom2(final Context context) {
        //dialogo1 = new AlertDialog.Builder(context);

        myDialog = new Dialog(context);
        myDialog.setContentView(R.layout.confirmcustom1);
        myDialog.setCancelable(false);

        ImageView ivClose = (ImageView)myDialog.findViewById(R.id.ivClose);
        Button btnAccept = (Button)myDialog.findViewById(R.id.btnAccept);
        Button btnCancel = myDialog.findViewById(R.id.btnCancel);

        ivClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDialog.dismiss();
            }
        });

        btnAccept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(context, "Clic en Aceptar.", Toast.LENGTH_SHORT).show();
                finish();
                Intent intent = new Intent(context, MainActivity.class);
                context.startActivity(intent);
                //startActivity(intent);
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(context, "Clic en Cancelar", Toast.LENGTH_SHORT).show();
                myDialog.dismiss();
            }
        });

        myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        myDialog.show();
    }


     */


    public void dialogAbout(final Context context) {
        //dialogo1 = new AlertDialog.Builder(context);
        myDialog = new Dialog(context);
        myDialog.setContentView(R.layout.dialog_autor);
        myDialog.setCancelable(false);

        ImageView BtnCerrarAutor = myDialog.findViewById(R.id.BtnCerrarAutor);

        BtnCerrarAutor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDialog.dismiss();
            }
        });

        myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        myDialog.show();
    }


/*
    public void dialogForm(final Context context) {
        //dialogo1 = new AlertDialog.Builder(context);
        myDialog = new Dialog(context);
        myDialog.setContentView(R.layout.modalogin);
        myDialog.setCancelable(false);

        final EditText etEmail = myDialog.findViewById(R.id.etEmail);
        final EditText etPassword = myDialog.findViewById(R.id.etPassword);
        Button btnAccept = myDialog.findViewById(R.id.btnAccept);
        ImageView BtnCerrar = myDialog.findViewById(R.id.BtnCerrar);

        btnAccept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String dato1 = etEmail.getText().toString();
                String dato2 = etPassword.getText().toString();

                Toast toast = Toast.makeText(context, "Datos: \n" + dato1 + "\n"+dato2, Toast.LENGTH_LONG);
                toast.setGravity(Gravity.CENTER, 0, 0);
                toast.show();
            }
        });

        BtnCerrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDialog.dismiss();
            }
        });

        myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        myDialog.show();
    }

 */
}
