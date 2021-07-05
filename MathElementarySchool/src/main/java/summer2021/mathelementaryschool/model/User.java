package summer2021.mathelementaryschool.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.lang.NonNull;

@Getter
@AllArgsConstructor
public class User {

    private final int               id;
    private final @NonNull String   email;
    private final @NonNull String   password;

    public String getLogin(){
        return email.split("@")[0];
    }
}
