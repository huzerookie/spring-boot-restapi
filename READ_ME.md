#Notes

##Difference between @Autowired and Constructor Injection
--- Classes with @Component are saved as beans in application context during @ComponentScan phase
--- When saving these classes as beans, if the class has no constructor, Spring by default creates a default constructor and creates an instance of it
--- Later Spring scans for all the classes having @Autowired annotation. When Spring finds a class having @Autowired annotation, it forces the class's constructor and makes it parameterized constructor using Reflection APIs.
--- To avoid this problem, Spring later came up with setting objects using constructor injection. Since all the classes are being marked with the @Component annotation, and parameterized constructor is created in classes requiring a dependency, it becomes easy for spring to provide instances.
--- Say for eg. Constructor Setting is like making a car from scratch: It requires Wheels, Engine, Steering etc
	class Car{
		Engine engine;
		Wheels wheels;
		Steering steering;
		public Car(Engine engine, Wheels wheels, Steering steering){
		//Setter logic
		}
	}

--- And @Autowired is like an empty Car is created by Spring. And later it tries to forcefully add the parts (Engine, Wheel, Steering)





