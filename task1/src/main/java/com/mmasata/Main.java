package com.mmasata;


import com.mmasata.utils.LinkedList;
import com.mmasata.utils.List;
import spark.Response;
import spark.Route;

import static spark.Spark.get;
import static spark.Spark.put;

public class Main {

    private static final String ERROR_MESSAGE_TEMPLATE = "{\"error\" : \"%s\"}";
    private static final String OK_MESSAGE_TEMPLATE = "{\"message\" : \"%s\"}";
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
            return OK_MESSAGE_TEMPLATE.formatted("LinkedList - %s".formatted(linkedList.toString()));
        };
    }

    private static Route insertAfter() {
        return (req, res) -> {
            res.type(APPLICATION_JSON);
            var o = req.params("o");
            var after = req.params("after");

            try {
                linkedList.insertAfter(o, after);
                return OK_MESSAGE_TEMPLATE.formatted("Value %s was inserted after value %s".formatted(o, after));
            } catch (RuntimeException ex) {
                return returnError(res, ex);
            }
        };
    }

    private static Route pop() {
        return (req, res) -> {
            res.type(APPLICATION_JSON);

            try {
                var poppedValue = linkedList.pop();
                return OK_MESSAGE_TEMPLATE.formatted("Value %s was popped from the LinkedList".formatted(poppedValue));
            } catch (RuntimeException ex) {
                return returnError(res, ex);
            }
        };
    }

    private static Route push() {
        return (req, res) -> {
            res.type(APPLICATION_JSON);
            var valueToPush = req.params("value");

            try {
                linkedList.push(valueToPush);
                return OK_MESSAGE_TEMPLATE.formatted("Value %s was pushed to the LinkedList".formatted(valueToPush));
            } catch (RuntimeException ex) {
                return returnError(res, ex);
            }
        };
    }

    private static String returnError(Response res, RuntimeException ex) {
        res.status(400);
        return ERROR_MESSAGE_TEMPLATE.formatted(ex.getMessage());
    }

}
