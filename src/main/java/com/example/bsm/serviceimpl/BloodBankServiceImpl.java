package com.example.bsm.serviceimpl;

import com.example.bsm.entity.Address;
import com.example.bsm.entity.Admin;
import com.example.bsm.entity.BloodBank;
import com.example.bsm.entity.Sample;
import com.example.bsm.enums.BloodGroup;
import com.example.bsm.exception.BloodBankNotFoundExceptionById;
import com.example.bsm.exception.UserNotFoundExceptionById;
import com.example.bsm.repository.AdminRepository;
import com.example.bsm.repository.BloodBankRepository;
import com.example.bsm.repository.SampleRepository;
import com.example.bsm.request.BloodBankRequest;
import com.example.bsm.response.AddressResponse;
import com.example.bsm.response.BloodBankPageResponse;
import com.example.bsm.response.BloodBankResponse;
import com.example.bsm.response.SampleResponse;
import com.example.bsm.service.BloodBankService;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class BloodBankServiceImpl implements BloodBankService {

    private final BloodBankRepository bloodRepository;
    private final AdminRepository adminRepository;
    private final SampleRepository sampleRepository;

    private BloodBankResponse mapToBloodBankResponse(BloodBank bloodBank) {
        return BloodBankResponse.builder()
                .bankId(bloodBank.getBankId())
                .name(bloodBank.getName())
                .emergencyUnitCount(bloodBank.getEmergencyUnitCount())
                .build();
    }

    private BloodBank mapToBloodBank(BloodBankRequest bankRequest, BloodBank bloodBank) {
        bloodBank.setName(bankRequest.getName());
        bloodBank.setEmergencyUnitCount(bankRequest.getEmergencyUnitsAvailable()); // Correct getter for available units
        return bloodBank;
    }

    @Override
    public BloodBankResponse findBloodBankById(int bankId) {
        BloodBank bloodBank = bloodRepository.findById(bankId)
                .orElseThrow(() -> new BloodBankNotFoundExceptionById("Blood Bank with ID " + bankId + " not found"));
        return mapToBloodBankResponse(bloodBank);
    }

    @Override
    public BloodBankResponse updateBloodBankById(int bankId, BloodBankRequest bankRequest) {
        BloodBank exBloodBank = bloodRepository.findById(bankId)
                .orElseThrow(() -> new BloodBankNotFoundExceptionById("Blood Bank with ID " + bankId + " not found"));
        BloodBank updatedBloodBank = mapToBloodBank(bankRequest, exBloodBank);
        return mapToBloodBankResponse(bloodRepository.save(updatedBloodBank));
    }

    @Override
    public BloodBankResponse addAdminBank(BloodBankRequest bankRequest, int adminId) {
        Admin admin = adminRepository.findById(adminId)
                .orElseThrow(() -> new UserNotFoundExceptionById("Admin with ID " + adminId + " not found"));

        BloodBank bloodBank = BloodBank.builder()
                .name(bankRequest.getName())
                .emergencyUnitCount(bankRequest.getEmergencyUnitsAvailable())
                .build();

        bloodRepository.save(bloodBank);

        List<Admin> admins = new ArrayList<>();
        admins.add(admin);
        bloodBank.setAdmin(admins);

        admin.setBloodBank(bloodBank);
        adminRepository.save(admin);

        return mapToBloodBankResponse(bloodBank);
    }

    @Override
    public Page<BloodBankPageResponse> findAllBloodBankByCity(
            List<String> city,
            List<BloodGroup> bloodGroups,
            int page,
            int size) {

        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "name"));

        Page<BloodBank> bloodBanks = bloodRepository.findByAddressCityInAndSamples_BloodGroupIn(
                city,
                bloodGroups,
                pageable
        );

        if (bloodBanks.isEmpty()) {
            throw new BloodBankNotFoundExceptionById("No blood banks found for the provided cities and blood groups");
        }

        return bloodBanks.map(bloodBank -> mapToBloodBankPageResponse(bloodBank, bloodGroups));
    }

    private BloodBankPageResponse mapToBloodBankPageResponse(BloodBank bloodBank, List<BloodGroup> requestedBloodGroups) {
        return BloodBankPageResponse.builder()
                .bankId(bloodBank.getBankId())
                .name(bloodBank.getName())
                .address(mapToAddressResponse(bloodBank.getAddress()))
                .samples(bloodBank.getSamples().stream()
                        .filter(sample -> requestedBloodGroups.contains(sample.getBloodGroup())) // Filter based on requested blood groups
                        .map(this::mapToSampleResponse)
                        .collect(Collectors.toList()))
                .build();
    }

    private AddressResponse mapToAddressResponse(Address address) {
        if (address == null) return null;

        return AddressResponse.builder()
                .addressId(address.getAddressId())
                .addressLine(address.getAddressLine())
                .landmark(address.getLandmark())
                .area(address.getArea())
                .city(address.getCity())
                .state(address.getState())
                .country(address.getCountry())
                .pincode(address.getPincode())
                .build();
    }

    private SampleResponse mapToSampleResponse(Sample sample) {
        return SampleResponse.builder()
                .sampleId(sample.getSampleId())
                .bloodGroup(sample.getBloodGroup())
                .quantity(sample.getQuantity())
                .availability(sample.isAvailability())
                .emergencyUnits(sample.getEmergencyUnits())
                .availableUnits(sample.getAvailableUnits())
                .build();
    }
}

