package bidfood.co.nz.helper;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import org.openqa.selenium.WebDriver;

import bidfood.co.nz.pages.Checkout;
import bidfood.co.nz.pages.Home;
import bidfood.co.nz.pages.Login;
import bidfood.co.nz.runner.RunSetup;

public class BaseClass {

	protected Helper objHelper;
	protected Login objLogin;
	protected Home objHome;
	protected Checkout objCheckout;

	public BaseClass() throws Exception 

	{
		objHelper = (Helper) loadComponent("Helper");
		objLogin = (Login) loadPage("Login");
		objHome = (Home) loadPage("Home");
		objCheckout = (Checkout) loadPage("Checkout");
		
	}

	public <T> Object loadPage(String criteria) throws IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException{

			

			String ClassName = "bidfood.co.nz.pages."+criteria;

	        Object clazz = null;

	        try {

	        	Class<?> cl = Class.forName(ClassName);

	            Constructor<?> cons = cl.getConstructor(WebDriver.class);

	            clazz = cons.newInstance(RunSetup.driver);

	        } catch (ClassNotFoundException e) {

	            e.printStackTrace();

	        }

	        return clazz;
   }

	

	public <T> Object loadComponent(String criteria) throws IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException{

		String ClassName = "bidfood.co.nz.helper."+criteria;

        Object clazz = null;

        try {

        	Class<?> cl = Class.forName(ClassName);

            Constructor<?> cons = cl.getConstructor(WebDriver.class);

            clazz = cons.newInstance(RunSetup.driver);

        } catch (ClassNotFoundException e) {

            e.printStackTrace();

        }

        return clazz;





    }


	

}
