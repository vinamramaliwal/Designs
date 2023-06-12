package PendencySystem.Models;

import lombok.Data;

@Data
public class Tag {
    int id;
    String tagName;
    public Tag(String name){
        tagName=name;
    }
}
