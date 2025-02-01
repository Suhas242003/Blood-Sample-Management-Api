package com.example.bsm.service;

import com.example.bsm.request.TransactionRequest;
import com.example.bsm.response.TransactionResponse;

public interface TransactionService {

    TransactionResponse checkTransaction(TransactionRequest transactionRequest, int hospitalId, int userId) throws Exception;

//    TransactionResponse addUserTransaction(TransactionRequest transactionRequest);

//    TransactionResponse addHospitalTransaction(TransactionRequest transactionRequest, int hospitalId);

}
