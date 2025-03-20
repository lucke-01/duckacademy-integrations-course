# EJB test

## Run a Jakarta EE instance (Wildfly)

### download wildfly version at least 35

Linux
```sh
wget -O wildfly-35.0.1.Final.zip https://github.com/wildfly/wildfly/releases/download/35.0.1.Final/wildfly-35.0.1.Final.zip
```

windows
```powershell
Invoke-WebRequest -Uri https://github.com/wildfly/wildfly/releases/download/35.0.1.Final/wildfly-35.0.1.Final.zip -OutFile wildfly-35.0.1.Final.zip
```

### Start and configure server


```
cd <wildfly_directory>
```


## Install and deploy server or client

client
```cmd
cd client
mvn clean install wildfly:deploy
```

Sever
```cmd
cd client
mvn clean install wildfly:deploy
```

## DOCUMENTATION

### EJB remote

https://github.com/wildfly/quickstart/tree/main/ejb-remote

### EJB MULTI-SERVER

