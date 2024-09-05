FROM ubuntu:latest
LABEL authors="emmanuel.houdon"

ENTRYPOINT ["top", "-b"]