package com.proyectohabitos.backend.service;

import com.proyectohabitos.backend.dto.CrearHabitRecordRequest;
import com.proyectohabitos.backend.exception.BadRequestException;
import com.proyectohabitos.backend.model.Habit;
import com.proyectohabitos.backend.model.HabitRecord;
import com.proyectohabitos.backend.model.Member;
import com.proyectohabitos.backend.repository.HabitRecordRepository;
import com.proyectohabitos.backend.repository.HabitRepository;
import com.proyectohabitos.backend.repository.MemberRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HabitRecordService {

    private final HabitRecordRepository recordRepo;
    private final MemberRepository memberRepo;
    private final HabitRepository habitRepo;

    public HabitRecordService(HabitRecordRepository recordRepo, MemberRepository memberRepo, HabitRepository habitRepo) {
        this.recordRepo = recordRepo;
        this.memberRepo = memberRepo;
        this.habitRepo = habitRepo;
    }

    public HabitRecord crear(CrearHabitRecordRequest req) {

        Member member = memberRepo.findById(req.getMemberId())
                .orElseThrow(() -> new BadRequestException("Miembro no existe"));

        Habit habit = habitRepo.findById(req.getHabitId())
                .orElseThrow(() -> new BadRequestException("Hábito no existe"));

        HabitRecord r = new HabitRecord();
        r.setMember(member);
        r.setHabit(habit);
        r.setFecha(req.getFecha());
        r.setCantidadRealizada(req.getCantidadRealizada());

        double porcentaje = (req.getCantidadRealizada() / habit.getMetaDiaria()) * 100;
        r.setPorcentajeCumplimiento(porcentaje);

        return recordRepo.save(r);
    }

    public List<HabitRecord> listarPorFamilia(Long familyId) {
        return recordRepo.findByMemberFamilyId(familyId);
    }
}
