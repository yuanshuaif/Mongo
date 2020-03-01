package springboot.config;

import com.mongodb.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.mongo.MongoProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2019/3/15.
 * 4.读取对应的配置信息并且构造对应的 MongoTemplate
 */
@Configuration
public class MultiMongoConfig {
    @Autowired
    private MultiMongoProperties multiMongoProperties;

    @Primary
    @Bean(name = PrimaryMongoConfig.MONGO_TEMPLATE)
    public MongoTemplate primaryMongoTemplate() throws Exception {
        return new MongoTemplate(primaryFactory(this.multiMongoProperties.getPrimary()));
    }

    @Bean(name = SecondaryMongoConfig.MONGO_TEMPLATE)
    public MongoTemplate secondaryMongoTemplate() throws Exception {
        return new MongoTemplate(secondaryFactory(this.multiMongoProperties.getSecondary()));
    }

    @Bean
    @Primary
    public MongoDbFactory primaryFactory(MongoProperties mongo) throws Exception {
        String uri = mongo.getUri();
        if(StringUtils.isEmpty(uri)){
            List<ServerAddress> serverAddressList = new ArrayList<>();
            ServerAddress serverAddress = new ServerAddress(mongo.getHost(), mongo.getPort());
            serverAddressList.add(serverAddress);
            List<MongoCredential> mongoCredentialList = new ArrayList<>();
            MongoCredential credential = MongoCredential.createScramSha1Credential(mongo.getUsername(), mongo.getDatabase(), mongo.getPassword());
            mongoCredentialList.add(credential);
            MongoClient client = new MongoClient(serverAddressList, mongoCredentialList, MongoClientOptions.builder().build());
            return new SimpleMongoDbFactory(client, mongo.getDatabase());
        }else {
            return 	new SimpleMongoDbFactory(new MongoClientURI(uri));
        }
    }

    @Bean
    public MongoDbFactory secondaryFactory(MongoProperties mongo) throws Exception {
        String uri = mongo.getUri();
        if(StringUtils.isEmpty(uri)){
            List<ServerAddress> serverAddressList = new ArrayList<>();
            ServerAddress serverAddress = new ServerAddress(mongo.getHost(), mongo.getPort());
            serverAddressList.add(serverAddress);
            List<MongoCredential> mongoCredentialList = new ArrayList<>();
            MongoCredential credential = MongoCredential.createScramSha1Credential(mongo.getUsername(), mongo.getDatabase(), mongo.getPassword());
            mongoCredentialList.add(credential);
            MongoClient client = new MongoClient(serverAddressList, mongoCredentialList, MongoClientOptions.builder().build());
            return new SimpleMongoDbFactory(client, mongo.getDatabase());
        }else {
            return 	new SimpleMongoDbFactory(new MongoClientURI(uri));
        }
    }
}
