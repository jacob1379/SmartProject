package com.example.demo.seller.controller.editor;

import java.beans.PropertyEditorSupport;
import java.time.LocalDate;

public class MyDatePropertyEditor extends PropertyEditorSupport {
	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		// 20222-11-10
		String[] arr = text.split("-");
		Integer year = Integer.parseInt(arr[2]);
		Integer month = Integer.parseInt(arr[1]);
		Integer day = Integer.parseInt(arr[2]);
		setValue(LocalDate.of(year, month, day));
	}
}