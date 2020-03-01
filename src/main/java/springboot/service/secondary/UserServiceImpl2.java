package springboot.service.secondary;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import springboot.config.SecondaryMongoConfig;

import java.util.List;

/**
 * Created by Administrator on 2019/3/14.
 */
@Service("userServiceImpl2")
public class UserServiceImpl2<T> implements IUserService2<T> {
    @Autowired
    @Qualifier(SecondaryMongoConfig.MONGO_TEMPLATE)
    private MongoTemplate mongoTemplate;

    /**
     * 保存
     * @param objClass   保存的对象
     * @param collectionName  保存到的集合名称
     */
    @Override
    public void save(T objClass, String collectionName){
        mongoTemplate.save(objClass, collectionName);
    }

    /**
     * 查询
     * @param query   查询的条件
     * @param tClass 返回实体的类
     * @param collectionName 集合名称
     * @return
     */
    @Override
    public List<T> findByName(Query query, Class<T> tClass, String collectionName){
        return mongoTemplate.find(query, tClass, collectionName);
    }

    /**
     * 修改
     * @param query  查询的条件
     * @param update 更新的字段
     * @param tClass  返回实体的类
     * @param collectionName  集合名称
     */
    public void update(Query query, Update update, Class<T> tClass, String collectionName){
       /* //更新查询返回结果集的第一条
        mongoTemplate.updateFirst(query,update,tClass,collectionName);
        //更新查询返回结果集的所有
        // mongoTemplate.updateMulti(query,update,tClass,collectionName);*/
        mongoTemplate.updateMulti(query, update, tClass, collectionName);
    }

    /**
     * 统计查询数量
     * @param query
     * @param tClass
     * @param collectionName
     * @return
     */
    public long count(Query query, Class<T> tClass, String collectionName){
        return mongoTemplate.count(query, tClass, collectionName);
    }

    /**
     * 删除
     * @param query
     * @param tClass
     * @param collectionName
     */
    public void delete(Query query, Class<T> tClass, String collectionName){
        mongoTemplate.remove(query, tClass, collectionName);
    }
}
