package com.example.securitylesson.controllers;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountController {

    @RequestMapping(value = "svc/v1/public/accounts/{accountNumber}")
    public  String getPublicAccountLinkedTo(@PathVariable final int accountNumber ){
        return  "Public Account Linked To "+accountNumber;
    }
    @RequestMapping(value = "svc/v1/private/accounts/{accountNumber}")
    public  String getPrivateAccountLinkedTo(@PathVariable final int accountNumber ){
        return  "Private Account Linked To "+accountNumber;
    }
}
