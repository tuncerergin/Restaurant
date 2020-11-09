package com.ergintuncer.restaurant.repository;

import com.ergintuncer.restaurant.entity.Masa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MasaRepository extends JpaRepository<Masa, Integer> {
    List<Masa> findAllByOrderByMasaNoAsc();
}
