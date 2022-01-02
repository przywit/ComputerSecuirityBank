package com.example.bank.controller;

import com.example.bank.DTO.UserDTO;
import com.example.bank.converter.UserConverter;
import com.example.bank.exception.BadCredentialsException;
import com.example.bank.exception.PasswordNotConfirmedException;
import com.example.bank.model.Transfer;
import com.example.bank.model.User;
import com.example.bank.service.TransferService;
import com.example.bank.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@org.springframework.stereotype.Controller
public class Controller {

    private final UserService userService;
    private final TransferService transferService;
    private final UserConverter userConverter;
    private final Argon2PasswordEncoder passwordEncoder = new Argon2PasswordEncoder();

    @Autowired
    public Controller(UserService userService, UserConverter userConverter, TransferService transferService) {
        this.transferService = transferService;
        this.userService = userService;
        this.userConverter = userConverter;
    }

    @GetMapping("")
    public String viewHomePage() {
        return "index";
    }

    @GetMapping("/reset_password")
    public String resetPasswordPage(Model model) {
        model.addAttribute("user", UserDTO.UserDTOBuilder.anUserDTO().build());

        return "reset_password";
    }

    @PostMapping("/process_reset_password")
    public String processPasswordReset(User user, Model model) {
        if (!userService.userExists(user.getLogin())) {
            throw new BadCredentialsException("Bad Credentials");
        }
        User userFromDataBase = userService.getUser(user.getLogin());

        if (!passwordEncoder.matches(user.getAnswer(), userFromDataBase.getAnswer())) {
            throw new BadCredentialsException("Bad Credentials");
        }

        model.addAttribute("user", UserDTO.UserDTOBuilder.anUserDTO().withLogin(user.getLogin()).build());

        return "reset_passwordv2";
    }

    @PostMapping("/process_reset_passwordv2")
    public String processPasswordResetV2(UserDTO userDTO) {
        if (!userDTO.getPassword().equals(userDTO.getConfirmPassword())) {
            throw new PasswordNotConfirmedException("Passwords does not match.");
        }
        User user = userConverter.convertDTOToEntity(userDTO);

        String encodedPassword = passwordEncoder.encode(user.getPassword());

        user.setPassword(encodedPassword);

        userService.update(user);
        return "index";
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", UserDTO.UserDTOBuilder.anUserDTO().build());

        return "signup_form";
    }

    @PostMapping("/process_register")
    public String processRegister(UserDTO userDTO) {
        if (!userDTO.getPassword().equals(userDTO.getConfirmPassword())) {
            throw new PasswordNotConfirmedException("Passwords does not match.");
        }
        User user = userConverter.convertDTOToEntity(userDTO);

        String encodedPassword = passwordEncoder.encode(user.getPassword());
        String encodedAnswer = passwordEncoder.encode(user.getAnswer());


        user.setPassword(encodedPassword);
        user.setAnswer(encodedAnswer);

        userService.save(user);

        return "index";
    }

    @GetMapping("/bank")
    public String showBank() {
        return "bank";
    }

    @GetMapping("/bank/history")
    public String showHistory(Model model) {
        User user = getUser();

        List<Transfer> transfers = transferService.getUserTransfers(user.getLogin());
        model.addAttribute("transfers", transfers);
        return "history";
    }

    @GetMapping("/bank/new_transfer")
    public String showNewTransferForm(Model model) {
        model.addAttribute("transfer", new Transfer());

        return "new_transfer";
    }

    @PostMapping("/bank/process_new_transfer")
    public String processNewTransferForm(Transfer transfer, Model model) {
        model.addAttribute("transfer",transfer);
        return "confirm_transfer";
    }

    @PostMapping("/bank/process_confrim_transfer")
    public String processConfirmTransfer(Transfer transfer, Model model) {
        User user = getUser();

        transfer.setSender(user.getLogin());

        transferService.save(transfer);

        model.addAttribute("transfer" ,transfer);
        return "transfer_done";
    }

    private User getUser() {
        Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
        String email = loggedInUser.getName();
        return userService.getUserByEmail(email);
    }
}
