package iam.phomenko.clothes.domain.payments;

import iam.phomenko.clothes.domain.clothes.Clothes;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "payments")
public class Payment {
    @Id
    private String id;

    @OneToMany
    private List<Clothes> clothes;

    @ManyToOne
    private Transaction transaction;
}
