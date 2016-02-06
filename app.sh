ps aux|grep spring-boot|awk '{print $2}'|xargs kill -9
nohup mvn spring-boot:run &
