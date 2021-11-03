package com.tma.demo.repositories.jpa;

import com.tma.demo.entities.jpa.ProductJPA;
import com.tma.demo.entities.jpa.SaleJPA;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ISaleJPARepository extends JpaRepository<SaleJPA, UUID> {
}
