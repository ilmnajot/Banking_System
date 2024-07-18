package uz.ilmnajot.banking_system.service.impl;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;
import uz.ilmnajot.banking_system.entity.Account;
import uz.ilmnajot.banking_system.entity.Transaction;
import uz.ilmnajot.banking_system.enums.TransactionType;
import uz.ilmnajot.banking_system.exception.LowBalanceException;
import uz.ilmnajot.banking_system.mapper.TransactionMapper;
import uz.ilmnajot.banking_system.model.common.ApiResponse;
import uz.ilmnajot.banking_system.model.request.DepositRequest;
import uz.ilmnajot.banking_system.model.request.TransferRequest;
import uz.ilmnajot.banking_system.model.request.WithdrawRequest;
import uz.ilmnajot.banking_system.model.response.TransactionResponse;
import uz.ilmnajot.banking_system.model.response.TransferResponse;
import uz.ilmnajot.banking_system.repository.AccountRepository;
import uz.ilmnajot.banking_system.repository.TransactionRepository;
import uz.ilmnajot.banking_system.service.TransactionService;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@RequiredArgsConstructor
@Service
public class TransactionServiceImpl implements TransactionService {


    private final TransactionRepository transactionRepository;

    private final AccountRepository accountRepository;

    private final TransactionMapper transactionMapper;


    @Override
    public TransactionResponse deposit(DepositRequest request) {
        Account account = accountRepository
                .findByCardNumber(request.getCard_number()).orElseThrow(() -> new BadCredentialsException("Bad credentials"));
        Long transactionId = performDeposit(account, request.getAmount());
        return transactionMapper.toResponse(transactionId, request.getAmount(), account.getBalance());
    }

    @Override
    public TransactionResponse withdraw(WithdrawRequest request) {
        Account account = accountRepository.findByCardNumberAndCcv(request.getCard_number(), request.getCvv())
                .orElseThrow(() -> new BadCredentialsException("Bad Credentials"));

        Long transactionId = performWithdraw(account, request.getAmount());
        return transactionMapper.toResponse(transactionId, request.getAmount(), account.getBalance());
    }



    @Override
    public ApiResponse transfer(TransferRequest request) {

        Account fromAccount = accountRepository.findByCardNumber(request.getFromAccount()).orElseThrow(() -> new BadCredentialsException("Bad credentials"));
        Account toAccount = accountRepository.findByCardNumber(request.getToAccount()).orElseThrow(() -> new BadCredentialsException("Bad credentials"));
        if (fromAccount ==null || toAccount == null) {
            throw new BadCredentialsException("Bad credentials");
        }
        if (fromAccount.getBalance().compareTo(request.getAmount())<0){
            throw new LowBalanceException("Low balance");
        }
        fromAccount.setBalance(fromAccount.getBalance().subtract(request.getAmount()));
        toAccount.setBalance(toAccount.getBalance().add(request.getAmount()));

        accountRepository.save(fromAccount);
        accountRepository.save(toAccount);

        Transaction transaction = new Transaction();

        transaction.setFromAccount(fromAccount);
        transaction.setToAccount(toAccount);
        transaction.setAmount(request.getAmount());
        transaction.setType(TransactionType.TRANSFER);
        transaction.setCreatedAt(LocalDateTime.now());

        TransferResponse transfer = new TransferResponse().toTransfer(transaction);
        return new ApiResponse(HttpStatus.OK, true,
                "money "+
                        request.getAmount() + " has been transferred from account: " +
                        request.getFromAccount() + " to the account: " +
                        request.getToAccount() + " now balance of the account: " +
                        request.getFromAccount() + " is equal to " +
                        fromAccount.getBalance() + " and the balance of the account: " +
                        request.getToAccount() + " is equal to " +
                        toAccount.getBalance());

    }



    private Long performDeposit(Account account, BigDecimal amount) {
        updateAccountBalance(account, amount);
        Transaction transaction = transactionRepository.save(transactionMapper.toEntity(amount, account, TransactionType.DEPOSIT));
        return transaction.getId();
    }

    private Long performWithdraw(Account account, BigDecimal amount) {
        if (account.getBalance().compareTo(amount)<0) {
            throw new LowBalanceException("Your Balance " + account.getBalance() + " is not enough to withdraw " + amount);
        }
        updateAccountBalance(account, amount.negate());
        Transaction transaction = transactionRepository.save(transactionMapper.toEntity(amount, account, TransactionType.WITHDRAW));
        return transaction.getId();
    }

    private void updateAccountBalance(Account account, BigDecimal amount) {
        account.setBalance(account.getBalance().add(amount));
        accountRepository.save(account);
    }


}
