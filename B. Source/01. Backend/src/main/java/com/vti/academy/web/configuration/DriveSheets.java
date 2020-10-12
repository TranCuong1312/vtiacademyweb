//
package com.vti.academy.web.configuration;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.SheetsScopes;
import com.google.api.services.sheets.v4.model.AppendValuesResponse;
import com.google.api.services.sheets.v4.model.ValueRange;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.GeneralSecurityException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Configuration;
/**
 * This class is . 
 * 
 * @Description: .
 * @author: NVANH
 * @create_date: Sep 26, 2020
 * @version: 1.0
 * @modifer: NVAnh
 * @modifer_date: Sep 26, 2020
 */
@Configuration
public class DriveSheets {
	private static Sheets sheetsService;
	private static final String APPLICATION_NAME = "vtiAcademyWeb";
	private static final String SPREADSHEET_ID = "1x7PWqKN_Bc6LO3QvlsaSq3ZQ6G5kwqvOagS8psR4Olw";
	
	private static Credential authorize() throws IOException, GeneralSecurityException {
        InputStream in = DriveSheets.class.getResourceAsStream("/credentials.json");
        
        GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(
        		JacksonFactory.getDefaultInstance(), 
        		new InputStreamReader(in)
        );
        
        List<String> scopes = Arrays.asList(SheetsScopes.SPREADSHEETS);
        
        GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
                GoogleNetHttpTransport.newTrustedTransport(), JacksonFactory.getDefaultInstance(), clientSecrets, scopes)
                .setDataStoreFactory(new FileDataStoreFactory(new java.io.File("tokens")))
                .setAccessType("offline")
                .build();
        
        Credential credential = new AuthorizationCodeInstalledApp(flow, new LocalServerReceiver())
        						.authorize("user");

		return credential;
	}
	
	private static Sheets getSheetsService() throws IOException, GeneralSecurityException {
		Credential credential = authorize();
		
		return new Sheets.Builder(GoogleNetHttpTransport.newTrustedTransport(), 
									JacksonFactory.getDefaultInstance(), 
									credential)
				.setApplicationName(APPLICATION_NAME)
				.build();
	}
	
	public void toSheet(String name, String phone, String email) throws IOException, GeneralSecurityException {
		sheetsService = getSheetsService();
		
		LocalDateTime myDateObj = LocalDateTime.now();
	    DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
	    String formattedDate = myDateObj.format(myFormatObj);
	    
		ValueRange appendBody = new ValueRange()
								.setValues(Arrays.asList(
										Arrays.asList(name, phone, email, formattedDate)
								));
		@SuppressWarnings("unused")
		AppendValuesResponse appendResult = sheetsService.spreadsheets().values()
											.append(SPREADSHEET_ID, "register", appendBody)
											.setValueInputOption("USER_ENTERED")
											.setInsertDataOption("INSERT_ROWS")
											.setIncludeValuesInResponse(true)
											.execute();
	}

}
