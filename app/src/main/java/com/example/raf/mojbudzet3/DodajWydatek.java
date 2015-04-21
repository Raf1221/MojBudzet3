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
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Date;
import java.util.Calendar;


public class DodajWydatek extends ActionBarActivity implements AdapterView.OnItemSelectedListener{
    ZarzadcaBazy zb=new ZarzadcaBazy(this);
    Spinner spinner;
    Button button;
    EditText Data;
    int rok,miesiac,dzien;
    static final int DIALOG_ID=0;
    //zmienne potrzebne do dodawania wydatku
    int KategriaID;
    String DataWydatku;
    float Cena;
    String Uwagi;
    EditText CenaET,DataET,UwagiET;


    @Override//przy otwieraniu okna dodaje sie kategoria RTV/AGD o id:1
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wydatki);
        spinner=(Spinner) findViewById(R.id.spinner_Kategorie);
        CenaET=(EditText)findViewById(R.id.CenaWydatek);
        DataET=(EditText)findViewById(R.id.DataWyswietl);
        UwagiET=(EditText)findViewById(R.id.UwagiWydatek);

        ArrayAdapter adapter=ArrayAdapter.createFromResource(this,R.array.kategorie,android.R.layout.simple_spinner_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
        final Calendar cal=Calendar.getInstance();
        rok=cal.get(Calendar.YEAR);
        miesiac=cal.get(Calendar.MONTH);
        dzien=cal.get(Calendar.DAY_OF_MONTH);

        DajKalendarz();

    }

    public void DajKalendarz(){
        button=(Button)findViewById(R.id.Kalendarz_B);
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
                    Data=(EditText)findViewById(R.id.DataWyswietl);
                    Data.setText(rok+"-"+miesiac+"-"+dzien);

                }
            };


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_wydatki, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    public void Powrot(View view) {
        Intent Powrot = new Intent("com.example.raf.mojbudzet3.EkranGlowny");
        startActivity(Powrot);
    }

    public void DodajWydatki(View view) {
        //spinner.getSelectedItem().toString();
        switch (spinner.getSelectedItem().toString()){
            case "Alkohol":
                KategriaID=1;
                break;
            //Toast.makeText(DodajWydatek.this,"Wybrano alkohol",Toast.LENGTH_LONG).show();
            case "Dom":
                KategriaID=2; break;
            case "Dziecko":
                KategriaID=3; break;
            case "Elektronika":
                KategriaID=4; break;
            case "Firma":
                KategriaID=5; break;
            case "Jedzenie":
                KategriaID=6; break;
            case "Kultura":
                KategriaID=7; break;
            case "Moda":
                KategriaID=8; break;
            case "Motoryzacja":
                KategriaID=9; break;
            case "Rozrywka":
                KategriaID=10; break;
            case "Sport":
                KategriaID=11; break;
            case "Sztuka":
                KategriaID=12; break;
            case "Uroda":
                KategriaID=13; break;
            case "Usługi":
                KategriaID=14; break;
            case "Wypoczynek":
                KategriaID=15; break;
            case "Zdrowie":
                KategriaID=16; break;
            default:
                Toast.makeText(DodajWydatek.this,"Kategoria nie została wybrana",Toast.LENGTH_LONG).show(); break;
        }
        Cena=Float.parseFloat(CenaET.getText().toString());
        DataWydatku=DataET.getText().toString();

        Uwagi=UwagiET.getText().toString();
        try{
            //zb.dodajWydatek(1, Date.valueOf("2008-9-2"),5000,"pensja xd");
            zb.dodajWydatek(KategriaID, Date.valueOf(DataWydatku),Cena,Uwagi);
            Toast.makeText(DodajWydatek.this,"Udalo sie dodac wydatek",Toast.LENGTH_LONG).show();
        }catch(Exception e){
            Toast.makeText(DodajWydatek.this,""+e,Toast.LENGTH_LONG).show();
        }

       // EditText ed_cena = (EditText) findViewById(R.id.CenaTB);
       // EditText ed_uwagi = (EditText) findViewById(R.id.UwagiTB);

        //DodajWydatki DW=new DodajWydatek(1,2006-12-23,23.26,"Baton")
        //zb.dodajWydatki(DW);
        /*
        public void dodajWydatki(int kategoria_id, Date data, float kwota, String opis){
        SQLiteDatabase db=getWritableDatabase();
        ContentValues wartosci=new ContentValues();

        wartosci.put("kategoria_id",kategoria_id);
        wartosci.put("data",dateFormat.format(data));
        wartosci.put("kwota",kwota);
        wartosci.put("opis",opis);

        db.insertOrThrow("Wydatki",null,wartosci);
    }
         */
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        TextView myText=(TextView) view;
        Toast.makeText(this,"Wybrano"+myText.getText(),Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

}
