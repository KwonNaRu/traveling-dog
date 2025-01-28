package com.travelingdog.backend.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.travelingdog.backend.auditing.BaseTimeEntity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, unique = true)
    @Email(regexp = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$") // 이메일 형식 검증
    private String email;

    @Column(name = "preferred_travel_style")
    private String preferredTravelStyle; // 예: "Adventure", "Relaxation", "Cultural"

    @ElementCollection
    @CollectionTable(name = "favorite_destinations", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "destination")
    @Builder.Default
    private List<String> favoriteDestinations = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<TravelPlan> travelPlans = new ArrayList<>();

    public void addTravelPlan(TravelPlan travelPlan) {
        this.travelPlans.add(travelPlan);
        travelPlan.setUser(this);
    }

    public void removeTravelPlan(TravelPlan travelPlan) {
        this.travelPlans.remove(travelPlan);
        travelPlan.setUser(null);
    }

}
