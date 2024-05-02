package com.example.demo.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.session.SessionInformation;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1")
public class SeguridadController {
	
	@Autowired
	private SessionRegistry sessionR;

	@GetMapping("/index")
	public String index() {
		
		return "Pag Segura";
	}
	
	@GetMapping("/index2")
	public String index2() {
		
		return "Pag.NO Segura :( ";
	}
	
	@GetMapping("/session")
	public ResponseEntity<?> getDetails(){
		
		String sessionId = "";
		User userObj = null;
		
		List<Object> sessions = sessionR.getAllPrincipals();
		
		for (Object session : sessions) {
			if(session instanceof User) {
				userObj = (User) session;
			}
			List<SessionInformation> sessionInformations = sessionR.getAllSessions(session, false);
			
			for (SessionInformation sessionIn : sessionInformations) {
				sessionId = sessionIn.getSessionId();
			}
		}
		
		Map<String, Object> response = new HashMap<>();
		response.put("response", "xD");
		response.put("sessionId", sessionId);
		response.put("sessionUser", userObj);
		return ResponseEntity.ok(response);
	}
}
