package com.example.yoelfebryan.feedme.Model;

/**
 * Created by Yoel Febryan on 29/03/2018.
 */

public class Item {
    private int id;
    private String name;
    private String harga;
    private String desc;
    private byte[] image;


    public Item(int id, String name, String harga, String desc, byte[] image) {
        this.id = id;
        this.name = name;
        this.harga = harga;
        this.desc = desc;
        this.image = image;
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

    public String getHarga() {
        return harga;
    }

    public void setHarga(String harga) {
        this.harga = harga;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }
}
