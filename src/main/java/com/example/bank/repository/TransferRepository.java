package com.example.bank.repository;

import com.example.bank.model.Transfer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface TransferRepository extends JpaRepository<Transfer, Integer> {
    @Query("SELECT t FROM Transfer t WHERE t.sender = ?1")
    Collection<Transfer> findUserTransfers(String login);
}
