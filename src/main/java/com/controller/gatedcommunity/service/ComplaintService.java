package com.controller.gatedcommunity.service;

import com.controller.gatedcommunity.model.Complaint;

import java.util.List;

public interface ComplaintService {

    public void saveComplaint(Complaint complaint);
    public List<Complaint>complaints(String username);
    public List<Complaint>getComplaints();
    public Complaint findById(int complaintId);

}
