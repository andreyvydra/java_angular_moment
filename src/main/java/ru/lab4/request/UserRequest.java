package ru.lab4.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Value;

@Value
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserRequest {
    @NotNull(message = "Имя пользователя не может быть пустым")
    @Size(min = 6, max = 24, message = "Имя пользователя должно быть от 6 до 24 символов")
    @Pattern(regexp = "[a-zA-Z0-9]+", message = "Имя пользователя содержит только латинские буквы и цифры")
    String username;
    @NotNull(message = "Пароль не может быть пустым")
    @Size(min = 6, max = 24, message = "Пароль должен быть от 6 до 24 символов")
    String password;

    @JsonCreator
    public UserRequest(@JsonProperty("password") String password,
                @JsonProperty("username") String username) {
        this.username = username;
        this.password = password;
    }
}
