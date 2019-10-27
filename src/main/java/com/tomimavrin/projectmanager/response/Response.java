package com.tomimavrin.projectmanager.response;

public class Response {
    private String status;
    private Object response;

    public Response(String status, Object response) {
        this.response = response;
        this.status = status;
    }

    public Response(String status, String message){
        this.status = status;
        this.response = message;
    }

    public String getStatus() {
        return status;
    }

    public Object getResponse() {
        return response;
    }

}
