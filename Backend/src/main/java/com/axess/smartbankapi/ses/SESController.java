package com.axess.smartbankapi.ses;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ses")
public class SESController {

    @Autowired
    private EmailService mailService;

    @PostMapping("/send")
    public String sendEmail(@RequestBody Email email) {
        return mailService.sendEmail(email);
    }
}