package Transaction;

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
    final int id;
    final double operation;
    final LocalDateTime DataTime;

    @ManyToOne
    @JoinColumn(name = "сategory_id")
    final Category сategory;
}
