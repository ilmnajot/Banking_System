package uz.ilmnajot.banking_system.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;
import uz.ilmnajot.banking_system.base.BaseEntity;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity(name = "ACCOUNT")
public class Account extends BaseEntity {

    @Column(unique = true, nullable = false)
    private String cardNumber;

    @Column(nullable = false)
    private String ccv;

    @Column(nullable = false)
    private Double balance;

    @ManyToOne
    private User user;


}
