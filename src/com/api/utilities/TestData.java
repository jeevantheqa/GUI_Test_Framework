package com.api.utilities;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

import org.apache.log4j.Logger;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.jayway.jsonpath.JsonPath;


public class TestData {
	final static Logger logger = Logger.getLogger(TestData.class);
	static Random randomno = new Random();
	static int r= randomno.nextInt(1000-10)+10;

	public static String getgroupcreateBody() throws IOException, InvalidFormatException, ParseException{
		FileReader fr = new FileReader(System.getProperty("user.dir")+"/testdata/API_TestData/groupCREATE.json");
		logger.info("Group body create method is invoked");
		
		JSONParser parser = new JSONParser();		
		Object obj = parser.parse(fr);		
		JSONObject jsonobject=(JSONObject) obj;	
		String rand= Integer.toString(r);
		logger.info("Random Number Generated:\t"+rand);
		
		String groupName="API_Automation_Group_"+rand;
		String createGroupBody = jsonobject.toString();
		logger.info("Fetched group body from the JSON:\t"+createGroupBody);
		
		String rn = JsonPath.read(createGroupBody, "$.m2m:grp.rn");
		logger.info("Resource Name Fetched from JSON:\t"+rn);
		
		String finalgroupBody = createGroupBody.replaceFirst(rn,groupName);
		logger.info("Message to be sent in the Create Group Body is:\t" + finalgroupBody);
		
		FileWriter wr = new FileWriter(System.getProperty("user.dir")+"/testdata/API_TestData/groupCREATE.json");
		wr.write(finalgroupBody);
		wr.flush();
		wr.close();
		return finalgroupBody;
	}


	public static String getGroupName() throws IOException, InvalidFormatException, ParseException{

		FileReader fr = new FileReader(System.getProperty("user.dir")+"/testdata/API_TestData/groupCREATE.json");
		logger.info("Get groupName Method is invoked");
		JSONParser parser = new JSONParser();		
		Object obj = parser.parse(fr);		
		JSONObject jsonobject=(JSONObject) obj;
		String createGroupBody = jsonobject.toString();
		logger.info("Fetched group body from the JSON:\t"+createGroupBody);
		String groupName = JsonPath.read(createGroupBody, "$.m2m:grp.rn");
		logger.info("Group Name fetched which will be retuned is :\t"+groupName);
		return groupName;		
	}


	public static String getcontainercreateBody() throws IOException, InvalidFormatException, ParseException{

		FileReader fr = new FileReader(System.getProperty("user.dir")+"/testdata/API_TestData/containerCREATE.json");
		logger.info("Container body create method is invoked");
		JSONParser parser = new JSONParser();		
		Object obj = parser.parse(fr);		
		JSONObject jsonobject=(JSONObject) obj;	
		String rand= Integer.toString(r);
		logger.info("Random Number Generated:\t"+rand);
		String groupName="FORM_"+rand;
		String createcontainerBody = jsonobject.toString();
		logger.info("Fetched group body from the JSON:\t"+createcontainerBody);
		String rn = JsonPath.read(createcontainerBody, "$.m2m:cnt.rn");
		logger.info("Resource Name Fetched from JSON:\t"+rn);
		String finalcontainerBody = createcontainerBody.replaceFirst(rn,groupName);
		logger.info("Message to be sent in the Create Group Body is:\t" + finalcontainerBody);
		FileWriter wr = new FileWriter(System.getProperty("user.dir")+"/testdata/API_TestData/containerCREATE.json");
		wr.write(finalcontainerBody);
		wr.flush();
		wr.close();
		return finalcontainerBody;
	}



	public static String getContainerName() throws IOException, InvalidFormatException, ParseException{

		FileReader fr = new FileReader(System.getProperty("user.dir")+"/testdata/API_TestData/containerCREATE.json");
		logger.info("Get Conatiner Name Method is invoked");
		JSONParser parser = new JSONParser();		
		Object obj = parser.parse(fr);		
		JSONObject jsonobject=(JSONObject) obj;
		String containerBody = jsonobject.toString();
		logger.info("Fetched group body from the JSON:\t"+containerBody);
		String containerName = JsonPath.read(containerBody, "$.m2m:cnt.rn");
		logger.info("Group Name fetched which will be retuned is :\t"+containerName);
		return containerName;		

	}

