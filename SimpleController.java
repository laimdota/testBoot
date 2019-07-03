package demo.controller;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import demo.model.Car;

//controller class

//asas
//blb

///blalaal
@Controller
public class SimpleController {
	/**list for all cars in system*/
	ArrayList<Car> allCars = new ArrayList<>();
	
	/**controller method for page hello.html showing*/
	@GetMapping(value="/hello") // localhost:8080/hello - url link
	public String hello()//only method
	{
		System.out.println("System is working");// Syso and ctrl+space
		return "hello";//this is html page - hello.html
	}
	
	/**controller method for String passing from controller to welcome.html page*/
	@GetMapping(value="/welcome") //localhost:8080/welcome - url link
	public String welcome(Model model)
	{
		model.addAttribute("packet", "Message from Bootcamp First day!!!");//packet - message name, "Message" - inside the packet
		return "welcome";//hmtl page welcome.html
	}
	
	/*-----------------------------------*/
	//CAR CRUD implementation - create (add), read (view), update, delete
	/*-----------------------------------*/
	
	/*-----------------------------------*/
	//READ (view) functionality
	/*-----------------------------------*/
	/**controller method for one dummy car passing from controller to onecarview.html page*/
	@GetMapping(value="/onecarview")//localhost:8080/onecarview
	public String onecarview(Model model){
		Car car = new Car("gasoline", 130.5, 2017, "Green" );
		model.addAttribute("object", car);
		return "onecarview";//onecarview.html
	}
	
	/**controller method for list of cars passing from controller to allcarsview.html page*/
		@GetMapping(value="/allcarsview")//localhost:8080/allcarsview
		public String allcarsview(Model model){
			model.addAttribute("object", allCars);
			return "allcarsview";//allcarsview.html
		}
		
		/**controller method for one car from list passing from controller to onecarview.html page*/
		@GetMapping(value="/allcarsview/{id}")
		public String allcarsviewById(@PathVariable(name="id") int id, Model model)
		{
			//find car by id and set as attribute to model
			System.out.println(id);
			for (int i = 0; i < allCars.size(); i++) {
				if(allCars.get(i).getId()==id)
				{
					model.addAttribute("object", allCars.get(i));
					break;
				}
			}
			return "onecarview";//onecarview.html page
		}
		
	/*-----------------------------------*/
	//CREATE (add) functionality
	/*-----------------------------------*/
	/** get controller method for new car insertion - showing the addnewcar.html page with empty car object*/
	@GetMapping(value="/addnewcar")//localhost:8080/addnewcar
	public String addcarGet(Car car)//empty car object
	{		
		return "addnewcar";//addnewcar.html
	}
	
	/** post controller method for new car insertion - get filled object "car" from view addnewcar.html and create new car with the same variable (to manage unique id)*/
	@PostMapping(value="/addnewcar")//after submit button pressed
	public String addcarPost(Car car)//filled car object
	{
		Car tempcar = new Car(car.getEngine(), car.getSpeed(), car.getYear(), car.getColor());
		allCars.add(tempcar);
		return "redirect:/allcarsview";
	}
	
	/*-----------------------------------*/
	//UPDATE functionality
	/*-----------------------------------*/
	
	/** get controller method for car updating - filtered by id set the car as model and pass to update.html page*/
	@GetMapping(value="/update/{id}")//localhost:8080/update/1
	public String updateCarGet(@PathVariable (name="id") int id, Model model)
	{
		Car car = null;//define car object and initialise to object found by id
		for(int i = 0; i < allCars.size();i++)
		{
			if(allCars.get(i).getId()==id)
			{
				car = allCars.get(i);
				break;
			}
		}
		model.addAttribute("car", car);
		return "update";//update.html
	}
	
	/**post controller method for changed variable storing for car - get all variables from car object and id from @pathvariable */
	@PostMapping(value="/update/{id}")
	public String updateCarPost(Car car, @PathVariable (name="id") int id)
	{
		//find the car in the list and update the variables
		for (int i = 0; i < allCars.size(); i++) {
			if(allCars.get(i).getId() == id)
			{
				allCars.get(i).setEngine(car.getEngine());
				allCars.get(i).setSpeed(car.getSpeed());
				allCars.get(i).setYear(car.getYear());
				allCars.get(i).setColor(car.getColor());
				break;
			}
		}
		return "redirect:/allcarsview";
	}
	
	/*-----------------------------------*/
	//DELETE functionality
	/*-----------------------------------*/
	/**get controller method for deleting the car by id - filtered by id and remove it from the list, call all to get all variables from car object and id from @pathvariable */
	@GetMapping(value="/delete/{id}")//localhost:8080/delete/1
	public String deleteCarGet(@PathVariable (name="id") int id, Model model)
	{
		Car car = null;
		for(int i = 0; i < allCars.size();i++)
		{
			if(allCars.get(i).getId()==id)
			{
				allCars.remove(i);
				break;
			}
		}
		return allcarsview(model);
	}
	
	
	
	
	
	

}
