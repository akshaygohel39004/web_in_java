package com.akshay.testing.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.util.UUID;

@Data
@Entity
public class People{

    @GeneratedValue(strategy = GenerationType.UUID)
    @Id
    private UUID Id;

    private String name;
}
