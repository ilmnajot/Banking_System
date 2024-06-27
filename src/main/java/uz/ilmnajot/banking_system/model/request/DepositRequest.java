package uz.ilmnajot.banking_system.model.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class DepositRequest {

    @NotNull(message = "Card Number is required")
    private String card_number;

    @NotNull(message = "Amount is required")
    @Min(value = 0, message = "Ammount cannot be negative")
    private Double amount;

}
