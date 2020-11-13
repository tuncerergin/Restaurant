package com.ergintuncer.restaurant.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "menu_icerik", schema = "public", catalog = "restaurant")
@Data
public class MenuIcerik  implements Serializable {
    @Id
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "icerik_baslik", nullable = false, length = 50)
    private String icerikBaslik;

    @Column(name = "fiyat", nullable = true)
    private float fiyat;

    @Column(name = "image_path", nullable = true)
    private String imagePath;

    @ManyToOne
    @JoinColumn(name = "icerik_tur", referencedColumnName = "id", nullable = false)
    private MenuKategori menuKategoriByIcerikTur;

    @OneToMany(mappedBy = "urun")
    private List<Siparis> siparis;

    @OneToMany(mappedBy = "menuIcerik")
    private List<Icindekiler> icindekiler;

    @Override
    public String toString() {
        return "MenuIcerik{" +
                "id=" + id +
                ", icerikBaslik='" + icerikBaslik + '\'' +
                ", fiyat=" + fiyat +
                ", imagePath='" + imagePath + '\'' +
                '}';
    }


}
