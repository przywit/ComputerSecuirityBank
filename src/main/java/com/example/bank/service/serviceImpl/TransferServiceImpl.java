package com.example.bank.service.serviceImpl;

import com.example.bank.model.Transfer;
import com.example.bank.repository.TransferRepository;
import com.example.bank.service.TransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransferServiceImpl implements TransferService {

    private final TransferRepository transferRepository;

    @Autowired
    public TransferServiceImpl(TransferRepository transferRepository) {
        this.transferRepository = transferRepository;
    }

    @Override
    public void save(Transfer transfer) {
        transferRepository.save(transfer);
    }

    @Override
    public List<Transfer> getUserTransfers(String login) {
        return (List<Transfer>) transferRepository.findUserTransfers(login);
    }
}