	public static String getsubscreateBody_GRP() throws IOException, InvalidFormatException, ParseException{
		FileReader fr = new FileReader(System.getProperty("user.dir")+"/testdata/API_TestData/groupsubscriptionCREATE.json");
		logger.info("Group Subscription body create method is invoked");
		JSONParser parser = new JSONParser();		
		Object obj = parser.parse(fr);		
		JSONObject jsonobject=(JSONObject) obj;	
		String rand= Integer.toString(r);
		logger.info("Random Number Generated:\t"+rand);
		String groupsubscritptionName="SUBSGROUP_"+rand;
		String groupsubscriptionBody = jsonobject.toString();
		logger.info("Fetched  subscription group body from the JSON:\t"+groupsubscriptionBody);
		String rn = JsonPath.read(groupsubscriptionBody, "$.m2m:sub.rn");
		logger.info("Resource Name Fetched from JSON:\t"+rn);
		String finalgroupsubscriptionBody = groupsubscriptionBody.replaceFirst(rn,groupsubscritptionName);
		logger.info("Message to be sent in the Create Group Body is:\t" + finalgroupsubscriptionBody);
		FileWriter wr = new FileWriter(System.getProperty("user.dir")+"/testdata/API_TestData/groupsubscriptionCREATE.json");
		wr.write(finalgroupsubscriptionBody);
		wr.flush();
		wr.close();
		return finalgroupsubscriptionBody;		

	}

	public static String getSubscriptionName_GRP() throws IOException, InvalidFormatException, ParseException{

		FileReader fr = new FileReader(System.getProperty("user.dir")+"/testdata/API_TestData/groupsubscriptionCREATE.json");
		logger.info("Get Subscription Name Method is invoked");
		JSONParser parser = new JSONParser();		
		Object obj = parser.parse(fr);		
		JSONObject jsonobject=(JSONObject) obj;
		String creategroupsubscriptionBody = jsonobject.toString();
		logger.info("Fetched Subscription body from the JSON:\t"+creategroupsubscriptionBody);
		String groupsubscriptionName = JsonPath.read(creategroupsubscriptionBody, "$.m2m:sub.rn");
		logger.info("Group Subscription Name fetched which will be returned is :\t"+groupsubscriptionName);
		return groupsubscriptionName;		

	}


	public static String getsubscreateBody_AE() throws IOException, InvalidFormatException, ParseException{

		FileReader fr = new FileReader(System.getProperty("user.dir")+"/testdata/API_TestData/aesubscriptionCREATE.json");
		logger.info("AE Subscription body create method is invoked");
		JSONParser parser = new JSONParser();		
		Object obj = parser.parse(fr);		
		JSONObject jsonobject=(JSONObject) obj;	
		String rand= Integer.toString(r);
		logger.info("Random Number Generated:\t"+rand);
		String aesubscritptionName="SUBSAE_"+rand;
		String aesubscriptionBody = jsonobject.toString();
		logger.info("Fetched  subscription AE body from the JSON:\t"+aesubscriptionBody);
		String rn = JsonPath.read(aesubscriptionBody, "$.m2m:sub.rn");
		logger.info("Resource Name Fetched from JSON:\t"+rn);
		String finalaesubscriptionBody = aesubscriptionBody.replaceFirst(rn,aesubscritptionName);
		logger.info("Message to be sent in the Create Subscription for AE Body is:\t" + finalaesubscriptionBody);
		FileWriter wr = new FileWriter(System.getProperty("user.dir")+"/testdata/API_TestData/aesubscriptionCREATE.json");
		wr.write(finalaesubscriptionBody);
		wr.flush();
		return finalaesubscriptionBody;	

	}
	public static String getSubscriptionName_AE() throws IOException, InvalidFormatException, ParseException{

		FileReader fr = new FileReader(System.getProperty("user.dir")+"/testdata/API_TestData/aesubscriptionCREATE.json");
		logger.info("Get AE Subscription Name Method is invoked");
		JSONParser parser = new JSONParser();		
		Object obj = parser.parse(fr);		
		JSONObject jsonobject=(JSONObject) obj;
		String aesubscriptionBody = jsonobject.toString();
		logger.info("Fetched group body from the JSON:\t"+aesubscriptionBody);
		String aesubscriptionName = JsonPath.read(aesubscriptionBody, "$.m2m:sub.rn");
		logger.info("AE's Subscription Name fetched which will be retuned is :\t"+aesubscriptionName);
		return aesubscriptionName;			

	}

