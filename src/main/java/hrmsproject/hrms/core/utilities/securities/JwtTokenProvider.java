package hrmsproject.hrms.core.utilities.securities;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtTokenProvider {
    @Value("${security.jwt.secret.key}")
    private String secretKey;

    @Value("${security.jwt.expiration.time}")
    private Long expires_in;

    public String generateJwtToken(Authentication auth)
    {
        JwtUserDetails jwtUserDetails = (JwtUserDetails) auth.getPrincipal();
        Date expireDate = new Date(new Date().getTime() + expires_in*1000);
        return Jwts.builder().setSubject(jwtUserDetails.getEmail())
                .setIssuedAt(new Date())
                .setExpiration(expireDate)
                .signWith(SignatureAlgorithm.HS512,secretKey).compact();
    }

    public String getEmailFromJwt(String token)
    {
        Claims claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
        return claims.getSubject();
    }

    public boolean validateToken(String token)
    {
        try {
            Claims claims =  Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
            return !isTokenExpired(claims);
        } catch (Exception e)
        {
            return  false;
        }
    }

    private boolean isTokenExpired(Claims claims)
    {
       Date expirationDate = claims.getExpiration();
       return expirationDate.before(new Date());
    }

    public Long getExpirationTime() {
        return expires_in;
    }
}
