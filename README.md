# app-java-market
Test Java project for Hybris Academy

### Installing
1. You should have running MYSQL server with database named *java_app_market_db* with list of tables in it:
    ```mysql
    create table order_items
    (
    order_id   int null,
    product_id int null,
    quantity   int null
    );
    ```
   ```mysql
    create table orders
    (
    id         int auto_increment
    primary key,
    user_id    int          null,
    status     varchar(255) null,
    created_at datetime     null
    );
    ```
   ```mysql
    create table products
    (
    id         int auto_increment
    primary key,
    name       varchar(255)                                     null,
    price      int                                              null,
    status     enum ('out_of_stock', 'in_stock', 'running_low') null,
    created_at datetime                                         null
    );
    ```
2. You should have installed jdk version v.17
3. Clone repo
4. To provide your own mysql config you can add *local.properties* file to project root folder with following configuration:
   ```
   mysql.host = localhost:3306
   mysql.user = admin
   mysql.password = _PASSword1_
   mysql.table_name = java_app_market_db
   ```
5. Open terminal, change directory to root folder of project and run 
   ```
    java -jar .\out\artifacts\Java_Market_jar\Java.Market.jar
    ```
   to view available commands.


### Available CLI options
**-cp**, **--create-product** - creates product with provided data.
```
java -jar .\out\artifacts\Java_Market_jar\Java.Market.jar -create-product
```
Follow steps, provide product data and confirm this step

**-sp**, **--show-products** - prints all added products.
```
java -jar .\out\artifacts\Java_Market_jar\Java.Market.jar -create-product
```

**-co**, **--create-order** - creates order. You should provide product id (from previous view for example) and products quantity.
```
java -jar .\out\artifacts\Java_Market_jar\Java.Market.jar --create-order
```

**--delete-product=[productId]** - deletes previously added product. Requires confirm in terminal.
```
java -jar .\out\artifacts\Java_Market_jar\Java.Market.jar --delete-product=20
```

**-dap**, **--delete-products** - deletes all products. Requires confirm and entering password (*pass*).
```
java -jar .\out\artifacts\Java_Market_jar\Java.Market.jar --delete-products
```

**-sop**, **--show-ordered-products** - show all products, which have been ordered at least once, with total ordered
quantity sorted descending by the quantity.
```
java -jar .\out\artifacts\Java_Market_jar\Java.Market.jar --show-ordered-products
```

**-lo**, **--list-orders** - show all orders with related product data: Order id, total products price, product name, total quantity and order created date.
```
java -jar .\out\artifacts\Java_Market_jar\Java.Market.jar --list-orders
```

**--show-order=orderId** - show one order using view from previous command.
```
java -jar .\out\artifacts\Java_Market_jar\Java.Market.jar --show-order=1
```