package summer2021.mathelementaryschool.restAPI.menu;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import summer2021.mathelementaryschool.datebase.repository.ResolvedProblemRepository;
import summer2021.mathelementaryschool.restAPI.DefaultResponse;
import summer2021.mathelementaryschool.restAPI.authorizate.IAuthenticateJWT;
import summer2021.mathelementaryschool.restAPI.authorizate.JWTPayload;

import javax.servlet.http.HttpServletRequest;

@RestController
@AllArgsConstructor
public class Menu {

    final IAuthenticateJWT          iAuthenticateJWT;
    final ResolvedProblemRepository resolvedProblemRepository;

    @GetMapping("/menu/countRP")
    public DefaultResponse getCountResolvedProblems(HttpServletRequest request){
        JWTPayload jwtPayload   = iAuthenticateJWT.checkAuthentication(request);
        if(jwtPayload == null)
            return new DefaultResponse("jwtfail", "401");

        Integer count           = resolvedProblemRepository.countResolvedProblemById(jwtPayload.getId_user());
        if (count == null){
            return new DefaultResponse("error", "500");
        }

        return new DefaultResponse(count.toString(), "200");
    }


    public String openMenu(){
        return null;
    }

}
