package com.ergintuncer.restaurant.entity;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "siparis", schema = "public", catalog = "restaurant")
@Data
public class Siparis implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;

    @CreationTimestamp
    @Column(name = "siparis_alma_tarihi", nullable = false)
    private Timestamp siparisAlmaTarihi;

    @Column(name = "garson_id", nullable = true)
    private Integer garsonId;

    @Column(name = "urun_fiyati", nullable = true)
    private float fiyat;

    @ManyToOne
    @JoinColumn(name = "urun_id", referencedColumnName = "id", nullable = false)
    private MenuIcerik urun;

    @ManyToOne
    @JoinColumn(name = "siparis_durum", referencedColumnName = "id", nullable = false)
    private SiparisDurum siparisDurum;

    @ManyToOne
    @JoinColumn(name = "masa_id", referencedColumnName = "id", nullable = false)
    private Masa masa;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Siparis siparis = (Siparis) o;
        return getGarsonId().equals(siparis.getGarsonId()) &&
                getUrun().equals(siparis.getUrun()) &&
                getSiparisDurum().equals(siparis.getSiparisDurum()) &&
                getMasa().equals(siparis.getMasa());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getGarsonId(), getUrun(), getSiparisDurum(), getMasa());
    }

    @Override
    public String toString() {
        return "Siparis{" +
                "id=" + id +
                ", siparisAlmaTarihi=" + siparisAlmaTarihi +
                ", garsonId=" + garsonId +
                ", fiyat=" + fiyat +
                '}';
    }
}
