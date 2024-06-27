package uz.ilmnajot.banking_system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.ilmnajot.banking_system.entity.Transaction;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {


}
