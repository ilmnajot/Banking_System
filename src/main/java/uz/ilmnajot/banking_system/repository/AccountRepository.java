package uz.ilmnajot.banking_system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.ilmnajot.banking_system.entity.Account;
import uz.ilmnajot.banking_system.entity.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    boolean existsByCardNumber(String cardNumber);

    List<Account> findAllByUser(User user);

    Optional<Account> findByCardNumber(String cardNumber);

    Optional<Account> findByCardNumberAndCcv(String cardNumber, String ccv);
}
