package PendencySystem.Services;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class TagNode {
    String name;
    int count;
    List<TagNode> nextNodes;
    public TagNode(String name){

        nextNodes= new ArrayList<>();
        this.name=name;
        count=1;
    }
}
