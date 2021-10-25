package com.example.eprep.web;

import com.example.eprep.model.bindng.UserLoginBindingModel;
import com.example.eprep.model.bindng.UsersRegisterBindingModel;
import com.example.eprep.model.service.UserServiceModel;
import com.example.eprep.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final ModelMapper modelMapper;

    public UserController(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/register")
    public String register() {
        return "register";
    }


    @PostMapping("/register")
    public String registerConfirm(@Valid UsersRegisterBindingModel usersRegisterBindingModel
            , BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors() || !usersRegisterBindingModel
                .getPassword().equals(usersRegisterBindingModel.getConfirmPassword())) {

            redirectAttributes
                    .addFlashAttribute("usersRegisterBindingModel", usersRegisterBindingModel)
                    .addFlashAttribute("org.springframework.validation.BindingResult.usersRegisterBindingModel", bindingResult);
            return "redirect:register";
        }

        userService.registerUser(modelMapper
                .map(usersRegisterBindingModel, UserServiceModel.class));

        return "redirect:login";
    }

    @GetMapping("/login")
    public String login(Model model) {
        if (!model.containsAttribute("isFound")) {
            model.addAttribute("isFound", true);
        }
        return "login";
    }


    @PostMapping("/login")
    public String loginConfirm(@Valid UserLoginBindingModel userLoginBindingModel
            , BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes
                    .addFlashAttribute("userLoginBindingModel", userLoginBindingModel)
                    .addFlashAttribute("org.springframework.validation.BindingResult.userLoginBindingModel", bindingResult);
            return "redirect:login";
        }

        UserServiceModel userServiceModel =
                userService
                        .findByUsernameAndPassword(userLoginBindingModel.getUsername()
                                , userLoginBindingModel.getPassword());

        if (userServiceModel == null) {
            redirectAttributes
                    .addFlashAttribute("userLoginBindingModel", userLoginBindingModel)
                    .addFlashAttribute("isFound", false);
            return "redirect:login";
        }

        userService.loginUser(userServiceModel.getId(), userLoginBindingModel.getUsername());

        return "redirect:/";
    }

    @GetMapping("/logout")
    public String logout(HttpSession httpSession) {
        httpSession.invalidate();
        return "redirect:/";
    }


    @ModelAttribute
    public UsersRegisterBindingModel usersRegisterBindingModel() {
        return new UsersRegisterBindingModel();
    }

    @ModelAttribute
    public UserLoginBindingModel userLoginBindingModel() {
        return new UserLoginBindingModel();
    }
}
