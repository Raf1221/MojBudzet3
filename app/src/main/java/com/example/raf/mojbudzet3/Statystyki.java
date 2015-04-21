package com.example.raf.mojbudzet3;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
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


public class Statystyki extends ActionBarActivity implements AdapterView.OnItemSelectedListener {
    ZarzadcaBazy zb = new ZarzadcaBazy(this);
    //TextView ladujWydatki=(TextView) findViewById(R.id.WydatkiB);
    Spinner Operacjespin;
    Spinner KategorieStatykispin;
    Button button;
    Button button2;
    EditText Data;
    EditText DataB;
    int rok, miesiac, dzien;
    static final int DIALOG_ID = 0;
    static final int DIALOG_IDB = 0;

    int from_year, from_month, from_day,to_year, to_month, to_day;
    DatePickerDialog.OnDateSetListener from_dateListener,to_dateListener;

    private TextView startDateDisplay;
    private TextView endDateDisplay;
    private Button startPickDate;
    private Button endPickDate;
    private Calendar startDate;
    private Calendar endDate;
    static final int DATE_DIALOG_ID = 0;

    private TextView activeDateDisplay;
    private Calendar activeDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_statystyki);
        TextView tv_statystyki = (TextView) findViewById(R.id.StatystykiTB);
       // DajKalendarz();
       // DajKalendarzB();

        Operacjespin = (Spinner) findViewById(R.id.Operacje);
        ArrayAdapter adapter = ArrayAdapter.createFromResource(this, R.array.Operacje, android.R.layout.simple_spinner_item);
        Operacjespin.setAdapter(adapter);
        Operacjespin.setOnItemSelectedListener(this);

        KategorieStatykispin = (Spinner) findViewById(R.id.KategorieStatystyki);
        ArrayAdapter adapter2 = ArrayAdapter.createFromResource(this, R.array.KategorieStatystyki, android.R.layout.simple_spinner_item);
        KategorieStatykispin.setAdapter(adapter2);
        KategorieStatykispin.setOnItemSelectedListener(this);

       /* startDateDisplay = (TextView) findViewById(R.id.OdT);
        startPickDate = (Button) findViewById(R.id.OdB);

        startDate = Calendar.getInstance();//pobranie aktualnej daty*/

        /*startPickDate.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
               showDialog()


            }
        });*/


        /*try{
           Dodawanie kategorii;
            zb.dodajKategoria(16,"Zdrowie");
        }catch(Exception e){
            Toast.makeText(Statystyki.this,"błąd"+e,Toast.LENGTH_SHORT).show();
        }*/



    /*try{
        Cursor k=zb.dajWszystkieKategoria();

        while(k.moveToNext()){
            int id_k=k.getInt(0);
            String nazwa=k.getString(1);
            tv_statystyki.setText(tv_statystyki.getText()+"\n"+id_k+" "+nazwa);
        }
    }catch(Exception e){
        tv_statystyki.setText("Blad"+e);
    }
*/

        /*try {
            Cursor k = zb.dajWszystkieWydatek();
            Cursor k2 = zb.dajWszystkieKategoria();
*/

           /* for(int i=0;i<=3;i++){
                zb.kasujKategoria(i);
            }*/
            /*for(int i=0;i<=100;i++){
                zb.kasujPrzychod(i);
            }*/

            /*for (int i = 49; i <= 59; i++) {
                zb.kasujWydaek(i);
            }*/


           /*while(k2.moveToNext()){
                int id_k=k2.getInt(0);
                String nazwa=k2.getString(1);
                tv_statystyki.setText(tv_statystyki.getText()+"\n"+id_k+" "+nazwa);
            }*/


            /*while (k.moveToNext()) {
                //int id_k=k.getInt(0);
                String nazwa = k.getString(0);
                String data = k.getString(1);
                String kwota = k.getString(2);
                String opis = k.getString(3);

                tv_statystyki.setText(tv_statystyki.getText() + "\n" + " " + nazwa + ", " + data + ", " + "-" + kwota + "zł" + ", " + opis);
            }
        } catch (Exception e) {
            tv_statystyki.setText("Blad" + e);
        }*/

        /////////////////wyswietlanie dochodu///////////////

       /* try {
            Cursor k3 = zb.dajWszystkieDochod();

             for(int i=0;i<100;i++){
                zb.kasujPrzychod(i);
            }

            while (k3.moveToNext()) {
                int id_p = k3.getInt(0);
                String data = k3.getString(1);
                String kwota = k3.getString(2);
                String opis = k3.getString(3);

                tv_statystyki.setText(tv_statystyki.getText() + "\n" +" " + data + ", " + "+" + kwota + "zł" + ", " + opis);
            }
        } catch (Exception e) {
            tv_statystyki.setText("Blad " + e);
        }
*/

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_statystyki, menu);
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

    public void Powrot1(View view) {
        Intent Powrot = new Intent("com.example.raf.mojbudzet3.EkranGlowny");
        startActivity(Powrot);
    }

    /////////////////////////////////////////////////////
    public void ladujWydatki() {

        TextView tv_statystyki2 = (TextView) findViewById(R.id.StatystykiTB);
        tv_statystyki2.setText("");
        try {
            Cursor k = zb.dajWszystkieWydatek();
            while (k.moveToNext()) {
                int id_w = k.getInt(0);
                // String id_w=k.getString(0);
                String nazwa = k.getString(0);
                String data = k.getString(1);
                String kwota = k.getString(2);
                String opis = k.getString(3);
                //tv_statystyki2.setText(tv_statystyki2.getText()+"\n"+" "+id_w+", "+data+", "+"-"+kwota+"zł"+", "+opis);
                tv_statystyki2.setText(tv_statystyki2.getText() + "\n" + " " + nazwa + ", " + data + ", " + "-" + kwota + "zł" + ", " + opis);
            }

        } catch (Exception e) {
            Toast.makeText(Statystyki.this, "" + e, Toast.LENGTH_SHORT).show();
        }
    }
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public void ladujDochod() {

        TextView tv_statystyki2 = (TextView) findViewById(R.id.StatystykiTB);
        tv_statystyki2.setText("");
        try {
            Cursor k3 = zb.dajWszystkieDochod();
            while (k3.moveToNext()) {
                int id_p = k3.getInt(0);
                String data = k3.getString(1);
                String kwota = k3.getString(2);
                String opis = k3.getString(3);

                tv_statystyki2.setText(tv_statystyki2.getText() + "\n" +/*id_p+*/" " + data + ", " + "+" + kwota + "zł" + ", " + opis);
            }

        } catch (Exception e) {
            Toast.makeText(Statystyki.this, "" + e, Toast.LENGTH_SHORT).show();
        }
    }
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public void ladujWszystkie() {
        TextView tv_statystyki2 = (TextView) findViewById(R.id.StatystykiTB);
        tv_statystyki2.setText("");
        try {
            Cursor k3 = zb.dajWszystkieDochod();
            while (k3.moveToNext()) {
                int id_p = k3.getInt(0);
                String data = k3.getString(1);
                String kwota = k3.getString(2);
                String opis = k3.getString(3);
                tv_statystyki2.setText(tv_statystyki2.getText() + "\n" +/*id_p+*/" " + data + ", " + "+" + kwota + "zł" + ", " + opis);
            }

            Cursor k = zb.dajWszystkieWydatek();
            while (k.moveToNext()) {
                String nazwaa = k.getString(0);
                String dataa = k.getString(1);
                String kwotaa = k.getString(2);
                String opiss = k.getString(3);
                tv_statystyki2.setText(tv_statystyki2.getText() + "\n" + " " + nazwaa + ", " + dataa + ", " + "-" + kwotaa + "zł" + ", " + opiss);
            }
        } catch (Exception e) {
            Toast.makeText(Statystyki.this, "" + e, Toast.LENGTH_SHORT).show();
        }
    }
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    /*public void DajKalendarz() {
        button = (Button) findViewById(R.id.OdB);
        button.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View w) {
                        showDialog(DIALOG_ID);

                    }
                }
        );
    }

    @Override//przy otwieraniu okna dodaje sie kategoria RTV/AGD o id:1
    protected Dialog onCreateDialog(int id) {
        if (id == DIALOG_ID) {
            return new DatePickerDialog(this, dpickerListener, rok, miesiac, dzien);
        } else {
            return null;
        }
    }

    private DatePickerDialog.OnDateSetListener dpickerListener =
            new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                    rok = year;
                    miesiac = monthOfYear + 1;
                    dzien = dayOfMonth;
                    // Toast.makeText(DodajWydatek.this,rok+"-"+miesiac+"-"+dzien,Toast.LENGTH_LONG).show();
                    Data = (EditText) findViewById(R.id.OdT);
                    Data.setText(rok + "-" + miesiac + "-" + dzien);

                }
            };

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public void DajKalendarzB() {
        button2 = (Button) findViewById(R.id.DoB);
        button2.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View w) {
                        showDialog(DIALOG_IDB);

                    }
                }
        );
    }


    protected Dialog onCreateDialogB(int id) {
        if (id == DIALOG_IDB) {
            return new DatePickerDialog(this, dpickerListener2, rok, miesiac, dzien);
        } else
            return null;
    }

    private DatePickerDialog.OnDateSetListener dpickerListener2 =
            new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                    rok = year;
                    miesiac = monthOfYear + 1;
                    dzien = dayOfMonth;
                    // Toast.makeText(DodajWydatek.this,rok+"-"+miesiac+"-"+dzien,Toast.LENGTH_LONG).show();
                    DataB = (EditText) findViewById(R.id.DoT);
                    DataB.setText(rok + "-" + miesiac + "-" + dzien);

                }
            };*/
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        Spinner spinner = (Spinner) parent;
        if (spinner.getId() == R.id.Operacje) {
            Object item1 = Operacjespin.getSelectedItem();
            TextView myText = (TextView) view;
            //Toast.makeText(this,"Wybrano "+myText.getText(),Toast.LENGTH_SHORT).show();
            if (item1.equals("Wydatki")) {
                ladujWydatki();
            } else if (item1.equals("Przychody")) {
                ladujDochod();
            } else if (item1.equals("Wszystko")) {
                ladujWszystkie();
            }
        } else if (spinner.getId() == R.id.KategorieStatystyki) {
            Object item1 = Operacjespin.getSelectedItem();
            Object item2 = KategorieStatykispin.getSelectedItem();


            if(item2.equals("*")){
                if (item1.equals("Wydatki")) {
                    Cursor KK=zb.dajWszystkieWydatek();
                    szybkieWypisywanieDoSpinnera(KK);
                } else if (item1.equals("Przychody")) {
                    ladujDochod();

                } else if (item1.equals("Wszystko")) {
                    ladujWszystkie();
                }
            }else if (item2.equals("Alkohol")) {
                //Toast.makeText(this, "Wybrano " + item2.toString(), Toast.LENGTH_SHORT).show();
                if (item1.equals("Wydatki")) {
                    Cursor KK = zb.dajWszystkieWydatekKategoria(item2.toString());
                    szybkieWypisywanieDoSpinnera(KK);
                } else if (item1.equals("Przychody")) {
                    ladujDochod();

                } else if (item1.equals("Wszystko")) {
                    ladujWszystkie();
                }
            } else if (item2.equals("Dom")) {
                //Toast.makeText(this, "Wybrano " + item2, Toast.LENGTH_SHORT).show();
                if (item1.equals("Wydatki")) {
                    Cursor KK = zb.dajWszystkieWydatekKategoria(item2.toString());
                    szybkieWypisywanieDoSpinnera(KK);
                } else if (item1.equals("Przychody")) {
                    ladujDochod();

                } else if (item1.equals("Wszystko")) {
                    ladujWszystkie();
                }
            } else if (item2.equals("Dziecko")) {
                //Toast.makeText(this, "Wybrano " + item2, Toast.LENGTH_SHORT).show();
                if (item1.equals("Wydatki")) {
                    Cursor KK = zb.dajWszystkieWydatekKategoria(item2.toString());
                    szybkieWypisywanieDoSpinnera(KK);
                } else if (item1.equals("Przychody")) {
                    ladujDochod();

                } else if (item1.equals("Wszystko")) {
                    ladujWszystkie();
                }
            } else if (item2.equals("Elektronika")) {
                // Toast.makeText(this, "Wybrano " + item2, Toast.LENGTH_SHORT).show();
                if (item1.equals("Wydatki")) {
                    Cursor KK = zb.dajWszystkieWydatekKategoria(item2.toString());
                    szybkieWypisywanieDoSpinnera(KK);
                } else if (item1.equals("Przychody")) {
                    ladujDochod();

                } else if (item1.equals("Wszystko")) {
                    ladujWszystkie();
                }
            } else if (item2.equals("Firma")) {
                //Toast.makeText(this, "Wybrano " + item2, Toast.LENGTH_SHORT).show();
                if (item1.equals("Wydatki")) {
                    Cursor KK = zb.dajWszystkieWydatekKategoria(item2.toString());
                    szybkieWypisywanieDoSpinnera(KK);
                } else if (item1.equals("Przychody")) {
                    ladujDochod();

                } else if (item1.equals("Wszystko")) {
                    ladujWszystkie();
                }
            } else if (item2.equals("Jedzenie")) {
                // Toast.makeText(this, "Wybrano " + item2, Toast.LENGTH_SHORT).show();
                if (item1.equals("Wydatki")) {
                    Cursor KK = zb.dajWszystkieWydatekKategoria(item2.toString());
                    szybkieWypisywanieDoSpinnera(KK);
                } else if (item1.equals("Przychody")) {
                    ladujDochod();

                } else if (item1.equals("Wszystko")) {
                    ladujWszystkie();
                }
            } else if (item2.equals("Kultura")) {
                // Toast.makeText(this, "Wybrano " + item2, Toast.LENGTH_SHORT).show();
                if (item1.equals("Wydatki")) {
                    Cursor KK = zb.dajWszystkieWydatekKategoria(item2.toString());
                    szybkieWypisywanieDoSpinnera(KK);
                } else if (item1.equals("Przychody")) {
                    ladujDochod();

                } else if (item1.equals("Wszystko")) {
                    ladujWszystkie();
                }
            } else if (item2.equals("Moda")) {
                // Toast.makeText(this, "Wybrano " + item2, Toast.LENGTH_SHORT).show();
                if (item1.equals("Wydatki")) {
                    Cursor KK = zb.dajWszystkieWydatekKategoria(item2.toString());
                    szybkieWypisywanieDoSpinnera(KK);
                } else if (item1.equals("Przychody")) {
                    ladujDochod();

                } else if (item1.equals("Wszystko")) {
                    ladujWszystkie();
                }
            } else if (item2.equals("Motoryzacja")) {
                // Toast.makeText(this, "Wybrano " + item2, Toast.LENGTH_SHORT).show();
                if (item1.equals("Wydatki")) {
                    Cursor KK = zb.dajWszystkieWydatekKategoria(item2.toString());
                    szybkieWypisywanieDoSpinnera(KK);
                } else if (item1.equals("Przychody")) {
                    ladujDochod();

                } else if (item1.equals("Wszystko")) {
                    ladujWszystkie();
                }
            } else if (item2.equals("Rozrywka")) {
                // Toast.makeText(this, "Wybrano " + item2, Toast.LENGTH_SHORT).show();
                if (item1.equals("Wydatki")) {
                    Cursor KK = zb.dajWszystkieWydatekKategoria(item2.toString());
                    szybkieWypisywanieDoSpinnera(KK);
                } else if (item1.equals("Przychody")) {
                    ladujDochod();

                } else if (item1.equals("Wszystko")) {
                    ladujWszystkie();
                }
            } else if (item2.equals("Sport")) {
                // Toast.makeText(this, "Wybrano " + item2, Toast.LENGTH_SHORT).show();
                if (item1.equals("Wydatki")) {
                    Cursor KK = zb.dajWszystkieWydatekKategoria(item2.toString());
                    szybkieWypisywanieDoSpinnera(KK);
                } else if (item1.equals("Przychody")) {
                    ladujDochod();

                } else if (item1.equals("Wszystko")) {
                    ladujWszystkie();
                }
            } else if (item2.equals("Sztuka")) {
                // Toast.makeText(this, "Wybrano " + item2, Toast.LENGTH_SHORT).show();
                if (item1.equals("Wydatki")) {
                    Cursor KK = zb.dajWszystkieWydatekKategoria(item2.toString());
                    szybkieWypisywanieDoSpinnera(KK);
                } else if (item1.equals("Przychody")) {
                    ladujDochod();

                } else if (item1.equals("Wszystko")) {
                    ladujWszystkie();
                }
            } else if (item2.equals("Uroda")) {
                // Toast.makeText(this, "Wybrano " + item2, Toast.LENGTH_SHORT).show();
                if (item1.equals("Wydatki")) {
                    Cursor KK = zb.dajWszystkieWydatekKategoria(item2.toString());
                    szybkieWypisywanieDoSpinnera(KK);
                } else if (item1.equals("Przychody")) {
                    ladujDochod();

                } else if (item1.equals("Wszystko")) {
                    ladujWszystkie();
                }
            } else if (item2.equals("Usługi")) {
                // Toast.makeText(this, "Wybrano " + item2, Toast.LENGTH_SHORT).show();
                if (item1.equals("Wydatki")) {
                    Cursor KK = zb.dajWszystkieWydatekKategoria(item2.toString());
                    szybkieWypisywanieDoSpinnera(KK);
                } else if (item1.equals("Przychody")) {
                    ladujDochod();

                } else if (item1.equals("Wszystko")) {
                    ladujWszystkie();
                }
            } else if (item2.equals("Wypoczynek")) {
                // Toast.makeText(this, "Wybrano " + item2, Toast.LENGTH_SHORT).show();
                if (item1.equals("Wydatki")) {
                    Cursor KK = zb.dajWszystkieWydatekKategoria(item2.toString());
                    szybkieWypisywanieDoSpinnera(KK);
                } else if (item1.equals("Przychody")) {
                    ladujDochod();

                } else if (item1.equals("Wszystko")) {
                    ladujWszystkie();
                }
            } else if (item2.equals("Zdrowie")) {
                // Toast.makeText(this, "Wybrano " + item2, Toast.LENGTH_SHORT).show();
                if (item1.equals("Wydatki")) {
                    Cursor KK = zb.dajWszystkieWydatekKategoria(item2.toString());
                    szybkieWypisywanieDoSpinnera(KK);
                } else if (item1.equals("Przychody")) {
                    ladujDochod();

                } else if (item1.equals("Wszystko")) {
                    ladujWszystkie();
                }
            }
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void szybkieWypisywanieDoSpinnera(Cursor k) {
        TextView tv_statystyki2 = (TextView) findViewById(R.id.StatystykiTB);
        tv_statystyki2.setText("");
        while (k.moveToNext()) {
            String nazwaa = k.getString(0);
            String dataa = k.getString(1);
            String kwotaa = k.getString(2);
            String opiss = k.getString(3);
            tv_statystyki2.setText(tv_statystyki2.getText() + "\n" + " " + nazwaa + ", " + dataa + ", " + "-" + kwotaa + "zł" + ", " + opiss);
        }
    }

    public void Laduj(View v){
        EditText et1=(EditText)findViewById(R.id.OdT);
        EditText et2=(EditText)findViewById(R.id.DoT);

        String s;
        String ss;

        s=et1.getText().toString();
        ss=et2.getText().toString();


        try{
            Cursor KK=zb.dajWszystkieWydatekData(s,ss);
            szybkieWypisywanieDoSpinnera(KK);
        }catch(Exception e){

        }



    }

}
