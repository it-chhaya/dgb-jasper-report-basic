package com.chanchhaya.jasperreport.service;

import com.chanchhaya.jasperreport.model.User;
import com.chanchhaya.jasperreport.repository.UserRepository;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ReportService {

    private final UserRepository userRepository;

    @Autowired
    public ReportService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Value("${report.server-path}")
    private String reportServerPath;

    public String exportReport(String reportFormat) throws FileNotFoundException, JRException {

        List<User> users = userRepository.findAll();

        // load file and compile it
        File file = ResourceUtils.getFile("classpath:small_user.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());

        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(users);

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("createdBy", "Chan Chhaya");

        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);

        if (reportFormat.equalsIgnoreCase("html")) {
            JasperExportManager.exportReportToHtmlFile(jasperPrint, reportServerPath + "users.html");
        }

        if (reportFormat.equalsIgnoreCase("pdf")) {
            JasperExportManager.exportReportToPdfFile(jasperPrint, reportServerPath + "users.pdf");
        }

        return "report generated in path : " + reportServerPath;

    }
}
