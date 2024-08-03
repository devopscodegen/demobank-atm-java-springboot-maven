package com.example.bank.atm.domain.model.atm;

import org.jmolecules.ddd.annotation.Repository;

import com.example.bank.atm.domain.model.common.BaseRepository;

@Repository
public interface AtmRepository extends BaseRepository<Atm, AtmId>{
    
}