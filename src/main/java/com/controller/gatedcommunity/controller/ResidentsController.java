package com.controller.gatedcommunity.controller;

import com.controller.gatedcommunity.model.Residents;
import com.controller.gatedcommunity.service.ResidentsService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/new")
public class ResidentsController {
    @Value("${admin.secret}")
    private String adminSecret;

    @Autowired
    ResidentsService residentsService;

    @GetMapping("/getAllResidents")
    public String getAll(Model model,HttpSession session){
       String username =(String) session.getAttribute("username");
        List<Residents> allResidents = residentsService.findAllResidents();
        model.addAttribute("all",allResidents);
        model.addAttribute("name",username);
        return "allusers";
    }

    @GetMapping("/")
    public String home() {
        return "redirect:/new/login";
    }

    @GetMapping("/signup")
    public String getPage(Model model){
        model.addAttribute("residents",new Residents());
        return "signup";
    }

    @GetMapping("/adminSignup")
    public String getAdminPage(Model model){
        model.addAttribute("residents",new Residents());
        return "adminSignup";
    }

    @PostMapping("/signUp")
    public String signUp(@ModelAttribute Residents residents,Model model,HttpSession session){
        residents.setRole("USER");
      residentsService.saveUser(residents);
      session.setAttribute("username",residents.getUserName());
      model.addAttribute("name",residents.getUserName());
      return "success";
    }

    @PostMapping("/adminsignUp")
    public String adminsignUp(@ModelAttribute Residents residents,Model model,HttpSession session){
        if(adminSecret.equals(residents.getSecret())) {
            session.setAttribute("username",residents.getUserName());
            residents.setRole("ADMIN");
            residentsService.saveUser(residents);
            model.addAttribute("name", residents.getUserName());
            return "adminSuccess";
        }
        model.addAttribute("error","Invalid admin Secret");
        return "adminSignup";
    }


    @GetMapping("/signin")
    public String signIn(){
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String userName, @RequestParam String password, Model model, HttpSession session){
        Residents byName = residentsService.findByName(userName);

        if(byName!=null && byName.getRole().equals("USER") && byName.getPassword().equals(password)){
            model.addAttribute("name",byName.getUserName());
            session.setAttribute("username",byName.getUserName());
            return "success";
        }
       return "failed";
    }

    @GetMapping("/adminLogin")
    public String adminLogin(){
        return "adminLogin";
    }
    @PostMapping("/adminlogin")
    public String adminLogin(@RequestParam String userName, @RequestParam String password, Model model, HttpSession session){
        Residents byName = residentsService.findByName(userName);

        if(byName!=null && byName.getRole().equals("ADMIN") && byName.getPassword().equals(password)){
            model.addAttribute("name",byName.getUserName());
            session.setAttribute("username",byName.getUserName());
            return "adminSuccess";
        }
        return "failed";
    }

}
