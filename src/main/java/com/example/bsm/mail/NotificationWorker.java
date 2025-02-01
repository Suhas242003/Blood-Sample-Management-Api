package com.example.bsm.mail;

import com.example.bsm.entity.*;
import com.example.bsm.repository.BloodBankRepository;
import com.example.bsm.repository.DonationRequestRepository;
import com.example.bsm.repository.HospitalRepository;
import com.example.bsm.repository.UserRepository;
import jakarta.mail.MessagingException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@AllArgsConstructor
public class NotificationWorker {
    private final MailService mailService;
    private final UserRepository userRepository;
    private final DonationRequestRepository donationRequestRepository;
    private final HospitalRepository hospitalRepository;
    private final BloodBankRepository bloodBankRepository;

    private void sendBloodDonationRequestNotification(Organization org, DonationRequest donationRequest) throws MessagingException {
        List<User> userList = userRepository.findByAvailableCityInAndBloodGroupIn(donationRequest.getCities(), donationRequest.getBloodGroups());
        for (User user : userList) {
            Map<String, Object> variable = new HashMap<>();
            variable.put("organizationName", org.getOrganizationName());
            variable.put("organizationAddress", org.getOrginizationAddress());
            variable.put("bloodGroup", user.getBloodGroup().name());
            variable.put("surveyLink", "");
            variable.put("donateLink", "");

            String text = mailService.genericContext("DonationRequest", variable);
            mailService.sendMail(user.getEmail(), "Urgent: Blood Required", text);
        }
    }

    @Scheduled(fixedRate = 60000)
    public void sendBloodDonationRequestNotification() throws MessagingException {
        List<DonationRequest> donationRequestList = donationRequestRepository.findByRequestCompleted(false);
        for (DonationRequest donationRequest : donationRequestList) {
            Organization org = this.findOrganizationDetails(donationRequest);
            if (org != null) {
                this.sendBloodDonationRequestNotification(org, donationRequest);
            }
        }
    }

    public Organization findOrganizationDetails(DonationRequest donationRequest) {
        Organization org = new Organization(); // Initialize the Organization object

        switch (donationRequest.getOrganizationType()) {
            case HOSPITAL -> {
                Hospital hospital = hospitalRepository.findByDonationRequest(donationRequest);
                if (hospital != null) { // Null check for hospital
                    org.setOrganizationName(hospital.getName());
                    Address address = hospital.getAddress();
                    org.setOrginizationAddress(getOrganizationAddress(address));
                } else {
                    org.setOrganizationName("Unknown Hospital");
                    org.setOrginizationAddress("Address not available");
                }
            }
            case BLOODBANK -> {
                BloodBank bloodBank = bloodBankRepository.findByDonationRequest(donationRequest);
                if (bloodBank != null) { // Null check for blood bank
                    org.setOrganizationName(bloodBank.getName());
                    Address address = bloodBank.getAddress();
                    org.setOrginizationAddress(getOrganizationAddress(address));
                } else {
                    org.setOrganizationName("Unknown Blood Bank");
                    org.setOrginizationAddress("Address not available");
                }
            }
            default -> {
                org.setOrganizationName("Unknown Organization");
                org.setOrginizationAddress("Address not available");
            }
        }

        return org;
    }

    private static String getOrganizationAddress(Address address) {
        if (address == null) return "No address available";
        return address.getAddressLine() + " \n  " + address.getArea() +
                " \n  " + address.getCity() + " \n  " + address.getState()
                + " \n  " + address.getCountry() + " \n  " + address.getPincode();
    }

    @Getter
    @Setter
    public static class Organization { // Made it static for proper usage
        private String organizationName;
        private String orginizationAddress;
    }
}
