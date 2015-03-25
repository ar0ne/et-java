/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.et.service;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import java.io.IOException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author ar1
 */

public class ProfessorDeserializer extends JsonDeserializer<ObservableList> {
 
    @Override
    public ObservableList deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        ObservableList result = FXCollections.observableArrayList();
        return result;
    }
} 