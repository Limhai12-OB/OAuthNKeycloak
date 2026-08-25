package com.genuine.oauth;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.Map;

@RestController
public class MainController {

    @RequestMapping("/")
    public String home() {
        return "Welcome!";
    }

//    @RequestMapping("/user")
//    public Principal user(Principal user) {
//        return user;
//    }

    @GetMapping("/user")
    public Map<String, Object> user(@AuthenticationPrincipal OidcUser user){
        return user.getClaims();
    }

    @GetMapping("/token-info")
    public Map<String, Object> tokenInfo(@RegisteredOAuth2AuthorizedClient("keycloak")OAuth2AuthorizedClient client){

        return Map.of(
                "tokenType", client.getAccessToken().getTokenType().getValue(),
                "expiresAt", client.getAccessToken().getExpiresAt(),
                "scopes", client.getAccessToken().getScopes(),
                "token", client.getAccessToken()
        );

    }

}
