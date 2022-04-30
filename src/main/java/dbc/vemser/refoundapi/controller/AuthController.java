package dbc.vemser.refoundapi.controller;

import dbc.vemser.refoundapi.dataTransfer.auth.AuthDTO;
import dbc.vemser.refoundapi.dataTransfer.auth.LogedDTO;
import dbc.vemser.refoundapi.security.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Validated
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;
    @PostMapping()
    public LogedDTO auth(@RequestBody @Valid AuthDTO authDTO) {
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                new UsernamePasswordAuthenticationToken(
                        authDTO.getLogin(),
                        authDTO.getPassword()
                );
        Authentication authenticate = authenticationManager.authenticate(usernamePasswordAuthenticationToken);
        return tokenService.getToken(authenticate);
    }
}
