# shop-api

> restful apis for shop-native, shop-ui.

### Setup By Intellij IDEA

##### Requirement
1. __Java8__ and above
2. __MySQL 5.7.2__ and above (support multiple triggers features)
3. __Tomcat__ (version unknown, 8.5 for me)
4. __Maven__ (intellij idea build-in it)

##### Steps:
1. New project from version control: Github
https://github.com/HerbLuo/shop-api.git.
2. create a new database and run the sql file in `/database/shop(latest).sql` 
(all the file is the full version).
3. In `/src/main/resources/db.example.properties`, 
modify the db user information to yourself's (and rename to `db.properties`, not needed)
4. Add a new run/debug config for tomcat.
5. Run it.

##### Test:
1. goto `http://localhost:8080/item/?itemIds=1&itemIds=2&itemIds=5`,
2. if success, server will return the json array as follows
```json
[{
  "id": 1,
  "name": "milk",
  "price": 100,
  "picLinksJson": "",
  "itemSellingInfo": {},
  "shop": {}
}]
```

### License MIT
