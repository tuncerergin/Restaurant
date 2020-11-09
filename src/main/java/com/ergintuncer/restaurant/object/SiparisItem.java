package com.ergintuncer.restaurant.object;

import lombok.Data;

import java.util.Objects;

@Data
public class SiparisItem {
    private int urunId;
    private String header;
    private String imageUrl;
    private float fiyat;
    private int count = 1;
    public SiparisItem( int urunId, String header, String imageUrl, float fiyat) {

        this.urunId = urunId;
        this.header = header;
        this.imageUrl = imageUrl;
        this.fiyat = fiyat;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SiparisItem that = (SiparisItem) o;
        return getUrunId() == that.getUrunId() &&
                Float.compare(that.getFiyat(), getFiyat()) == 0 &&
                getHeader().equals(that.getHeader()) &&
                Objects.equals(getImageUrl(), that.getImageUrl());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUrunId(), getHeader(), getImageUrl(), getFiyat());
    }

}
