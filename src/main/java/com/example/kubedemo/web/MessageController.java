package com.example.kubedemo.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.kubedemo.service.InstanceInformationService;

@RestController("/")
public class MessageController {

	private InstanceInformationService instanceService;

	@Autowired
	public MessageController(InstanceInformationService instanceService) {
		this.instanceService = instanceService;
	}

	@GetMapping(path = { "/", "/message" })
	public String getMessage() {
		return String.format("<h3> Hello from instance : </h3> <p> %s </p>", instanceService.getInstanceInfo());
	}
}
