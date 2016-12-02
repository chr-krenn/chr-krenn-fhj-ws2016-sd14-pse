FROM jboss/wildfly

MAINTAINER Richard Raumberger <richard.raumberger@edu.fh-joanneum.at>
LABEL Customizes the base wildfly image by adding the customization script.

USER root
ADD customization /opt/jboss/wildfly/customization/

#Download the mysql driver
ARG MYSQL_DRIVER_VERSION=5.1.40
ENV MYSQL_DRIVER_VERSION $MYSQL_DRIVER_VERSION

ADD http://dev.mysql.com/get/Downloads/Connector-J/mysql-connector-java-$MYSQL_DRIVER_VERSION.zip /opt/jboss/wildfly/customization/
RUN unzip -qq /opt/jboss/wildfly/customization/mysql-connector-java-$MYSQL_DRIVER_VERSION.zip \
    && mv mysql-connector-java-${MYSQL_DRIVER_VERSION}/mysql-connector-java-${MYSQL_DRIVER_VERSION}-bin.jar /opt/jboss/wildfly/customization/ \
    && rm -rf mysql-connector-java-${MYSQL_DRIVER_VERSION} \
    && rm -rf mysql-connector-java-${MYSQL_DRIVER_VERSION}.zip

RUN chown -R jboss:jboss /opt/jboss/wildfly/customization/

USER jboss

RUN /opt/jboss/wildfly/bin/add-user.sh admin admin --silent
CMD ["/opt/jboss/wildfly/customization/execute.sh"]
