package summer2021.mathelementaryschool.restAPI;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class SignUpRequest {

    @NonNull private String email;
    @NonNull private String password;
}
