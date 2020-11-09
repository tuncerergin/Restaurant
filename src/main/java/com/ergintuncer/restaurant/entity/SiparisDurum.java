package com.ergintuncer.restaurant.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "siparis_durum", schema = "public", catalog = "restaurant")
public class SiparisDurum  implements Serializable {
    @Id
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "siparis_durum", nullable = true, length = 255)
    private String siparisDurum;

    @OneToMany(mappedBy = "siparisDurum")
    private List<Siparis> siparis;

    @Override
    public String toString() {
        return "SiparisDurum{" +
                "id=" + id +
                ", siparisDurum='" + siparisDurum + '\'' +
                '}';
    }
}
