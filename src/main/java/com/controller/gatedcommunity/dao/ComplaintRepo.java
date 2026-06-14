package com.controller.gatedcommunity.dao;

import com.controller.gatedcommunity.model.Complaint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping
public interface ComplaintRepo extends JpaRepository<Complaint,Integer> {

    public List<Complaint> findByUsername(String username);
}
