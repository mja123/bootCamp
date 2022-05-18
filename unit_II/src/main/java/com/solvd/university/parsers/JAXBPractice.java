package com.solvd.university.parsers;

import com.solvd.university.model.Faculty;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;

public class JAXBPractice {
  private static final Logger LOGGER = LogManager.getLogger(JAXBPractice.class);

  public static void main(String[] args) {

    Faculty faculty = new Faculty(1L, "Administration", null, null);

    try {
      JAXBContext context = JAXBContext.newInstance(Faculty.class);

      Marshaller marshaller = context.createMarshaller();
      marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
      File xmlFile = new File(System.getenv("XML_FILE"));

      marshaller.marshal(faculty, System.out);
      marshaller.marshal(faculty, xmlFile);
      Unmarshaller unmarshaller = context.createUnmarshaller();
      Faculty faculty2;
      faculty2 = (Faculty) unmarshaller.unmarshal(xmlFile);

      LOGGER.info(faculty2);

      //Error in annotations, problems with field and getters. The name of the element appear twice.
      // Student student1 = new Student("Matias", "matias@gmail.com", 20, 3);
      // JAXBContext jaxb = JAXBContext.newInstance(Student.class);
      // Marshaller marshaller = jaxb.createMarshaller();
      // marshaller.marshal(student1, xmlFile);
      // marshaller.marshal(student1, System.out);
    } catch (JAXBException e) {
      LOGGER.error(e);
    }
  }
}
