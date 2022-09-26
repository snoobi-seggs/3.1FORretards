//package me.exzork.pojo;
package emu.grasscutter.command.pojo;

import com.google.gson.annotations.SerializedName;

public class ProfilePicture{

	@SerializedName("avatarId")
	private Double avatarId;

	public Double getAvatarId(){
		return avatarId;
	}
}