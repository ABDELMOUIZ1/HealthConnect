package com.tech.healthconnect.config;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import jakarta.xml.bind.DatatypeConverter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

    public JwtAuthenticationFilter() {
        super("/api/**"); // Endpoint où ce filtre doit être appliqué (votre API)
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException, IOException, ServletException {
        // Extraire le JWT du header Authorization
        String token = extractTokenFromHeader(request);

        // Valider et décoder le JWT pour obtenir les informations de l'utilisateur
        // Implémentez cette logique dans votre JwtUtil ou JwtTokenProvider
        // Exemple : UserDetails userDetails = jwtUtil.extractUserDetails(token);

        // Créer une instance d'Authentication contenant les informations de l'utilisateur
        // Exemple : return getAuthenticationManager().authenticate(new JwtAuthenticationToken(userDetails));

        return null; // Remplacez cela par votre logique réelle
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                                            FilterChain chain, Authentication authResult)
            throws IOException, ServletException {
        // Si l'authentification est réussie, ajoutez l'utilisateur authentifié à SecurityContext
        // Exemple : SecurityContextHolder.getContext().setAuthentication(authResult);

        chain.doFilter(request, response); // Poursuivre la chaîne de filtres
    }

    private String extractTokenFromHeader(HttpServletRequest request) {
        String header = request.getHeader("Authorization");
        if (header != null && header.startsWith("Bearer ")) {
            return header.substring(7); // Retourner le JWT sans le préfixe "Bearer "
        }
        return null;
    }
}