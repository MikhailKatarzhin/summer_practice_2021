package summer2021.mathelementaryschool.restAPI;

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
        for (Cookie cookie : request.getCookies()) {
            if (cookie.getName().equals(authCookieName)) {
                return JWTPayload.valueOf(cookie.getValue(), authenticateSecret);
            }
        }
        return null;
    }

    @PostMapping("/register")
    public SignUpResponse sign_up(@RequestBody(required = false) SignUpRequest request){

        String  salt            = RandomString.make();
        String  password_hash   = DigestUtils.sha1Hex(String.format("%s%s", request.getPassword(), salt));

        try{
            Long id_salt = saltRepository.save(new Salt(salt)).getId();
            try {
                userRepository.save(new User(request.getEmail(), password_hash, saltRepository.findById(id_salt).orElseThrow()));
            } catch (Exception exception) {
                exception.printStackTrace();
                saltRepository.deleteById(id_salt);
                return new SignUpResponse("This user already exists", "400");
            }
        }catch (Exception exception){
            return new SignUpResponse("Salt creating fault", "500");
        }
        return new SignUpResponse("Completed", "200");
    }

    @PostMapping("/authenticate")
    public SignInResponse sign_in(@RequestBody SignInRequest request, HttpServletResponse response){
        User    user;
        try {
            user                = userRepository.findUserByEmail(request.getEmail());
            if(user == null) throw new NullPointerException();
        }catch (Exception exception){
            exception.printStackTrace();
            return new SignInResponse("User does not exist", "404");
        }

        Salt    salt            = saltRepository.findById(user.getSalt().getId()).orElse(null);
        if(salt==null){
            return new SignInResponse("Validation is failed", "500");
        }

        String  password_hash   = DigestUtils.sha1Hex(String.format("%s%s", request.getPassword(), salt.getSalt()));
        if(!password_hash.equals(user.getPassword())){
            return new SignInResponse("Invalid password", "400");
        }

        String jwtString        = new JWTPayload(user.getId(), user.getEmail()).toJWT(authenticateSecret);
        if (jwtString == null) {
            return new SignInResponse("Can not to authorise", "500");
        }

        response.addCookie(new Cookie(authCookieName, jwtString));

        return new SignInResponse("Successful signed in", "200");
    }

}