	public static String getsubscreateBody_CNT() throws IOException, InvalidFormatException, ParseException{

		FileReader fr = new FileReader(System.getProperty("user.dir")+"/testdata/API_TestData/containersubscriptionCREATE.json");
		logger.info("AE Container's Subscription body create method is invoked");
		JSONParser parser = new JSONParser();		
		Object obj = parser.parse(fr);		
		JSONObject jsonobject=(JSONObject) obj;	
		String rand= Integer.toString(r);
		logger.info("Random Number Generated:\t"+rand);
		String cntsubscritptionName="SUBSCNT_"+rand;
		String cntsubscriptionBody = jsonobject.toString();
		logger.info("Fetched  subscription AE body from the JSON:\t"+cntsubscriptionBody);
		String rn = JsonPath.read(cntsubscriptionBody, "$.m2m:sub.rn");
		logger.info("Resource Name Fetched from JSON:\t"+rn);
		String finalcntsubscriptionBody = cntsubscriptionBody.replaceFirst(rn,cntsubscritptionName);
		logger.info("Message to be sent in the Create Subscription for AE conatiner's Body is:\t" + finalcntsubscriptionBody);
		FileWriter wr = new FileWriter(System.getProperty("user.dir")+"/testdata/API_TestData/containersubscriptionCREATE.json");
		wr.write(finalcntsubscriptionBody);
		wr.flush();
		return finalcntsubscriptionBody;		
	}
	
	public static String getDownlinkBody() throws IOException, InvalidFormatException, ParseException{

		FileReader fr = new FileReader(System.getProperty("user.dir")+"/testdata/API_TestData/downlinkBody.json");
		logger.info("Downlink Body Invoked");
		JSONParser parser = new JSONParser();		
		Object obj = parser.parse(fr);		
		JSONObject jsonobject=(JSONObject) obj;	
		String rand= Integer.toString(r);
		logger.info("Random Number Generated:\t"+rand);
		String cntsubscritptionName="DownLink_Automation"+rand;
		String cntsubscriptionBody = jsonobject.toString();
		logger.info("Fetched  Downlink  body from the JSON:\t"+cntsubscriptionBody);
		String rn = JsonPath.read(cntsubscriptionBody, "$.m2m:cin.con");
		logger.info("Resource Name Fetched from JSON:\t"+rn);
		String finalcntsubscriptionBody = cntsubscriptionBody.replaceFirst(rn,cntsubscritptionName);
		logger.info("Message to be sent in the Create Subscription for AE conatiner's Body is:\t" + finalcntsubscriptionBody);
		FileWriter wr = new FileWriter(System.getProperty("user.dir")+"/testdata/API_TestData/downlinkBody.json");
		wr.write(finalcntsubscriptionBody);
		wr.flush();
		return finalcntsubscriptionBody;		
	}
	
	public static String getDownlinkBody_PatternMatch() throws IOException, InvalidFormatException, ParseException{

		FileReader fr = new FileReader(System.getProperty("user.dir")+"/testdata/API_TestData/downlinkBody.json");
		logger.info("Downlink Body Invoked");
		JSONParser parser = new JSONParser();		
		Object obj = parser.parse(fr);		
		JSONObject jsonobject=(JSONObject) obj;	
		String cntBody = jsonobject.toString();
		logger.info("Fetched  Downlink  body from the JSON:\t"+cntBody);
		String content = JsonPath.read(cntBody, "$.m2m:cin.con");
		logger.info("Content Fetched from JSON:\t"+content);
		return content;		
	}
	

	
	
