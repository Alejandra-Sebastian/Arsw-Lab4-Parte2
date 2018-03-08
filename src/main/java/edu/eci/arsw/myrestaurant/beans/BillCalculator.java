package edu.eci.arsw.myrestaurant.beans;

import edu.eci.arsw.myrestaurant.model.Order;
import edu.eci.arsw.myrestaurant.model.RestaurantProduct;
import org.springframework.stereotype.Service;

import java.util.Map;

public interface BillCalculator {
	
	public int calculateBill(Order o,Map<String,RestaurantProduct> productsMap) ;
	
}
