package com.property;

import jakarta.annotation.Nonnull;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Size;

@Entity
public class Property {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String fullAddress;

	@Nonnull
	@Size(min = 1, max = 2147483647, message = "Value must be between 1 and 2147483647 characters")
	private String prefecture;

	@Nonnull
	@Size(min = 1, max = 2147483647, message = "Value must be between 1 and 2147483647 characters")
	private String city;

	@Nonnull
	@Size(min = 1, max = 2147483647, message = "Value must be between 1 and 2147483647 characters")
	private String town;

	@Nonnull
	@Size(min = 1, max = 2147483647, message = "Value must be between 1 and 2147483647 characters")
	private String chome;

	@Nonnull
	@Size(min = 1, max = 2147483647, message = "Value must be between 1 and 2147483647 characters")
	private String danchi;

	@Nonnull
	@Size(min = 1, max = 2147483647, message = "Value must be between 1 and 2147483647 characters")
	private String go;

	@Nonnull
	@Size(min = 1, max = 2147483647, message = "Value must be between 1 and 2147483647 characters")
	private String building;

	@Nonnull
	@Size(min = 1, max = 2147483647, message = "Value must be between 1 and 2147483647 characters")
	private String price;

	@Nonnull
	@Size(min = 1, max = 2147483647, message = "Value must be between 1 and 2147483647 characters")
	private String nearestStation;

	@Nonnull
	@Size(min = 1, max = 2147483647, message = "Value must be between 1 and 2147483647 characters")
	private String propertyType;

	@Nonnull
	@Size(min = 1, max = 2147483647, message = "Value must be between 1 and 2147483647 characters")
	private String landArea;

	// Getters and setters
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFullAddress() {
		return fullAddress;
	}

	public void setFullAddress(String fullAddress) {
		this.fullAddress = fullAddress;
	}

	public String getPrefecture() {
		return prefecture;
	}

	public void setPrefecture(String prefecture) {
		this.prefecture = prefecture;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getTown() {
		return town;
	}

	public void setTown(String town) {
		this.town = town;
	}

	public String getChome() {
		return chome;
	}

	public void setChome(String chome) {
		this.chome = chome;
	}

	public String getDanchi() {
		return danchi;
	}

	public void setDanchi(String danchi) {
		this.danchi = danchi;
	}

	public String getGo() {
		return go;
	}

	public void setGo(String go) {
		this.go = go;
	}

	public String getBuilding() {
		return building;
	}

	public void setBuilding(String building) {
		this.building = building;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getNearestStation() {
		return nearestStation;
	}

	public void setNearestStation(String nearestStation) {
		this.nearestStation = nearestStation;
	}

	public String getPropertyType() {
		return propertyType;
	}

	public void setPropertyType(String propertyType) {
		this.propertyType = propertyType;
	}

	public String getLandArea() {
		return landArea;
	}

	public void setLandArea(String landArea) {
		this.landArea = landArea;
	}
}