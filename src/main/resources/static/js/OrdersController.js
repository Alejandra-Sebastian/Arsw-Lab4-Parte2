var orders = [{"tableId":1, "products":[{"Product":"PIZZA","Quantity":3, "Price":15000}, {"Product":"HOTDOG", "Quantity":1, "Price":3000}, {"Product":"COKE", "Quantity":4, "Price":1300}]}];

var OrdersControllerModule = (function () {

    var showOrdersByTable = function () {

        var callback = {

            onSuccess: function(ordersList){
                for(order in ordersList) {
                    var p = ordersList[order];
                    addOrder(p);
                }
            },
            onFailed: function(error){
                window.alert("There is a problem with our servers. We apologize for the inconvince, please try again later");
            }
        }
        RestControllerModule.getOrders(callback);
    };

    var loadOrders = function() {
        for(var i = 0; i < orders.length; i++) {
            createTable(orders[i]);
            var tv = document.getElementById("tableView");
            tv.appendChild(document.createElement("BR"));
        }
    }

    var addOrder = function(order) {
        orders.push({"tableId":order.tableId, "products":order.products});
    }

    var removeOrder = function(order) {
        for(var i = 0; i < orders.length; i++) {
            if (orders[i].tableId == tableId) {
                orders.splice(i, 1);
            }
        }
    }

    var updateOrder = function () {
        // todo implement
    };

    var deleteOrderItem = function (itemName) {
        // todo implement
    };

    var addItemToOrder = function (orderId, item) {
        // todo implement
    };

    return {
        showOrdersByTable: showOrdersByTable,
        updateOrder: updateOrder,
        deleteOrderItem: deleteOrderItem,
        addItemToOrder: addItemToOrder
    };

})();


/*
var orders = [{"tableId":1, "products":[{"Product":"PIZZA","Quantity":3, "Price":15000}, {"Product":"HOTDOG", "Quantity":1, "Price":3000}, {"Product":"COKE", "Quantity":4, "Price":1300}]}];

function createTable(order) {
    var table = document.createElement("TABLE");
    table.border = "1";
    var tableBody = document.createElement("TBODY");
    table.appendChild(tableBody);
    var tr = document.createElement("TR");
    tableBody.appendChild(tr);
    for(var key in order.products[0]) {
        var td = document.createElement("TD");
        td.width = "75";
        if (order.products[0].hasOwnProperty(key)) {
            td.appendChild(document.createTextNode(key));
            tr.appendChild(td);
        }
    }
    for(var i = 0; i < order.products.length; i++) {
        var tr = document.createElement("TR");
        tableBody.appendChild(tr);
        for(var key in order.products[i]) {
            var td = document.createElement("TD");
            td.width = "75";
            if(order.products[i].hasOwnProperty(key)) {
                if(key == "Price") {
                    td.appendChild(document.createTextNode("$" + order.products[i][key]));
                    tr.appendChild(td);
                } else {
                    td.appendChild(document.createTextNode(order.products[i][key]));
                    tr.appendChild(td);
                }
            }
        }
    }
    var tv = document.getElementById("tableView");
    tv.appendChild(table);
}

function loadOrders() {
    for(var i = 0; i < orders.length; i++) {
        createTable(orders[i]);
        var tv = document.getElementById("tableView");
        tv.appendChild(document.createElement("BR"));
    }
    axios.get("/orders")
        .then(function(response) {
            var orders = response.data;
            for(var i = 0; i < orders.length; i++) {
                addTable(orders[i]);
            }
        })
        .catch(function (error) {
            console.log("There is a problem with our servers. We apologize for the inconvince, please try again later");
        })

}

function addTable(order) {
    orders.push({"tableId":order.tableId, "products":order.products});
}

function removeTable(tableId) {
    for(var i = 0; i < orders.length; i++) {
        if(orders[i].tableId == tableId) {
            orders.splice(i, 1);
        }
    }

}
*/
