package org.example.flow_analyzer.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor(force = true)
@AllArgsConstructor

public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private double operation;
    private LocalDateTime DataTime;

    @ManyToOne
    @JoinColumn(name = "сategory_id")
    Category сategory;
}
