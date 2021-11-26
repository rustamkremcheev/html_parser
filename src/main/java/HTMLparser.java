import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Parser;

import org.w3c.dom.*;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;

public class HTMLparser {
    private static final String FILENAME = "C:\\Users\\Rustam\\Desktop\\report_katm_48.xml";


    public static void pars() throws Exception {

        String pinfl = "";
        String name = "";
        String number_passport= "";
        String seria_passport= "";

        File file = new File("D:\\salary.html");
        System.out.println("file: " + file);
        Document doc = Jsoup.parse(file, "UTF-8");  //Сюда html вставляем

        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        try {
            dbf.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);
            DocumentBuilder db = dbf.newDocumentBuilder();
            org.w3c.dom.Document docXml = db.parse(new File(FILENAME));
            docXml.getDocumentElement().normalize();

            NodeList list = docXml.getElementsByTagName("data");

            for (int temp = 0; temp < list.getLength(); temp++) {
                Node node = list.item(temp);

                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    org.w3c.dom.Element elementXml = (org.w3c.dom.Element) node;

                    pinfl = elementXml.getElementsByTagName("pinfl").item(0).getTextContent();
                    String company_tin = elementXml.getElementsByTagName("company_tin").item(0).getTextContent();
                    String company_name = elementXml.getElementsByTagName("company_name").item(0).getTextContent();

                    name = elementXml.getElementsByTagName("name").item(0).getTextContent();
                    number_passport = elementXml.getElementsByTagName("number_passport").item(0).getTextContent();
                    seria_passport = elementXml.getElementsByTagName("series_passport").item(0).getTextContent();

                    String year = elementXml.getElementsByTagName("year").item(0).getTextContent();
                    String period = elementXml.getElementsByTagName("period").item(0).getTextContent();

                    String salary = elementXml.getElementsByTagName("salary").item(0).getTextContent();
                    String salary_tax_sum = elementXml.getElementsByTagName("salary_tax_sum").item(0).getTextContent();

                    BigDecimal bigDecimal1=new BigDecimal(salary);
                    String salaryDecimal=bigDecimal1.toString();

                    BigDecimal bigDecimal2=new BigDecimal(salary_tax_sum);
                    String salary_tax_sumDecimal=bigDecimal1.toString();

                    String tr = "<tr>\n" +
                            "          <TD>" +temp+ " </TD>\n" +
                            "          <TD>"+month(period)+" "+year +"</TD>\n" +
                            "          <TD>"+company_name+"</TD>\n" +
                            "          <TD> "+company_tin+"</TD>\n" +
                            "          <TD>"+salaryDecimal+"</TD>\n" +
                            "      <TD>"+salary_tax_sumDecimal+"</TD>\n" +
                            "        </tr>";

                    Element trElement = Jsoup.parse(tr, "", Parser.xmlParser());
                    doc.getElementById("table_incoms").appendChild(trElement);

                }

            }
            Element FIO = Jsoup.parse("<span>"+name+"</span>", "", Parser.xmlParser());
            doc.getElementById("client_full_name").appendChild(FIO);

            Element client_pin = Jsoup.parse("<span>"+pinfl+"</span>", "", Parser.xmlParser());
            doc.getElementById("client_pin").appendChild(client_pin);

            Element document_data = Jsoup.parse("<span>"+seria_passport+" "+number_passport+"</span>", "", Parser.xmlParser());
            doc.getElementById("document_data").appendChild(document_data);

        }catch (Exception e) {
            e.printStackTrace();
        }

        String body = doc.body().toString();
        body = body.replaceAll("<#root>", "");
        body = body.replaceAll("</#root>", "");

        System.out.println(body);
    }

    public static String month(String num) {
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

        return month_number.get(num);
    }
}
