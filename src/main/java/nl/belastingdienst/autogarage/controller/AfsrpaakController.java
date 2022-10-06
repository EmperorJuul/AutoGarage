package nl.belastingdienst.autogarage.controller;

import nl.belastingdienst.autogarage.model.Afspraak;
import nl.belastingdienst.autogarage.repository.AfspraakRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/afspraak")
public class AfsrpaakController {

    @Autowired
    private AfspraakRepository afspraakRepository;

    @GetMapping
    public ResponseEntity<List<Afspraak>> alleAfspraken(){
        List<Afspraak> afspraakList = new ArrayList<>();
        for(Afspraak afspraak : afspraakRepository.findAll()){
            afspraakList.add(afspraak);
        }
        return ResponseEntity.ok(afspraakList);
    }
}
