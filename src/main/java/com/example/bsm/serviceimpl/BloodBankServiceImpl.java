//package com.example.bsm.service impl;

//import com.example.bsm.entity.BloodBank;
//import com.example.bsm.exception.BloodBankNotFoundException;
//import com.example.bsm.repository.BloodBankRepository;
//import com.example.bsm.request.BloodBankRequest;
//import com.example.bsm.response.BloodBankResponse;
//import com.example.bsm.service.BloodBankService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Service;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//
//@Service
//@RequiredArgsConstructor
//public class BloodBankServiceImpl implements BloodBankService {
//
//    private final BloodBankRepository bloodBankRepository;
//
//    @Override
//    public BloodBankResponse addBloodBank(BloodBankRequest request) {
//        BloodBank bloodBank = BloodBank.builder()
//                .name(request.getName())
//                .emergencyUnitCount(request.isEmergencyUnitsAvailable())
//                .build();
//
//        BloodBank savedBloodBank = bloodBankRepository.save(bloodBank);
//        return mapToResponse(savedBloodBank);
//    }
//
//    @Override
//    public BloodBankResponse getBloodBankById(int id) {
//        BloodBank bloodBank = bloodBankRepository.findById(id)
//                .orElseThrow(() -> new BloodBankNotFoundException("BloodBank not found with ID: " + id));
//        return mapToResponse(bloodBank);
//    }
//
//    @Override
//    public BloodBankResponse updateBloodBank(int bankId, BloodBankRequest bloodBankRequest) {
//        Optional<BloodBank> optional = bloodBankRepository.findById(bankId);
//
//        if (optional.isEmpty()) {
//            throw new BloodBankNotFoundException("Fail to update BloodBank with ID: " + bankId);
//        }
//
//        BloodBank bloodBank = mapToBloodBank(bloodBankRequest, optional.get());
//        BloodBank updatedBloodBank = bloodBankRepository.save(bloodBank);
//        return getBloodBankResponse(updatedBloodBank);
//    }
//
//    @Override
//    public List<BloodBankResponse> findAllBloodBanks() {
//        List<BloodBank> bloodBanks = bloodBankRepository.findAll();
//        List<BloodBankResponse> bloodBanksResponse = new ArrayList<>();
//
//        for (BloodBank bloodBank : bloodBanks) {
//            bloodBanksResponse.add(mapToResponse(bloodBank)); // Mapping each entity to response
//        }
//
//        return bloodBanksResponse;
//    }
//
//
//
//    @Override
//    public List<BloodBankResponse> findBloodBank() {
//        List<BloodBank> bloodBanks = bloodBankRepository.findAll();
//        List<BloodBankResponse> bloodBanksResponse = new ArrayList<>();
//
//        for (BloodBank bloodBank : bloodBanks) {
//            bloodBanksResponse.add(mapToResponse(bloodBank)); // Mapping each entity to response
//        }
//
//        return bloodBanksResponse;
//    }
//
//
//
//
//    private BloodBankResponse mapToResponse(BloodBank bloodBank) {
//        return BloodBankResponse.builder()
//                .bankId(bloodBank.getBankId())  // Bank ID
//                .name(bloodBank.getName())  // Name of the BloodBank
//                .emergencyUnitsAvailable(bloodBank.isEmergencyUnitCount())  // Emergency units availability
//                .build();
//    }
//
//
//    private BloodBank mapToBloodBank(BloodBankRequest request, BloodBank existingBloodBank) {
//        existingBloodBank.setName(request.getName());
//        existingBloodBank.setEmergencyUnitCount(request.isEmergencyUnitsAvailable());
//        return existingBloodBank;
//    }
//
//
//    private BloodBankResponse getBloodBankResponse(BloodBank bloodBank) {
//        return BloodBankResponse.builder()
//                .bankId(bloodBank.getBankId())
//                .name(bloodBank.getName())
//                .emergencyUnitsAvailable(bloodBank.isEmergencyUnitCount())
//                .build();
//    }
//}
