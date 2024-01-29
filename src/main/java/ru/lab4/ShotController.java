package ru.lab4;

import jakarta.inject.Inject;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.core.MediaType;
import ru.lab4.model.Shot;
import ru.lab4.model.User;
import ru.lab4.repository.ShotRepository;
import ru.lab4.repository.UserRepository;
import ru.lab4.request.ShotRequest;
import ru.lab4.response.ShotResponse;
import ru.lab4.security.Secured;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@Path("/shots")
@Secured
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ShotController {
    @Inject
    private ShotRepository shotRepository;

    @Inject
    private UserRepository userRepository;

    @GET
    public List<ShotResponse> retrieveAll(@Context HttpServletRequest req) {
        String token = req.getHeader(HttpHeaders.AUTHORIZATION).substring(5).trim();
        String usernameAndPassword = new String(Base64.getUrlDecoder().decode(token), StandardCharsets.UTF_8);
        String username = usernameAndPassword.split(":")[0];
        User user = userRepository.findByUsername(username);
        List<Shot> shotsRepo = shotRepository.findByUser(user);

        List<ShotResponse> shots = new ArrayList<>();
        for (Shot shot : shotsRepo) {
            shots.add(new ShotResponse(
                    shot.getId(),
                    shot.getX(),
                    shot.getY(),
                    shot.getR(),
                    shot.getInArea(),
                    shot.getShotTime())
            );
        }
        return shots;
    }

    @POST
    public ShotResponse addShot(@Context HttpServletRequest req, @Valid ShotRequest shotRequest) {
        String token = req.getHeader(HttpHeaders.AUTHORIZATION).substring(5).trim();
        String usernameAndPassword = new String(Base64.getUrlDecoder().decode(token), StandardCharsets.UTF_8);
        String username = usernameAndPassword.split(":")[0];
        User user = userRepository.findByUsername(username);

        Shot shot = Shot.builder().x(shotRequest.getX()).y(shotRequest.getY()).r(shotRequest.getR()).user(user).build();

        shotRepository.save(shot);
        System.out.println(123);

        return new ShotResponse(
                shot.getId(),
                shot.getX(),
                shot.getY(),
                shot.getR(),
                shot.getInArea(),
                shot.getShotTime()
        );
    }

}
