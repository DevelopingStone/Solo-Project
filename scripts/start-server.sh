#!/bin/bash


echo "--------------서버시작--------------"

cd /root/instagram-server
sudo fuser -k -n tcp 8080 || true
nohup java -jar project.jar > ./output.log 2>&1 &
echo "--------------서버배포 끝--------------"