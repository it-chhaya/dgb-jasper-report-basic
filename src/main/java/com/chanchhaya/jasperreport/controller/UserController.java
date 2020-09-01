package com.chanchhaya.jasperreport.controller;

import com.chanchhaya.jasperreport.service.ReportService;
import net.sf.jasperreports.engine.JRException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileNotFoundException;

@RestController
public class UserController {

    private final ReportService reportService;

    @Autowired
    public UserController(ReportService reportService) {
        this.reportService = reportService;
    }

    @GetMapping("/users/reports/{format}")
    public String viewUserReport(@PathVariable String format) throws FileNotFoundException, JRException {
        return reportService.exportReport(format);
    }

}
