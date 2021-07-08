package summer2021.mathelementaryschool.restAPI.authorizate;

import javax.servlet.http.HttpServletRequest;

public interface IAuthenticateJWT {
    JWTPayload checkAuthentication(HttpServletRequest request);
}
