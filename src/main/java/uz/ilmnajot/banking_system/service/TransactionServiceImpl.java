package uz.ilmnajot.banking_system.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;
import uz.ilmnajot.banking_system.entity.Account;
import uz.ilmnajot.banking_system.entity.Transaction;
import uz.ilmnajot.banking_system.enums.TransactionType;
import uz.ilmnajot.banking_system.exception.LowBalanceException;
import uz.ilmnajot.banking_system.mapper.TransactionMapper;
import uz.ilmnajot.banking_system.model.request.DepositRequest;
import uz.ilmnajot.banking_system.model.request.WithdrawRequest;
import uz.ilmnajot.banking_system.model.response.TransactionResponse;
import uz.ilmnajot.banking_system.repository.AccountRepository;
import uz.ilmnajot.banking_system.repository.TransactionRepository;

@RequiredArgsConstructor
@Service
public class TransactionServiceImpl implements TransactionService {


    private final TransactionRepository transactionRepository;

    private final AccountRepository accountRepository;

    private final TransactionMapper transactionMapper;

    @Override
    public TransactionResponse deposit(DepositRequest request) {
        Account account = accountRepository
                .findByCardNumber(request.getCard_number())
                .orElseThrow(() -> new BadCredentialsException("Bad credentials"));
        Long transactionId = performDeposit(account, request.getAmount());

        return transactionMapper.toResponse(transactionId, request.getAmount(), account.getBalance());
    }

    @Override
    public TransactionResponse withdraw(WithdrawRequest request) {
        Account account = accountRepository
                .findByCardNumberAndCcv(request.getCard_number(), request.getCvv())
                .orElseThrow(() -> new BadCredentialsException("Bad Credentials"));

        Long transactionId = performWithdraw(account, request.getAmount());
        return transactionMapper.toResponse(transactionId, request.getAmount(), account.getBalance());
    }

    private Long performDeposit(Account account, double amount) {
        updateAccountBalance(account, amount);
        Transaction transaction = transactionRepository.save(transactionMapper.toEntity(amount, account, TransactionType.DEPOSIT));
        return transaction.getId();
    }

    private Long performWithdraw(Account account, double amount) {
        if (account.getBalance() < amount) {
            throw new LowBalanceException("Your Balance " + account.getBalance() + " is not enough to withdraw " + amount);
        }
        updateAccountBalance(account, -amount);
        Transaction transaction = transactionRepository.save(transactionMapper.toEntity(amount, account, TransactionType.WITHDRAW));
        return transaction.getId();
    }

    private void updateAccountBalance(Account account, double amount) {
        account.setBalance(account.getBalance() + amount);
        accountRepository.save(account);
    }


}
