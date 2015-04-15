package com.company.et.domain;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.ArrayList;
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
        try {
            for (JsonNode childNode : childNodes) { 
                Task child = new Task();

                if (childNode.get("professorsWork") != null) {
                    child.setProfessorsWork(childNode.get("professorsWork").asText());
                }
                
                if(childNode.get("period") != null) {
                    child.setPeriod(childNode.get("period").asText());
                }
                
                if(childNode.get("capacities") != null) {
                    JsonNode capacities = childNode.get("capacities");
                    for (int i = 0; i < capacities.size(); i++) {
                        child.getCapacities().set(i, capacities.get(i).asDouble());
                    }
                }
                
                child.setCompleteWork(childNode.get("completeWork").asBoolean());
                tasks.add(child);
                   
            }
        } catch (Exception e ) {
           System.out.println("Error: Something wrong with JSON file!");
           e.printStackTrace();
        }
    }
    
}