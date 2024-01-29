package ru.lab4;

import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerResponseContext;
import jakarta.ws.rs.container.ContainerResponseFilter;
import jakarta.ws.rs.container.PreMatching;
import jakarta.ws.rs.ext.Provider;

import java.io.IOException;

@Provider
@PreMatching
public class CorsConfig implements ContainerResponseFilter {
    @Override
    public void filter(ContainerRequestContext requestCtx, ContainerResponseContext responseCtx ) throws IOException {
        responseCtx.getHeaders().add("Access-Control-Allow-Origin", "*");
        responseCtx.getHeaders().add("Access-Control-Allow-Credentials", "true");
        responseCtx.getHeaders().add("Access-Control-Allow-Headers", "*");
        responseCtx.getHeaders().add("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD");
        responseCtx.getHeaders().add("Access-Control-Max-Age", "100000");
    }
}
