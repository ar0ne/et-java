/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.et.service;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import com.company.et.domain.Professor;
import com.company.et.domain.Task;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.text.ParseException;

/**
 *
 * @author ar1
 */
public class JsonService {

    private static String filename = "data.json";

    public static void setFilename(String name) {
        filename = name;
    }

    public static String getFilename() {
        return filename;
    }

    public static String objectToString(Object obj) throws IOException, ParseException {

        ObjectMapper mapper = new ObjectMapper();

        // настраиваю нормальное отображение json'a
        mapper.configure(SerializationFeature.INDENT_OUTPUT, true);
        mapper.configure(SerializationFeature.ORDER_MAP_ENTRIES_BY_KEYS, true);

        String jsonString = mapper.writeValueAsString(obj);

        return jsonString;
    }

    public static Professor jsonToObjectProfessor(String json) throws IOException, ParseException {

        ObjectMapper mapper = new ObjectMapper();
        Professor obj = mapper.readValue(json, Professor.class);

        return obj;
    }

    public static Task jsonToObjectTask(String json) throws IOException, ParseException {

        ObjectMapper mapper = new ObjectMapper();
        Task obj = mapper.readValue(json, Task.class);

        return obj;
    }
    
    /**
     *
     * @param json String what we write to file
     */
    public static void writeToFile(String json) {

        try {
            File file = new File(getFilename());
            if (!file.exists()) {
                file.createNewFile();
            }

            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(json);
            bw.close();

            // @TODO: add LOG message
        } catch (IOException e) {
            e.printStackTrace();

            // @TODO: add LOG message
        }

    }

    /**
     *
     * @return JSON String
     */
    public static String readFromFile() {

        String str = "";

        try (BufferedReader br = new BufferedReader(new FileReader(getFilename()))) {

            String currentLine;

            while ((currentLine = br.readLine()) != null) {
                str += currentLine;
            }

            // @TODO: add LOG message
        } catch (IOException e) {
            e.printStackTrace();

            // @TODO: add LOG message
        }

        return str;
    }

}
