# Ktor-AppEngine-CloudSQL-connection
I spent a day figuring out how to connect Ktor on AppEngine to CloudSQL. Posting this barebone repo here so you don't have to.

Official google documentation: https://cloud.google.com/sql/docs/mysql/connect-app-engine


## How To Run

1. Clone this repo
2. In `DatabaseHelper` class, update the constants `CLOUD_SQL_CONNECTION_NAME`, `USERNAME` and `PASSWORD`
3. In `HelloApplication` class, update the SQL query in `databaseHelper.runQuery("SELECT id, name from db_name.table_name where id = 2;")` with your own query string.
4. In the project root, run the following command `./gradlew appengineRun`.
5. In your own browser, visit `localhost:8080` to see the results.
6. If you wish to deploy the code to App Engine, run `./gradlew appengineDeploy`.
## Common Issues
> Caused by: java.lang.ClassNotFoundException: com.google.appengine.api.ThreadManager

Link to discussion: https://github.com/GoogleCloudPlatform/cloud-sql-jdbc-socket-factory/issues/159

TLDR: Solved by adding both the following dependencies:
```
implementation "com.google.appengine:appengine:$appengine_version"
implementation "com.google.appengine:appengine-api-1.0-sdk:$appengine_version"
```

> java.sql.SQLException: No suitable driver found for jdbc:mysql...

Solved by adding `libs/mysql-socket-factory-connector-j-8-1.0.15-jar-with-driver-and-dependencies.jar`

Socket factory download: https://github.com/GoogleCloudPlatform/cloud-sql-jdbc-socket-factory/releases

> Caused by: java.net.BindException: Address already in use

On Mac:
To check the process ids that are using the port

```lsof -t -i :8080```

Will show something like `54499`

To kill the process, run

```kill -9 54499```