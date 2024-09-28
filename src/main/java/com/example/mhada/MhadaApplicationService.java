package com.example.mhada;

import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

@Service
public class MhadaApplicationService {

  @Autowired private MhadaDtoRepository repository;

  public void processPdfAndInsertData(String filePath) {
    try {
      File file = new File("document.pdf");
      PDDocument document = Loader.loadPDF(file);
      PDFTextStripper pdfStripper = new PDFTextStripper();

      String text = pdfStripper.getText(document);
      document.close();

      String[] lines = text.split("\\r?\\n");

      for (String line : lines) {
        String[] data = line.split("\\s+");

        if (data.length >= 5) {
          try {
            int srNo = Integer.parseInt(data[0]);
            String applicationNo = data[1];
            String scheme = data[2]; // Adjust this if scheme has multiple words
            String category = data[3];
            String applicantName = String.join(" ", Arrays.copyOfRange(data, 4, data.length));

            MhadaDto application = new MhadaDto();
            application.setSrNo(srNo);
            application.setApplicationNo(applicationNo);
            application.setScheme(scheme);
            application.setCategory(category);
            application.setApplicantName(applicantName);

            repository.save(application);
          } catch (NumberFormatException e) {
            System.out.println("Invalid data format: " + line);
          }
        }
      }

    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
