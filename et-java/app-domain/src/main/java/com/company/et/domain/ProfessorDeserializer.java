/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.et.domain;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author ar1
 */
public class ProfessorDeserializer extends JsonDeserializer<Professor> { 
    
    public ProfessorDeserializer() { }
    
    @Override 
    public Professor deserialize(JsonParser parser,
        DeserializationContext ctx) throws IOException, JsonProcessingException {
        Professor parentObject = new Professor();

        ObjectMapper objectMapper = (ObjectMapper) parser.getCodec(); 
        JsonNode  node = objectMapper.readTree(parser);
        
        parentObject.setFio (node.get("fio").asText()); 
        parentObject.setRate(node.get("rate").asDouble()); 
        
        JsonNode childNodesTasks = node.get("tasks");
               
        ArrayList<ObservableList<Task>> tasks = new ArrayList<>();
        
        for (JsonNode childNode : childNodesTasks) { 
            ObservableList<Task> tasksList = FXCollections.observableArrayList();
            setFieldList(childNode, tasksList);
            tasks.add(tasksList);
        }
        
        parentObject.setTasks(tasks);
       
        return parentObject; 
    }
    
    private void setFieldList(JsonNode childNodes, ObservableList<Task> tasks){
        for (JsonNode childNode : childNodes) { 
            Task child = new Task();

            child.setProfessorsWork     (childNode.get("professorsWork").asText());
            child.setPeriod             (childNode.get("period").asText());
            JsonNode capacities = childNode.get("capacities");
            for (int i=0;i<capacities.size();i++) {
                child.getCapacities().set(i,capacities.get(i).asDouble());
            }  
            child.setCompleteWork       (childNode.get("completeWork").asBoolean());
            tasks.add(child);
        }
    }
    
}