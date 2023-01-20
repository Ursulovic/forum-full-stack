package com.example.raf_backend.services;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import com.example.raf_backend.entities.User;
import com.example.raf_backend.entities.UserType;
import com.example.raf_backend.filters.AuthenticationFilter;
import com.example.raf_backend.repositories.user.UserRepository;
import com.example.raf_backend.requests.EditStatusRequest;
import com.example.raf_backend.requests.EditUserRequest;
import com.example.raf_backend.resources.Secured;
import org.apache.commons.codec.digest.DigestUtils;

import javax.inject.Inject;
import javax.validation.Valid;
import java.util.Date;
import java.util.List;

public class UserService {

    @Inject
    private UserRepository userRepository;


    public String login(String email, String password) {

        String hashedPassword = DigestUtils.sha256Hex(password);

        User user = this.userRepository.findUser(email.trim());

        if (user == null || !user.getPassword().equals(hashedPassword)) {
            return null;
        }

        Date issuedAt = new Date();
        Date expiresAt = new Date(issuedAt.getTime() + 24 * 60 * 60 * 1000); // One day

        Algorithm algorithm = Algorithm.HMAC256("secret");

        return JWT.create()
                .withIssuedAt(issuedAt)
                .withExpiresAt(expiresAt)
                .withSubject(user.getEmail())
                .withClaim("type", user.getType())
                .withClaim("name", user.getName())
                .sign(algorithm);

    }

    public List<User> fetchUsers(int page) {
        return this.userRepository.fetchUsers(page);
    }


    public int getUserNumber() {return this.userRepository.getUserNumber();}



    public void isSigned(String token) {
        Algorithm algorithm = Algorithm.HMAC256("secret");
        JWTVerifier verifier = JWT.require(algorithm)
                .build();
        DecodedJWT jwt = verifier.verify(token);

//        String email = jwt.getSubject();
//        String type = jwt.getClaim("type").asString();
//
//        User user = this.userRepository.findUser(email);
//
//        if (user == null){
//            return false;
//        }
//
//        return true;
    }

    public UserType isAuthorised(String token) {

        Algorithm algorithm = Algorithm.HMAC256("secret");
        JWTVerifier verifier = JWT.require(algorithm)
                .build();
        DecodedJWT jwt = verifier.verify(token);

        String email = jwt.getSubject();
        String type = jwt.getClaim("type").asString();

        User user = this.userRepository.findUser(email);

        return UserType.valueOf(user.getType());
    }



    @Secured({UserType.ADMIN})
    public boolean addUser(User user) {

        String hashedPassword = DigestUtils.sha256Hex(user.getPassword());
        System.out.println(hashedPassword);

        user.setPassword(hashedPassword);

        return this.userRepository.addUser(user);

    }


    @Secured({UserType.ADMIN})
    public boolean editUser(@Valid EditUserRequest request) {
        return this.userRepository.editUser(request);
    }

    @Secured({UserType.ADMIN})
    public boolean changeStatus(@Valid EditStatusRequest request) {
        return false;
    }

}
