package com.example.bsm.controller;

import com.example.bsm.request.TransactionRequest;
import com.example.bsm.response.TransactionResponse;
import com.example.bsm.service.TransactionService;
import com.example.bsm.utility.ResponseStructure;
import com.example.bsm.utility.RestResponseBuilder;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class TransactionController {

    private final TransactionService transactionService;
    private final RestResponseBuilder responseBuilder;

//    @PostMapping("/transaction-user")
//    public ResponseEntity<ResponseStructure<TransactionResponse>> addUserTransaction(@RequestBody TransactionRequest transactionRequest){
//        TransactionResponse transactionResponse = transactionService.addUserTransaction(transactionRequest);
//        return responseBuilder.success(HttpStatus.CREATED, "User Transaction Created", transactionResponse);
//    }
//
//    @PreAuthorize("hasAnyAuthority('OWNER_ADMIN') || hasAnyAuthority('GUEST_ADMIN')")
//    @PostMapping("/transaction-hospital")
//    public ResponseEntity<ResponseStructure<TransactionResponse>> addHospitalTransaction(@RequestBody TransactionRequest transactionRequest, @PathVariable int hospitalId){
//        TransactionResponse transactionResponse = transactionService.addHospitalTransaction(transactionRequest, hospitalId);
//        return responseBuilder.success(HttpStatus.CREATED, "Hospital Transaction Created", transactionResponse);
//    }

    @PreAuthorize("hasAnyAuthority('OWNER_ADMIN') || hasAnyAuthority('GUEST_ADMIN')")
    @PostMapping("/transactions/{hospitalId}/{userId}")
    public ResponseEntity<ResponseStructure<TransactionResponse>> checkTransaction(@RequestBody TransactionRequest transactionRequest, @PathVariable int hospitalId, @PathVariable int userId) throws Exception {
        TransactionResponse transactionResponse = transactionService.checkTransaction(transactionRequest, hospitalId, userId);
        return responseBuilder.success(HttpStatus.CREATED, "Transaction Successful", transactionResponse);
    }

}
