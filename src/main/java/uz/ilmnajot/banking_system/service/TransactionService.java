package uz.ilmnajot.banking_system.service;

import uz.ilmnajot.banking_system.entity.Transaction;
import uz.ilmnajot.banking_system.model.request.DepositRequest;
import uz.ilmnajot.banking_system.model.request.WithdrawRequest;
import uz.ilmnajot.banking_system.model.response.TransactionResponse;

public interface TransactionService {
    TransactionResponse deposit(DepositRequest request);

    TransactionResponse withdraw(WithdrawRequest request);
}
