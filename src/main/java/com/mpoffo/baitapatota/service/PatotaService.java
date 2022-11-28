package com.mpoffo.baitapatota.service;

import com.mpoffo.baitapatota.dto.MessageResponseDTO;
import com.mpoffo.baitapatota.entity.Patota;
import com.mpoffo.baitapatota.entity.Patoteiro;
import com.mpoffo.baitapatota.repository.PatotaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class PatotaService {

    @Autowired
    private PatotaRepository patotaRepository;

    private  PatotaService(){}

    public MessageResponseDTO create (Patota patota) {
        Patota savedPatota = patotaRepository.save(patota);
        return MessageResponseDTO.builder()
                .message("Patota criada com ID "+savedPatota.getId())
                .build();
    }

    public Set<Patoteiro> getInscritos(Long patotaId) {
        Patota patota = patotaRepository.getReferenceById(patotaId);
        return patota.getInscritos();
    }
}
