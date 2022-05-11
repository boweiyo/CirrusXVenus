package com.axess.smartbankapi.controller;

import com.axess.smartbankapi.ses.Email;
import com.axess.smartbankapi.ses.EmailService;
import com.axess.smartbankapi.sqs.SQSService;
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
public class RedeemptionHistoryController {

	
	@Autowired
	private RedeemptionHistoryService historyService;

	@Autowired
	private SQSService sqsService;

	@Autowired
	private EmailService sesService;
	
	@PostMapping("/")
	public ResponseEntity<?> saveHistory(@RequestBody UserRedeemptionHistoryDto historyDto) throws RecordNotFoundException, RecordExistException, RecordNotCreatedException {

		sqsService.sendMessage("Item " + historyDto.getItemsRedeemed() + " are redeeded with " + historyDto.getTotalPointsRedeemed() + " points.");
		Email email = new Email();
		email.setFrom("admin@cloudtech-training.com");
		email.setTo("boweiiii@gmail.com");
		email.setSubject("Redemption");
		email.setBody("Item " + historyDto.getItemsRedeemed() + " are redeeded with " + historyDto.getTotalPointsRedeemed() + " points.");
		sesService.sendEmail(email);

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
