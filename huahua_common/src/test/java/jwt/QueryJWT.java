package jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @ClassName: QueryJWT
 * @Author: zhangyuyang
 * @Time : 2019/4/25 -下午4:28
 * @Version:1.0
 */
public class QueryJWT {
    public static void main(String[] args) {
        //token 令牌
     String token ="eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxMjM0NTY3ODkiLCJzdWIiOiIxNjA4diBhZG1pbiIsImlhdCI6MTU1NjE4MjM3Niwicm9sZXMiOiJhZG1pbix0ZWFjaGVyIiwidGVscGhvbmUiOiIxODUwMDM0ODEwNiJ9.1xuf6hh2Uw4_-I9c-WmpRMcd1RnEo8WKXo1HtNXdXSk";
     try {
         Claims body = Jwts.parser().setSigningKey("huahuaCommunity")
                 .parseClaimsJws(token).getBody();
         System.out.println("用户的id: "+body.getId());
         System.out.println("用户角色："+body.get("roles"));
         System.out.println("用户手机号："+body.get("telphone"));
         System.out.println("用户的名称："+body.getSubject());
         System.out.println("系统时间："+new Date());
         SimpleDateFormat sdf  = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
         System.out.println("过期时间："+sdf.format(body.getExpiration()));
         System.out.println("签发时间："+sdf.format(body.getIssuedAt()));
     }catch (Exception e){
         System.out.println("签名认证失效，请重新获取");
     }


    }

}