//@Override
//public List<BloodBankPageResponse> findAllBloodBankByCity(List<String> city, List<BloodGroup> bloodGroups, int page, int size) {
//    Pageable pageable = PageRequest.of(page, size);
//
//    Page<BloodBank> bloodBanks = bloodRepository.findByAddress_CityInAndSamples_BloodGroupIn(city, bloodGroups, pageable);
//
//    if (bloodBanks.isEmpty()) {
//        throw new BloodBankNotFoundExceptionById("No blood banks found in the provided cities and blood groups.");
//    }
//
//    List<BloodBank> bloodbanks = bloodBanks.toList();
//
//    List<BloodBankResponse> bloodBankResponses = new ArrayList<>();
//    List<BloodBankPageResponse> bloodBankPageResponses = new ArrayList<>();
//
//    for (BloodBank bloodBank:bloodbanks) {
//        List<Sample> samples = sampleRepository.findByBloodBankAndBloodGroupIn(bloodBank,bloodGroups);
//        List<SampleResponse> sampleResponses=new ArrayList<>();
//        for (Sample sample:samples){
//            sampleResponses.add(this.mapToSampleRes(sample));
//        }
//
//        bloodBankPageResponses.add(mapToBloodBankPageMapper(bloodBank, sampleResponses));
//    }
//
//    return bloodBankPageResponses;
//}
//    private BloodBankPageResponse mapToBloodBankPageMapper(BloodBank bloodBank, List<SampleResponse> sampleResponses) {
//        return BloodBankPageResponse.builder().bankId(bloodBank.getBankId())
//                .name(bloodBank.getName())
//                .address(this.mapToAddressRes(bloodBank.getAddress()))
//                .samples(sampleResponses)
//                .build();
//    }
//
//    public SampleResponse mapToSampleRes(Sample sample) {
//        return SampleResponse.builder()
//                .sampleId(sample.getSampleId())
//                .bloodGroup(sample.getBloodGroup())
//                .quantity(sample.getQuantity())
//                .build();
//    }
//
//    public AddressResponse mapToAddressRes(Address address) {
//        return AddressResponse.builder()
//                .addressId(address.getAddressId())
//                .addressLine(address.getAddressLine())
//                .landmark(address.getLandmark())
//                .area(address.getArea())
//                .state(address.getState())
//                .country(address.getCountry())
//                .pincode(address.getPincode())
//                .build();
//
//
/// /    private BloodBankPageResponse mapToBloodBankPageResponse(BloodBank bloodBank, List<BloodGroup> requestedBloodGroups) {
/// /        return BloodBankPageResponse.builder()
/// /                .bankId(bloodBank.getBankId())
/// /                .name(bloodBank.getName())
/// /                .address(mapToAddressResponse(bloodBank.getAddress())
/// /                .samples(bloodBank.getSamples().stream()
/// /                        .filter(sample -> requestedBloodGroups.contains(sample.getBloodGroup())) // 🔥 Filter by requested blood groups
/// /                        .map(this::mapToSampleResponse)
/// /                        .collect(Collectors.toList()))
/// /                .build();
/// /    }
//
//
////    private AddressResponse mapToAddressResponse(Address address) {
////        if (address == null) return null;
////
////        return AddressResponse.builder()
////                .addressId(address.getAddressId())
////                .addressLine(address.getAddressLine())
////                .landmark(address.getLandmark())
////                .area(address.getArea())
////                .city(address.getCity())
////                .state(address.getState())
////                .country(address.getCountry())
////                .pincode(address.getPincode())
////                .build();
////    }
////
////    private SampleResponse mapToSampleResponse(Sample sample) {
////        if (sample == null) return null;
////
////        return SampleResponse.builder()
////                .sampleId(sample.getSampleId())
////                .bloodGroup(sample.getBloodGroup())
////                .quantity(sample.getQuantity())
////                .availability(sample.isAvailability())
////                .emergencyUnits(sample.getEmergencyUnits())
////                .availableUnits(sample.getAvailableUnits())
////                .build();
////    }}
//




//
//    @Override
//    public List<BloodBankPageResponse> findAllBloodBankByCity(List<String> city, List<BloodGroup> bloodGroups, int page, int size) {
//        Pageable pageable = PageRequest.of(page, size);
//        Page<BloodBank> bloodBanks = bloodRepository.findByAddress_CityInAndSamples_BloodGroupIn(city, bloodGroups, pageable);
//
//        if (bloodBanks.isEmpty()) {
//            throw new BloodBankNotFoundExceptionById("No blood banks found in the provided cities and blood groups");
//        }
//        if (bloodBanks.isEmpty()) {
//            return Collections.emptyList();
//        }
//
//        return bloodBanks.getContent().stream()
//                .map(bloodBank -> mapToBloodBankPageResponse(bloodBank, bloodGroups)) // 🔥 Pass the blood groups for filtering
//                .collect(Collectors.toList());
//    }
//
//

