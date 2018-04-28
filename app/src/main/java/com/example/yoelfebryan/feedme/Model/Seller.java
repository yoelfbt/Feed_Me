package com.example.yoelfebryan.feedme.Model;

/**
 * Created by Yoel Febryan on 16/04/2018.
 */

public class Seller {
    private int id;
    private String name;
    private String nohp;
    private String alamat;

    public Seller(int id, String name, String nohp, String alamat) {
        this.id = id;
        this.name = name;
        this.nohp = nohp;
        this.alamat = alamat;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNohp() {
        return nohp;
    }

    public void setNohp(String nohp) {
        this.nohp = nohp;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }
}
