package uz.ilmnajot.banking_system.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.ilmnajot.banking_system.model.common.ApiResponse;
import uz.ilmnajot.banking_system.model.request.DepositRequest;
import uz.ilmnajot.banking_system.model.request.TransferRequest;
import uz.ilmnajot.banking_system.model.request.WithdrawRequest;
import uz.ilmnajot.banking_system.service.TransactionService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/transactions")
public class TransactionController {

    private final TransactionService transactionService;

    @PostMapping("/deposit")
    public ResponseEntity<ApiResponse> deposit(@Valid @RequestBody DepositRequest request) {
        return ResponseEntity.ok(
                ApiResponse
                        .builder()
                        .status(HttpStatus.OK)
                        .success(true)
                        .data(transactionService.deposit(request))
                        .build()
        );
    }

    @PostMapping("/withdraw")
    public ResponseEntity<ApiResponse> withdraw(@Valid @RequestBody WithdrawRequest request) {
        return ResponseEntity.ok(
                ApiResponse
                        .builder()
                        .status(HttpStatus.OK)
                        .success(true)
                        .data(transactionService.withdraw(request))
                        .build()
        );
    }

    @PostMapping("/transfer")
    public ResponseEntity<ApiResponse> transfer(@Valid @RequestBody TransferRequest request) {
        return ResponseEntity.ok(
                ApiResponse
                        .builder()
                        .status(HttpStatus.OK)
                        .success(true)
                        .data(transactionService.transfer(request))
                        .build());
    }
}
