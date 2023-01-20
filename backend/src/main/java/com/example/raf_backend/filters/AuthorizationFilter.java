package com.example.raf_backend.filters;

import com.example.raf_backend.entities.UserType;
import com.example.raf_backend.resources.Secured;
import com.example.raf_backend.services.UserService;

import javax.annotation.Priority;
import javax.inject.Inject;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Secured
@Provider
@Priority(Priorities.AUTHORIZATION)
public class AuthorizationFilter implements ContainerRequestFilter {

    @Inject
    private UserService userService;

    @Context
    private ResourceInfo resourceInfo;



    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {

        // Get the resource class which matches with the requested URL
        // Extract the roles declared by it

          final String REALM = "example";
          final String AUTHENTICATION_SCHEME = "Bearer";

        // Get the resource class which matches with the requested URL
        // Extract the roles declared by it
        Class<?> resourceClass = resourceInfo.getResourceClass();
        List<UserType> classRoles = extractRoles(resourceClass);

        // Get the resource method which matches with the requested URL
        // Extract the roles declared by it

        Method resourceMethod = resourceInfo.getResourceMethod();
        List<UserType> methodRoles = extractRoles(resourceMethod);

        String authorizationHeader = requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);
        String token = authorizationHeader.substring(AUTHENTICATION_SCHEME.length()).trim();



        try {

            UserType type = this.userService.isAuthorised(token);
            if (!(methodRoles.contains(type) || type.equals(UserType.ADMIN) || classRoles.contains(type))) {
                requestContext.abortWith(Response.status(Response.Status.FORBIDDEN).build());

            }







        } catch (Exception e) {
            requestContext.abortWith(Response.status(Response.Status.FORBIDDEN).build());
        }



    }

    private List<UserType> extractRoles(AnnotatedElement annotatedElement) {
        if (annotatedElement == null) {
            return new ArrayList<UserType>();
        } else {
            Secured secured = annotatedElement.getAnnotation(Secured.class);
            if (secured == null) {
                return new ArrayList<UserType>();
            } else {
                UserType[] allowedRoles = secured.value();
                return Arrays.asList(allowedRoles);
            }
        }
    }
}
