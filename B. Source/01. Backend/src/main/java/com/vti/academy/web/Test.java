package com.vti.academy.web;

import java.io.IOException;
import java.security.GeneralSecurityException;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class Test {

	public static void main(String[] args) throws IOException, GeneralSecurityException {
		// TODO Auto-generated method stub
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
//		System.out.println(encoder.matches("phan", "$2a$10$pRoYL8N3KvNxgAJ7z5tEoecBZ1JHb5ctYlH3rRmL5WKJBOfrjmnV2"));
		System.out.println(encoder.encode("phan"));
	}

}