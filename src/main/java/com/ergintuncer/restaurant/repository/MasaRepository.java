package com.ergintuncer.restaurant.repository;

import com.ergintuncer.restaurant.entity.Masa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MasaRepository extends JpaRepository<Masa, Integer> {
    List<Masa> findAllByOrderByMasaNoAsc();

    @Query("SELECT masa.id, SUM(urun.fiyat) FROM Siparis WHERE siparisDurum.id < ?1 GROUP BY masa.id")
    List<Object[]> findTotalPriceByMasa(Integer siparisDurum);

}
