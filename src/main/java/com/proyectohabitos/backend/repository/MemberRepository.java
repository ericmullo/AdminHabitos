package com.proyectohabitos.backend.repository;

import com.proyectohabitos.backend.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member, Long> {

    List<Member> findByFamilyId(Long familyId);

    boolean existsByCedula(String cedula);
}
