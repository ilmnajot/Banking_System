package uz.ilmnajot.banking_system.model.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import uz.ilmnajot.banking_system.model.response.TransferResponse;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ApiResponse {

    private HttpStatus status;
    private boolean success;
    private Object data;
    private Object errors;

    public ApiResponse(String success, boolean b, TransferResponse transfer) {

    }

    public ApiResponse(HttpStatus httpStatus, boolean success, String data) {
        this.status = httpStatus;
        this.success = success;
        this.data = data;
    }
}
