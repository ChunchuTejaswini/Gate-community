package com.controller.gatedcommunity.dao;

import com.controller.gatedcommunity.model.Residents;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResidentsRepo extends JpaRepository<Residents,Integer> {

    public Residents findByUserName(String userName);
    //public Residents fingByRole(String role);
}
