package com.axess.smartbankapi.ses;

import lombok.Data;

@Data
public class Email {

    String from;

    String to;

    String subject;

    String body;
}