package com.proyectohabitos.backend.controller;

import com.proyectohabitos.backend.dto.CrearMemberRequest;
import com.proyectohabitos.backend.model.Member;
import com.proyectohabitos.backend.service.MemberService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/members")
@CrossOrigin(origins = "http://localhost:5173")
public class MemberController {

    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping
    public Member crear(@RequestBody @Valid CrearMemberRequest request) {
        return memberService.crearMember(request);
    }

    @GetMapping("/by-family/{familyId}")
    public List<Member> listarPorFamilia(@PathVariable Long familyId) {
        return memberService.listarPorFamilia(familyId);
    }
}
