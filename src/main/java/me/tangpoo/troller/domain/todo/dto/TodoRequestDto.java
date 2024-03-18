package me.tangpoo.troller.domain.todo.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class TodoRequestDto {
    private String todoName;

    public TodoRequestDto(String todoName) {
        this.todoName = todoName;
    }
}
