package com.dev.rest.Models;

public class TransferBalanceModel {
    private Integer senderId;
    private Integer receiverId;
    private double transferAmount;

    public TransferBalanceModel(Integer senderId, Integer receiverId, double transferAmount) {
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.transferAmount = transferAmount;
    }

    public Integer getSenderId() {
        return senderId;
    }

    public Integer getReceiverId() {
        return receiverId;
    }

    public double getTransferAmount() {
        return transferAmount;
    }
}
