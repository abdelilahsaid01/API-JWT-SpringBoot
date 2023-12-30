package org.sid.ebankingbackend.controllers;

import lombok.AllArgsConstructor;
import org.sid.ebankingbackend.entities.BankDetails;
import org.sid.ebankingbackend.repositories.BankDetailsRepository;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/bankDetails")
public class BankDetailsController {
    private BankDetailsRepository bankDetailsRepository;


    @PostMapping("/create")
    public BankDetails addBank(@RequestBody BankDetails bankDetails) {
      return this.bankDetailsRepository.save(bankDetails);
    }

    @GetMapping()
    public List<BankDetails>  getAll() {
        return this.bankDetailsRepository.findAll();
    }
}
