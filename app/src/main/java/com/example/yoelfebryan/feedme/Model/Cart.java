package com.example.yoelfebryan.feedme.Model;

/**
 * Created by Yoel Febryan on 23/04/2018.
 */

public class Cart {
    private int id;
    private String name;
    private String harga;
    private String desc;
    private String qty;
    private String total;
    private String keterangan;

    public Cart(int id, String name, String harga, String desc, String qty, String total, String keterangan) {
        this.id = id;
        this.name = name;
        this.harga = harga;
        this.desc = desc;
        this.qty = qty;
        this.total = total;
        this.keterangan = keterangan;
    }

    public int getId() {return id;}

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHarga() {
        return harga;
    }

    public void setHarga(String harga) {
        this.harga = harga;
    }

    public String getDesc() {return desc;}

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public  String getQty(){return qty; }

    public void setQty(String qty) {
        this.qty = qty;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }
}
