package com.WayFinder.Server.Main;

import com.google.maps.model.LatLng;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@RestController
public class RestAPIController {
    private List<RestAPIInformation> myRestAPIInformation = new ArrayList();
    private final AtomicLong counter = new AtomicLong();

    public RestAPIController(){
        //note not sure which one to do


    }
    //Read
    @GetMapping(value = "/")
    public ResponseEntity index(@RequestParam(value="name") String name) {
        RestAPIInformation itemToReturn = null;
        System.out.println(name);
        for (RestAPIInformation object : myRestAPIInformation){
            System.out.println(object.getName());
            if(object.getName().contains(name)){
                itemToReturn = object;
            }
        }
        return ResponseEntity.ok(itemToReturn);
    }
    @GetMapping(value = "/bucket")
    public ResponseEntity getUser(@RequestParam(value="id") Long id) {
        RestAPIInformation itemToReturn = null;
        for(RestAPIInformation user : myRestAPIInformation){
            if(user.getId() == id)
                itemToReturn = user;
        }
        return ResponseEntity.ok(itemToReturn);
    }

    @PostMapping(value = "/")
    public ResponseEntity addToUserList(@RequestBody RestAPIInformation request) {
        if(request!=null){
            System.out.println("Success");
            myRestAPIInformation.add(request);
        }

        return ResponseEntity.ok(myRestAPIInformation);
    }
    //Update/Replace
    @PutMapping(value = "/")
    public ResponseEntity updateUserList(@RequestParam(value="name") String name, @RequestParam(value="id") Long id) {
        myRestAPIInformation.forEach(RestAPIInformation ->  {
            if(RestAPIInformation.getId() == id){
                RestAPIInformation.setName(name);
            }
        });
        return ResponseEntity.ok(myRestAPIInformation);
    }
    @DeleteMapping(value = "/")
    public ResponseEntity removeUserList(@RequestParam(value="id") Long id) {
        RestAPIInformation itemToRemove = null;
        for(RestAPIInformation user : myRestAPIInformation){
            if(user.getId() == id)
                itemToRemove = user;
        }
        myRestAPIInformation.remove(itemToRemove);
        return ResponseEntity.ok(myRestAPIInformation);
    }
}
