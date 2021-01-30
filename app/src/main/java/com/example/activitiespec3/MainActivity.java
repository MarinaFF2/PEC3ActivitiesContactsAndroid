package com.example.activitiespec3;

import androidx.appcompat.app.AppCompatActivity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    EditText editFechaNacimiento,editNombre,editPhone,editEmail,editDescripcion;
    Button buttonSiguiente;
    Calendar c;
    DatePickerDialog dpd;
    int cont = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(cont == 0){
            iniciarPantalla();

            editFechaNacimiento.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                        //tratamos la fecha de nacimiento
                        crearfechaNacimiento();
                }
            });
            cont++;
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        if(cont == 1){
            iniciarPantalla();
            editFechaNacimiento.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    modificarfechaNacimiento();
                }
            });
        }
    }

    private void iniciarPantalla(){
        editNombre = (EditText) findViewById(R.id.editNombre);
        editPhone = (EditText) findViewById(R.id.editPhone);
        editEmail = (EditText) findViewById(R.id.editEmail);
        editDescripcion = (EditText) findViewById(R.id.editDescripcion);

        editFechaNacimiento = (EditText) findViewById(R.id.editFechaNacimiento);

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
    public void modificarfechaNacimiento(){
        //recogemos la fecha a modificar
        String fechaModificar = editFechaNacimiento.getText().toString();
        String[] parts = fechaModificar.split("/");
        int year = Integer.parseInt(parts[0]);
        int month = Integer.parseInt(parts[1]);
        int day = Integer.parseInt(parts[2]);
        //abrimos un dilog con el calendario y la fecha a modificar
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