package com.spring.service;

import com.spring.entities.Admin;

public interface AdminServiceI {
    Admin findByEmail(String email);

    Admin addAdmin(Admin admin);
}
