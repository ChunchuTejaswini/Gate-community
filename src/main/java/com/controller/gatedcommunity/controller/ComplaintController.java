package com.controller.gatedcommunity.controller;

import com.controller.gatedcommunity.model.Complaint;
import com.controller.gatedcommunity.model.Residents;
import com.controller.gatedcommunity.service.ComplaintService;
import com.controller.gatedcommunity.service.ResidentsService;
import jakarta.persistence.Column;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/new")
public class ComplaintController {

    @Autowired
    ComplaintService complaintService;
    @Autowired
    ResidentsService residentsService;

    @GetMapping("/getCom")
    public String getComp(Model model, HttpSession session){

        String user = (String) session.getAttribute("username");
        Residents byName = residentsService.findByName(user);

        Complaint complaint=new Complaint();
        complaint.setUsername(byName.getUserName());
        complaint.setPhNo(byName.getPhNo());
        complaint.setEmail(byName.getEmail());

        model.addAttribute("name",byName.getUserName());
        model.addAttribute("complaint",complaint);

        return "complaintForm";
    }

    @PostMapping("/addComp")
    public String addComp(@ModelAttribute Complaint complaint,Model model){
        complaintService.saveComplaint(complaint);
        return "success";
    }

    @GetMapping("/getAll")
    public String complaintList(Model model,HttpSession session){
        String user=(String) session.getAttribute("username");

        List<Complaint>complaints=complaintService.complaints(user);
        model.addAttribute("name",user);
        model.addAttribute("complaints",complaints);
        return "viewComplaints";
    }

    @GetMapping("/getAllComplaints")
    public String admincomplaintList(Model model,HttpSession session){
        String username =(String) session.getAttribute("username");
        List<Complaint> complaints = complaintService.getComplaints();
        model.addAttribute("complaints",complaints);
        model.addAttribute("name",username);
        return "adminViewComplaints";
    }

    @GetMapping("/viewSolved")
    public  String solvedComplaintList(Model model,HttpSession session){

            String username =(String) session.getAttribute("username");
            List<Complaint> complaints = complaintService.complaints(username);
        List<Complaint> list = complaints.stream().filter(complaint -> "SOLVED".equals(complaint.getStatus())).toList();
        model.addAttribute("name",username);
            model.addAttribute("complaints",list);
            return "viewComplaints";


       // List<Complaint> complaints = complaintService.complaints(username);
       // model.addAttribute("name",username);
       // model.addAttribute("complaints",complaints);

    }

    @PostMapping("/updateStatus")
    public String statusUpdate(@RequestParam int complaintId,@RequestParam String status){

        Complaint byId = complaintService.findById(complaintId);
        byId.setStatus(status);
        complaintService.saveComplaint(byId);
        return "redirect:/new/getAllComplaints";
    }

}
