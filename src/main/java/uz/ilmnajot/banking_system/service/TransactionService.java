package uz.ilmnajot.banking_system.service;

import uz.ilmnajot.banking_system.model.common.ApiResponse;
import uz.ilmnajot.banking_system.model.request.DepositRequest;
import uz.ilmnajot.banking_system.model.request.TransferRequest;
import uz.ilmnajot.banking_system.model.request.WithdrawRequest;
import uz.ilmnajot.banking_system.model.response.TransactionResponse;

public interface TransactionService {

    TransactionResponse deposit(DepositRequest request);

    TransactionResponse withdraw(WithdrawRequest request);

    ApiResponse transfer(TransferRequest request);
}
