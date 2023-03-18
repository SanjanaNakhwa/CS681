package edu.umb.CS681.hw01;

import java.util.ArrayList;
import java.util.List;

public class Car {
	private String make;
	private String model;
	private int mileage;
	private int year;
	private float price;
	private int dominationCount;
	
	public Car(String make, String model, int mileage, int year, float price) {
		this.make = make;
		this.model = model;
		this.mileage = mileage;
		this.year = year;
		this.price = price;
	}

	public String getMake() {
		return make;
	}

	public String getModel() {
		return model;
	}

	public int getMileage() {
		return mileage;
	}

	public int getYear() {
		return year;
	}

	public float getPrice() {
		return price;
	}

	public int getDominationCount() {
		return dominationCount;
	}
	
	public void setDominationCount(List<Car> cars) {
		this.dominationCount = 0;
		for (Car car : cars) {
			if(car.getYear() >= this.getYear() && car.getMileage() <= this.getMileage() && car.getPrice() <= this.getPrice()) {
				if(car.getYear() > this.getYear() || car.getMileage() < this.getMileage() || car.getPrice() < this.getPrice()) {
					dominationCount++;

				}
			}
		}
	}

	@Override
    public String toString() {
        return "Car{" +
                "model='" + model + '\'' +
                ", make='" + make + '\'' +
                ", mileage=" + mileage +
                ", year=" + year +
                ", price=" + price +
                '}';
    }
}
