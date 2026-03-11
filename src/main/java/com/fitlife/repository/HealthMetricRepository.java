package com.fitlife.repository;

import com.fitlife.entity.HealthMetric;
import com.fitlife.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface HealthMetricRepository extends JpaRepository<HealthMetric, Long> {

    // Hàm này ĐÚNG -> Giữ lại
    Optional<HealthMetric> findFirstByMemberOrderByRecordedAtDesc(Member member);

    // Hàm này ĐÚNG -> Giữ lại
    List<HealthMetric> findByMemberIdOrderByRecordedAtDesc(Long memberId);

}