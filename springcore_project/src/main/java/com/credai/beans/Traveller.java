package com.credai.beans;

public class Traveller {
	private Vehicle vehicle;

	public Vehicle getVehicle() {
		return vehicle;
	}

	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}
	
	public void startTravel() {
		this.vehicle.ride();
	}
}
