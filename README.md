# XFactorWeb
servlet con accesso a database MS SQLServer

# How to run

| #       | Description           | Command  |
| :------------- |:-------------| :-----|
| 1      | compila i file java linkando le librerie per le servlet | `javac -cp XFactorWeb/WEB-INF/lib/servlet-api-2.5.jar XFactorWeb/src/*.java -d XFactorWeb/WEB-INF/classes` |
| 2      | sposta tutti i file nelle cartelle corrispondenti di tomcat | `cp -r XFactorWeb/* $TOMCAT_HOME/webapps/ROOT` |
| 3      | fa partire il server Tomcat | `$TOMCAT_HOME/bin/catalina.sh run 2>&1` |
| 4      | per l'uso su codenvy.io, aggiungere nella "Preview URL" | ${server.8080} |

