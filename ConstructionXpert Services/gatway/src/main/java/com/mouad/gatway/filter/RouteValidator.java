package com.mouad.gatway.filter;

import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.function.Predicate;

@Component
public class RouteValidator {

    public static final List<String> openApiEndPoint = List.of (
            "/api/auth/register",
            "/api/auth/authenticate",
            "/eureka"
    );

    public Predicate<ServerHttpRequest> isSecred =
            request -> openApiEndPoint
                    .stream()
                    .noneMatch(uri -> request.getURI().getPath().contains(uri));

}