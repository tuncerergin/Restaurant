package com.ergintuncer.restaurant.repository;

import com.ergintuncer.restaurant.entity.Siparis;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface SiparisRepository extends JpaRepository<Siparis, Integer> {

    @Transactional
    @Modifying
    @Query("DELETE FROM Siparis WHERE urun.id=:urunId and masa.id=:masaId and siparisAlmaTarihi=(" +
            "SELECT MAX(siparisAlmaTarihi) FROM Siparis WHERE  urun.id=:urunId and masa.id=:masaId)")
    void deleteLastSiparisByUrunIdAndMasaId(Integer urunId,Integer masaId);

   List<Siparis> findAllByMasaIdAndSiparisDurum_IdLessThanEqual(Integer masaId, int durum);
}

