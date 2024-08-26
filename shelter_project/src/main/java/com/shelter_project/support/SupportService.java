package com.shelter_project.support;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class SupportService {
	
	@Value("${imp.api.Key}")
	private String impApiKey;
	
	@Value("${imp.api.Secret}")
	private String impApiSecret;
	
}
