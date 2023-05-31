package com.spring.controller;

import com.spring.entities.Admin;
import com.spring.service.AdminServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/admin")
public class AdminControlled {
    @Autowired
    private AdminServiceI adminService;

    @PostMapping("/addAdmin")
    public ResponseEntity<Admin> addAdmin(@RequestBody Admin admin){
        Admin admin1=this.adminService.addAdmin(admin);

        return ResponseEntity.status(HttpStatus.CREATED).body(admin1);
    }


    @GetMapping("/findAdmin/{email}")
    public ResponseEntity<Admin> getAdminByEmail(@PathVariable String email) {
        Admin admin=this.adminService.findByEmail(email);
        if(admin==null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.of(Optional.of(admin));
    }

}
