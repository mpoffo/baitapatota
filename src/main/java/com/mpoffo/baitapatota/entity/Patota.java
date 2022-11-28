package com.mpoffo.baitapatota.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Patota {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(nullable = false, unique = true)
    private String nome;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "inscritos",
            joinColumns = {@JoinColumn(name = "patota_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "patoteiro_id", referencedColumnName = "id")})
    private Set<Patoteiro> inscritos;
}
