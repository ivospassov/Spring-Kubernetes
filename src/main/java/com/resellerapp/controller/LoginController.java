package com.resellerapp.controller;

import com.resellerapp.model.dto.LoginDTO;
import com.resellerapp.service.interfaces.LoginService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class LoginController {
    private final LoginService loginService;

    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @ModelAttribute("loginDTO")
    public LoginDTO initLoginDTO() {
        return new LoginDTO();
    }

    @GetMapping("/users/login")
    public String renderLoginForm() {
        return "/login";
    }

    @PostMapping("/users/login")
    public String handleLoginForm(@Valid LoginDTO loginDTO,
                                  BindingResult bindingResult,
                                  RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes
                    .addFlashAttribute("loginDTO", loginDTO)
                    .addFlashAttribute("org.springframework.validation.BindingResult.loginDTO", bindingResult);

            return "redirect:/users/login";
        }

        if (!this.loginService.areCredentialsValid(loginDTO.getUsername(), loginDTO.getPassword())) {
            redirectAttributes
                    .addFlashAttribute("loginDTO", loginDTO)
                    .addFlashAttribute("areCredentialsValid", false);

            return "redirect:/users/login";
        }

        this.loginService.loginUser(loginDTO.getUsername());
        return "redirect:/home";
    }

    @GetMapping("/users/logout")
    public String logout() {
        this.loginService.logout();
        return "redirect:/";
    }
}
