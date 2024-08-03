package com.example.bank.atm.domain.model.common;

import java.io.Serializable;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

import jakarta.annotation.Nonnull;

@NoRepositoryBean
public interface BaseRepository<Aggregate extends BaseAggregateRoot<Aggregate, ID>, ID extends Serializable> extends JpaRepository<Aggregate, ID>, JpaSpecificationExecutor<Aggregate> {

    default @Nonnull Aggregate getById(@Nonnull ID id) {
        return findById(id).orElseThrow(() -> new EmptyResultDataAccessException(1));
    }
}
