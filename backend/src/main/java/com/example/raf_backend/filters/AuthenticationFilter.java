package com.example.raf_backend.filters;

import com.example.raf_backend.resources.Secured;
import com.example.raf_backend.services.UserService;

import javax.annotation.Priority;
import javax.inject.Inject;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import java.io.IOException;

@Secured
@Provider
@Priority(Priorities.AUTHENTICATION)
public class AuthenticationFilter implements ContainerRequestFilter {

    @Inject
    private UserService userService;

    private static final String REALM = "example";
    private static final String AUTHENTICATION_SCHEME = "Bearer";

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {

        String authorizationHeader = requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);

        // ovo prevarava da li bearer tag  postoji
        if (!isTokenBasedAuthentication(authorizationHeader)) {
            abortWithUnauthorized(requestContext);
            return;
        }

        // ovo vadi samo token
        String token = authorizationHeader.substring(AUTHENTICATION_SCHEME.length()).trim();

        try {

            userService.isSigned(token);


        } catch (Exception e) {
            abortWithUnauthorized(requestContext);
        }





    }







    private boolean isTokenBasedAuthentication(String authorizationHeader) {

        // Check if the Authorization header is valid
        // It must not be null and must be prefixed with "Bearer" plus a whitespace
        // The authentication scheme comparison must be case-insensitive
        return authorizationHeader != null && authorizationHeader.toLowerCase()
                .startsWith(AUTHENTICATION_SCHEME.toLowerCase() + " ");
    }

    private void abortWithUnauthorized(ContainerRequestContext requestContext) {

        // Abort the filter chain with a 401 status code response
        // The WWW-Authenticate header is sent along with the response
        requestContext.abortWith(
                Response.status(Response.Status.UNAUTHORIZED)
                        .header(HttpHeaders.WWW_AUTHENTICATE,
                                AUTHENTICATION_SCHEME + " realm=\"" + REALM + "\"")
                        .build());
    }
}
