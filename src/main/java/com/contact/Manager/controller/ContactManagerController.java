package com.contact.Manager.controller;

import com.contact.Manager.data.model.CallHistoryEntry;
import com.contact.Manager.data.model.Contact;
import com.contact.Manager.data.model.UserMessage;
import com.contact.Manager.dtos.request.AddContactRequest;
import com.contact.Manager.dtos.request.ComposeMessageRequest;
import com.contact.Manager.dtos.request.DeleteMessageRequest;
import com.contact.Manager.dtos.request.RegisterRequest;
import com.contact.Manager.dtos.response.*;
import com.contact.Manager.service.ContactMangerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.method.P;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @PostMapping("/addContact")
    public ResponseEntity<?> addContact(@RequestBody AddContactRequest addContactRequest){
        AddContactResponse addContactResponse = new AddContactResponse();

        try {
            contactMangerService.addContact(addContactRequest);
            addContactResponse.setMessage("Contact added successfully");
            return new ResponseEntity<>(new ApiResponse(true,addContactResponse), HttpStatus.ACCEPTED);
        } catch (Exception exception){
            addContactResponse.setMessage(exception.getMessage());
            return new ResponseEntity<>(new ApiResponse(false,addContactResponse), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/findContact/{name}")
    public ResponseEntity<?> findContact(@PathVariable("name") String name){
        FindContactResponse findContactResponse = new FindContactResponse();
        try {
           Contact contact = contactMangerService.findContact(name);
            findContactResponse.setMessage(String.valueOf(contact));
            return new ResponseEntity<>(new ApiResponse(true,findContactResponse), HttpStatus.ACCEPTED);
        } catch (Exception exception){
            findContactResponse.setMessage(exception.getMessage());
            return new ResponseEntity<>(new ApiResponse(false,findContactResponse), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/deleteContact")
    public  ResponseEntity<?> deleteContact(@RequestBody String number){
        DeleteContactResponse deleteContactResponse = new DeleteContactResponse();

        try {
            contactMangerService.deleteContact(number);
            deleteContactResponse.setMessage("contact deleted");
            return new ResponseEntity<>(new ApiResponse(true,deleteContactResponse), HttpStatus.ACCEPTED);
        } catch (Exception exception){
            deleteContactResponse.setMessage(exception.getMessage());
            return new ResponseEntity<>(new ApiResponse(false,deleteContactResponse), HttpStatus.BAD_REQUEST);
        }

    }

    @PostMapping("/blockContact")
    public ResponseEntity<?> blockContact(@RequestBody String name){
        BlockContactResponse blockContactResponse = new BlockContactResponse();

        try {
            contactMangerService.blockContact(name);
            blockContactResponse.setMessage("Contact blocked");
            return new ResponseEntity<>(new ApiResponse(true,blockContactResponse), HttpStatus.ACCEPTED);
        } catch (Exception e){
            blockContactResponse.setMessage(e.getMessage());
            return new ResponseEntity<>(new ApiResponse(false,blockContactResponse), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/deleteAllContact")
    public ResponseEntity<?> deleteAllContact(){
        DeleteContactResponse deleteContactResponse = new DeleteContactResponse();
        try {
            contactMangerService.deleteAllMessage();
            deleteContactResponse.setMessage("Contact deleted");
            return new ResponseEntity<>(new ApiResponse(true,deleteContactResponse), HttpStatus.ACCEPTED);
        } catch (Exception exception){
            deleteContactResponse.setMessage(exception.getMessage());
            return new ResponseEntity<>(new ApiResponse(false,deleteContactResponse), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/dialNumber")
    public ResponseEntity<?> dialNumber(@RequestBody String number){
        DialNumberResponse dialNumberResponse = new DialNumberResponse();

        try {
            contactMangerService.dialNumber(number);
            dialNumberResponse.setMessage("Dialing....");
            return new ResponseEntity<>(new ApiResponse(true,dialNumberResponse), HttpStatus.ACCEPTED);
        } catch (Exception exception){
            dialNumberResponse.setMessage(exception.getMessage());
            return new ResponseEntity<>(new ApiResponse(false,dialNumberResponse), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/callHistory")
    public ResponseEntity<?> callHistory(){
        CallHistoryResponse callHistoryResponse = new CallHistoryResponse();

        try {
            List<CallHistoryEntry> callHistoryEntries = contactMangerService.callHistory();
            callHistoryResponse.setMessage(callHistoryEntries.toString());
            return new ResponseEntity<>(new ApiResponse(true,callHistoryResponse), HttpStatus.ACCEPTED);
        }
        catch (Exception exception){
            callHistoryResponse.setMessage(exception.getMessage());
            return new ResponseEntity<>(new ApiResponse(false,callHistoryResponse), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/composeMessage")
    public ResponseEntity<?> composeMessage(ComposeMessageRequest composeMessageRequest){
        ComposeMessageResponse composeMessageResponse = new ComposeMessageResponse();

        try {
            contactMangerService.composeMessage(composeMessageRequest);
            composeMessageResponse.setMessage("message composed");
            return new ResponseEntity<>(new ApiResponse(true,composeMessageResponse), HttpStatus.ACCEPTED);

        } catch (Exception exception){
            composeMessageResponse.setMessage(exception.getMessage());
            return new ResponseEntity<>(new ApiResponse(false,composeMessageResponse), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/deleteMessage")
    public ResponseEntity<?> deleteMessage(DeleteMessageRequest deleteMessageRequest){
        DeleteMessageResponse deleteMessageResponse = new DeleteMessageResponse();

        try {
            contactMangerService.deleteMessage(deleteMessageRequest);
            deleteMessageResponse.setMessage("message deleted");
            return new ResponseEntity<>(new ApiResponse(true,deleteMessageResponse), HttpStatus.ACCEPTED);
        } catch (Exception exception){
            deleteMessageResponse.setMessage(exception.getMessage());
            return new ResponseEntity<>(new ApiResponse(false,deleteMessageResponse), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/findAllMessage")
    public  ResponseEntity<?> findMessage(){
        FindMessageResponse findMessageResponse = new FindMessageResponse();

        try {
            List<UserMessage> messageList = contactMangerService.findAllMessages();
            findMessageResponse.setMessage(messageList.toString());
            return new ResponseEntity<>(new ApiResponse(true,findMessageResponse), HttpStatus.ACCEPTED);
        } catch (Exception exception){
            findMessageResponse.setMessage(exception.getMessage());
            return new ResponseEntity<>(new ApiResponse(false,findMessageResponse), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/deleteAllMessage")
    public ResponseEntity<?> deleteAllMessage(){
        DeleteMessageResponse deleteMessageResponse = new DeleteMessageResponse();

        try {
            contactMangerService.deleteAllMessage();
            deleteMessageResponse.setMessage("All messages has been deleted");
            return new ResponseEntity<>(new ApiResponse(true,deleteMessageResponse), HttpStatus.ACCEPTED);
        } catch (Exception exception){
            deleteMessageResponse.setMessage(exception.getMessage());
            return new ResponseEntity<>(new ApiResponse(false,deleteMessageResponse), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/findMessage/{number}")
    public ResponseEntity<?> findMessage(@PathVariable("number") String number){
        FindMessageResponse findMessageResponse = new FindMessageResponse();

        try {
           UserMessage userMessage = contactMangerService.findMessage(number);
            findMessageResponse.setMessage(userMessage+"");
            return new ResponseEntity<>(new ApiResponse(true,findMessageResponse), HttpStatus.ACCEPTED);
        }catch (Exception exception){
            findMessageResponse.setMessage(exception.getMessage());
            return new ResponseEntity<>(new ApiResponse(false,findMessageResponse), HttpStatus.BAD_REQUEST);
        }
    }

}
