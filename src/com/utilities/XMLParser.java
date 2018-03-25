package com.utilities;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.base.Base;

public class XMLParser extends Base {
	
	public static   void ModifyContainerXML(String filepath) throws ParserConfigurationException, SAXException, IOException, TransformerException{
		
		String file = filepath;
		DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
		Document doc = docBuilder.parse(filepath);

		Node profileName = doc.getElementsByTagName("ContainerProfileName").item(0);
		profileName.setTextContent(prop.getProperty("contianer_name")+randomNumber());
		
		// write the content into xml file
				TransformerFactory transformerFactory = TransformerFactory.newInstance();
				Transformer transformer = transformerFactory.newTransformer();
				DOMSource source = new DOMSource(doc);
				StreamResult result = new StreamResult(new File(file));
				transformer.transform(source, result);

	}
	
	public static void ModifyDeviceProfileXML(String filepath) throws ParserConfigurationException, SAXException, IOException, TransformerException{
		
		String file = filepath;
		DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
		Document doc = docBuilder.parse(filepath);
		String  version ;
		
		device_profile=randomNumber()+prop.getProperty("device_profile");
		
		prop.setProperty("device_profile", device_profile);
		
		Node deviceProfile = doc.getElementsByTagName("Metadata").item(0);
		
		NodeList list = deviceProfile.getChildNodes();
		
		for(int i=0;i<=list.getLength();i++){
			
			Node node = list.item(i);
			
			if(node.getNodeName().equals("Manufacturer")){
				
			node.setTextContent(device_profile);
			}
			
			if(node.getNodeName().equals("Model")){
				
				model=prop.getProperty("model")+randomNumber();
				
				prop.setProperty("model", model);
				
			node.setTextContent(model);
			}
			if(node.getNodeName().equals("Version")){
				
				
				node.setTextContent(prop.getProperty("version"));
				break;
			}
		}
		
		// write the content into xml file
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		DOMSource source = new DOMSource(doc);
		StreamResult result = new StreamResult(new File(file));
		transformer.transform(source, result);

	}
	
	public static void ModifyDeviceProfileXMLForGenericDC(String filepath,String dcType) throws ParserConfigurationException, SAXException, IOException, TransformerException{
		
		String file = filepath;
		DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
		Document doc = docBuilder.parse(filepath);
		String  version ;
		
		
		
		Node deviceProfile = doc.getElementsByTagName("Metadata").item(0);
		
		NodeList list = deviceProfile.getChildNodes();
		
		for(int i=0;i<=list.getLength();i++){
			
			Node node = list.item(i);
			
			if(node.getNodeName().equals("TransportChannel")){
				
				if(dcType.equals("HTTP")){
			node.setTextContent(assetDetailsProp.getProperty("httpJenkinsKey"));
			break;
				}
				else{
					node.setTextContent(assetDetailsProp.getProperty("mqttJenkinsKey"));
					break;
				}
			}
		}
		
		Node transportChannels = doc.getElementsByTagName("TransportChannels").item(0);
		
		NodeList listTransport = transportChannels.getChildNodes();
		
		for(int i=0;i<=listTransport.getLength();i++){
			
			Node node = listTransport.item(i);
			
			if(node.getNodeName().equals("TransportChannel")){
				
				if(dcType.equals("HTTP")){
			node.setTextContent(assetDetailsProp.getProperty("httpJenkinsKey"));
			break;
				}
				else{
					node.setTextContent(assetDetailsProp.getProperty("mqttJenkinsKey"));
					break;
				}
			}
			
		}
		
		
		
		
		// write the content into xml file
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		DOMSource source = new DOMSource(doc);
		StreamResult result = new StreamResult(new File(file));
		transformer.transform(source, result);

	}
}
