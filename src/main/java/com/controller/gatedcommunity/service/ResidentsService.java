package com.controller.gatedcommunity.service;

import com.controller.gatedcommunity.model.Residents;

import java.util.List;

public interface ResidentsService {

    public void saveUser(Residents residents);
    public Residents findByName(String username);
  //  public Residents findByRole(String role);
    public List<Residents> findAllResidents();

}
