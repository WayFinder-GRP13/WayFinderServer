package com.WayFinder.Server.Main;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@RestController
public class RestAPIController {
    private List<RestAPIRequestInformation> myRestAPIRequestInformation = new ArrayList();
    private final AtomicLong counter = new AtomicLong();

    public RestAPIController(){
        //note not sure which one to do


    }
    //Read
    @GetMapping(value = "/")
    public ResponseEntity index(@RequestParam(value="name") String name) {
        RestAPIRequestInformation itemToReturn = null;
        System.out.println(name);
        for (RestAPIRequestInformation object : myRestAPIRequestInformation){
            System.out.println(object.getName());
            if(object.getName().contains(name)){
                itemToReturn = object;
            }
        }
        return ResponseEntity.ok(itemToReturn);
    }
    @GetMapping(value = "/bucket")
    public ResponseEntity getUser(@RequestParam(value="id") int id) {
        RestAPIRequestInformation itemToReturn = null;
        for(RestAPIRequestInformation user : myRestAPIRequestInformation){
            if(user.getId() == id)
                itemToReturn = user;
        }
        return ResponseEntity.ok(itemToReturn);
    }

    @PostMapping(value = "/")
    public ResponseEntity addToUserList(@RequestBody RestAPIRequestInformation request) {
        if(request!=null){
            System.out.println("Success");
            myRestAPIRequestInformation.add(request);
        }

        return ResponseEntity.ok(myRestAPIRequestInformation);
    }
    //Update/Replace
    @PutMapping(value = "/")
    public ResponseEntity updateUserList(@RequestParam(value="name") String name, @RequestParam(value="id") Long id) {
        myRestAPIRequestInformation.forEach(RestAPIRequestInformation ->  {
            if(RestAPIRequestInformation.getId() == id){
                RestAPIRequestInformation.setName(name);
            }
        });
        return ResponseEntity.ok(myRestAPIRequestInformation);
    }
    @DeleteMapping(value = "/")
    public ResponseEntity removeUserList(@RequestParam(value="id") Long id) {
        RestAPIRequestInformation itemToRemove = null;
        for(RestAPIRequestInformation user : myRestAPIRequestInformation){
            if(user.getId() == id)
                itemToRemove = user;
        }
        myRestAPIRequestInformation.remove(itemToRemove);
        return ResponseEntity.ok(myRestAPIRequestInformation);
    }
}