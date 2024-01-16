package com.tbs.personnel.deployment.tracker.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class WebController {
    @RequestMapping(value = "/index")
    public String index() {
        return "index";
    }

    @RequestMapping(value = "/login")
    public String login() {
        return "login";
    }

    @RequestMapping(value = "/commander/home")
    public String commanderMvc() {
        return "commander-home";
    }

    @RequestMapping(value = "/enlisted/home")
    public String enlistedMvc() {
        return "enlisted-home";
    }


    @RequestMapping(value = "/enlisted-details/**")
    public String enlistedDetails() {
        return "enlisted-details";
    }

    @RequestMapping(value = "/enlisted")
    public String enlistedList() {
        return "enlisted";
    }


    @RequestMapping(value = "/commander/deployments")
    public String commanderDeployments() {
        return "commander-deployments";
    }

    @RequestMapping(value = "/commander/leave-requests")
    public String commanderLeaveRequest() {
        return "commander-leave-request";
    }


    @RequestMapping(value = "/enlisted/deployments")
    public String enlistedDeployments() {
        return "enlisted-deployments";
    }

    @RequestMapping(value = "/enlisted/leave-requests")
    public String enlistedLeaveRequest() {
        return "enlisted-leave-request";
    }


    @RequestMapping(value = "/bootstrap")
    public String bootstrap() {
        return "bootstrap";
    }


}
