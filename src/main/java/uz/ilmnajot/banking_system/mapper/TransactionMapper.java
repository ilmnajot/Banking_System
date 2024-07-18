package uz.ilmnajot.banking_system.mapper;

import uz.ilmnajot.banking_system.entity.Account;
import uz.ilmnajot.banking_system.entity.Transaction;
import uz.ilmnajot.banking_system.enums.TransactionType;
import uz.ilmnajot.banking_system.model.response.TransactionResponse;

import java.math.BigDecimal;

public interface TransactionMapper {


    Transaction toEntity(BigDecimal amount, Account account, TransactionType type);

    TransactionResponse toResponse(Long id, BigDecimal amount, BigDecimal balance);
}
