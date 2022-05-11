package com.solvd.university.parsers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

public class XMLDOMParser {

  private static final Logger LOGGER = LogManager.getLogger(XMLDOMParser.class);

  public static void main(String[] args) {

    try {
      File xmlFile = new File(System.getenv("XML_FILE"));
      DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
      DocumentBuilder builder = factory.newDocumentBuilder();
      Document document = builder.parse(xmlFile);

      document.getDocumentElement().normalize();

      NodeList list = document.getElementsByTagName("Student");

      for (int i = 0; i < list.getLength(); i++) {

        Node node = list.item(i);

        if (node.getNodeType() == Node.ELEMENT_NODE) {
          LOGGER.info("****** Stuedent " + i + " ******");
          Element element = (Element) node;
          LOGGER.info("Email: " + element.getAttribute("email"));
          LOGGER.info(
              "Average: " + element.getElementsByTagName("average").item(0).getTextContent());
          LOGGER.info("Age: " + element.getElementsByTagName("age").item(0).getTextContent());
          LOGGER.info(
              "Years in the degree: "
                  + element.getElementsByTagName("year_in_degree").item(0).getTextContent());
          LOGGER.info("************\n");
        }
      }

    } catch (ParserConfigurationException | SAXException | IOException e) {
      LOGGER.error(e.getMessage());
    }
  }
}
