package com.solvd.university.parsers;

import com.solvd.university.model.Student;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;

public class JAXBMarshall {
  private static final Logger LOGGER = LogManager.getLogger(JAXBMarshall.class);

  public static void main(String[] args) {
    Student student1 = new Student();
    //StudentDAO studentDAO = new StudentDAO();
    //studentDAO.saveEntity(student1);
    try {
      JAXBContext jaxb = JAXBContext.newInstance(Student.class);
      Marshaller marshaller = jaxb.createMarshaller();
      marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
      File xmlFile = new File(System.getenv("PATH_FILE"));
      marshaller.marshal(student1, xmlFile);
      marshaller.marshal(student1, System.out);

    } catch (JAXBException e) {
      LOGGER.error(e.getMessage());
    }
  }
}
