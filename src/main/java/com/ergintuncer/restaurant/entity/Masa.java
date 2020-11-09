package com.ergintuncer.restaurant.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "masa", schema = "public")
@Data
public class Masa implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;
    @Column(name = "masa_no", nullable = false)
    private int masaNo;
    @Column(name = "masa_durum", nullable = false)
    private short masaDurum;
    @Column(name = "masa_kapasite", nullable = false)
    private short masaKapasite;
    @Transient
    private float toplamFiyat;
    @OneToMany(mappedBy = "masa")
    private List<Siparis> siparis;

    @Override
    public String toString() {
        return "Masa{" +
                "id=" + id +
                ", masaNo=" + masaNo +
                ", masaDurum=" + masaDurum +
                ", masaKapasite=" + masaKapasite +
                ", toplamFiyat=" + toplamFiyat +
                '}';
    }
}
