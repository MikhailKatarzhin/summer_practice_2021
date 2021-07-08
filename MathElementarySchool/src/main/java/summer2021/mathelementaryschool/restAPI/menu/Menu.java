package summer2021.mathelementaryschool.restAPI.menu;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import summer2021.mathelementaryschool.datebase.repository.ResolvedProblemRepository;
import summer2021.mathelementaryschool.restAPI.authorizate.IAuthenticateJWT;
import summer2021.mathelementaryschool.restAPI.authorizate.JWTPayload;

import javax.servlet.http.HttpServletRequest;

@RestController
@AllArgsConstructor
public class Menu {

    final IAuthenticateJWT          iAuthenticateJWT;
    final ResolvedProblemRepository resolvedProblemRepository;

    @GetMapping("/menu/countRP")
    public String getCountResolvedProblems(HttpServletRequest request){
        JWTPayload jwtPayload   = iAuthenticateJWT.checkAuthentication(request);
        if(jwtPayload == null)
            return "jwtfail";

        Integer count           = resolvedProblemRepository.countResolvedProblemById(jwtPayload.getId_user());
        if (count == null){
            return "error";
        }

        return count.toString();
    }


    public String openMenu(){
        return null;
    }

}
