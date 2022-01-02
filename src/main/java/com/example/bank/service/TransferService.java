package com.example.bank.service;

import com.example.bank.model.Transfer;

import java.util.List;

public interface TransferService {
    void save(Transfer transfer);

    List<Transfer> getUserTransfers(String login);
}
