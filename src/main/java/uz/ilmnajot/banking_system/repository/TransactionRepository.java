package uz.ilmnajot.banking_system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.ilmnajot.banking_system.entity.Account;
import uz.ilmnajot.banking_system.entity.Transaction;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    Optional<Transaction> findByFromAccount(Account accountNumber);

    List<Transaction> findByFromAccountAndCreatedAtBetween(Account accountNumber, LocalDateTime from, LocalDateTime to);


}
