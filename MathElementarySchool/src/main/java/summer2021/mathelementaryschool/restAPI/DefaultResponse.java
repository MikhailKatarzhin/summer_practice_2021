package summer2021.mathelementaryschool.restAPI;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class DefaultResponse {
    @NonNull private String status;
    @NonNull private String key;
}