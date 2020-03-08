package cn.ecnuer996.volunteer.util;

import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xusheng
 */
public class MongoUtil {
    public static List<ObjectId> toObjectIdList(List<String> list){
        List<ObjectId> objectIdList = new ArrayList<ObjectId>();
        for (String id: list) {
            ObjectId objectId=new ObjectId(id);
            objectIdList.add(objectId);
        }
        return objectIdList;
    }
}
