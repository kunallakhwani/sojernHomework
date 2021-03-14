package com.sojern.takehome.controller;

import com.sojern.takehome.utils.Validator;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Collections;

@RestController
public class MathApiController {

    /**
     * Returns a list of minimum number(s), given
     * a list of numbers and a quantifier (how many)
     */
    @RequestMapping(value = "/min",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<int[]> min(@RequestBody String data) {
        JSONObject jsonObject = new JSONObject(data);
        if (!new Validator().inputValidatorMinMax(jsonObject))
            return new ResponseEntity(HttpStatus.BAD_REQUEST);

        JSONArray jsonArray = (JSONArray) jsonObject.get("numArr");
        int quantifier = jsonObject.getInt("quantifier");
        int[] numArr = new int[jsonArray.length()];
        for (int i = 0; i < numArr.length; i++){
            numArr[i] = jsonArray.getInt(i);
        }
        Arrays.sort(numArr);

        int[] output = new int[quantifier];
        for (int i = 0; i < quantifier; i++) {
            output[i] = numArr[i];
        }
        return new ResponseEntity(output, HttpStatus.OK);
    }

    /**
     * Returns a list of maximum number(s), given
     * a list of numbers and a quantifier (how many)
     */
    @RequestMapping(value = "/max",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<int[]> max(@RequestBody String data) {
        JSONObject jsonObject = new JSONObject(data);
        if (!new Validator().inputValidatorMinMax(jsonObject))
            return new ResponseEntity(HttpStatus.BAD_REQUEST);

        JSONArray jsonArray = (JSONArray) jsonObject.get("numArr");
        int quantifier = jsonObject.getInt("quantifier");
        Integer[] numArr = new Integer[jsonArray.length()];
        for (int i = 0; i < numArr.length; i++){
            numArr[i] = jsonArray.getInt(i);
        }
        Arrays.sort(numArr, Collections.reverseOrder());

        int[] output = new int[quantifier];
        for (int i = 0; i < quantifier; i++) {
            output[i] = numArr[i];
        }
        return new ResponseEntity(output, HttpStatus.OK);
    }

    /**
     * Returns the average of given list of numbers
     * Average is calculated as (Sum of all elements) / (No. of elements)
     */
    @RequestMapping(value = "/avg",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Double> avg(@RequestBody String data) {
        JSONObject jsonObject = new JSONObject(data);
        if (!new Validator().inputValidator(jsonObject))
            return new ResponseEntity(HttpStatus.BAD_REQUEST);

        JSONArray jsonArray = (JSONArray) jsonObject.get("numArr");
        int total = 0;
        for (int i = 0; i < jsonArray.length(); i++){
            total += jsonArray.getInt(i);
        }
        double output = (double)total/jsonArray.length();

        return new ResponseEntity(output, HttpStatus.OK);
    }

    /**
     * Returns the median of given list of numbers
     * Median is calculated as the middle of the list
     * that is sorted in ascending order
     */
    @RequestMapping(value = "/median",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Double> median(@RequestBody String data) {
        JSONObject jsonObject = new JSONObject(data);
        if (!new Validator().inputValidator(jsonObject))
            return new ResponseEntity(HttpStatus.BAD_REQUEST);

        JSONArray jsonArray = (JSONArray) jsonObject.get("numArr");
        int length = jsonArray.length();
        int[] numArr = new int[length];
        for (int i = 0; i < numArr.length; i++){
            numArr[i] = jsonArray.getInt(i);
        }
        Arrays.sort(numArr);
        double output = 0.0;
        if (length % 2 == 0)
            output = (double)(numArr[(length-1) / 2] + numArr[length / 2]) / 2;
        else
            output = numArr[length / 2];

        return new ResponseEntity(output, HttpStatus.OK);
    }

    /**
     * Returns the qth percentile, given a
     * list of numbers and a quantifier (q)
     */
    @RequestMapping(value = "/percentile",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Integer> percentile(@RequestBody String data) {
        JSONObject jsonObject = new JSONObject(data);
        if (!new Validator().inputValidatorPerc(jsonObject))
            return new ResponseEntity(HttpStatus.BAD_REQUEST);

        JSONArray jsonArray = (JSONArray) jsonObject.get("numArr");
        int quantifier = jsonObject.getInt("quantifier");
        int length = jsonArray.length();
        int[] numArr = new int[length];
        for (int i = 0; i < numArr.length; i++){
            numArr[i] = jsonArray.getInt(i);
        }
        Arrays.sort(numArr);
        int output = 0;

        int percentileIndex = (int) Math.ceil((double)quantifier / 100 * length) - 1;
        output = numArr[percentileIndex];

        return new ResponseEntity(output, HttpStatus.OK);
    }
}
