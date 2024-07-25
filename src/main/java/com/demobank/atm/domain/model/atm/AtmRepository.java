package com.demobank.atm.domain.model.atm;

import org.jmolecules.ddd.annotation.Repository;

import com.demobank.atm.domain.model.common.BaseRepository;

@Repository
public interface AtmRepository extends BaseRepository<Atm, AtmId>{
    
}