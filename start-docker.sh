echo "Starting application via DOCKER image"

docker build -t springio/processo-seletivo .
docker run --net=host -p 8080:8080 springio/processo-seletivo
