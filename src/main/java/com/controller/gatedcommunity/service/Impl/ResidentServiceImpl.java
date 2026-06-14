package com.controller.gatedcommunity.service.Impl;

import com.controller.gatedcommunity.dao.ResidentsRepo;
import com.controller.gatedcommunity.model.Residents;
import com.controller.gatedcommunity.service.ResidentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResidentServiceImpl implements ResidentsService {

    @Autowired
    ResidentsRepo residentsRepo;

    @Override
    public void saveUser(Residents residents) {
        residentsRepo.save(residents);
    }

    public Residents findByName(String username){
        Residents byUserName = residentsRepo.findByUserName(username);
        return byUserName;
    }

   /* @Override
    public Residents findByRole(String role) {
        Residents residents = residentsRepo.fingByRole(role);
        return residents;
    }*/
   public List<Residents> findAllResidents(){
       List<Residents> all = residentsRepo.findAll();
       return all;
   }
}
