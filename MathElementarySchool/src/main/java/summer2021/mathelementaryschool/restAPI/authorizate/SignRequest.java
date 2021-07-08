package summer2021.mathelementaryschool.restAPI.authorizate;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class SignRequest {
    @NonNull private String email;
    @NonNull private String password;
}
