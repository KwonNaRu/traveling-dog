package com.travelingdog.backend.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TravelLocation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String placeName; // 장소 이름

    @Column(nullable = false)
    private double latitude; // 위도

    @Column(nullable = false)
    private double longitude; // 경도

    @Column(length = 500)
    private String description; // 장소 설명

    @Column(name = "location_order", nullable = false)
    @PositiveOrZero(message = "Order must be positive number")
    private int locationOrder; // 여행 계획 내 순서

    @ManyToOne
    @JoinColumn(name = "travel_plan_id", nullable = false)
    private TravelPlan travelPlan; // 여행 계획과의 관계
}
