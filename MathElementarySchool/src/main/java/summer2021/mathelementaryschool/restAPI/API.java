package summer2021.mathelementaryschool.restAPI;

import net.bytebuddy.utility.RandomString;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import summer2021.mathelementaryschool.datebase.model.Salt;
import summer2021.mathelementaryschool.datebase.model.User;
import summer2021.mathelementaryschool.datebase.repository.SaltCRUDRepository;
import summer2021.mathelementaryschool.datebase.repository.UserCRUDRepository;

@RestController
public class API {

    final SaltCRUDRepository saltCRUDRepository;
    final UserCRUDRepository userCRUDRepository;

    public API(SaltCRUDRepository saltCRUDRepository, UserCRUDRepository userCRUDRepository){
        this.saltCRUDRepository = saltCRUDRepository;
        this.userCRUDRepository = userCRUDRepository;
    }

    @GetMapping("/signup")
    public SignUpResponse signUp(@RequestBody(required = false) SignUpRequest request){

        String  salt            = RandomString.make();
        String  passwordHash    = DigestUtils.sha1Hex(String.format("%s%s", request.getPassword(), salt));
        Integer id_salt         = saltCRUDRepository.save(new Salt(0, salt)).id_salt;

        try {
            userCRUDRepository.save(new User(0, request.getEmail(), passwordHash, id_salt));
        }catch (Exception exception){
            saltCRUDRepository.deleteById(id_salt);
            return new SignUpResponse("This user already exists", "");
        }

        return new SignUpResponse("Completed", "1");
    }

}
