package com.ics.fred.common;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;

public class ReadImageValueFromSQLFile {

	public Map<String,String> getTagValue(String templateFilePath,String fileName){
		
		String startTag="<Items>";
		String endTag ="</Items>";
		String s=new String();
		StringBuffer sbf = new StringBuffer();
		Map<String,String> itemIdandImageMap = new HashMap<String,String>();
		String contentBetweenItems =null;
		
		String filePath = templateFilePath+fileName;
			try{
			FileReader fr=new FileReader(new File(filePath));
			BufferedReader br = new BufferedReader(fr);
			
			while((s = br.readLine())!= null){
				sbf.append(s);
				}
			br.close();
			
			String content =sbf.toString();		
			int indexStartTag=content.indexOf(startTag);
			int length = startTag.length();
			int indexEndTag=content.indexOf(endTag);
			contentBetweenItems = content.substring(indexStartTag+length, indexEndTag);
			
			String[] itemArray=contentBetweenItems.split("<Item>");
			String startTagItemId="<ItemId>";
			String endTagItemId ="</ItemId>";
			int indexStartTagItemId,indexStartImage,indexEndTagItemId,indexEndTagImage;
			int lengthItemdId,lengthImage;
			String imageValue,ItemIdValue;
			String imageStartTag="<Image>";
			String imageEndTag ="</Image>";
			
			for(String itemArrayValues:itemArray){
				if(itemArrayValues.contains(imageStartTag)&&itemArrayValues.contains(startTagItemId))
				{
					indexStartTagItemId=itemArrayValues.indexOf(startTagItemId);
					lengthItemdId=startTagItemId.length();
					indexEndTagItemId=itemArrayValues.indexOf(endTagItemId);
					ItemIdValue=itemArrayValues.substring(indexStartTagItemId+lengthItemdId, indexEndTagItemId);
					
					indexStartImage=itemArrayValues.indexOf(imageStartTag);
					lengthImage=imageStartTag.length();
					indexEndTagImage=itemArrayValues.indexOf(imageEndTag);
					imageValue=itemArrayValues.substring(indexStartImage+lengthImage, indexEndTagImage);
					
					itemIdandImageMap.put(ItemIdValue, imageValue);
				}
			}
			}
			catch(Exception e){
				System.out.println(e.getMessage());
			}
			
	      return itemIdandImageMap;
	}
	
	
	

}
