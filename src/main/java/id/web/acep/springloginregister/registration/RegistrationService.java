package id.web.acep.springloginregister.registration;

import id.web.acep.springloginregister.appuser.AppUser;
import id.web.acep.springloginregister.appuser.AppUserRole;
import id.web.acep.springloginregister.appuser.AppUserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RegistrationService {

    private EmailValidatorService emailValidatorService;

    private final AppUserService appUserService;

    public String register(RegistrationRequest request) {
        boolean isValidEmail = emailValidatorService.test(request.getEmail());

        if (!isValidEmail) {
            throw new IllegalStateException("Email is not valid.");
        }

        return appUserService.signUpUser(
            new AppUser(
                request.getFirstName(),
                request.getLastName(),
                request.getEmail(),
                request.getPassword(),
                AppUserRole.USER
            )
        );
    }
}
