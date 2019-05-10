package jwt;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

/**
 * @ClassName: CreatJWT
 * @Author: zhangyuyang
 * @Time : 2019/4/25 -下午4:18
 * @Version:1.0
 */
public class CreatJWT {
    /**
     * 生成token (jwt)
     */
    public static void main(String[] args) {
        //Jwts.builder() 创建
        // .setId("123456789") 设置id
        JwtBuilder huahuaCommunity = Jwts.builder().setId("123456789")
                .setSubject("1608v admin") //使用者
                //.setExpiration(new Date(new Date().getTime()+60000))
                .setIssuedAt(new Date()) //jwt的签发时间
                .claim("roles","admin,teacher")
                .claim("telphone","18500348106")
                .signWith(SignatureAlgorithm.HS256, "huahuaCommunity"); //加密的算法HS256 加密的签名是：huahuaCommunity
        System.out.println(huahuaCommunity.compact());
        //通过加盐的规则  huahuaCommunity.compact() 获取token令牌

    }
}
