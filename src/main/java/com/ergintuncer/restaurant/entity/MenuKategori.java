package com.ergintuncer.restaurant.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "menu_kategori", schema = "public", catalog = "restaurant")
@Data
public class MenuKategori  implements Serializable {
    @Id
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "kategori_adi", nullable = true, length = 255)
    private String kategoriAdi;

    @OneToMany(mappedBy = "menuKategoriByIcerikTur")
    private List<MenuIcerik> menuIcerik;

    @ManyToOne
    @JoinColumn(name = "tur_id", referencedColumnName = "id", nullable = false)
    private MenuTur menuTurByTurId;

    @Override
    public String toString() {
        return "MenuKategori{" +
                "id=" + id +
                ", kategoriAdi='" + kategoriAdi + '\'' +
                '}';
    }
}
