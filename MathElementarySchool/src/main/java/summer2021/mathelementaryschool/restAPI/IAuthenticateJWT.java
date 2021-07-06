package summer2021.mathelementaryschool.restAPI;

import javax.servlet.http.HttpServletRequest;

public interface IAuthenticateJWT {
    JWTPayload checkAuthentication(HttpServletRequest request);
}