	public static String getSubscriptionName_CNT() throws IOException, InvalidFormatException, ParseException{
		FileReader fr = new FileReader(System.getProperty("user.dir")+"/testdata/API_TestData/containersubscriptionCREATE.json");
		logger.info("Get Container Subscription Name Method is invoked");
		JSONParser parser = new JSONParser();		
		Object obj = parser.parse(fr);		
		JSONObject jsonobject=(JSONObject) obj;
		String cntsubscriptionBody = jsonobject.toString();
		logger.info("Fetched group body from the JSON:\t"+cntsubscriptionBody);
		String cntsubscriptionName = JsonPath.read(cntsubscriptionBody, "$.m2m:sub.rn");
		logger.info("Group Subscription Name fetched which will be retuned is :\t"+cntsubscriptionName);
		return cntsubscriptionName;			

	}		



	public static String getupdateAEBody() throws IOException, InvalidFormatException, ParseException{

		FileReader fr = new FileReader(System.getProperty("user.dir")+"/testdata/API_TestData/aeUPDATE.json");
		logger.info("AE Update body generating method is invoked");
		JSONParser parser = new JSONParser();		
		Object obj = parser.parse(fr);		
		JSONObject jsonobject=(JSONObject) obj;	
		String rand= Integer.toString(r);
		logger.info("Random Number Generated:\t"+rand);
		String aeupdateName="UPDATE_"+rand;
		String aeupdateBody = jsonobject.toString();
		logger.info("Fetched AE body from the JSON:\t"+aeupdateBody);
		int referIndex=aeupdateBody.indexOf("_");
		int startIndex = referIndex-6;
		int endIndex = referIndex+3;
		String replace =aeupdateBody.substring(startIndex, endIndex);
		String updateAEBODY = aeupdateBody.replaceFirst(replace, aeupdateName);
		logger.info("Group Subscription Name fetched which will be retuned is :\t"+updateAEBODY);
		FileWriter wr = new FileWriter(System.getProperty("user.dir")+"/testdata/API_TestData/aeUPDATE.json");
		wr.write(updateAEBODY);
		wr.flush();
		return updateAEBODY;
	}

	public static String getAEUpdatelabel() throws IOException, InvalidFormatException, ParseException{

		FileReader fr = new FileReader(System.getProperty("user.dir")+"/testdata/API_TestData/aeUPDATE.json");
		logger.info("Get AE Upadted label Method is invoked");
		JSONParser parser = new JSONParser();		
		Object obj = parser.parse(fr);		
		JSONObject jsonobject=(JSONObject) obj;
		String aeupdatelabelBody = jsonobject.toString();
		logger.info("Fetched AE Update body from the JSON:\t"+aeupdatelabelBody);
		int referIndex=aeupdatelabelBody.indexOf("_");
		int startIndex = referIndex-6;
		int endIndex = referIndex+3;
		String updatelabelName = aeupdatelabelBody.substring(startIndex, endIndex).trim();
		logger.info("Group Subscription Name fetched which will be retuned is :\t"+updatelabelName);
		return updatelabelName;			
	

	}	

	public static String getupdateCNTBody() throws IOException, InvalidFormatException, ParseException{

		FileReader fr = new FileReader(System.getProperty("user.dir")+"/testdata/API_TestData/cntUPDATE.json");
		logger.info("CNT Update body generating method is invoked");
		JSONParser parser = new JSONParser();		
		Object obj = parser.parse(fr);		
		JSONObject jsonobject=(JSONObject) obj;	
		String rand= Integer.toString(r);
		logger.info("Random Number Generated:\t"+rand);
		String cntupdateName="UPDATE_"+rand;
		String cntupdateBody = jsonobject.toString();
		logger.info("Fetched update Conatiner body from the JSON:\t"+cntupdateBody);
		int referIndex=cntupdateBody.indexOf("_");
		int startIndex = referIndex-6;
		int endIndex = referIndex+3;
		String replace =cntupdateBody.substring(startIndex, endIndex);
		String updateCNTBODY = cntupdateBody.replaceFirst(replace, cntupdateName);
		logger.info("Update Container label fetched which will be retuned is :\t"+updateCNTBODY);
		FileWriter wr = new FileWriter(System.getProperty("user.dir")+"/testdata/API_TestData/cntUPDATE.json");
		wr.write(updateCNTBODY);
		wr.flush();
		return updateCNTBODY;
	}		
	

