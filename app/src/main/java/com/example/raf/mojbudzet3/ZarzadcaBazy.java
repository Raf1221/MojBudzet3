package com.example.raf.mojbudzet3;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Raf on 2015-03-24.
 */
public class ZarzadcaBazy extends SQLiteOpenHelper {

    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    Date date = new Date(2015-11-29);
    public ZarzadcaBazy(Context context){
        super(context,"BudzetDB8.db",null,8);//najnwsza

    }
    @Override
    public void onCreate(SQLiteDatabase db){

    db.execSQL("create table Uzytkownik(" +
            "nr integer primary key," +
            "haslo text);");

    db.execSQL("create table Kategoria(" +
            "id_k integer primary key," +
            "nazwa text);");
    db.execSQL(
            "create table Wydatki(" +
                    "id_w integer primary key autoincrement," +
                    "kategoria_id integer REFERENCES Kategoria(id_k)," +
                    "data DATE," +
                    "kwota float," +
                    "opis text);");
    db.execSQL(
            "create table Dochody(" +
                    "id_d integer primary key autoincrement," +
                    "data DATE," +
                    "kwota float," +
                    "opis text);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db,int oldVersion,int newVersion){

    }
   //------------------UZYTKOWNIK----------------//
   public void dodajUser(int nr,String haslo){
        SQLiteDatabase db=getWritableDatabase();
        ContentValues wartosci=new ContentValues();
        wartosci.put("nr",nr);
        wartosci.put("haslo",haslo);

        db.insertOrThrow("Uzytkownik",null,wartosci);
    }

    public Cursor dajWszystkie(){
        String[] kolumny={"nr","haslo"};
        SQLiteDatabase db=getReadableDatabase();
        Cursor kursor=db.query("Uzytkownik",kolumny,null,null,null,null,null);

        return  kursor;
    }

    public void kasujUzytkownika(int id){
    SQLiteDatabase db=getWritableDatabase();
        String [] argumenty={""+id};
        db.delete("Uzytkownik","nr=?",argumenty);
    }
    public void aktualizujUzytownika(int nr,String noweHaslo){
    SQLiteDatabase db=getWritableDatabase();
        ContentValues wartosci=new ContentValues();
        wartosci.put("haslo",noweHaslo);
        String args[]={nr+""};
        db.update("Uzytkownik",wartosci,"nr=?",args);

    }
    //--------------KATEGORIA-------------//
    public void dodajKategoria(int id_k,String nazwa){
        SQLiteDatabase db=getWritableDatabase();
        ContentValues wartosci=new ContentValues();
        wartosci.put("id_k",id_k);
        wartosci.put("nazwa",nazwa);
        db.insertOrThrow("Kategoria",null,wartosci);
    }
    public Cursor dajWszystkieKategoria(){
        String[] kolumny={"id_k","nazwa"};
        SQLiteDatabase db=getReadableDatabase();
        Cursor kursor=db.query("Kategoria",kolumny,null,null,null,null,null);

        return  kursor;
    }
    public void kasujKategoria(int id){
        SQLiteDatabase db=getWritableDatabase();
        String [] argumenty={""+id};
        db.delete("Kategoria","id_k=?",argumenty);
    }
    /////////////////////////////////////////////
    //////--------------WYDATKI------------//////
    /////////////////////////////////////////////
    public  void dodajWydatek(int kategoria_id,Date data,float kwota,String opis){
        SQLiteDatabase db=getWritableDatabase();
        ContentValues wartosci=new ContentValues();
        wartosci.put("kategoria_id",kategoria_id);
        wartosci.put("data",dateFormat.format(data));
        wartosci.put("kwota",kwota);
        wartosci.put("opis",opis);

        db.insertOrThrow("Wydatki",null,wartosci);
    }

    public Cursor dajWszystkieWydatek(){
        String[] kolumny={"k.nazwa","w.data","w.kwota","w.opis"};
        SQLiteDatabase db=getReadableDatabase();
        Cursor kursor=db.rawQuery("select k.nazwa,w.data,w.kwota,w.opis from Wydatki w join Kategoria k on w.kategoria_id=k.id_k order by data;",null);
         /*String[] kolumny={"k.id_w","w.data","w.kwota","w.opis"};
        SQLiteDatabase db=getReadableDatabase();
        Cursor kursor=db.rawQuery("select w.id_w,w.data,w.kwota,w.opis from Wydatki w join Kategoria k on w.kategoria_id=k.id_k order by data;",null);*/
        //Cursor kursor=db.query("Wydatki w join Kategoria k",kolumny,"k.id_k=1",null,null,null,null);


        return  kursor;
    }

    public Cursor dajWszystkieWydatekKategoria(String cos){
        String[] kolumny={"k.nazwa","w.data","w.kwota","w.opis"};
        SQLiteDatabase db=getReadableDatabase();
        Cursor kursor=db.rawQuery("select k.nazwa,w.data,w.kwota,w.opis from Wydatki w join Kategoria k on w.kategoria_id=k.id_k where k.nazwa='"+cos+"' order by data;",null);
        return kursor;
    }

    public Cursor dajWszystkieWydatekData(String odd,String doo){
        String[] kolumny={"k.nazwa","w.data","w.kwota","w.opis"};
        SQLiteDatabase db=getReadableDatabase();
        Cursor kursor=db.rawQuery("select k.nazwa,w.data,w.kwota,w.opis from Wydatki w join Kategoria k on w.kategoria_id=k.id_k where w.data between'"+odd+"'and '"+doo+"' "+
                "order by data;",null);
        return kursor;
    }
   /* public Cursor dajWszystkieWszystkieKategoria(String cos){
        String[] kolumny={"k.nazwa","w.data","w.kwota","w.opis"};
        SQLiteDatabase db=getReadableDatabase();
        Cursor kursor=db.rawQuery("select k.nazwa,w.data,w.kwota,w.opis from Wydatki w join Kategoria k on w.kategoria_id=k.id_k where k.nazwa='"+cos+"' order by data;",null);
        return kursor;
    }*/
    public Cursor dajWszystkiePrzychodyKategoria(String cos){
        String[] kolumny={"k.nazwa","w.data","w.kwota","w.opis"};
        SQLiteDatabase db=getReadableDatabase();
        Cursor kursor=db.rawQuery("select k.nazwa,w.data,w.kwota,w.opis from Przychody w join Kategoria k on w.kategoria_id=k.id_k where k.nazwa='"+cos+"' order by data;",null);
        return kursor;
    }

    public void kasujWydaek(int id){
        SQLiteDatabase db=getWritableDatabase();
        String[] argumenty={""+id};
        db.delete("Wydatki","id_w=?",argumenty);

    }
    /////////////////////////////////////////////
    //////--------------DOCHODY------------//////
    /////////////////////////////////////////////

    public  void dodajDochod(Date data,float kwota,String opis){
        SQLiteDatabase db=getWritableDatabase();
        ContentValues wartosci=new ContentValues();
       // wartosci.put("id_d",id_d);
        wartosci.put("data",dateFormat.format(data));
        wartosci.put("kwota",kwota);
        wartosci.put("opis",opis);

        db.insertOrThrow("Dochody",null,wartosci);
    }

    public Cursor dajWszystkieDochod(){
        String[] kolumny={"id_p","data","kwota","opis"};
        SQLiteDatabase db=getReadableDatabase();
        Cursor kursor=db.rawQuery("select id_d,data,kwota,opis from Dochody order by data;",null);

        return  kursor;
    }

    public void kasujPrzychod(int id){
        SQLiteDatabase db=getWritableDatabase();
        String[] argumenty={""+id};
        db.delete("Dochody","id_d=?",argumenty);

    }




}
