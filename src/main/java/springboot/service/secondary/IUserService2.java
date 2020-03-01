package springboot.service.secondary;

import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import java.util.List;

/**
 * Created by Administrator on 2019/3/14.
 */
public interface IUserService2<T> {
    /**
     *
     * @param objClass   保存的对象
     * @param collectionName  保存到的集合名称
     */
    public void save(T objClass, String collectionName);

    /**
     *
     * @param query   查询的条件
     * @param tClass 返回实体的类
     * @param collectionName 集合名称
     * @return
     */
    public List<T> findByName(Query query, Class<T> tClass, String collectionName);

    /**
     *
     * @param query  查询的条件
     * @param update 更新的字段
     * @param tClass  返回实体的类
     * @param collectionName  集合名称
     */
    public void update(Query query, Update update, Class<T> tClass, String collectionName);

    /**
     *
     * @param query
     * @param tClass
     * @param collectionName
     * @return
     */
    public long count(Query query, Class<T> tClass, String collectionName);

    /**
     *
     * @param query
     * @param tClass
     * @param collectionName
     */
    public void delete(Query query, Class<T> tClass, String collectionName);
}
