package com.example.pusula.Controller;


import com.example.pusula.DTO.UserDTO;
import com.example.pusula.Entity.User;
import com.example.pusula.Service.UserService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.logging.Logger;

@Controller
@RequestMapping("/")
public class RegisterController {

    private Logger log = Logger.getLogger(this.getClass().getName());
    private UserService userService;

    @Autowired
    public RegisterController(UserService userService) {
        this.userService = userService;
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
        binder.registerCustomEditor(String.class, stringTrimmerEditor);
    }

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("newUser", new UserDTO());
        return "/register";
    }

    @PostMapping("/processRegistrationForm")
    public String processRegistrationForm(@Valid @ModelAttribute("newUser") UserDTO newUserDTO, BindingResult bindingResult,
                                          HttpSession session, Model model) {
        String username = newUserDTO.getUsername();
        log.info("Username : " + username);

        if (bindingResult.hasErrors()) {
            return "/register"; // Correct view name
        }

        User existing = userService.findByUsername(username);
        if (existing != null) {
            model.addAttribute("newUser", new UserDTO()); // Correct attribute name
            model.addAttribute("error", "Bu kullanıcı adı alındı");
            return "/register"; // Correct view name
        }

        userService.save(newUserDTO, 3); // Save the user with roleID 3
        session.setAttribute("user", newUserDTO); // Set the user in the session
        return "redirect:/login";
    }
}