	public static String getCNTUpdatelabel() throws IOException, InvalidFormatException, ParseException{

		FileReader fr = new FileReader(System.getProperty("user.dir")+"/testdata/API_TestData/cntUPDATE.json");
		logger.info("Get CNT Upadted label Method is invoked");
		JSONParser parser = new JSONParser();		
		Object obj = parser.parse(fr);		
		JSONObject jsonobject=(JSONObject) obj;
		String cntupdatelabelBody = jsonobject.toString();
		logger.info("Fetched AE Update body from the JSON:\t"+cntupdatelabelBody);
		int referIndex=cntupdatelabelBody.indexOf("_");
		int startIndex = referIndex-6;
		int endIndex = referIndex+3;
		String updatelabelName = cntupdatelabelBody.substring(startIndex, endIndex).trim();
		logger.info("Group Subscription Name fetched which will be retuned is :\t"+updatelabelName);
		return updatelabelName;				

	}	



	public static String getsubscreateBody_Meta() throws IOException, InvalidFormatException, ParseException{

		try{
		FileReader fr = new FileReader(System.getProperty("user.dir")+"/testdata/API_TestData/subscribeMETA.json");
		logger.info("Meta data subscribe body create method is invoked");
		JSONParser parser = new JSONParser();		
		Object obj = parser.parse(fr);		
		JSONObject jsonobject=(JSONObject) obj;	
		String rand= Integer.toString(r);
		logger.info("Random Number Generated:\t"+rand);
		String metasubscritptionName="SUBSMETAS_"+rand;
		String metasubscriptionBody = jsonobject.toString();
		logger.info("Fetched meta subscription body from the JSON:\t"+metasubscriptionBody);
		String rn = JsonPath.read(metasubscriptionBody, "$.m2m:sub.rn");
		logger.info("Resource Name Fetched from JSON:\t"+rn);
		String finalmetasubscriptionBody = metasubscriptionBody.replaceFirst(rn,metasubscritptionName);
		logger.info("Message to be sent in the meta subcription test Body is:\t" + finalmetasubscriptionBody);
		FileWriter wr = new FileWriter(System.getProperty("user.dir")+"/testdata/API_TestData/subscribeMETA.json");
		wr.write(finalmetasubscriptionBody);
		wr.flush();
		return finalmetasubscriptionBody;	
		
		}catch(Exception e){
			logger.error("Unbale to get JSON body for Subscription Meta Data");
			logger.error(e.toString());
			throw(e);
			
		}

	}
	public static String getSubscriptionName_meta() throws IOException, InvalidFormatException, ParseException{

		FileReader fr = new FileReader(System.getProperty("user.dir")+"/testdata/API_TestData/subscribeMETA.json");
		logger.info("Get META Subscription Name Method is invoked");
		JSONParser parser = new JSONParser();		
		Object obj = parser.parse(fr);		
		JSONObject jsonobject=(JSONObject) obj;
		String metasubscriptionBody = jsonobject.toString();
		logger.info("Fetched group body from the JSON:\t"+metasubscriptionBody);
		String metasubscriptionName = JsonPath.read(metasubscriptionBody, "$.m2m:sub.rn");
		logger.info("Group Subscription Name fetched which will be retuned is :\t"+metasubscriptionName);
		return metasubscriptionName;					

	}
	
