//package me.exzork.pojo;
package emu.grasscutter.command.pojo;

import com.google.gson.annotations.SerializedName;

public class FetterInfo{

	@SerializedName("expLevel")
	private Double expLevel;

	public Double getExpLevel(){
		return expLevel;
	}
}