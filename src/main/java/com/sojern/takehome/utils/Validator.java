package com.sojern.takehome.utils;

import org.json.JSONArray;
import org.json.JSONObject;

public class Validator {

    public boolean inputValidatorMinMax(JSONObject jsonObject) {
        if (jsonObject.isEmpty() || !jsonObject.has("numArr") || !jsonObject.has("quantifier"))
            return false;

        JSONArray jsonArray = (JSONArray) jsonObject.get("numArr");
        int quantifier = jsonObject.getInt("quantifier");
        if (quantifier < 0 || quantifier > jsonArray.length())
            return false;

        return true;
    }

    public boolean inputValidator(JSONObject jsonObject) {
        if (jsonObject.isEmpty() || !jsonObject.has("numArr"))
            return false;

        return true;
    }

    public boolean inputValidatorPerc(JSONObject jsonObject) {
        if (jsonObject.isEmpty() || !jsonObject.has("numArr") || !jsonObject.has("quantifier"))
            return false;

        int quantifier = jsonObject.getInt("quantifier");
        if (quantifier < 0 || quantifier > 100)
            return false;

        return true;
    }
}
