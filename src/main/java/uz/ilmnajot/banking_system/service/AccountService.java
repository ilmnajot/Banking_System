package uz.ilmnajot.banking_system.service;

import uz.ilmnajot.banking_system.model.ApiResponse;
import uz.ilmnajot.banking_system.model.response.AccountResponse;

import java.util.List;

public interface AccountService {
    AccountResponse createNewAccount();

    List<AccountResponse> getMyAccounts();
}
