package com.ergintuncer.restaurant.controller;

import com.ergintuncer.restaurant.entity.MenuIcerik;
import com.ergintuncer.restaurant.entity.MenuKategori;
import com.ergintuncer.restaurant.entity.MenuTur;
import com.ergintuncer.restaurant.repository.MenuIcerikRepository;
import com.ergintuncer.restaurant.repository.MenuKategoriRepository;
import com.ergintuncer.restaurant.repository.MenuTurRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class MenuController {

    final MenuIcerikRepository menuIcerikRepository;
    final MenuKategoriRepository menuKategoriRepository;
    final MenuTurRepository menuTurRepository;

    public MenuController(MenuIcerikRepository menuIcerikRepository, MenuKategoriRepository menuKategoriRepository, MenuTurRepository menuTurRepository) {
        this.menuIcerikRepository = menuIcerikRepository;
        this.menuKategoriRepository = menuKategoriRepository;
        this.menuTurRepository = menuTurRepository;
    }

    @RequestMapping(value = "/menu", method = RequestMethod.GET)
    public String personList(Model model) {

        List<MenuIcerik> menuIcerik = menuIcerikRepository.findAll();
        List<MenuKategori> menuKategori = menuKategoriRepository.findAll();
        List<MenuTur> menuTur = menuTurRepository.findAll();

        model.addAttribute("menuIcerik", menuIcerik);
        model.addAttribute("menuKategori", menuKategori);
        model.addAttribute("menuTur", menuTur);
        return "fragments/components :: menu";
    }




   /* @PostConstruct
    public void saveImage() throws Exception {
        for (int i = 1; i <22;i++){
            MenuIcerik m = menuIcerikRepository.findById(i).get();
            File file = new File("/image/kahvalti.jpg");
            m.setImagePath(file.getPath());
            menuIcerikRepository.save(m);
        }

    }*/
}
