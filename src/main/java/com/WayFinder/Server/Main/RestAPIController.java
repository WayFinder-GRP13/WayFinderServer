package com.WayFinder.Server.Main;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@RestController
public class RestAPIController {
    private List<UserInformation> myUserInformation = new ArrayList();
    private final AtomicLong counter = new AtomicLong();

    public RestAPIController(){
        myUserInformation.add(new UserInformation(counter.incrementAndGet(), "new user"));
    }
    @GetMapping(value = "/")
    public ResponseEntity index() {
        return ResponseEntity.ok(myUserInformation);
    }
    @GetMapping(value = "/bucket")
    public ResponseEntity getUser(@RequestParam(value="id") Long id) {
        UserInformation itemToReturn = null;
        for(UserInformation user : myUserInformation){
            if(user.getId() == id)
                itemToReturn = user;
        }
        return ResponseEntity.ok(itemToReturn);
    }
    @PostMapping(value = "/")
    public ResponseEntity addToUserList(@RequestParam(value="name") String name) {
        myUserInformation.add(new UserInformation(counter.incrementAndGet(), name));
        return ResponseEntity.ok(myUserInformation);
    }
    @PutMapping(value = "/")
    public ResponseEntity updateUserList(@RequestParam(value="name") String name, @RequestParam(value="id") Long id) {
        myUserInformation.forEach(userInformation ->  {
            if(userInformation.getId() == id){
                userInformation.setName(name);
            }
        });
        return ResponseEntity.ok(myUserInformation);
    }
    @DeleteMapping(value = "/")
    public ResponseEntity removeUserList(@RequestParam(value="id") Long id) {
        UserInformation itemToRemove = null;
        for(UserInformation user : myUserInformation){
            if(user.getId() == id)
                itemToRemove = user;
        }
        myUserInformation.remove(itemToRemove);
        return ResponseEntity.ok(myUserInformation);
    }
}
