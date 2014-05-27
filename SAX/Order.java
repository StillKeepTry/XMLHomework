import java.io.File;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.jar.Attributes;
import javax.xml.*;
import javax.xml.parsers.*;
import org.xml.sax.*;
import org.xml.sax.helpers.DefaultHandler;

/** 
 * @author: skt E-mail:sktsxy@gmai.com
 * @Create：May 26, 2014 9:08:44 PM 
 * @filename: Order.java
 * run way: 
 *       javac Order.java
 *       java Order
 */

public class Order {
	static class OrderHander extends DefaultHandler {
		Scanner in;
		PrintWriter out;
		String type;
		boolean op1, op2;
		
		public OrderHander(Scanner in, PrintWriter out) {
			this.in = in;
			this.out = out;
			op1 = op2 = false;
		}

		@Override
		public void startElement(String uri, String localName, String qName, org.xml.sax.Attributes attributes) {
			if (qName == "StockSymbol") {
				op1 = true;
				op2 = false;
			} else if (qName == "Quantity") {
				op1 = false;
				op2 = true;
			} else {
				op1 = op2 = false;
			}
		}
		
		@Override
		public void endElement(String uri, String localName, String qName) {
			if (qName == "Quantity") {
				op2 = false;
			}
		}
		
		public void characters(char[] ch, int start, int length) {
			String s = new String(ch, start, length);
			s = s.replace(" ", "");
			s = s.replace("\n", "");
			if (op1 == true) {
				type = s;
				if (!hash.containsKey(s)) {
					hash.put(s, 0);
				}
 			}
			if (op2 == true) {
				Integer now = hash.get(type);
				now = now + Integer.parseInt(s);
				hash.put(type, now);
			}
		}
	}
	
	static SAXParserFactory factory; 
	
	static SAXParser parser;
	
	static HashMap<String, Integer> hash;
	
	static DefaultHandler handler;
	
	public static void work(Scanner in, PrintWriter out) {
		try {
			hash = new HashMap<String, Integer>();
			factory = SAXParserFactory.newInstance();
			parser = factory.newSAXParser();
			handler = new OrderHander(in, out);
			parser.parse(new File("Orders.xml"), handler);
			Iterator<Entry<String, Integer>> it = hash.entrySet().iterator();
			while (it.hasNext()) {
				Entry<String, Integer> entry = (Entry<String, Integer>)it.next();
				String name = entry.getKey();
				Integer quantity = entry.getValue();
				out.println("股票代号=" + name + ";订购总数＝" + quantity);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		work(in, out);
		in.close();
		out.close();
	}
}

