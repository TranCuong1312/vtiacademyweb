
package com.vti.academy.web.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;
import java.util.Locale;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;

public class Utils {

	private static final Logger LOG = LoggerFactory.getLogger(Utils.class);
	private static final String DEFAULT_FORMATMONEY = "###,###";
	public static final String MMDDYYYYHHMMSS = "MM/dd/yyyy HH:mm:ss";
	public static final String MMDDYYYY = "MM/dd/yyyy";
	
	private static final Logger log = LoggerFactory.getLogger(Utils.class);

	private Utils() {

	}

	/**
	 * Get Format money
	 * 
	 * @param inputValue
	 * @return string
	 */
	public static String getFormatMoney(final Long inputValue) {
		if (inputValue == null) {
			return "";
		}
		final DecimalFormat decimalFormat = new DecimalFormat(DEFAULT_FORMATMONEY);
		decimalFormat.setDecimalFormatSymbols(new DecimalFormatSymbols(Locale.ITALY));
		return decimalFormat.format(inputValue);
	}

	/**
	 * Compare date
	 * 
	 * @param d1
	 * @param d2
	 * @return true|false
	 */
	public static boolean compareDate(Date d1, Date d2) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		return sdf.format(d1).equals(sdf.format(d2));
	}

	/**
	 * Convert Object to String json
	 * 
	 * @param obj
	 * @return string
	 */
	public static String toJson(Object obj) {
		String jsonInString = null;
		try {
			if (obj == null) {
				return "";
			}
			ObjectMapper mapper = new ObjectMapper();
			jsonInString = mapper.writeValueAsString(obj);
		} catch (Exception ex) {
			LOG.error("Exception! Cannot to json Reason {}", ex.getMessage());
		}
		return jsonInString;
	}

	/**
	 * @param inputStream
	 * @return a json object from an input stream
	 */
	public static JSONObject toJsonObject(InputStream inputStream) {
		try {
			BufferedReader streamReader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
			StringBuilder responseStrBuilder = new StringBuilder();

			String inputStr;
			while ((inputStr = streamReader.readLine()) != null) {
				responseStrBuilder.append(inputStr);
			}

			JSONObject jsonObject = new JSONObject(responseStrBuilder.toString());
			return jsonObject;
		} catch (IOException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		}

		// if something went wrong, return null
		return null;
	}

	public static byte[] getBytes(final ByteBuffer buffer) {
		byte[] dest = new byte[buffer.remaining()];
		buffer.get(dest);
		return dest;
	}
	
	public static String convertToString(InputStream inputStream, Charset charset) throws IOException {
		StringBuilder stringBuilder = new StringBuilder();
		String line = null;

		try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, charset))) {
			while ((line = bufferedReader.readLine()) != null) {
				stringBuilder.append(line);
			}
		}

		return stringBuilder.toString();
	}
	
	public static String decode(String encodedString) {
	    return new String(Base64.getUrlDecoder().decode(encodedString));
	}
	
	public static void zipFolder(String sourceFolder, String zipFolder){
        // Creating a ZipOutputStream by wrapping a FileOutputStream
        try (FileOutputStream fos = new FileOutputStream(zipFolder); 
            ZipOutputStream zos = new ZipOutputStream(fos, Charset.forName("SHIFT-JIS"))) {
            Path sourcePath = Paths.get(sourceFolder);
            // Walk the tree structure using WalkFileTree method
            Files.walkFileTree(sourcePath, new SimpleFileVisitor<Path>(){
                @Override
                // Before visiting the directory create the directory in zip archive
                public FileVisitResult preVisitDirectory(final Path dir, final BasicFileAttributes attrs) throws IOException {
                    // Don't create dir for root folder as it is already created with .zip name 
                    if(!sourcePath.equals(dir)){
                        System.out.println("Directory- " + dir);
                        zos.putNextEntry(new ZipEntry(sourcePath.relativize(dir).toString() + "/"));                  
                        zos.closeEntry();    
                    }
                    return FileVisitResult.CONTINUE;
                }
                @Override
                // For each visited file add it to zip entry
                public FileVisitResult visitFile(final Path file, final BasicFileAttributes attrs) throws IOException {
                    System.out.println("File Name- " + sourcePath.relativize(file).toString());
                    zos.putNextEntry(new ZipEntry(sourcePath.relativize(file).toString()));
                    Files.copy(file, zos);
                    zos.closeEntry();
                    return FileVisitResult.CONTINUE;
                }});
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
	/*
	 * Copy Image From URL to Folder template
	 * 
	 * @Param : urlImage, pathFolder, fileName
	 */
	public static void copyImageToTemplateFolder(String urlImage, String pathFolder, String fileName) {
		try (InputStream in = new URL(urlImage).openStream()) {
			Files.copy(in, Paths.get(pathFolder + "//" + fileName));
		} catch (IOException e) {
		}
	}

	/*
	 * Create Folder
	 * 
	 * @Param : path folder
	 * 
	 * @return: true/false
	 */
	public static boolean createFolder(String path) {
		log.info("createFolder = {}", path);
		boolean result = true;
		File file = new File(path);
		if (!file.exists()) {
			result = file.mkdir();
		}
		log.info("result = {}", result);
		return result;
	}
	
	public static boolean deleteDirectory(File directoryToBeDeleted) {
	    File[] allContents = directoryToBeDeleted.listFiles();
	    if (allContents != null) {
	        for (File file : allContents) {
	            deleteDirectory(file);
	        }
	    }
	    return directoryToBeDeleted.delete();
	}
}
