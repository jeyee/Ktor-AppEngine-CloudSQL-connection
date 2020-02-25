# Ktor-AppEngine-CloudSQL-connection
I spent a day figuring out how to connect Ktor on AppEngine to CloudSQL. Posting this barebone repo here so you don't have to.

Official google documentation: https://cloud.google.com/sql/docs/mysql/connect-app-engine


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
