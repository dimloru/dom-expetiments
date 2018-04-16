package com.dimlo.domex;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import org.w3c.dom.*;

public class DomExperiment {
    public static void main(String[] args) throws Exception {
        File xmlFile = new File("res/ord.xml");
        BufferedReader reader = new BufferedReader(new FileReader(xmlFile));
        String str = null;
        while ((str = reader.readLine()) != null) {
            System.out.println(str);
        }

        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document doc = db.parse(xmlFile);

        System.out.println("===============================================");

        Element root = doc.getDocumentElement();
        System.out.println("The root element is " + root.getNodeName() + ", tag:" + root.getTagName());

        NodeList children = root.getChildNodes();
        System.out.println("Nubmer of chilren: " + children.getLength());

        stepThroughNodes(root);


    }

    private static void stepThroughNodes(Node start) {
        System.out.println(start.getNodeName() + " = " + start.getNodeValue());

        //checking attributes
        if (start.getNodeType() == start.ELEMENT_NODE) {
            NamedNodeMap attributes = start.getAttributes();
            for (int i = 0; i < attributes.getLength(); i++) {
                Node att = attributes.item(i);
                System.out.println("    Attribute: " + att.getNodeName() + " = " + att.getNodeValue());
            }
        }

        for (Node child = start.getFirstChild();
             child != null;
             child = child.getNextSibling()) {
            stepThroughNodes(child);
        }
    }

}
