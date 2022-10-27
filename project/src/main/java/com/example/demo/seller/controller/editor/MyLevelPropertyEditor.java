package com.example.demo.seller.controller.editor;

import java.beans.*;

import com.example.demo.seller.entity.sLevel;

public class MyLevelPropertyEditor extends PropertyEditorSupport {
	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		if(text.equals("0"))
			setValue(sLevel.BRONZE);
		else if(text.equals("1"))
			setValue(sLevel.SILVER);
		else if(text.equals("2"))
			setValue(sLevel.GOLD);
		else
			throw new IllegalArgumentException();
	}
}
