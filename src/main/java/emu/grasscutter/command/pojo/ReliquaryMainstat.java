//package me.exzork.pojo;
package emu.grasscutter.command.pojo;

import com.google.gson.annotations.SerializedName;

public class ReliquaryMainstat{

	@SerializedName("statValue")
	private Double statValue;

	@SerializedName("mainPropId")
	private String mainPropId;

	public Double getStatValue(){
		return statValue;
	}

	public String getMainPropId(){
		return mainPropId;
	}
}