package com.bitsun.provider.common.utils;

import com.bitsun.provider.dto.response.UserResDto;
import com.google.common.base.CaseFormat;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * jwt工具类
 *
 */
@ConfigurationProperties(prefix = "hgshop.jwt")
@Component
public class JwtUtils {
    private Logger logger = LoggerFactory.getLogger(getClass());

    private String secret;
    private long expire;
    private String header;

    /**
     * 生成jwt token
     */
    public String generateToken(UserResDto user) {
        Date nowDate = new Date();
        //过期时间
        Date expireDate = new Date(nowDate.getTime() + expire * 1000);

        String token = Jwts.builder().setHeaderParam("type", "JWT").setSubject(user.getId() + "").claim("user",user).setIssuedAt(nowDate)
            .setExpiration(expireDate).signWith(SignatureAlgorithm.HS512, secret).compact();

        return token;
    }

    public Claims getClaimByToken(String token) {
        try {
            return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
        } catch (Exception e) {
            logger.debug("validate is token error ", e);
            return null;
            // throw  new RRException(e.getMessage(),50000,e);

        }
    }

    /**
     * token是否过期
     *
     * @return true：过期
     */
    public boolean isTokenExpired(Date expiration) {
        return expiration.before(new Date());
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public long getExpire() {
        return expire;
    }

    public void setExpire(long expire) {
        this.expire = expire;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public static void main(String[] s) {

        String str = CaseFormat.UPPER_CAMEL.to(CaseFormat.UPPER_UNDERSCORE, "createdBy");
        System.out.println("s=" + str);
    }
}
