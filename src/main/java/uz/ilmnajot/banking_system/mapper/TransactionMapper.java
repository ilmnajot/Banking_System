package uz.ilmnajot.banking_system.mapper;

import uz.ilmnajot.banking_system.entity.Account;
import uz.ilmnajot.banking_system.entity.Transaction;
import uz.ilmnajot.banking_system.enums.TransactionType;
import uz.ilmnajot.banking_system.model.response.TransactionResponse;

public interface TransactionMapper {


    Transaction toEntity(double amount, Account account, TransactionType type);

    TransactionResponse toResponse(Long id, double amount, double balance);
}
