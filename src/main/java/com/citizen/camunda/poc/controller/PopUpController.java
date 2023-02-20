package com.citizen.camunda.poc.controller;

import com.citizen.camunda.poc.model.LimitedProviderModel;
import com.citizen.camunda.poc.model.PopUpModel;
import com.citizen.camunda.poc.model.ProviderModel;
import com.citizen.camunda.poc.model.ProviderReviewModel;
import com.citizen.camunda.poc.model.User;
import com.citizen.camunda.poc.service.IPopUpService;
import com.citizen.camunda.poc.service.IUserService;
import com.citizen.camunda.poc.service.ProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/popup")
public class PopUpController {

    @Autowired
    private IPopUpService iPopUpService;

    @GetMapping
    public List<PopUpModel> getAllPopUp() {
        return iPopUpService.getAll();
    }

    @GetMapping("/{id}")
    public PopUpModel getAllLimitedProviderPopUpById(@PathVariable Long id) {
        return iPopUpService.getPopUpModelById(id);
    }

    @PostMapping
    public ResponseEntity<Object> savePopUp(@RequestBody PopUpModel popUpModel) {
        iPopUpService.save(popUpModel);
        return ResponseEntity.ok().body("Successfully saved record");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable Long id) {
        iPopUpService.deleteById(id);
        return ResponseEntity.ok().body("Successfully deleted record");
    }
}
