package com.savindi.codestatebkend;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.amazonaws.xray.spring.aop.XRayEnabled;


@SpringBootApplication
@RestController
@XRayEnabled
public class CodestatebkendApplication {
    final static String serverUrl1 = "states_hash.json";

    final static String serverUrl2 = "states_titlecase.json";
    
    final static Logger logger = LogManager.getLogger(CodestatebkendApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(CodestatebkendApplication.class, args);
    }

    public static String requestProcessedData(int urlid){
        
    
    
    	
    	
    	String result = null;
        if(urlid == 1){
        	try {
        		
        		Resource resource = new ClassPathResource(serverUrl1);
        	       
                InputStream dbAsStream = resource.getInputStream();
                
        		System.out.println("el archivo: "+serverUrl1);
        		
        		  StringBuilder textBuilder = new StringBuilder();
        		    try (Reader reader = new BufferedReader(new InputStreamReader
        		      (dbAsStream, Charset.forName(StandardCharsets.UTF_8.name())))) {
        		        int c = 0;
        		        while ((c = reader.read()) != -1) {
        		            textBuilder.append((char) c);
        		        }
        		    }
                
        		result =textBuilder.toString();
                   
                } catch (Exception e) {
                	logger.error("[ERROR] : [CUSTOM_LOG] : "+e.getMessage(), e);
                }
        }else if (urlid == 2){
        	try {
        		Resource resource = new ClassPathResource(serverUrl2);
     	       
                InputStream dbAsStream = resource.getInputStream();
                
        		System.out.println("el archivo: "+serverUrl2);
        		
        		  StringBuilder textBuilder = new StringBuilder();
        		    try (Reader reader = new BufferedReader(new InputStreamReader
        		      (dbAsStream, Charset.forName(StandardCharsets.UTF_8.name())))) {
        		        int c = 0;
        		        while ((c = reader.read()) != -1) {
        		            textBuilder.append((char) c);
        		        }
        		    }
                
        		result =textBuilder.toString();
                
             } catch (Exception e) {
            	 logger.error("[ERROR] : [CUSTOM_LOG] : "+e.getMessage(), e);
             }
        }else{
            return "ERROR";
        }

      //  RestTemplate request = new RestTemplate();
       // String result = request.getForObject(serverUrl, String.class);
        //System.out.print(serverUrl);
        return (result);
    }

  
    
    
    @GetMapping("/")
    public static String Hello(){
        return "HELLO IM DATA READER";
    }

    @GetMapping("/readDataForCode")
    public static String requestCodeData(){
        return requestProcessedData(1);
    }

    @GetMapping("/readDataForState")
    public static String requestStateData() {
        return requestProcessedData(2);
    }
}
