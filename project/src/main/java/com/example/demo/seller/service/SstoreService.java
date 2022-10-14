package com.example.demo.seller.service;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.seller.dao.SstoreDao;
import com.example.demo.seller.dto.SstoreDto;
import com.example.demo.seller.entity.Sstore;
import com.example.demo.seller.exception.SstoreException;

@Service
public class SstoreService {

	@Autowired
	SstoreDao storedao;
	@Value("/Users/uhzza/ff")
	private String LogoFolder;
	@Value("http://localhost:8080/LogoImg/")
	private String profilePath;
	
	
	public void AddStore(SstoreDto.write dto, MultipartFile logoimg) {
		Sstore store = dto.toEntity();
		MultipartFile storeLogo= logoimg;
		String storeLogoName = "no image";
		
		if(storeLogo != null && storeLogo.isEmpty()==false) {
			File file = new File(LogoFolder, storeLogo.getOriginalFilename());
			try {
				storeLogo.transferTo(file);
			     storeLogoName = storeLogo.getOriginalFilename();
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}		
		}
		store.addStoreInfo(storeLogoName);
		storedao.StoreAdd(store);
	}
	
	
	
	
	public Optional<SstoreDto.Read> ReadStore(Integer sStoreNum) {
		return storedao.StoreRead(sStoreNum);
	}
	
	
	
	public void UpdateStore(SstoreDto.update dto, MultipartFile logoimg) {
		Sstore store = dto.toEntity();
		MultipartFile storeLogo = logoimg;
		
		if(storeLogo != null && storeLogo.isEmpty()==false) {
			File file = new File(LogoFolder, storeLogo.getOriginalFilename());
			try {
				storeLogo.transferTo(file);
				String storeLogoName = storeLogo.getOriginalFilename();
				store.addStoreInfo(storeLogoName);
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}	
		}
		storedao.StoreUpdate(store);
	}
	
	public Integer DeleteStore(Integer sStoreNum) {
		return storedao.StoreDelete(sStoreNum);
	}
	 
	
	public Integer OverlapStoreBnum(Integer sStoreBNum) {
		Integer cnt = storedao.StoreBNumOverlap(sStoreBNum);
			return cnt;
	}
}
