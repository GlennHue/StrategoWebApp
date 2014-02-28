package be.kdg.controllers;

import be.kdg.service.api.UserServiceApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by wouter on 24/02/14.
 */
@Controller
public class RegistrationController {
    @Autowired
    private UserServiceApi userService;

    //Check if the uuid send in the verification e-mail is the same as the one in the database
    //If they are the same, set property verified to true so the user is able to login
    @RequestMapping(method = RequestMethod.GET, value = "/verify")
    public ModelAndView checkVerificationEmail(@RequestParam("uuid") String uuid) {
        if (userService.uuidIsVerified(uuid)) {
            return new ModelAndView("verify.xhtml");

        }
        return new ModelAndView("verifyFailed.xhtml");
    }
}
