package com.testng;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import org.apache.log4j.Logger;
import org.testng.IAnnotationTransformer;
import org.testng.annotations.ITestAnnotation;

import com.base.Base;
import com.utilities.CSVUtils;

public class TestNGAnnotationTransformer implements IAnnotationTransformer {
	
	final static Logger Log = Logger.getLogger(TestNGAnnotationTransformer.class.getName());

	String[][] testData =null;
	
	public TestNGAnnotationTransformer() throws Exception{
		
		 testData =CSVUtils.readCSV(System.getProperty("user.dir")+"//testdata//SELECT_TC_TO_RUN.csv");
	}
	
	@Override
	public void transform(ITestAnnotation annotation, Class testClass, Constructor testConstructor, Method testMethod) {
		try{
	
			//System.out.println(testMethod.getName()+",true");
		if(testMethod.getName().equals(testData[getArrayIndex(testMethod.getName())][0])){
			
			if(testData[getArrayIndex(testMethod.getName())][1].equalsIgnoreCase("false")){
			annotation.setEnabled(false);
			//System.out.println(testMethod.getName()+"false\n");
			
			}else{
				//System.out.println(testMethod.getName()+ " true\n");
				annotation.setEnabled(true);
			
			}
		}
		else{
			annotation.setEnabled(false);
			//System.out.println(testMethod.getName()+"false\n");
		}
		
		}catch(Exception e){
			throw(e);
		}
		
	}
	
	public  int getArrayIndex(String methodName){
		try{
			
			int i =0;
		for( i=0;i<testData.length;i++){
			
			if(methodName.equals(testData[i][0])){
				
				break;
			}
		}
		if(i==testData.length){
			i=i-1;
		}
		
		return i ;
		}catch(Exception e){
			throw(e);
		}
	}

}
