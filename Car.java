package demo.model;

import java.util.Date;

public class Car {
	private String engine;//"" by default
	private double speed; // 0.0 by default
	private int year; //0 by default
	private String color; //"" by default
	private int id;
	private static int idCounter = 0;
	
	//fff
	public Car/**/()
	{
		//TODO add all
//example
	}
	
	public Car(String e, double s, int y, String c)
	{
		setEngine(e);
		setSpeed(s);
		setYear(y);
		setColor(c);
		setId();
	}
	
	public int getId()
	{
		return id;
	}
	
	public void setId()
	{
		this.id = idCounter;
		idCounter++;
	}

	public String getEngine() {
		return engine;
	}

	public void setEngine(String engine) {
		this.engine = engine;
	}

	public double getSpeed() {
		return speed;
	}

	public void setSpeed(double speed) {
		if (speed >= 0 && speed <= 250)
			this.speed = speed;
		else
			this.speed = 50;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		if (year > 1700 && year < 2019)
			this.year = year;
		else {
			Date date = new Date();
			this.year = date.getYear()+1900;//119
		}

	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		if (color != null) {
			boolean isOnlyLetters = false;
			for (int i = 0; i < color.length(); i++) {
				if (Character.isLetter(color.charAt(i)))
				{
					isOnlyLetters = true;
				}
				else {
					isOnlyLetters = false;
					break;
				}
			}
			if (isOnlyLetters)
			{
				this.color = color;
			}
			else
			{
				this.color = "Red";
			}
		} else
		{
			this.color = "None";
		}

	}

	@Override
	public String toString() {
		return ""+engine +" " + speed + " km/h " + year + " " + color;
		}

}
