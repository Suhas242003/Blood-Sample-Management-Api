package com.example.bsm.controller;

import com.example.bsm.enums.BloodGroup;
import com.example.bsm.request.BloodBankRequest;
import com.example.bsm.response.BloodBankResponse;
import com.example.bsm.response.BloodBankPageResponse;
import com.example.bsm.service.BloodBankService;
import com.example.bsm.utility.PageStructure;
import com.example.bsm.utility.ResponseStructure;
import com.example.bsm.utility.RestResponseBuilder;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class BloodBankController {

    private BloodBankService bankService;
    private RestResponseBuilder responseBuilder;

    @PreAuthorize("hasAnyAuthority('OWNER_ADMIN')")
    @PostMapping("/bloodbanks-admin/{adminId}")
    public ResponseEntity<ResponseStructure<BloodBankResponse>> addAdminBank(@RequestBody BloodBankRequest bankRequest, @PathVariable int adminId){
        BloodBankResponse bankResponse = bankService.addAdminBank(bankRequest, adminId);
        return responseBuilder.success(HttpStatus.CREATED, "Blood Bank Admin Created", bankResponse);
    }

    @GetMapping("/bloodbanks/{bankId}")
    public ResponseEntity<ResponseStructure<BloodBankResponse>> findBloodBankById(@PathVariable int bankId){
        BloodBankResponse bankResponse = bankService.findBloodBankById(bankId);
        return responseBuilder.success(HttpStatus.FOUND, "BloodBank Found", bankResponse);
    }

    @GetMapping("/blood-banks")
    public ResponseEntity<PageStructure<List<BloodBankPageResponse>>> findAllBloodBankByCity(@RequestParam List<String> city, @RequestParam List<BloodGroup> bloodGroup, @RequestParam int page, @RequestParam int size){
        Page<BloodBankPageResponse> bankResponse = bankService.findAllBloodBankByCity(city, bloodGroup, page, size);
        return responseBuilder.success(HttpStatus.FOUND, "BloodBanks Found", bankResponse.toList(),
        bankResponse.getNumber(),
                bankResponse.getTotalPages(),
                bankResponse.getSize());
    }

    @PreAuthorize("hasAnyAuthority('OWNER_ADMIN') || hasAnyAuthority('GUEST_ADMIN')")
    @PutMapping("/bloodbanks/{bankId}")
    public ResponseEntity<ResponseStructure<BloodBankResponse>> updateBloodBankById(@PathVariable int bankId, @RequestBody @Valid BloodBankRequest bankRequest){
        BloodBankResponse bankResponse = bankService.updateBloodBankById(bankId, bankRequest);
        return responseBuilder.success(HttpStatus.FOUND, "BloodBank Updated", bankResponse);
    }
//    @GetMapping("/blood-banks")
//    public ResponseEntity<ResponseStructure<List<BloodBankPageResponse>>> findAllBloodBankByCity(@RequestParam List<String> city, @RequestParam List<BloodGroup> bloodGroup, @RequestParam int page, @RequestParam int size){
//        List<BloodBankPageResponse> bankResponse = bankService.findAllBloodBankByCity(city, bloodGroup, page, size);
//        return responseBuilder.success(HttpStatus.FOUND, "BloodBanks Found", bankResponse);
//    }

}
