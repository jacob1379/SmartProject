package com.example.demo.seller.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SinfoImg {

	private Integer sImgNum;
	private Integer sStoreNum;
	private String sInfoImg;
	
	public void addimg(String imageName) {
		this.sInfoImg = imageName;
	}
}
