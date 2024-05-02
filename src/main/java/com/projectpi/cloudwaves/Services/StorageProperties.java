package com.projectpi.cloudwaves.Services;

import org.springframework.boot.context.properties.ConfigurationProperties;


@ConfigurationProperties("storage")
public class StorageProperties {

	/**
	 * Folder location for storing files
	 */
	private String location = "C:\\Users\\akerm\\OneDrive\\Images\\Captures d’écran";

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

}
