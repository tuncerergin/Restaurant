package com.ergintuncer.restaurant.controller;

import com.ergintuncer.restaurant.entity.Masa;
import com.ergintuncer.restaurant.repository.MasaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class MasaController {
    @Autowired
    MasaRepository masaRepository;

    private final int HESAP_ODENDI = 6;

    @GetMapping("/masalar")
    public String getMasalar(Model model) {
        getMasaList(model);
        return "masa";
    }

    @GetMapping("/updatemasafregment")
    public String updateMasaList(Model model) {
        getMasaList(model);
        return "masa :: masalar";
    }

    private void getMasaList(Model model) {
        List<Masa> masa = masaRepository.findAllByOrderByMasaNoAsc();
        List<Object[]> result = masaRepository.findTotalPriceByMasa(HESAP_ODENDI);
        if (result != null && !result.isEmpty()) {
            for (Object[] object : result) {
                masa = masa.parallelStream().peek(m -> {
                    if (m.getId() == (Integer) object[0]) {
                        m.setToplamFiyat((Double) object[1]);
                    }
                }).collect(Collectors.toList());
            }
        }
        model.addAttribute("masa", masa);
    }

}
