package com.akshay.crud_maping_demo.onetomany.entity;

import com.akshay.crud_maping_demo.onetomany.entity.SCourse;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;
import java.util.UUID;

@Entity
@Table(name = "s_student")
@Data
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
public class SStudent {

    @Id
    @GeneratedValue
    private UUID id;

    @NonNull
    private String name;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "course_id")
    private SCourse course;
}
