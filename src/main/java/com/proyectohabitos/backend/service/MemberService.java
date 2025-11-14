package com.proyectohabitos.backend.service;

import com.proyectohabitos.backend.dto.CrearMemberRequest;
import com.proyectohabitos.backend.exception.BadRequestException;
import com.proyectohabitos.backend.model.Family;
import com.proyectohabitos.backend.model.Member;
import com.proyectohabitos.backend.repository.FamilyRepository;
import com.proyectohabitos.backend.repository.MemberRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberService {

    private final MemberRepository memberRepository;
    private final FamilyRepository familyRepository;

    public MemberService(MemberRepository memberRepository, FamilyRepository familyRepository) {
        this.memberRepository = memberRepository;
        this.familyRepository = familyRepository;
    }

    public Member crearMember(CrearMemberRequest request) {

        // 1) Validación formato simple
        if (!request.getCedula().matches("\\d{10}")) {
            throw new BadRequestException("La cédula debe tener exactamente 10 dígitos.");
        }

        // 2) No repetida
        if (memberRepository.existsByCedula(request.getCedula())) {
            throw new BadRequestException("La cédula ya está registrada en el sistema.");
        }

        // 3) (Podrías poner aquí la lógica real de cédula ecuatoriana)
        // if (!cedulaValidaEcuador(request.getCedula())) ...

        Family family = familyRepository.findById(request.getFamilyId())
                .orElseThrow(() -> new BadRequestException("La familia seleccionada no existe."));

        Member member = new Member();
        member.setNombres(request.getNombres());
        member.setCedula(request.getCedula());
        member.setFechaNacimiento(request.getFechaNacimiento());
        member.setFamily(family);

        return memberRepository.save(member);
    }

    public List<Member> listarPorFamilia(Long familyId) {
        return memberRepository.findByFamilyId(familyId);
    }
}
