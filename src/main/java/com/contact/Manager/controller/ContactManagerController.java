package com.contact.Manager.controller;

import com.contact.Manager.dtos.request.RegisterRequest;
import com.contact.Manager.dtos.response.ApiResponse;
import com.contact.Manager.dtos.response.RegisterResponse;
import com.contact.Manager.service.ContactMangerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ContactManagerController {
    @Autowired
    ContactMangerServiceImpl contactMangerService;

    @PostMapping("/addProfile")
    public ResponseEntity<?> addProfile(@RequestBody RegisterRequest registerRequest) {

    RegisterResponse registerResponse = new RegisterResponse();

    try {
        contactMangerService.addProfile(registerRequest);
        registerResponse.setMessage("Successful");
        return new ResponseEntity<>(new ApiResponse(true,registerResponse), HttpStatus.ACCEPTED);
    }catch (Exception e){
        registerResponse.setMessage(e.getMessage());
        return new ResponseEntity<>(new ApiResponse(false,registerResponse), HttpStatus.BAD_REQUEST);
    }
    }

}
