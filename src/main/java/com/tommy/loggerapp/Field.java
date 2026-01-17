package com.tommy.loggerapp;

public class Field {
    private String prompt;
    private String response;

    public Field() {
        this.prompt = "How are you?";
        this.response = "";
    }
    public Field(String prompt) {
        this.prompt = prompt;
        this.response = "";
    }

    public Field(String prompt, String response) {
        this.prompt = prompt;
        this.response = response;
    }

    public String getPrompt() {
        return prompt;
    }

    public void setPrompt(String prompt) {
        this.prompt = prompt;
    }

    public String getResponse() {
        return response;
    }
    public void setResponse(String response) {
        this.response = response;
    }

    @Override
    public String toString() {

        return "Field: \n" +
                "Prompt: " + prompt + "\n" +
                "Response: " + response + "\n";
    }
}
