package com.example.mhada;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MhadaDtoRepository extends JpaRepository<MhadaDto, Long> {}
