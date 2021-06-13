package top.baozoulolw.webchat.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.tomcat.util.codec.binary.Base64;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Date;
import java.util.Map;

/**
 * jwt工具类
 *
 * @author Baozoulolw
 * @version 1.0
 * @date 2021-06-09 14:35
 */
public class JwtUtils {
    /**
     * 用于生成secret key的stingKey
     */
    private static final String JWT_SECRET = "asdfghjkl1234567890";

    public static final String TOKEN_HEADER = "Authorization";


    /**
     *
     *@author Baozoulolw
     *@description 创建JWT Token
     *@param: claims jwt的所含的用于校验的信息
     *@param: subject 用户唯一标识
     *@param: ttlMillis  过期时间（毫秒）
     *@return java.lang.String
     */
    public static String createJwt(Map<String, Object> claims, String subject, long ttlMillis) throws Exception {
        //指定签名的时候使用的签名算法，也就是header那部分，jjwt已经将这部分内容封装好了。
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        // 生成JWT的时间
        long nowMillis = System.currentTimeMillis();
        // 将long型的时间毫秒转为日期时间
        Date now = new Date(nowMillis);

        // 生成签名的时候使用的秘钥secret
        SecretKey key = generalKey();

        // 下面就是在为payload添加各种标准声明和私有声明了
        // 这里其实就是new一个JwtBuilder，设置jwt的body
        JwtBuilder builder = Jwts.builder()
                // 如果有私有声明，一定要先设置这个自己创建的私有的声明，这个是给builder的claim赋值，一旦写在标准的声明赋值之后，就是覆盖了那些标准的声明的
                .setClaims(claims)
                // iat: jwt的签发时间
                .setIssuedAt(now)
                //一个json格式的字符串作为用户的唯一标志。
                .setSubject(subject)
                // 设置签名使用的签名算法和签名使用的秘钥
                .signWith(signatureAlgorithm, key);

        if (ttlMillis >= 0) {
            long expMillis = nowMillis + ttlMillis;
            Date exp = new Date(expMillis);
            // 设置过期时间戳
            builder.setExpiration(exp);
        }

        return builder.compact();

    }

    /**
     *
     *@author Baozoulolw
     *@description 通过jwt解析得到claims数据描述对象
     *@param: jwt
     *@return io.jsonwebtoken.Claims
     */
    public static Claims parseJwt(String jwt) throws Exception{
        //得到原来的签名秘钥，用其才能解析JWT
        SecretKey key = generalKey();
        //得到 DefaultJwtParser

        return Jwts.parser()
                // 设置签名的秘钥
                .setSigningKey(key)
                // 设置需要解析的jwt
                .parseClaimsJws(jwt).getBody();
    }

    /**
     *
     *@author Baozoulolw
     *@description
     *@param:  生成secret key
     *@return javax.crypto.SecretKey
     */
    private static SecretKey generalKey(){
        // 使用base64解码
        byte[] encodedKey = Base64.decodeBase64(JWT_SECRET);
        // 根据给定的字节数组使用AES加密算法构造一个密钥
        return new SecretKeySpec(encodedKey, 0, encodedKey.length, "AES");
    }

}

