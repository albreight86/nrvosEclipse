
Spring is used for injection and property reading.
CXF is used for the web server.
Jackson is used to marshal/unmarshal JSON.
Maven is used for building.
JdbcTemplate is used for database access.

Groovy, TestNg, and Mockito were introduced intially to facilitate unit testing, but time ran out to use them.

Things to do:
- write unit tests for SovrnBusiness and RestService.
- several spots exists where streaming might improve performance.
- investigate ORM access to the DB.
- The current design relies on reading the providers table at startup. This might need to be performed
  in real time, or in the background when providers are added or removed.


Integration testing performed is summarized below.

MYSQL:
From the applicationContext.xml file:
   <bean id="ds" class="org.springframework.jdbc.datasource.DriverManagerDataSource">
      <property name="driverClassName" value="com.mysql.jdbc.Driver" />
      <property name="url" value="jdbc:mysql://localhost:3306/sovrn?useSSL=false" />
      <property name="username" value="root" />
      <property name="password" value="iggle" />
   </bean>

To run: (insure mysql db is running)
java -DclickTimer=2100 -cp target/dependency/*:target/sovrn-1.0-SNAPSHOT.jar com.arb.SpringMain


Integration testing.
1. A Mysql db was installed.
2. The SQL included in the problem statement was executed. (several typos are described below)
3. The following data was inserted in the DB:

    insert into user (user_id, username) values  (2,'user2');
    insert into user (user_id, username) values  (3,'user3');

    insert into user_provider_assoc (user_id, provider_id, user_provider_assoc_id) values  (2,22, 1);
    insert into user_provider_assoc (user_id, provider_id, user_provider_assoc_id) values  (2,23, 2);

    insert into provider (provider_id, provider_name, url) values  (22,'provider22', 'someurl');
    insert into provider (provider_id, provider_name, url) values  (23,'provider23', 'anotherurl');

    insert into ad_size (width, height, ad_size_id) values  (11,22, 32);
    insert into ad_size (width, height, ad_size_id) values  (33,44, 33);

    insert into provider_size_assoc (provider_id, ad_size_id, provider_size_assoc_id) values  (22,32, 1);
    insert into provider_size_assoc (provider_id, ad_size_id, provider_size_assoc_id) values  (23,33, 2);

http://localhost:85:80/ad?width=36&height=56&userId=1
produces: {
           "AdBid": ""
           }
http://localhost:80/ad?width=33&height=44&userId=2
	   {
             "AdBid": {
                       "tid": "db4514f2-8943-4c39-9b7d-2a0b794ac003",
                      "html": "some html"
                      }
           }

//The following is after several /ad were executed.
http://localhost:80/history
	{
	"history": {
	"transactions": [
	{
	"transactionId": "6ff03fab-9b01-4752-a689-e6da9730597b",
	"userid": 2,
	"bids": {
	"bidprice": 0.33522546002486664,
	"adhtml": "some html",
	"providerId": 23
	},
	"winningPrice": 0.33522546002486664,
	"winningProvider": 23,
	"clickResult": "REQUEST"
	},
	{
	"transactionId": "95c8a1a2-7970-46a7-a75a-0d939eaa1da0",
	"userid": 2,
	"bids": {
	"bidprice": 0.6296455412490851,
	"adhtml": "some html",
	"providerId": 23
	},
	"winningPrice": 0.6296455412490851,
	"winningProvider": 23,
	"clickResult": "REQUEST"
	}
	]
	}
	}





Possible typos in the problem statement and requirements:
- /click from a REST perspective should be a POST or a PUT because it changes a resource.
- This following statement should probably not be AUTOINCREMENT for `url` as it is a varchar,
  plus it likely doesnt make sense to increment a field called `url`.
    CREATE TABLE `provider` (
    `provider_id` INT NOT NULL,
    `provider_name` varchar(64) NOT NULL UNIQUE,
    `url` varchar(512) NOT NULL AUTO_INCREMENT UNIQUE,
    PRIMARY KEY (`provider_id`)
    );
- incorrect alter table to designate a foriegn key:
     ALTER TABLE `user_size_assoc`
     ADD CONSTRAINT `user_size_assoc_fk0`
     REFERENCES `user`(`user_id`);


