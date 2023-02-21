package com.resellerapp.controller;

import com.resellerapp.model.dto.RegisterDTO;
import com.resellerapp.service.interfaces.RegisterService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class RegisterController {

    private final RegisterService registerService;

    public RegisterController(RegisterService registerService) {
        this.registerService = registerService;
    }

    @ModelAttribute("registerDTO")
    public RegisterDTO initRegisterDTO() {
        return new RegisterDTO();
    }

    @GetMapping("/users/register")
    public String renderRegisterForm() {
        return "/register";
    }

    @PostMapping("/users/register")
    public String handleRegisterForm(@Valid RegisterDTO registerDTO,
                                  BindingResult bindingResult,
                                  RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes
                    .addFlashAttribute("registerDTO", registerDTO)
                    .addFlashAttribute("org.springframework.validation.BindingResult.registerDTO", bindingResult);

            return "redirect:/users/register";
        }

        boolean usernameExists = this.registerService.usernameExists(registerDTO.getUsername());
        boolean emailExists = this.registerService.emailExists(registerDTO.getEmail());


        //Check if username or email exists already
        if (usernameExists) {
            redirectAttributes
                    .addFlashAttribute("usernameIsValid", false)
                    .addFlashAttribute("registerDTO", registerDTO);

            return "redirect:/users/register";
        } else if (emailExists) {
            redirectAttributes
                    .addFlashAttribute("emailIsValid", false)
                    .addFlashAttribute("registerDTO", registerDTO);

            return "redirect:/users/register";
        }


        //Check if passwords match
        if (!this.registerService.doPasswordsMatch(registerDTO.getPassword(), registerDTO.getConfirmPassword())) {
            redirectAttributes
                    .addFlashAttribute("registerDTO", registerDTO)
                    .addFlashAttribute("doPasswordsMatch", false);

            return "redirect:/users/register";
        }

        this.registerService.registerUser(registerDTO);
        return "redirect:/users/login";
    }
}
