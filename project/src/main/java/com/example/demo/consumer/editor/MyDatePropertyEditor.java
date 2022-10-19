package com.example.demo.consumer.editor;

import java.beans.PropertyEditorSupport;
import java.time.LocalDate;

public class MyDatePropertyEditor extends PropertyEditorSupport {
	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		String[] arr = text.split("-");
		Integer year = Integer.parseInt(arr[0]);
		Integer month = Integer.parseInt(arr[1]);
		Integer day = Integer.parseInt(arr[2]);
		setValue(LocalDate.of(year, month, day));
	}
}
