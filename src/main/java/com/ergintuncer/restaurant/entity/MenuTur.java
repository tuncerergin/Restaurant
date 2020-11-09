package com.ergintuncer.restaurant.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;

@Entity
@Table(name = "menu_tur", schema = "public", catalog = "restaurant")
@Data
public class MenuTur  implements Serializable {
    @Id
    @Column(name = "id", nullable = false)
    private int id;
    @Column(name = "tur_adi", nullable = false, length = 25)
    private String turAdi;
    @OneToMany(mappedBy = "menuTurByTurId")
    private Collection<MenuKategori> menuKategoriById;

    @Override
    public String toString() {
        return "MenuTur{" +
                "id=" + id +
                ", turAdi='" + turAdi + '\'' +
                '}';
    }
}
