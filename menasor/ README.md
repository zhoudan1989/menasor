# menasor
## 0.pom.xml
```xml
    <dependencies>
        <dependency>
            <groupId>org.zhou.menasor</groupId>
            <artifactId>menasor</artifactId>
            <version>1.0</version>
        </dependency>
    </dependencies>
```
## 1.传输对象定义
```java
public class User {
    private String name;
    private String passwd;

    public String getName() {
        return name;
    }

    public User setName(String name) {
        this.name = name;
        return this;
    }

    public String getPasswd() {
        return passwd;
    }

    public User setPasswd(String passwd) {
        this.passwd = passwd;
        return this;
    }

    public String toString(){
        return "name:"+this.name+",passwd:"+this.passwd;
    }
}
```

## 2.接口定义
```java
public interface DemoService {
    void send(int i);

    void send(String message);

    void send(User user);
}
```

## 3.生产端配置
```xml
    <asyncRpc:producer id="demoService"
                     interface="DemoService"
                     bootstrapServers="kafkaserver:9092"/>
```

## 4.生产端调用
```java
@Service
public class DemoAction {
    @Resource
    private DemoService demoService;

    public void doAction(){
        User user = new User();
        user.setName("zhoudan");
        user.setPasswd(String.valueOf(new Random().nextLong()));
        demoService.send(user);
    }
}
```

## 5.消费端实现
```java
@Service
public class DemoServiceImpl implements DemoService {
    @Resource
    private Demo2Service demo2Service;

    @Override
    public void send(int i) {
        System.out.println("DemoServiceImpl:"+i);
        demo2Service.send(i);
    }

    @Override
    public void send(String message) {
        System.out.println("DemoServiceImpl:"+message);
        demo2Service.send(message);
    }

    @Override
    public void send(User user) {
        System.out.println("DemoServiceImpl:"+user);
        demo2Service.send(user);
    }
}
```

## 6.消费端配置
```xml
	<asyncRpc:consumer id="demoServiceConsumer"
                         interface="DemoService"
                         execnum="2"
                         ref="demoServiceImpl"
                         bootstrapServers="kafkaserver:9092"
                         groupId="demoTest"/>

    <asyncRpc:producer id="demo2Service"
                       interface="Demo2Service"
                       bootstrapServers="kafkaserver:9092"/>

    <asyncRpc:consumer id="demo2ServiceConsumer"
                       interface="Demo2Service"
                       execnum="2"
                       ref="demo2ServiceImpl"
                       bootstrapServers="kafkaserver:9092"
                       groupId="demoTest"/>
```