package uz.ilmnajot.banking_system.mapper.impl;

import org.springframework.stereotype.Component;
import uz.ilmnajot.banking_system.entity.Account;
import uz.ilmnajot.banking_system.mapper.AccountMapper;
import uz.ilmnajot.banking_system.model.response.AccountResponse;

@Component
public class AccountMapperImpl implements AccountMapper {


    @Override
    public AccountResponse toAccountResponse(Account account) {

        return AccountResponse
                .builder()
                .card_number(account.getCardNumber())
                .cvv(account.getCcv())
                .balance(account.getBalance())
                .build();
    }
}
