package com.example.inside_test.controller.impl;

import com.example.inside_test.controller.IMessageController;
import com.example.inside_test.model.dto.CreatedMessages;
import com.example.inside_test.model.dto.NewMessage;
import com.example.inside_test.service.IMessagesService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("messages")
@PreAuthorize("isAuthenticated()")
@RequiredArgsConstructor
public class MessageController implements IMessageController {
    private final IMessagesService IMessagesService;

    @PostMapping("add")
    @Override
    public void addMessage(@NonNull NewMessage newMessage) {
        IMessagesService.add(newMessage);
    }

    @GetMapping("history")
    @Override
    public CreatedMessages getLastCountMessages(@NonNull int count, @NonNull Principal principal) throws Exception {
            return IMessagesService.getLastCountMessages(count, principal);
    }
}
