package com.dev.rest.Models;

public class TransferBalanceResponseModel {
    private Boolean status;
    private String message;
    private String senderUpdate;
    private String receiverUpdate;

    public TransferBalanceResponseModel(Boolean status, String message) {
        this.status = status;
        this.message = message;
    }

    public TransferBalanceResponseModel(Boolean status, String senderUpdate, String receiverUpdate) {
        this.status = status;
        this.senderUpdate = senderUpdate;
        this.receiverUpdate = receiverUpdate;
    }

    public TransferBalanceResponseModel(Boolean status, String message, String senderUpdate, String receiverUpdate) {
        this.status = status;
        this.message = message;
        this.senderUpdate = senderUpdate;
        this.receiverUpdate = receiverUpdate;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getSenderUpdate() {
        return senderUpdate;
    }

    public void setSenderUpdate(String senderUpdate) {
        this.senderUpdate = senderUpdate;
    }

    public String getReceiverUpdate() {
        return receiverUpdate;
    }

    public void setReceiverUpdate(String receiverUpdate) {
        this.receiverUpdate = receiverUpdate;
    }
}
