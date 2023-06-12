package PendencySystem.Services;

import PendencySystem.Models.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class TrackingServicesImpl implements  TrackingServices{

    HashMap<Integer,List<Tag>> TrackingEntityMap= new HashMap<>();

    @Autowired
    TagsMap tagsMap;

    @Override
    public void startTracking(int id, List<String> tags) {
        List<Tag> tagList = new ArrayList<>();
        for(String tag: tags){
            Tag newTag= new Tag(tag);
            tagList.add(newTag);
        }
        TrackingEntityMap.put(id,tagList);
        tagsMap.addNode(tagList);
    }

    @Override
    public void stopTracking(int id) {
        tagsMap.deleteNode(TrackingEntityMap.get(id));
        TrackingEntityMap.remove(id);
    }

    @Override
    public int getCounts(List<String> tagList) {
        return tagsMap.getCount(tagList);

    }
}
