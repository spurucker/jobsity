package com.challenge.jobsity.file;

import lombok.Builder;
import lombok.Data;

import java.util.Map;

@Data
@Builder
public class Row {
    private Map<String, String> row;

    public void put(String key,String value){
        this.row.put(key,value);
    }

    public String get(String key){
        return this.row.get(key);
    }
}
