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
import com.company.et.domain.ProfessorDeserializer;
import com.company.et.domain.Task;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.module.SimpleModule;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.text.ParseException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import javafx.collections.FXCollections;

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
        
        SimpleModule testModule = new SimpleModule("MyModule", new Version(1, 0, 0, null))
                .addDeserializer(Professor.class, new ProfessorDeserializer());
        
        mapper.registerModule(testModule);
        
        
        Professor obj = mapper.readValue(json, Professor.class);

        return obj;
    }
    
    public static Professor [] jsonToObjectProfessorArray(String json) throws IOException, ParseException {
               
        ObjectMapper mapper = new ObjectMapper();
        List <String> json_list = split(json);
        
        Professor [] profs = new Professor[json_list.size()];
        
        SimpleModule testModule = new SimpleModule("MyModule", new Version(1, 0, 0, null))
                .addDeserializer(Professor.class, new ProfessorDeserializer());
        
        mapper.registerModule(testModule);
        
        for(int i = 0; i < json_list.size(); i++){
            //System.out.println(json_list.get(i));
            profs[i] = mapper.readValue(json_list.get(i), Professor.class);
        }
        
        return profs;
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
    public static String readFromFile(File file) {

        String str = "";

        try (BufferedReader br = new BufferedReader(new FileReader(file.getAbsoluteFile()))) {

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
    
    /**
     * Разбивает массив объектом Json'a на отдельные строки-json'ы
     * @param jsonArray
     * @return список из строк Json
     * @throws IOException 
     */  
    public static List<String> split(final String jsonArray) throws IOException {
        final JsonNode jsonNode = new ObjectMapper().readTree(jsonArray);
            return StreamSupport.stream(jsonNode.spliterator(), false) 
                .map(JsonNode::toString) 
                .collect(Collectors.toList()); 
    }

        
    
}

