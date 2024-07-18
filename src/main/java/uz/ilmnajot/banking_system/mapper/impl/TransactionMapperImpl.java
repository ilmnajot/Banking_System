package uz.ilmnajot.banking_system.mapper.impl;

import org.springframework.stereotype.Component;
import uz.ilmnajot.banking_system.entity.Account;
import uz.ilmnajot.banking_system.entity.Transaction;
import uz.ilmnajot.banking_system.enums.TransactionType;
import uz.ilmnajot.banking_system.mapper.TransactionMapper;
import uz.ilmnajot.banking_system.model.response.TransactionResponse;

import java.math.BigDecimal;

@Component
public class TransactionMapperImpl implements TransactionMapper {


    @Override
    public Transaction toEntity(BigDecimal amount, Account account, TransactionType type) {
        return Transaction
                .builder()
                .amount(amount)
                .toAccount(account)
                .type(type)
                .notes("Account Balance" + account.getBalance())
                .build();
    }

    @Override
    public TransactionResponse toResponse(Long id, BigDecimal amount, BigDecimal balance) {
        return TransactionResponse
                .builder()
                .id(id)
                .amount(amount)
                .balance(balance)
                .build();
    }
}
