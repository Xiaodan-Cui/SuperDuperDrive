package com.udacity.jwdnd.course1.cloudstorage.Controller;

import com.udacity.jwdnd.course1.cloudstorage.Mapper.UserMapper;
import com.udacity.jwdnd.course1.cloudstorage.services.HashService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.udacity.jwdnd.course1.cloudstorage.Entity.User;

import java.security.SecureRandom;
import java.util.Base64;

@Controller
@RequestMapping("/signup")
public class SignUpController {
   UserMapper userMapper;
   HashService hashService;

    public SignUpController(UserMapper userMapper, HashService hashService) {

        this.userMapper = userMapper;
        this.hashService = hashService;
    }

    @GetMapping
    public String getSignUp(){
        return "signup";
    }

    @PostMapping
    public String signUp(Model model, @ModelAttribute User user)  {
        if (userMapper.getUserByName(user.getUsername()) != null){
            model.addAttribute("fail", true);
            return "signup";
        }
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        String encodedSalt = Base64.getEncoder().encodeToString(salt);
        String hashedPassword = hashService.getHashedValue(user.getPassword(), encodedSalt);
        userMapper.insert(new User(null, user.getUsername(), encodedSalt, hashedPassword, user.getFirstname(), user.getLastname()));
        model.addAttribute("success",true);
        return "signup";
    }
}
