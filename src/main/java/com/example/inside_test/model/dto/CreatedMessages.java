package com.example.inside_test.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
@AllArgsConstructor
public class CreatedMessages {
    private List<MessageDto> lastMessages;

}
