package com.plannerapp.controller;

import com.plannerapp.model.dto.UserLoginDTO;
import com.plannerapp.model.dto.UserRegisterDTO;
import com.plannerapp.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public ModelAndView login(@Valid @ModelAttribute("userLoginDTO") UserLoginDTO userLoginDTO) {
        return new ModelAndView("login");
    }

    @PostMapping("/login")
    public ModelAndView login(@Valid @ModelAttribute("userLoginDTO") UserLoginDTO userLoginDTO,
                              BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ModelAndView("login");
        }

        boolean hasSuccessfulLogin= userService.login(userLoginDTO);

        if (!hasSuccessfulLogin) {
            ModelAndView modelAndView = new ModelAndView("login");
            modelAndView.addObject("hasLoginError", true);
            return modelAndView;
        }

        return new ModelAndView("redirect:/home");
    }


    @GetMapping("/register")
    public ModelAndView register(@ModelAttribute("userRegisterDTO") UserRegisterDTO userRegisterDTO) {
        return new ModelAndView("register");
    }


    @PostMapping("/register")
    public ModelAndView register(@ModelAttribute("userRegisterDTO") @Valid UserRegisterDTO userRegisterDTO,
                                 BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return new ModelAndView("register");
        }

        boolean hasSuccessfulRegistration = userService.register(userRegisterDTO);

        if (!hasSuccessfulRegistration) {
            ModelAndView modelAndView = new ModelAndView("register");
            modelAndView.addObject("hasRegistrationError", true);
            return modelAndView;
        }

        return new ModelAndView("redirect:/login");
    }

    @PostMapping("/logout")
    public ModelAndView logout() {

        this.userService.logout();
        return new ModelAndView("redirect:/");
    }
}
