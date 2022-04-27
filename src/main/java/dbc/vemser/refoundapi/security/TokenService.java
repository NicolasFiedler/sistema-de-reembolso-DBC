package dbc.vemser.refoundapi.security;

import dbc.vemser.refoundapi.dataTransfer.LogedDTO;
import dbc.vemser.refoundapi.entity.UserEntity;
import dbc.vemser.refoundapi.repository.UserRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class TokenService {
    private static final String PREFIX = "Bearer ";
    private static final String HEADER_AUTHORIZATION = "Authorization";
    private static final String CHAVE_REGRAS = "REGRAS";

    private final UserRepository userRepository;

    @Value("${jwt.expiration}")
    private String expiration;

    @Value("${jwt.secret}")
    private String secret;

    public LogedDTO getToken(Authentication authentication) {
        UserEntity user = (UserEntity) authentication.getPrincipal();

        Date now = new Date();
        Date exp = new Date(now.getTime() + Long.parseLong(expiration));

        List<String> regras = user.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        String token = Jwts.builder()
                .setIssuer("sistema-de-reembolso")
                .setSubject(user.getIdUser().toString())
                .claim(CHAVE_REGRAS, regras)
                .setIssuedAt(now)
                .setExpiration(exp)
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();

        if (userRepository.findById(user.getIdUser()).isPresent()) {
            user = userRepository.findById(user.getIdUser()).get();
        }

        LogedDTO logedDTO = LogedDTO.builder()
                .id(user.getIdUser())
                .name(user.getName())
                .email(user.getEmail())
                .roles(user.getRoleEntities())
                .token(PREFIX + token)
                .build();

        if (user.getImage() != null) {
            logedDTO.setImage(Base64.getEncoder().encodeToString(user.getImage()));
        }

        return logedDTO;
    }

    public Authentication getAuthentication(HttpServletRequest request) {
        String tokenBearer = request.getHeader(HEADER_AUTHORIZATION); // Bearer hfUIfs

        if (tokenBearer != null) {
            String token = tokenBearer.replace(PREFIX, "");
            Claims body = Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();
            String user = body.getSubject();
            if (user != null) {
                List<String> roleList = body.get(CHAVE_REGRAS, List.class);
                List<SimpleGrantedAuthority> roles = roleList.stream()
                        .map(SimpleGrantedAuthority::new)
                        .collect(Collectors.toList());
                return new UsernamePasswordAuthenticationToken(user, null, roles);
            }
        }
        return null;
    }
}