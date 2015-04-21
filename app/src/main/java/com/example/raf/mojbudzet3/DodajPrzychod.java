package com.example.raf.mojbudzet3;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Date;
import java.util.Calendar;

public class DodajPrzychod extends ActionBarActivity {

    ZarzadcaBazy zb=new ZarzadcaBazy(this);
    Button button;
    EditText Data;
    int rok,miesiac,dzien;
    static final int DIALOG_ID=0;
    //zmienne potrzebne do dodawania przychod

    String DataDochod;
    float Kwota;
    String Uwagi;
    EditText KwotaET,DataET,UwagiET;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dochody);
        KwotaET=(EditText)findViewById(R.id.KwotaDochod);
        DataET=(EditText)findViewById(R.id.Data_wys2);
        UwagiET=(EditText)findViewById(R.id.UwagiDochod);


        final Calendar cal=Calendar.getInstance();
        rok=cal.get(Calendar.YEAR);
        miesiac=cal.get(Calendar.MONTH);
        dzien=cal.get(Calendar.DAY_OF_MONTH);

        DajKalendarz();
    }

    public void DajKalendarz(){
        button=(Button)findViewById(R.id.Kalendarz_A);
        button.setOnClickListener(
                new View.OnClickListener(){
                    @Override
                    public void  onClick(View w){
                        showDialog(DIALOG_ID);

                    }
                }
        );
    }

    @Override//przy otwieraniu okna dodaje sie kategoria RTV/AGD o id:1
    protected Dialog onCreateDialog(int id){
        if(id==DIALOG_ID){
            return new DatePickerDialog(this,dpickerListener,rok,miesiac,dzien);
        }else{
            return null;
        }
    }
    private DatePickerDialog.OnDateSetListener dpickerListener=
            new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                    rok=year;
                    miesiac=monthOfYear+1;
                    dzien=dayOfMonth;
                    // Toast.makeText(DodajWydatek.this,rok+"-"+miesiac+"-"+dzien,Toast.LENGTH_LONG).show();
                    Data=(EditText)findViewById(R.id.Data_wys2);
                    Data.setText(rok+"-"+miesiac+"-"+dzien);

                }
            };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_dochody, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle ąaction bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void DodajDochody(View view) {

        Kwota=Float.parseFloat(KwotaET.getText().toString());
        DataDochod=DataET.getText().toString();
        Uwagi=UwagiET.getText().toString();

        try{
            zb.dodajDochod(Date.valueOf(DataDochod),Kwota,Uwagi);
            Toast.makeText(DodajPrzychod.this,"Udalo sie dodac dochod",Toast.LENGTH_LONG).show();
        }catch(Exception e) {
            Toast.makeText(DodajPrzychod.this, "" + e, Toast.LENGTH_LONG).show();
        }


    }

    public void PowrotDochody(View view) {
            Intent Powrot = new Intent("com.example.raf.mojbudzet3.EkranGlowny");
            startActivity(Powrot);
    }
}
