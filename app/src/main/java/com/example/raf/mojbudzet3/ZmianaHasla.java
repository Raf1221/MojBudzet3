package com.example.raf.mojbudzet3;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import com.example.raf.mojbudzet3.Login;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;



public class ZmianaHasla extends ActionBarActivity {
    ZarzadcaBazy zb=new ZarzadcaBazy(this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zmiana_hasla);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_zmiana_hasla, menu);
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

    public void ZmienHaslo(View view) {
        String stare, noweHaslo,NowePowtorzone;

        EditText stareed = (EditText) findViewById(R.id.StarePW);
        stare=stareed.getText().toString();
        EditText noweed = (EditText) findViewById(R.id.NowePW);
        noweHaslo=noweed.getText().toString();
        EditText powted = (EditText) findViewById(R.id.PowtorzPW);
        NowePowtorzone=powted.getText().toString();

        Cursor k=zb.dajWszystkie();
        k.moveToFirst();
        String hasloUzytkownika = k.getString(1);

        if((stare.equals(hasloUzytkownika))==true){
            if(noweHaslo.equals(NowePowtorzone)){
                zb.aktualizujUzytownika(1,NowePowtorzone);
                Toast.makeText(ZmianaHasla.this,"Hasło zostało zmienione poprawnie",Toast.LENGTH_SHORT).show();
            }
        }




    }

}
