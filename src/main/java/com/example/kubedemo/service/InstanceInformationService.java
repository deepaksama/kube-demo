package com.example.kubedemo.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class InstanceInformationService {

	private static final String ENV_NAME_HOST_NAME = "HOSTNAME";
	private static final String DEFAULT_INSTANCE_GUID = "LOCAL_INSTANCE";

	@Value("${" + ENV_NAME_HOST_NAME + ":" + DEFAULT_INSTANCE_GUID + "}")
	private String INSTANCE_NAME;

	public String getInstanceInfo() {
		if (StringUtils.hasText(INSTANCE_NAME))
			return INSTANCE_NAME;

		return DEFAULT_INSTANCE_GUID;
	}
}
