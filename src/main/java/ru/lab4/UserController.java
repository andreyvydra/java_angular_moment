package ru.lab4;

import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.core.MediaType;
import jakarta.xml.bind.DatatypeConverter;
import ru.lab4.model.User;
import ru.lab4.repository.UserRepository;

import jakarta.inject.Inject;
import jakarta.ws.rs.core.Response;
import ru.lab4.request.UserRequest;
import ru.lab4.response.TokenResponse;
import ru.lab4.response.UserResponse;


@Path("/user")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UserController {

    @Inject
    private UserRepository repository;

    @POST
    @Path("/register")
    public TokenResponse register(@Valid UserRequest requestData) {
        String username = requestData.getUsername();
        String password = requestData.getPassword();

        if (repository.findByUsername(username) != null)
            throw new WebApplicationException("Пользователь с таким именем уже существует", Response.Status.CONFLICT);

        User user = User.builder()
                .username(username)
                .password(password).build();
        repository.save(user);

        String usernameAndPassword = username + ":" + password;
        String token = DatatypeConverter.printBase64Binary(usernameAndPassword.getBytes());

        return new TokenResponse(token);
    }

    @POST
    @Path("/login")
    public TokenResponse login(@Valid UserRequest requestData) {
        User user = repository.findByUsername(requestData.getUsername());
        if (user == null || !user.checkPassword(requestData.getPassword()))
            throw new WebApplicationException("Invalid credentials", Response.Status.UNAUTHORIZED);

        String usernameAndPassword = requestData.getUsername() + ":" + requestData.getPassword();
        String token = DatatypeConverter.printBase64Binary(usernameAndPassword.getBytes());

        return new TokenResponse(token);
    }

}