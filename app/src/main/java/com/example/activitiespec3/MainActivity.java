package com.example.activitiespec3;

import androidx.appcompat.app.AppCompatActivity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    private EditText editFechaNacimiento,editNombre,editPhone,editEmail,editDescripcion;
    private Button buttonSiguiente;

    private Calendar c;
    private DatePickerDialog dpd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        iniciarPantalla();
    }

    private void iniciarPantalla(){
        editNombre = (EditText) findViewById(R.id.editNombre);
        editPhone = (EditText) findViewById(R.id.editPhone);
        editEmail = (EditText) findViewById(R.id.editEmail);
        editDescripcion = (EditText) findViewById(R.id.editDescripcion);

        editFechaNacimiento = (EditText) findViewById(R.id.editFechaNacimiento);
        editFechaNacimiento.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //tratamos la fecha de nacimiento
                crearfechaNacimiento();
            }
        });

        buttonSiguiente = (Button) findViewById(R.id.buttonSiguiente);
        buttonSiguiente.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                siguiente();
            }
        });
    }

    private void siguiente() {
        //comprobamos que no este vacio
        if(!editFechaNacimiento.getText().toString().trim().equalsIgnoreCase("") && !editNombre.getText().toString().trim().equalsIgnoreCase("") &&
                !editPhone.getText().toString().trim().equalsIgnoreCase("") && !editEmail.getText().toString().trim().equalsIgnoreCase("") &&
                !editDescripcion.getText().toString().trim().equalsIgnoreCase("")){

            String nombre = editNombre.getText().toString();
            String phone = editPhone.getText().toString();
            String email = editEmail.getText().toString();
            String descricion = editDescripcion.getText().toString();
            String fechaNacimiento = editFechaNacimiento.getText().toString();

            Intent intent = new Intent(this,ConfirmarDatosActivity.class);
            //metemos la informacion que queremos pasa a la otra pagina
            //atraves de una constante
            intent.putExtra("nombre", nombre);
            intent.putExtra("phone", phone);
            intent.putExtra("email", email);
            intent.putExtra("descricion", descricion);
            intent.putExtra("fechaNacimiento", fechaNacimiento);
           // startActivityForResult(new Intent (getApplicationContext(),intent,REQUES_CODE));
            startActivity(intent);
        }else{
            //mostramos un mensaje
            Toast.makeText(MainActivity.this, R.string.imcompleto, Toast.LENGTH_SHORT).show();
        }
    }

    public void crearfechaNacimiento(){
        //recogemos la fecha actual del dispositivo
        c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);
        //abrimos un dilog con el calendario y la fecha actual del dispositivo
        mostrarDatePickerDilog(year,month,day);
    }
    public void mostrarDatePickerDilog( int year, int month, int day){
        dpd = new DatePickerDialog(MainActivity.this, new DatePickerDialog.OnDateSetListener(){
            @Override
            public void onDateSet(DatePicker datePicker, int mYear, int mMonth, int mDday) {
                //recogemos la fecha y la mostramos en el editText de la Fecha de Nacimiento
                editFechaNacimiento.setText(mDday+"/"+(mMonth+ 1)+"/"+mYear);
            }
        },day,month,year);
        //mostramos el dilog
        dpd.show();
    }
}