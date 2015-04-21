package com.example.raf.mojbudzet3;

import java.util.Date;

/**
 * Created by Raf on 2015-03-26.
 */
public class WydatkiDAO {
    private int id_w;
    private int kategoria_id;
    private Date data;
    private float kwota;
    private String opis;


    public void setId_w(int id_w) {
        this.id_w = id_w;
    }


    public void setKwota(float kwota) {
        this.kwota = kwota;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public void setKategoria_id(int kategoria_id) {
        this.kategoria_id = kategoria_id;
    }

    public int getId_w() {

        return id_w;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public String getOpis() {

        return opis;
    }

    public float getKwota() {
        return kwota;
    }

    public Date getData() {
        return data;
    }

    public int getKategoria_id() {
        return kategoria_id;
    }



}
