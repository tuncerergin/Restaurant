package com.ergintuncer.restaurant.controller;

import com.ergintuncer.restaurant.entity.Masa;
import com.ergintuncer.restaurant.repository.MasaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class MasaController {
    @Autowired
    MasaRepository masaRepository;

    @GetMapping("/masalar")
    public String getMasaList(Model model) {
        List<Masa> masa;
        masa = masaRepository.findAllByOrderByMasaNoAsc();
        masa.forEach(m -> m.setToplamFiyat(0));
        model.addAttribute("masa", masa);
        return "masa";
    }

    @GetMapping("/updatemasafregment")
    public String updateMasaList(Model model) {
        List<Masa> masa;
        masa = masaRepository.findAllByOrderByMasaNoAsc();
        masa.forEach(m -> m.setToplamFiyat(0));
        model.addAttribute("masa", masa);
        return "masa :: masalar";
    }

}
