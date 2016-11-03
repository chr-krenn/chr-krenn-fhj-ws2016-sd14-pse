FROM jboss/wildfly

MAINTAINER Richard Raumberger <richard.raumberger@edu.fh-joanneum.at>
LABEL Customizes the base wildfly image by adding the customization script.

USER root
ADD customization /opt/jboss/wildfly/customization/
RUN chown -R jboss:jboss /opt/jboss/wildfly/customization/

USER jboss

CMD ["/opt/jboss/wildfly/customization/execute.sh"]