	public static String getHttpUplinkBody() throws IOException, InvalidFormatException, ParseException{

		FileReader fr = new FileReader(System.getProperty("user.dir")+"/testdata/API_TestData/httpUplinkBody.json");
		logger.info("Uplink Body Invoked");
		JSONParser parser = new JSONParser();		
		Object obj = parser.parse(fr);		
		JSONObject jsonobject=(JSONObject) obj;	
		String rand= Integer.toString(r);
		logger.info("Random Number Generated:\t"+rand);
		String uplinkMessage="Uplink_Automation"+rand;
		String uplinkbody = jsonobject.toString();
		logger.info("Fetched  Downlink  body from the JSON:\t"+uplinkbody);
		String rn = JsonPath.read(uplinkbody, "$.m2m:cin.con");
		logger.info("Resource Name Fetched from JSON:\t"+rn);
		String finalcntsubscriptionBody = uplinkbody.replaceFirst(rn,uplinkMessage);
		logger.info("Message to be sent in the Http uplink Body is:\t" + finalcntsubscriptionBody);
		FileWriter wr = new FileWriter(System.getProperty("user.dir")+"/testdata/API_TestData/httpUplinkBody.json");
		wr.write(finalcntsubscriptionBody);
		wr.flush();
		return finalcntsubscriptionBody;		
	}
	
	@SuppressWarnings("deprecation")
	public static String getHttpUplinkBody_Encrypt(String deviceEUI, String devAddr) throws IOException, InvalidFormatException, ParseException {

		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(DeserializationFeature.UNWRAP_ROOT_VALUE, true);
		JsonFactory jsonFactory = new JsonFactory();
		
		JsonParser jp = jsonFactory.createJsonParser(new File(System.getProperty("user.dir")+"\\testdata\\API_TestData\\EncrypDecryptUplinkBody.json"));
		jp.setCodec(new ObjectMapper());
		JsonNode jsonNode = jp.readValueAsTree();
		
		String con = "{\"payloads_ul\":{\"id\":1509042613312,\"deveui\":\""+deviceEUI+"\",\"timestamp\":\"2017-10-26T18:30:13.312Z\",\"devaddr\": \""+devAddr+"\",\"live\": true,\"dataFrame\":\"AAosMAAA\",\"fcnt\":11577,\"port\":1,\"rssi\":-16,\"snr\":10,\"freq\": 865062500,\"sf_used\":7,\"dr_used\": \"SF11BW125\",\"cr_used\":\"4/5\",\"device_redundancy\":1,\"time_on_air_ms\":56.576,\"decrypted\":true},\"userid\":\"hpe_test\"}";
				
		changePayloadContent(jsonNode, "con", con);		
		
		logger.info("Modified Payload content ::: "+ jsonNode);

		return mapper.writeValueAsString(jsonNode);
	}

	
	
	@SuppressWarnings("deprecation")
	public static String getDownlinkBody_Encrypt(String deviceEUI,String devAddr) throws IOException, InvalidFormatException, ParseException{

		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(DeserializationFeature.UNWRAP_ROOT_VALUE, true);
		JsonFactory jsonFactory = new JsonFactory();
		
		JsonParser jp = jsonFactory.createJsonParser(new File(System.getProperty("user.dir")+"\\testdata\\API_TestData\\EncrypDecryptDownlinkBody.json"));
		jp.setCodec(new ObjectMapper());
		JsonNode jsonNode = jp.readValueAsTree();
		
		String con = "{\"payload_dl\":{\"deveui\":\""+deviceEUI+"\",\"devaddress\":\""+devAddr+"\",\"fcnt\":1157,\"port\":10,\"confirmed\":false,\"data\":\"AA0205\",\"on_busy\":\"fail\",\"tag\":\"assigned tag\"}}";
				
		changePayloadContent(jsonNode, "con", con);	
		
		logger.info("Modified Payload content ::: "+ jsonNode);

		return mapper.writeValueAsString(jsonNode);		
	}
	
	public static void changePayloadContent(JsonNode parent, String fieldName, String newValue) throws JsonProcessingException, IOException {
		logger.debug("Start of change");
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(DeserializationFeature.UNWRAP_ROOT_VALUE, true);
		if (parent.has(fieldName)) {
			try {
				JsonNode jsonNode = mapper.readTree(newValue);
				((ObjectNode) parent).put(fieldName, jsonNode);
			} catch (Exception e) {
				logger.info("GenericFlow::replace::NewValue is not JSON String");
				((ObjectNode) parent).put(fieldName, newValue);
			}
		}
		for (JsonNode child : parent) {
			changePayloadContent(child, fieldName, newValue);
		}
		logger.debug("End of change");
	}	
	
}


