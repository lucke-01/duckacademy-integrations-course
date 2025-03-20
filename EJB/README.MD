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

go to wildfly bin directory
```cmd
cd <wildfly_directory>/bin
```

Optional create admin user (.bat for windows .sh for linux)
```cmd
./add-user.[sh|bat] -u 'admin' -p 'admin'
```

Execute server standalone (.bat for windows .sh for linux)
```cmd
./standalone.[sh|bat]
```

## Install and deploy server and client

client
```cmd
cd client
mvn clean install wildfly:deploy
```

Sever
```cmd
cd server
mvn clean install wildfly:deploy
```

## DOCUMENTATION and EXAMPLES

### EJB remote

https://github.com/wildfly/quickstart/tree/main/ejb-remote

### EJB MULTI-SERVER

https://github.com/wildfly/quickstart/tree/main/ejb-multi-server
