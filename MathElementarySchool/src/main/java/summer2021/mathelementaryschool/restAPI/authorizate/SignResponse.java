package summer2021.mathelementaryschool.restAPI.authorizate;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class SignResponse {
    @NonNull private String status;
    @NonNull private String key;
}