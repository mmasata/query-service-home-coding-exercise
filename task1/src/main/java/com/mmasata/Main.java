package com.mmasata;


import com.mmasata.utils.LinkedList;
import com.mmasata.utils.List;
import spark.Route;

import static spark.Spark.get;
import static spark.Spark.put;

public class Main {

    private static final String MESSAGE_TEMPLATE = "{\"message\" : \"%s\"}";
    private static final String APPLICATION_JSON = "application/json";

    private static final List linkedList = new LinkedList();

    public static void main(String[] args) {
        get("/list", showList());
        put("/list/insertAfter/:o/:after", insertAfter());
        put("/list/pop", pop());
        put("/list/push/:value", push());
    }

    private static Route showList() {
        return (req, res) -> {
            res.type(APPLICATION_JSON);
            return MESSAGE_TEMPLATE.formatted("LinkedList - %s".formatted(linkedList.toString()));
        };
    }

    private static Route insertAfter() {
        return (req, res) -> {
            var o = req.params("o");
            var after = req.params("after");
            linkedList.insertAfter(o, after);

            res.type(APPLICATION_JSON);
            return MESSAGE_TEMPLATE.formatted("Value %s was inserted after value %s".formatted(o, after));
        };
    }

    private static Route pop() {
        return (req, res) -> {
            var poppedValue = linkedList.pop();

            res.type(APPLICATION_JSON);
            return MESSAGE_TEMPLATE.formatted("Value %s was popped from the LinkedList".formatted(poppedValue));
        };
    }

    private static Route push() {
        return (req, res) -> {
            var valueToPush = req.params("value");
            linkedList.push(valueToPush);

            res.type(APPLICATION_JSON);
            return MESSAGE_TEMPLATE.formatted("Value %s was pushed to the LinkedList".formatted(valueToPush));
        };
    }

}
