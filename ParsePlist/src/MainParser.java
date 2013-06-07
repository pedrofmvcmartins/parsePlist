import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.Format;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;
import org.dom4j.tree.DefaultElement;
import org.omg.CORBA.NVList;


public class MainParser {
	
	private static String readPlistFromAssets() {
		  StringBuffer sb = new StringBuffer();
		  BufferedReader br=null;
		  try {
		    br = new BufferedReader(new InputStreamReader(new FileInputStream("C:\\Users\\Pedro\\Downloads\\Stands.plist"),"UTF-8")); 
		   String temp;
		   while ((temp = br.readLine()) != null)
		    sb.append(temp);
		  } catch (IOException e) {
		   e.printStackTrace();
		  } finally {
		   try {
		    br.close(); // stop reading
		   } catch (IOException ex) {
		    ex.printStackTrace();
		   }
		  }
		  return sb.toString();
		 }
	
	public static void main(String [ ] args)
	{
		
		String xmlString = readPlistFromAssets();
	    try {
			Document document = DocumentHelper.parseText(xmlString);
			List list = document.getRootElement().selectNodes( "//array/dict" );
			System.out.println(list.size());			
			
			Document outDocument = DocumentHelper.createDocument();
		    Element stands = outDocument.addElement("stands");
			
		    for (Iterator iter = list.iterator(); iter.hasNext(); ) {
		    	Element elem = (Element) iter.next();
				System.out.println(elem.getName());
				
				int i = 0;
				
				String namekey = elem.node(i).getText();i++;
				String nameValue = elem.node(i).getText();i++;			
				Node name = DocumentHelper.createElement(namekey).addText(nameValue);
				
				String addresskey = elem.node(i).getText();i++;	
				String addressValue = elem.node(i).getText();i++;					
				Node address = DocumentHelper.createElement(addresskey).addText(addressValue);
				
				String postal_codekey = elem.node(i).getText();i++;	
				String postal_codeValue = elem.node(i).getText();i++;					
				Node postal_code = DocumentHelper.createElement(postal_codekey).addText(postal_codeValue);
				
				String citykey = elem.node(i).getText();i++;	
				String cityValue = elem.node(i).getText();i++;					
				Node city = DocumentHelper.createElement(citykey).addText(cityValue);
				
				String phone_saleskey = elem.node(i).getText();i++;	
				String phone_salesValue = elem.node(i).getText();i++;					
				Node phone_sales = DocumentHelper.createElement(phone_saleskey).addText(phone_salesValue);
				
				String phone_serviceskey = elem.node(i).getText();i++;	
				String phone_servicesValue = elem.node(i).getText();i++;					
				Node phone_services = DocumentHelper.createElement(phone_serviceskey).addText(phone_servicesValue);
				
				String fax_saleskey = elem.node(i).getText();i++;	
				String fax_salesValue = elem.node(i).getText();i++;					
				Node fax_sales = DocumentHelper.createElement(fax_saleskey).addText(fax_salesValue);
				
				String fax_serviceskey = elem.node(i).getText();i++;	
				String fax_servicesValue = elem.node(i).getText();i++;					
				Node fax_services = DocumentHelper.createElement(fax_serviceskey).addText(fax_servicesValue);
				
				String email_saleskey = elem.node(i).getText();i++;	
				String email_salesValue = elem.node(i).getText();i++;					
				Node email_sales = DocumentHelper.createElement(email_saleskey).addText(email_salesValue);
				
				String email_serviceskey = elem.node(i).getText();i++;	
				String email_servicesValue = elem.node(i).getText();i++;					
				Node email_services = DocumentHelper.createElement(email_serviceskey).addText(email_servicesValue);
				
				String latValue = elem.selectSingleNode( ".//dict/string[1]" ).getText();
				String lngValue = elem.selectSingleNode( ".//dict/string[2]" ).getText();
				Node lat = DocumentHelper.createElement("latitude").addText(latValue);
				Node lng = DocumentHelper.createElement("longitude").addText(lngValue);
				
				Element stand = DocumentHelper.createElement("stand");
				stand.add(name);
				stand.add(address);
				stand.add(postal_code);
				stand.add(city);
				stand.add(phone_sales);
				stand.add(phone_services);
				stand.add(fax_sales);
				stand.add(fax_services);
				stand.add(email_sales);
				stand.add(email_services);
				stand.add(lat);
				stand.add(lng);
				
				stands.add(stand);
	        }
		    OutputFormat format = new OutputFormat("\t", true, "UTF-8");
		    XMLWriter writer = new XMLWriter(
		            new FileWriter( "C:\\code\\stands.xml" ),
		            format
	        );
	        writer.write( outDocument );
	        writer.close();
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
