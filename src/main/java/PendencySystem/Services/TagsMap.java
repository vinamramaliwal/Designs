package PendencySystem.Services;

import PendencySystem.Models.Tag;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class TagsMap {

    TagNode root;
    TagsMap(){
        root = new TagNode("start");
    }

    void addNode(List<Tag> tagList) {
        TagNode node= root;
        boolean isTagPresent;
        for(Tag tag:tagList) {
            List<TagNode> nextNodes= node.getNextNodes();
            isTagPresent=false;
            for(TagNode childNode: nextNodes ) {
                if(childNode.getName().equals(tag.getTagName())){
                    node=childNode;
                    childNode.setCount(childNode.getCount()+1);
                    isTagPresent=true;
                    break;
                }
            }
            if(!isTagPresent) {
                TagNode newTagNode = new TagNode(tag.getTagName());
                node.getNextNodes().add(newTagNode);
                node=newTagNode;
            }
        }
    }

    void deleteNode(List<Tag> tagList) {
        TagNode node = root;
        for (Tag tag : tagList) {
            List<TagNode> nextNodes = node.getNextNodes();
            for (TagNode childNode : nextNodes) {
                if (childNode.getName().equals(tag.getTagName())) {
                    node = childNode;
                    childNode.setCount(Math.max(childNode.getCount() - 1,0));
                    break;
                }
            }
        }
    }

    int getCount(List<String> tagList){
        TagNode node = root;
        for (String tag : tagList) {
            List<TagNode> nextNodes = node.getNextNodes();
            boolean tagFound=false;
            for (TagNode childNode : nextNodes) {
                if (childNode.getName().equals(tag)) {
                    node = childNode;
                    tagFound=true;
                    break;
                }
            }
            if(!tagFound)
                return  0;
        }
        return node.getCount();
    }
}
