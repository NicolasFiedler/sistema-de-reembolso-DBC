package dbc.vemser.refoundapi.controller;

import dbc.vemser.refoundapi.dataTransfer.AuthDTO;
import dbc.vemser.refoundapi.dataTransfer.LogedDTO;
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
        System.out.println("mais antes ainda");
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                new UsernamePasswordAuthenticationToken(
                        authDTO.getLogin(),
                        authDTO.getPassword()
                );
        System.out.println("antes");
        Authentication authenticate = authenticationManager.authenticate(usernamePasswordAuthenticationToken);
        System.out.println("chegour");
        return tokenService.getToken(authenticate);
    }
}
