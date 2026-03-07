package com.fitlife.repository;

import com.fitlife.entity.AiWorkoutPlan;
import com.fitlife.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface AiPlanRepository extends JpaRepository<AiWorkoutPlan, Long> {
    // Lấy danh sách lịch sử tập luyện của 1 hội viên, mới nhất xếp lên đầu
    List<AiWorkoutPlan> findByMemberOrderByCreatedAtDesc(Member member);
}