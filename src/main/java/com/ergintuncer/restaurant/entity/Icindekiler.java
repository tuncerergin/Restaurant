package com.ergintuncer.restaurant.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Icindekiler {
    @Id
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "menu_icerik_id", nullable = false)
    private int menuIcerikId;

    @Column(name = "malzeme_id", nullable = false)
    private int malzemeId;

    @ManyToOne
    @JoinColumn(name = "menu_icerik_id", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    private MenuIcerik menuIcerik;

    @ManyToOne
    @JoinColumn(name = "malzeme_id", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    private Malzeme malzeme;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Icindekiler that = (Icindekiler) o;

        if (id != that.id) return false;
        if (menuIcerikId != that.menuIcerikId) return false;
        return malzemeId == that.malzemeId;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + menuIcerikId;
        result = 31 * result + malzemeId;
        return result;
    }


}
