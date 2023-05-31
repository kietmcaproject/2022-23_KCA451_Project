package com.spring.service;

import com.spring.entities.Admin;
import com.spring.repo.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService implements AdminServiceI{
     String email="admin@gmail.com";

    @Autowired
    private AdminRepository adminRepository;

    @Override
    public Admin findByEmail(String email) {
        Admin admin=this.adminRepository.findByEmail(email);
        return admin;
    }



    @Override
    public Admin addAdmin(Admin admin) {
        return adminRepository.save(admin);
    }
}
