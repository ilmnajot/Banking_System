package uz.ilmnajot.banking_system.model.response;

import lombok.Data;
import lombok.NoArgsConstructor;
import uz.ilmnajot.banking_system.entity.Transaction;
import uz.ilmnajot.banking_system.enums.TransactionType;
import uz.ilmnajot.banking_system.model.request.TransferRequest;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class TransferResponse {


    private Long id;

    private String fromAccount;
    private String toAccount;
    private TransactionType type;
    private BigDecimal amount;


    public TransferResponse toTransfer(Transaction transaction) {
        TransferResponse request = new TransferResponse();
        request.setId(transaction.getId());
        request.setFromAccount(transaction.getFromAccount().toString());
        request.setToAccount(transaction.getToAccount().toString());
        request.setAmount(transaction.getAmount());
        request.setType(transaction.getType());
        return request;
    }


}
