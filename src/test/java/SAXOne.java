import javax.xml.parsers.*;
import org.xml.sax.helpers.*;
import org.xml.sax.*;
import java.io.*;
public class SAXOne{
    public static void main(String args[]){
        try {File file = new File("项目容器.xml");
            SAXParserFactory factory = SAXParserFactory.newInstance();
            factory.setNamespaceAware(true);
            factory.setValidating(true);
            SAXParser saxParser = factory.newSAXParser();
            EventHandler handler = new EventHandler();
            saxParser.parse(file,handler);
        }
        catch(Exception e){
            System.out.println(e);
        }
    }
}
class EventHandler extends DefaultHandler{
    public void startDocument(){
        System.out.println("开始解析xml文件");
    }
    public void endDocument(){
        System.out.println("解析结束");
    }
    public void startElement(String uri,String localName,String qName,Attributes atts){
        System.out.print("<"+qName+">");
    }
    public void endElement(String uri,String localName,String qName){
        System.out.print("</"+qName+">");
    }
    public void characters(char[] ch,int start,int length){
        String text = new String(ch,start,length);
        System.out.print(text);
    }
    public void ignorableWhitespace(char[] ch,int start,int length){
        String text = new String(ch,start,length);
        System.out.print(text);
    }
}
