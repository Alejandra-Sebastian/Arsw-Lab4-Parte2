/*
 * Copyright (C) 2016 Pivotal Software, Inc.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package edu.eci.arsw.myrestaurant.restcontrollers;

import edu.eci.arsw.myrestaurant.model.Order;
import edu.eci.arsw.myrestaurant.services.RestaurantOrderServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author hcadavid
 */
@RestController
@RequestMapping(value = "/orders")
public class OrdersAPIController {

    @Autowired
    private RestaurantOrderServices ros;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> manejadorGetRecursoOrders(){
        try {
            return new ResponseEntity<>(ros.getTablesWithOrders(),HttpStatus.ACCEPTED);
        } catch (Exception ex) {
            Logger.getLogger(OrdersAPIController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>("Error, no se encontraron mesas",HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(path = "/{tableId}", method = RequestMethod.GET)
    public ResponseEntity<?> manejadorGetRecursoOrdersById(@PathVariable int tableId){
        try {
            return new ResponseEntity<>(ros.getTableOrder(tableId),HttpStatus.ACCEPTED);
        } catch (Exception ex) {
            Logger.getLogger(OrdersAPIController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>("Error, no se encontro esa mesa",HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(path = "/{tableId}/total", method = RequestMethod.GET)
    public ResponseEntity<?> manejadorGetRecursoOrdersTotal(@PathVariable int tableId){
        try {
            return new ResponseEntity<>(ros.calculateTableBill(tableId),HttpStatus.ACCEPTED);
        } catch (Exception ex) {
            Logger.getLogger(OrdersAPIController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>("Error, no se pudo calcular el total",HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> manejadorPostRecursoOrders(@RequestBody Order o){
        try {
            ros.addNewOrderToTable(o);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception ex) {
            Logger.getLogger(OrdersAPIController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>("Error, no se pudo agregar la orden",HttpStatus.FORBIDDEN);
        }

    }

    @RequestMapping(path = "/{tableId}}",method = RequestMethod.PUT)
    public ResponseEntity<?> manejadorPutRecursoOrders(@PathVariable int tableId, @RequestBody String dish, @RequestBody int amount){
        try{
            Order o = ros.getTableOrder(tableId);
            o.addDish(dish, amount);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch(Exception ex){
            return new ResponseEntity<>("error", HttpStatus.FORBIDDEN);
        }
    }

    @RequestMapping(path = "/{tableId}}",method = RequestMethod.DELETE)
    public ResponseEntity<?> manejadorDeleteRecursoOrders(@PathVariable int tableId){
        try{
            ros.releaseTable(tableId);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch(Exception ex){
            return new ResponseEntity<>("error", HttpStatus.FORBIDDEN);
        }
    }
}
