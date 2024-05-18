package com.example.SwiftCash.model.dto;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class TransactionDTO {

    @NotNull(message = "Amount cannot be Null")
    private BigDecimal amount;

    @NotNull(message = "Sender Id cannot be Null")
    private Long senderAccountId;

    @NotNull(message = "Receiver Id cannot be Null")
    private Long receiverAccountId;

    private LocalDateTime timestamp;

    public TransactionDTO() {
    }

    public TransactionDTO(BigDecimal amount, Long senderAccountId, Long receiverAccountId, LocalDateTime timestamp) {
        this.amount = amount;
        this.senderAccountId = senderAccountId;
        this.receiverAccountId = receiverAccountId;
        this.timestamp = timestamp;
    }


}
