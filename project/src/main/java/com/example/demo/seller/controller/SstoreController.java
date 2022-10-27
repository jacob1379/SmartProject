package com.example.demo.seller.controller;



import java.io.File;
import java.nio.file.Files;
import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.seller.dto.RestResponse;
import com.example.demo.seller.dto.SstoreDto;
import com.example.demo.seller.dto.SstoreDto.Read;
import com.example.demo.seller.service.SstoreService;

@Validated
@Controller
public class SstoreController {

	@Autowired
	private SstoreService service;
	
	String prin = "test1";
	
	
	@PostMapping(value="/store/new" , produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<RestResponse> write( SstoreDto.write dto, MultipartFile logoimg) {
		
		System.out.println(logoimg);
		Integer store = service.AddStore(dto, logoimg, prin);
		return ResponseEntity.ok(new RestResponse("OK", store, "/"));
	}
	
	@PutMapping(value="/store/update", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<RestResponse> update (SstoreDto.update dto , MultipartFile logoimg) {
		service.UpdateStore(dto,logoimg, prin);
		return ResponseEntity.ok(new RestResponse("OK", "변경이 완료되었습니다.", "/"));
	}
	
	@DeleteMapping(value="/store/delete", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<RestResponse> delete(Integer sStoreNum) {
		service.DeleteStore(sStoreNum);
		return ResponseEntity.ok(new RestResponse("OK", "가게 삭제 성공", "/"));
	}
	
	@GetMapping(value="/store" , produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<RestResponse> read(Integer sStoreNum) {
	 Read store = service.ReadStore(sStoreNum);
		return ResponseEntity.ok(new RestResponse("OK", store, null));
	}
	
	private MediaType getMediaType(String imagename) {
		int position = imagename.lastIndexOf(".");
		String ext = imagename.substring(position+1).toUpperCase();
		if(ext.equals("JPG"))
			return MediaType.IMAGE_JPEG;
		else if(ext.equals("PNG"))
			return MediaType.IMAGE_PNG;
		else 
			return MediaType.IMAGE_GIF;
	}
	@GetMapping(path="/LogoImg/{imagename}", produces=MediaType.APPLICATION_OCTET_STREAM_VALUE)
	public ResponseEntity<byte[]> showLogo(@PathVariable String imagename) {
		File file = new File("/Users/uhzza/ff", imagename);
		if(file.exists()==false)
			return null;
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(getMediaType(imagename));
		headers.add("Content-Disposition", "inline;filename="+imagename);
		try {
			return ResponseEntity.ok().headers(headers)
					.body(Files.readAllBytes(file.toPath()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
