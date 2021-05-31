package io.ngss.taksitle.report.security;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AngularConfig {

    private static String FORWARD_STR = "forward:/";

    @RequestMapping(value = "/{path:[^\\.]*}")
    public String redirect() {
        return FORWARD_STR;
    }

    @RequestMapping(value = "/secure/**")
    public String redirectSecure() {
        return FORWARD_STR;
    }

    @RequestMapping(value = "/offers/**")
    public String redirectOffers() {
        return FORWARD_STR;
    }

    @RequestMapping(value = "/backoffice/**")
    public String redirectBackOffice() {
        return FORWARD_STR;
    }

    @RequestMapping(value = "/stock/**")
    public String redirectSecureS() {
        return FORWARD_STR;
    }

    @RequestMapping(value = "/training/**")
    public String redirectTraining() {
        return FORWARD_STR;
    }
}