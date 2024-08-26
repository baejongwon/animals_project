package com.shelter_project.support;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.siot.IamportRestClient.IamportClient;
import com.siot.IamportRestClient.exception.IamportResponseException;
import com.siot.IamportRestClient.response.IamportResponse;
import com.siot.IamportRestClient.response.Payment;

@Controller
public class SupportController {

	@Value("${imp.api.Key}")
	private String impApiKey;

	@Value("${imp.api.Secret}")
	private String impApiSecret;

	@GetMapping("support")
	public String support() {
		return "support/support";
	}

	private final IamportClient iamportClient;

	public SupportController(@Value("${imp.api.Key}") String impApiKey,
			@Value("${imp.api.Secret}") String impApiSecret) {
		this.iamportClient = new IamportClient(impApiKey, impApiSecret);
	}

	@ResponseBody
	@RequestMapping("/verify/{imp_uid}")
	public IamportResponse<Payment> paymentByImpUid(@PathVariable("imp_uid") String imp_uid)
			throws IamportResponseException, IOException {
		return iamportClient.paymentByImpUid(imp_uid);
	}
}
