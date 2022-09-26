//package me.exzork.pojo;
package emu.grasscutter.command.pojo;

import com.google.gson.annotations.SerializedName;

public class WeaponStatsItem{

	@SerializedName("statValue")
	private Double statValue;

	@SerializedName("appendPropId")
	private String appendPropId;

	public Double getStatValue(){
		return statValue;
	}

	public String getAppendPropId(){
		return appendPropId;
	}
}