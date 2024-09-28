package com.example.mhada;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MhadaApplicationController {

  @Autowired private MhadaApplicationService service;

  @GetMapping("/process-pdf")
  public String processPdf(@RequestParam String filePath) {
    service.processPdfAndInsertData(filePath);
    return "Data processed and inserted into the database!";
  }
}
