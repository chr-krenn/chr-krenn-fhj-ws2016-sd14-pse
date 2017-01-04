FROM jboss/wildfly

MAINTAINER Richard Raumberger <richard.raumberger@edu.fh-joanneum.at>
LABEL Customizes the base wildfly image by adding the customization script.

ARG MYSQL_DRIVER_VERSION=5.1.40
ENV MYSQL_DRIVER_VERSION $MYSQL_DRIVER_VERSION

RUN /opt/jboss/wildfly/bin/add-user.sh admin admin --silent

USER root

RUN curl -SL http://dev.mysql.com/get/Downloads/Connector-J/mysql-connector-java-$MYSQL_DRIVER_VERSION.zip \
    && unzip -qq mysql-connector-java-$MYSQL_DRIVER_VERSION.zip \
    && mv mysql-connector-java-${MYSQL_DRIVER_VERSION}/mysql-connector-java-${MYSQL_DRIVER_VERSION}-bin.jar /opt/jboss/wildfly/customization/ \
    && rm -rf mysql-connector-java-${MYSQL_DRIVER_VERSION} \
    && rm -rf mysql-connector-java-${MYSQL_DRIVER_VERSION}.zip

ADD customization /opt/jboss/wildfly/customization/
RUN chown -R jboss:jboss /opt/jboss/wildfly/customization/

USER jboss

CMD ["/opt/jboss/wildfly/customization/execute.sh"]
