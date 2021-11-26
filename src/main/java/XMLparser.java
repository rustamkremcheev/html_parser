import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.HashMap;

public class XMLparser {
    private static final String FILENAME = "C:\\Users\\Rustam\\Desktop\\report_katm_48.xml";

    public static void xml_parser() throws Exception {

        HashMap<String, String> month_number = new HashMap<String, String>();
        month_number.put("1","Январь");
        month_number.put("2","Февраль");
        month_number.put("3","Март");
        month_number.put("4","Апрель");
        month_number.put("5","Май");
        month_number.put("6","Июнь");
        month_number.put("7","Июль");
        month_number.put("8","Август");
        month_number.put("9","Сентябрь");
        month_number.put("10","Октябрь");
        month_number.put("11","Ноябрь");
        month_number.put("12","Декабрь");

        //System.out.println("Month: " + month_number.get("1") + " 2019");

        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

        try {
            dbf.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(new File(FILENAME));
            doc.getDocumentElement().normalize();

            System.out.println("Root Element :" + doc.getDocumentElement().getNodeName());
            System.out.println("------");

            NodeList list = doc.getElementsByTagName("data");

            for (int temp = 0; temp < list.getLength(); temp++) {

                Node node = list.item(temp);

                if (node.getNodeType() == Node.ELEMENT_NODE) {

                    Element element = (Element) node;

                    // get staff's attribute
                    //String id = element.getAttribute("id");

                    // get text
                    String pinfl = element.getElementsByTagName("pinfl").item(0).getTextContent();
                    String company_tin = element.getElementsByTagName("company_tin").item(0).getTextContent();
                    String company_name = element.getElementsByTagName("company_name").item(0).getTextContent();

                    String name = element.getElementsByTagName("name").item(0).getTextContent();
                    String number_passport = element.getElementsByTagName("number_passport").item(0).getTextContent();
                    String seria_passport = element.getElementsByTagName("series_passport").item(0).getTextContent();

                    String year = element.getElementsByTagName("year").item(0).getTextContent();
                    String period = element.getElementsByTagName("period").item(0).getTextContent();

                    String salary = element.getElementsByTagName("salary").item(0).getTextContent();
                    String salary_tax_sum = element.getElementsByTagName("salary_tax_sum").item(0).getTextContent();

                    //NodeList salaryNodeList = element.getElementsByTagName("salary");
                    //String salary = salaryNodeList.item(0).getTextContent();

                    // get salary's attribute
                    //String currency = salaryNodeList.item(0).getAttributes().getNamedItem("currency").getTextContent();

                    //System.out.println("Current Element :" + node.getNodeName());
                    //System.out.println("Staff Id : " + id);

                    System.out.println("list: " + list.getLength());
                    //System.out.printf("Salary [Currency] : %,.2f [%s]%n%n", Float.parseFloat(salary), currency);

                }
            }

        }  catch (Exception e) {
            e.printStackTrace();
        }


    }
}
