start=$(date +"%s")

ssh -p ${SERVER_PORT} ${SERVER_USER}@${SERVER_HOST} -i key.txt -t -t -o StrictHostKeyChecking=no << 'ENDSSH'
docker pull -a moteloff/blog:latest
docker pull postgres:latest

APP_CONTAINER_NAME=blogapp
if [ "$(docker ps -qa -f name=$APP_CONTAINER_NAME)" ]; then
    if [ "$(docker ps -q -f name=$APP_CONTAINER_NAME)" ]; then
        echo "Container is running -> stopping it..."
        docker stop $APP_CONTAINER_NAME;
    fi
fi

DB_CONTAINER_NAME=postgresdb
if [ "$(docker ps -qa -f name=$DB_CONTAINER_NAME)" ]; then
    if [ "$(docker ps -q -f name=$DB_CONTAINER_NAME)" ]; then
        echo "Container is running -> stopping it..."
        docker stop $DB_CONTAINER_NAME;
    fi
fi

docker run -d --rm -p 5432:5432 --name $DB_CONTAINER_NAME postgres:latest
docker run -d --rm -p 8080:8080 --name $APP_CONTAINER_NAME moteloff/blog:1.0.0

exit
ENDSSH

if [ $? -eq 0 ]; then
  exit 0
else
  exit 1
fi

end=$(date +"%s")

diff=$(($end - $start))

echo "Deployed in : ${diff}s"

The most important part of this code is the following block:


ssh -p ${SERVER_PORT} ${SERVER_USER}@${SERVER_HOST} -i key.txt -t -t -o StrictHostKeyChecking=no << 'ENDSSH'


ENDSSH
