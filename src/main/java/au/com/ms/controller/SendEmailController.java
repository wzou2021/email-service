package au.com.ms.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import au.com.ms.exception.SendEmailResponse;
import au.com.ms.model.EmailRequest;
import au.com.ms.service.SendEmailService;

@RestController
@RequestMapping("api/v1")
public class SendEmailController {
	
	@Autowired
	private SendEmailService service;
	
	@PostMapping(value = "sendEmail")
	public ResponseEntity<SendEmailResponse> sendEmail(@Valid @RequestBody EmailRequest request) {
		return  service.sendEmail(request);
	}
}
