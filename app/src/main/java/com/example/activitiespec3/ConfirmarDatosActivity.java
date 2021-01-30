package com.example.activitiespec3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ConfirmarDatosActivity extends AppCompatActivity {
    public String nombre = null, phone = null, email = null, descripcion = null, fechaNacimiento = null;
    TextView textNombre, textFechaNacimiento, textPhone, textEmail, textDescripcion;
    Button buttonEditarDatos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmar_datos);

        iniciarPantalla();
    }

    private void iniciarPantalla(){
        nombre = getIntent().getExtras().getString("nombre");
        phone = getIntent().getExtras().getString("phone");
        email = getIntent().getExtras().getString("email");
        descripcion = getIntent().getExtras().getString("descricion");
        fechaNacimiento = getIntent().getExtras().getString("fechaNacimiento");

        textNombre = (TextView) findViewById(R.id.textNombre);
        textNombre.setText(textNombre.getText().toString()+" "+nombre);
        textFechaNacimiento = (TextView) findViewById(R.id.textFechaNacimiento);
        textFechaNacimiento.setText(textFechaNacimiento.getText().toString()+" "+fechaNacimiento);
        textPhone = (TextView) findViewById(R.id.textPhone);
        textPhone.setText(textPhone.getText().toString()+" "+phone);
        textEmail = (TextView) findViewById(R.id.textEmail);
        textEmail.setText(textEmail.getText().toString()+" "+email);
        textDescripcion = (TextView) findViewById(R.id.textDescripcion);
        textDescripcion.setText(textDescripcion.getText().toString()+" "+descripcion);

        buttonEditarDatos = (Button) findViewById(R.id.buttonEditarDatos);
        buttonEditarDatos.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                editarDatos();
            }
        });
    }
    private void editarDatos() {
            Intent intent = new Intent(this,MainActivity.class);
            //metemos la informacion que queremos pasa a la otra pagina
            //atraves de una constante
            intent.putExtra("nombre", nombre);
            intent.putExtra("phone", phone);
            intent.putExtra("email", email);
            intent.putExtra("descricion", descripcion);
            intent.putExtra("fechaNacimiento", fechaNacimiento);
            startActivityForResult(intent,1);
            //startActivity(intent);

    }
}