package uz.ilmnajot.banking_system.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import uz.ilmnajot.banking_system.entity.Account;
import uz.ilmnajot.banking_system.entity.User;
import uz.ilmnajot.banking_system.mapper.AccountMapper;
import uz.ilmnajot.banking_system.model.response.AccountResponse;
import uz.ilmnajot.banking_system.repository.AccountRepository;
import uz.ilmnajot.banking_system.repository.UserRepository;
import uz.ilmnajot.banking_system.utils.Utils;

import java.util.List;

@RequiredArgsConstructor
@Service
public class AccountServiceImpl implements AccountService {


    private final AccountRepository accountRepository;

    private final UserRepository userRepository;

    private final AccountMapper accountMapper;


    @Override
    public AccountResponse createNewAccount() {

        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository
                .findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException("User " + email + " Not Found"));

        Account account = accountRepository.save(
                Account
                        .builder()
                        .cardNumber(generateUniqueCardNumber())
                        .ccv(Utils.generateCVV())
                        .balance(0.0)
                        .user(user)
                        .build()
        );
        return accountMapper.toAccountResponse(account);
    }

    @Override
    public List<AccountResponse> getMyAccounts() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository
                .findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException("User " + email + " Not Found"));

        return accountRepository.findAllByUser(user)
                .stream()
                .map(accountMapper::toAccountResponse)
                .toList();
    }


    public String generateUniqueCardNumber(){
        String cardNumber = Utils.generateCardNumber();

        while (accountRepository.existsByCardNumber(cardNumber)){
            cardNumber = Utils.generateCardNumber();
        }
        return cardNumber;
    }
}
