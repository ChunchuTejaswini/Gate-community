package com.controller.gatedcommunity.service.Impl;

import com.controller.gatedcommunity.dao.ComplaintRepo;
import com.controller.gatedcommunity.dao.ResidentsRepo;
import com.controller.gatedcommunity.model.Complaint;
import com.controller.gatedcommunity.model.Residents;
import com.controller.gatedcommunity.service.ComplaintService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ComplaintServiceImpl implements ComplaintService {

    @Autowired
    ComplaintRepo complaintRepo;

    @Override
    public void saveComplaint(Complaint complaint) {
        complaintRepo.save(complaint);
    }

    @Override
    public List<Complaint> complaints(String username) {
        return complaintRepo.findByUsername(username);
    }

    @Override
    public List<Complaint> getComplaints() {
      return complaintRepo.findAll();
    }

    public Complaint findById(int complaintId) {
      return   complaintRepo.findById(complaintId).get();
    }

}
