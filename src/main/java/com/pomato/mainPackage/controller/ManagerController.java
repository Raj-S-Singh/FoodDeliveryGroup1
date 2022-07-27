package com.pomato.mainPackage.controller;

import com.pomato.mainPackage.model.*;
import com.pomato.mainPackage.services.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class ManagerController {

    @Autowired
    ManagerService managerService;

    @PostMapping(value = "/managersignup", consumes = "application/json" , produces = "application/json")
    public ResponseEntity<ManagerSignupResponse> managerSignUp(@RequestBody ManagerSignupRequest managerSignupRequest){

        ManagerSignupResponse managerSignupResponse = managerService.registerManager(managerSignupRequest);

        if(managerSignupResponse.isStatus()){
            return new ResponseEntity<ManagerSignupResponse>(managerSignupResponse, HttpStatus.OK );
        }
        else{
            return new ResponseEntity<ManagerSignupResponse>(managerSignupResponse, HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping(value = "/deleteitem/{restaurantId}/{itemId}", consumes = "application/json", produces = "application/json")
    public ResponseEntity<DeleteItemResponse> deleteItemResponseResponse(@PathVariable("restaurantId") int restaurantId, @PathVariable("itemId") int itemId, @RequestHeader(name = "jwtToken") String jwtToken, @RequestBody DeleteItemRequest deleteItemRequest){


        DeleteItemResponse deleteItemResponse = managerService.deleteItem(itemId, deleteItemRequest.getUserId(), jwtToken);
        if(deleteItemResponse.isStatus()){
            return new ResponseEntity<>(deleteItemResponse, HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(deleteItemResponse, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping(value = "/updateitem/{restaurantId}/{itemId}", produces = "application/json")
    public ResponseEntity<UpdateItemResponse> updateItem(@RequestBody UpdateItemRequest item, @PathVariable int restaurantID,
                                                         @PathVariable int itemId, @RequestHeader("jwtToken") String jwtToken){

        item.setItemId(itemId);
        item.setRestaurantId(restaurantID);
        UpdateItemResponse updateItemResponse = managerService.update(item, jwtToken);

        if(updateItemResponse.isStatus()){
            return new ResponseEntity<UpdateItemResponse>(updateItemResponse, HttpStatus.OK);
        }
        else{
            return new ResponseEntity<UpdateItemResponse>(updateItemResponse, HttpStatus.BAD_REQUEST);
        }


    }
}
