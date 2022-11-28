package com.mpoffo.baitapatota.controller;

import com.mpoffo.baitapatota.dto.MessageResponseDTO;
import com.mpoffo.baitapatota.entity.Patota;
import com.mpoffo.baitapatota.entity.Patoteiro;
import com.mpoffo.baitapatota.service.PatotaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api/v1/patota")
public class PatotaController {

    @Autowired
    private PatotaService patotaService;

    @PostMapping
    public MessageResponseDTO create(@RequestBody Patota patota) {
        return patotaService.create(patota);
    }

    @RequestMapping("/{patotaId}")
    public Set<Patoteiro> getInscritos(@PathVariable(value="patotaId") Long patotaId) {
        return patotaService.getInscritos(patotaId);
    }
}
