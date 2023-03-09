package com.citizen.camunda.poc.controller;

import com.citizen.camunda.poc.model.ReferenceTypeModel;
import com.citizen.camunda.poc.model.ReferenceTypeSearchModel;
import com.citizen.camunda.poc.model.ReferenceTypeViewModel;
import com.citizen.camunda.poc.service.IReferenceTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/referenceType")
public class ReferenceTypeController {

    @Autowired
    private IReferenceTypeService iReferenceTypeService;

    @GetMapping
    public List<ReferenceTypeViewModel> getAll() {
        return iReferenceTypeService.getAll();
    }

    @GetMapping("/{id}")
    public ReferenceTypeModel getById(@PathVariable Long id) {
        return iReferenceTypeService.getById(id);
    }

    @PostMapping
    public ResponseEntity<Object> save(@RequestBody ReferenceTypeModel referenceTypeModel) {
        iReferenceTypeService.save(referenceTypeModel);
        return ResponseEntity.ok().body("Successfully saved record");
    }

    @DeleteMapping
    public ResponseEntity<Object> delete(@RequestBody ReferenceTypeViewModel searchModel) {
        iReferenceTypeService.deleteById(searchModel);
        return ResponseEntity.ok().body("Successfully deleted record");
    }

    @DeleteMapping("/byIds")
    public ResponseEntity<Object> delete(@RequestBody List<Long> ids) {
        iReferenceTypeService.deleteByIdIn(ids);
        return ResponseEntity.ok().body("Successfully deleted records");
    }

    @PostMapping("/search")
    public List<ReferenceTypeViewModel> search(@RequestBody ReferenceTypeSearchModel searchModel) {
        return iReferenceTypeService.search(searchModel);
    }

    @PostMapping("/findByTypeAndDesc")
    public List<ReferenceTypeViewModel> findByTypeAndDesc(@RequestBody ReferenceTypeViewModel searchModel) {
        return iReferenceTypeService.findByTypeAndDesc(searchModel);
    }

    @PostMapping("/export")
    public void exportIntoCSV(@RequestBody List<Long> ids, HttpServletResponse response) throws IOException {
        response.setContentType("text/csv");
        response.addHeader("Content-Disposition", "attachment; filename=\"ReferenceType.csv\"");
        iReferenceTypeService.exportData(ids, response);
    }

}
