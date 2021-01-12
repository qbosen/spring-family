## 启动mongo
```shell 
docker pull mongo

docker run --name mongo -p 27017:27017 -v ~/docker-data/mongo:/data/db -e MONGO_INITDB_ROOT_USERNAME=admin -e MONGO_INITDB_ROOT_PASSWORD=admin -d mongo --auth

docker exec -it mongo bash
mongo -u admin -p admin
```
