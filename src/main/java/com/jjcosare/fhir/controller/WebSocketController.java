package com.jjcosare.fhir.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageExceptionHandler;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;

/**
 * Created by jjcosare on 3/6/19.
 */
@Controller
public class WebSocketController {

    @Autowired
    private SimpMessageSendingOperations webSocketTemplate;

    @MessageExceptionHandler
    public String handleException(Throwable exception) {
        webSocketTemplate.convertAndSend("/errors", exception.getMessage());
        return exception.getMessage();
    }

}
