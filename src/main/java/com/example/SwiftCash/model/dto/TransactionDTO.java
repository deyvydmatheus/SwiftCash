package com.example.SwiftCash.model.dto;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

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

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Long getSenderAccountId() {
        return senderAccountId;
    }

    public void setSenderAccountId(Long senderAccountId) {
        this.senderAccountId = senderAccountId;
    }

    public Long getReceiverAccountId() {
        return receiverAccountId;
    }

    public void setReceiverAccountId(Long receiverAccountId) {
        this.receiverAccountId = receiverAccountId;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TransactionDTO that = (TransactionDTO) o;
        return Objects.equals(amount, that.amount) && Objects.equals(senderAccountId, that.senderAccountId) && Objects.equals(receiverAccountId, that.receiverAccountId) && Objects.equals(timestamp, that.timestamp);
    }

    @Override
    public int hashCode() {
        int result = Objects.hashCode(amount);
        result = 31 * result + Objects.hashCode(senderAccountId);
        result = 31 * result + Objects.hashCode(receiverAccountId);
        result = 31 * result + Objects.hashCode(timestamp);
        return result;
    }
}
