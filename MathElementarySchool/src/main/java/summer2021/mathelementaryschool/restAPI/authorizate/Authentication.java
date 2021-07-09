package summer2021.mathelementaryschool.restAPI.authorizate;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.NonNull;
import net.bytebuddy.utility.RandomString;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import summer2021.mathelementaryschool.datebase.model.Salt;
import summer2021.mathelementaryschool.datebase.model.User;
import summer2021.mathelementaryschool.datebase.repository.SaltRepository;
import summer2021.mathelementaryschool.datebase.repository.UserRepository;
import summer2021.mathelementaryschool.restAPI.DefaultResponse;

@RestController
public class Authentication implements IAuthenticateJWT{

    @NonNull private static final String authCookieName = "authentication_token";

    @Value("${authenticate.secret}")
    private String authenticateSecret;

    final SaltRepository saltRepository;
    final UserRepository userRepository;

    public Authentication(SaltRepository saltRepository, UserRepository userRepository){
        this.saltRepository = saltRepository;
        this.userRepository = userRepository;
    }

    public JWTPayload checkAuthentication(HttpServletRequest request) {
        if (request.getCookies() == null)
            return null;
        for (Cookie cookie : request.getCookies())
            if (cookie.getName().equals(authCookieName))
                return JWTPayload.valueOf(cookie.getValue(), authenticateSecret);
        return null;
    }

    @PostMapping("/register")
    public DefaultResponse sign_up(@RequestBody(required = false) SignRequest request){

        String  salt            = RandomString.make();
        String  password_hash   = DigestUtils.sha1Hex(String.format("%s%s", request.getPassword(), salt));

        try{
            Long id_salt = saltRepository.save(new Salt(salt)).getId();
            try {
                User tempUser = userRepository.findUserByEmail(request.getEmail());
                if(tempUser != null) throw new IllegalArgumentException();
                userRepository.save(new User(request.getEmail(), password_hash, saltRepository.findById(id_salt).orElseThrow()));
            } catch (Exception exception) {
                saltRepository.deleteById(id_salt);
                return new DefaultResponse("This user already exists", "400");
            }
        }catch (Exception exception){
            return new DefaultResponse("Salt creating fault", "500");
        }
        System.out.println("created");
        return new DefaultResponse("Completed", "200");
    }

    @PostMapping("/authenticate")
    public DefaultResponse sign_in(@RequestBody SignRequest request, HttpServletResponse response){
        User    user;
        try {
            user                = userRepository.findUserByEmail(request.getEmail());
            if(user == null) throw new NullPointerException();
        }catch (Exception exception){
            exception.printStackTrace();
            return new DefaultResponse("User does not exist", "400");
        }

        Salt    salt            = saltRepository.findById(user.getSalt().getId()).orElse(null);
        if(salt==null)
            return new DefaultResponse("Validation is failed", "500");

        String  password_hash   = DigestUtils.sha1Hex(String.format("%s%s", request.getPassword(), salt.getSalt()));
        if(!password_hash.equals(user.getPassword()))
            return new DefaultResponse("Invalid password", "400");

        String jwtString        = new JWTPayload(user.getId(), user.getEmail()).toJWT(authenticateSecret);
        if (jwtString == null)
            return new DefaultResponse("Can not to authorise", "500");

        Cookie cookie = new Cookie(authCookieName, jwtString);
        cookie.setPath("/");
        response.addCookie(cookie);

        return new DefaultResponse("Successful signed in", "200");
    }

}
