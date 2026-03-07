package com.fitlife.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "ai_workout_plans")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AiWorkoutPlan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    private String goal;

    @Column(columnDefinition = "LONGTEXT") // Để chứa được cục JSON rất dài của AI
    private String planData;

    private LocalDateTime createdAt;
}