package com.axess.smartbankapi.controller;

import com.axess.smartbankapi.ses.Email;
import com.axess.smartbankapi.ses.EmailService;
import com.axess.smartbankapi.sqs.SQSService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.axess.smartbankapi.config.restapi.ApiSuccessResponse;
import com.axess.smartbankapi.dto.UserRedeemptionHistoryDto;
import com.axess.smartbankapi.exception.RecordExistException;
import com.axess.smartbankapi.exception.RecordNotCreatedException;
import com.axess.smartbankapi.exception.RecordNotFoundException;
import com.axess.smartbankapi.service.RedeemptionHistoryService;

@RestController
@CrossOrigin
@RequestMapping("/history")
@Slf4j
public class RedeemptionHistoryController {

	
	@Autowired
	private RedeemptionHistoryService historyService;
	@Autowired
	SQSService sqsService;
	@Autowired
	private EmailService mailService;

	@PostMapping("/")
	public ResponseEntity<?> saveHistory(@RequestBody UserRedeemptionHistoryDto historyDto) throws RecordNotFoundException, RecordExistException, RecordNotCreatedException {
		sqsService.sendMessage("Congratulations. You have successfully redeemed your points. Vouchers will be sent within 3-5 working days. Thank you for your trust with Smart Bank.");
		log.info("Message sent to SQS");

		ApiSuccessResponse response = new ApiSuccessResponse();

		response.setMessage("Successfully added to history. ");
		response.setHttpStatus(String.valueOf(HttpStatus.CREATED));
		response.setHttpStatusCode(201);
		response.setBody(historyService.saveHistory(historyDto));
		response.setError(false);
		response.setSuccess(true);

		return ResponseEntity.status(HttpStatus.OK).header("status", String.valueOf(HttpStatus.OK))
				.body(response);


	}

	
	
}
