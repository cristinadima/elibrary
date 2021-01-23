FROM openjdk:11

ENV WAIT_HOST localhost
ENV WAIT_PORT 8080

COPY target/products.jar app.jar

ADD docker-run.sh run.sh

RUN bash -c 'chmod +x /run.sh'

ENTRYPOINT ["/run.sh"]
