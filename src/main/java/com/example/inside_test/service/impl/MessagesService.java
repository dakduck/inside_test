package com.example.inside_test.service.impl;

import com.example.inside_test.model.dto.CreatedMessages;
import com.example.inside_test.model.dto.NewMessage;
import com.example.inside_test.model.dto.MessageDto;
import com.example.inside_test.model.Message;
import com.example.inside_test.model.auth.User;
import com.example.inside_test.repository.MessageRepository;
import com.example.inside_test.repository.UserRepository;
import com.example.inside_test.service.IMessagesService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.EntityManager;
import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@PreAuthorize("isAuthenticated()")
@RequiredArgsConstructor
@Log4j2
public class MessagesService implements IMessagesService {
    private final MessageRepository messageRepository;
    private final EntityManager entityManager;
    private final UserRepository userRepository;

    @Override
    public void add(@NonNull NewMessage newMessage) {
        Message message = new Message();
        message.setTextMessage(newMessage.getMessage());
        messageRepository.save(message);
    }

    @Override
    public CreatedMessages getLastCountMessages(@NonNull int count, @NonNull Principal principal) throws Exception {
        User user = userRepository.findByUsername(principal.getName()).orElseThrow(() -> {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        });

        List<Message> messages = getMessages(user, count);

        if (messages.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Messages for user '%s' not found", principal.getName()));
        }
        List<MessageDto> lastMessageDtos = messages.stream()
                .map(message -> new MessageDto(message.getTextMessage(), message.getDate()))
                .collect(Collectors.toList());
        return new CreatedMessages(lastMessageDtos);
    }

    //        На стороне базы выбираем записи конкретного пользователя, сортируем и забираем n последних записей.
    private List<Message> getMessages(@NonNull User user, int count) {
       return entityManager
                .createQuery("SELECT m FROM Message m WHERE m.user = :user ORDER BY m.date DESC")
                .setParameter("user", user)
                .setMaxResults(count)
                .getResultList();

    }
}
