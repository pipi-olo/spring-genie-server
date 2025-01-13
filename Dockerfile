FROM openjdk:11-slim
LABEL authors="pipiolo"

# SBT 설치
RUN apt-get update && apt-get install -y curl gnupg

RUN echo "deb https://repo.scala-sbt.org/scalasbt/debian all main" > /etc/apt/sources.list.d/sbt.list && \
    curl -sL "https://keyserver.ubuntu.com/pks/lookup?op=get&search=0x99e82a75642ac823" | apt-key add && \
    apt-get update && apt-get install -y sbt && \
    apt-get clean && rm -rf /var/lib/apt/lists/*

# 작업 디렉토리 설정
WORKDIR /app

COPY . /app

RUN sbt update

ENTRYPOINT ["sbt", "run"]