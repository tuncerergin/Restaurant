package com.ergintuncer.restaurant.controller;

import com.ergintuncer.restaurant.entity.Masa;
import com.ergintuncer.restaurant.entity.MenuIcerik;
import com.ergintuncer.restaurant.entity.Siparis;
import com.ergintuncer.restaurant.entity.SiparisDurum;
import com.ergintuncer.restaurant.object.SiparisItem;
import com.ergintuncer.restaurant.repository.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;


@Controller
public class SiparisController {

    final MenuIcerikRepository menuIcerikRepository;
    final MenuKategoriRepository menuKategoriRepository;
    final MenuTurRepository menuTurRepository;
    final SiparisRepository siparisRepository;
    final SiparisDurumRepository siparisDurumRepository;
    final MasaRepository masaRepository;
    private final short MASA_ACIK = 1;
    private final short MASA_KAPALI = 0;
    private final int SIPARIS_DURUM_HESAP_ISTENDI = 5;
    List<SiparisItem> siparisList;
    private float toplamTutar = 0;

    public SiparisController(MenuIcerikRepository menuIcerikRepository, MenuKategoriRepository menuKategoriRepository, MenuTurRepository menuTurRepository, SiparisRepository siparisRepository, SiparisDurumRepository siparisDurumRepository, MasaRepository masaRepository) {
        this.menuIcerikRepository = menuIcerikRepository;
        this.menuKategoriRepository = menuKategoriRepository;
        this.menuTurRepository = menuTurRepository;
        this.siparisRepository = siparisRepository;
        this.siparisDurumRepository = siparisDurumRepository;

        this.masaRepository = masaRepository;
    }


    @RequestMapping(value = "/siparis", method = RequestMethod.GET)
    public String siparis(Model model, @RequestParam("masaId") String masaId) {
        toplamTutar = 0;
        siparisList = new ArrayList<>();
        model.addAttribute("masaId", masaId);
        return "masadetay";
    }

    @RequestMapping(value = "/siparisList", method = RequestMethod.GET)
    public String siparisListele(Model model, @RequestParam("masaId") String masaId) {
        SiparisDurum siparisDurum = siparisDurumRepository.getOne(SIPARIS_DURUM_HESAP_ISTENDI);
        List<Siparis> siparisItems = siparisRepository.findAllByMasaIdAndSiparisDurumLessThanEqual(Integer.valueOf(masaId), siparisDurum);

        for (Siparis siparisItem : siparisItems) {
            SiparisItem item = new SiparisItem(
                    siparisItem.getUrun().getId(),
                    siparisItem.getUrun().getIcerikBaslik(),
                    siparisItem.getUrun().getImagePath(),
                    siparisItem.getFiyat()
            );
            if (siparisList.parallelStream().anyMatch(o -> o.getUrunId() == item.getUrunId())) {
                siparisList.get(siparisList.indexOf(item)).setCount(siparisList.get(siparisList.indexOf(item)).getCount() + 1);
            } else {
                siparisList.add(0, item);
            }
            toplamTutar += item.getFiyat();
        }
        System.out.println(siparisList);
        model.addAttribute("siparisList", siparisList);
        model.addAttribute("toplamTutar", toplamTutar);
        return "fragments/components :: siparis";
    }

    @RequestMapping(value = "/siparisEkle", method = RequestMethod.GET)
    public String siparisEkle(@RequestParam("itemId") String itemId, @RequestParam("siparisDurum") String siparisDurum, @RequestParam("masaId") String masaId, Model model) {

        MenuIcerik menuIcerik = menuIcerikRepository.findById(Integer.valueOf(itemId)).get();
        SiparisItem item = new SiparisItem(
                menuIcerik.getId(),
                menuIcerik.getIcerikBaslik(),
                menuIcerik.getImagePath(),
                menuIcerik.getFiyat()
        );

        if (siparisDurum.equals("ekle")) {
            Siparis siparis = new Siparis();
            siparis.setUrun(menuIcerik);
            siparis.setSiparisDurum(siparisDurumRepository.findById(1).get());
            // ToDo Sipariş alan garson bilgisi eklenecek
            siparis.setGarsonId(1);
            siparis.setMasa(masaRepository.findById(Integer.valueOf(masaId)).get());
            siparis.setFiyat(menuIcerik.getFiyat());
            siparisRepository.save(siparis);


            /*
             * Liste boş ise ekle
             * Liste dolu ise aynı siparişten varmı diye bak
             * var ise sayısını arttır
             * yok ise listeye ekle
             * */

            if (siparisList.isEmpty()) {
                siparisList.add(0, item);
                //Masayı aç
                Masa masa = masaRepository.getOne(Integer.valueOf(masaId));
                masa.setMasaDurum(MASA_ACIK);
                masaRepository.save(masa);
            } else {
                if (siparisList.contains(item)) {
                    siparisList.get(siparisList.indexOf(item)).setCount(siparisList.get(siparisList.indexOf(item)).getCount() + 1);
                } else {
                    siparisList.add(0, item);
                }
            }
            toplamTutar += item.getFiyat();
        } else {
            if (siparisList.get(siparisList.indexOf(item)).getCount() > 1) {
                siparisList.get(siparisList.indexOf(item)).setCount(siparisList.get(siparisList.indexOf(item)).getCount() - 1);
            } else {
                siparisList.remove(item);
                if (siparisList.size() == 0) {
                    //Masayı Kapat
                    Masa masa = masaRepository.getOne(Integer.valueOf(masaId));
                    masa.setMasaDurum(MASA_KAPALI);
                    masaRepository.save(masa);
                }
            }
            siparisRepository.deleteLastSiparisByUrunIdAndMasaId(Integer.valueOf(itemId), Integer.valueOf(masaId));
            toplamTutar -= item.getFiyat();
        }
        model.addAttribute("siparisList", siparisList);
        model.addAttribute("toplamTutar", toplamTutar);
        return "fragments/components :: siparis";
    }


}
