package com.ergintuncer.restaurant.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Data
@Entity
public class Malzeme {
    @Id
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "malzeme_adi", nullable = false, length = 255)
    private String malzemeAdi;

    @OneToMany(mappedBy = "malzeme")
    private List<Icindekiler> icindekiler;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Malzeme malzeme = (Malzeme) o;

        if (id != malzeme.id) return false;
        return malzemeAdi != null ? malzemeAdi.equals(malzeme.malzemeAdi) : malzeme.malzemeAdi == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (malzemeAdi != null ? malzemeAdi.hashCode() : 0);
        return result;
    }


}
