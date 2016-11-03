FROM jboss/wildfly

MAINTAINER Richard Raumberger <richard.raumberger@edu.fh-joanneum.at>
LABEL Customizes the base wildfly image by adding the customization script and the deployment artifacts.

ADD customization /opt/jboss/wildfly/customization/
ADD ../backend/backend-assembly/target/backend-assembly-1.0-SNAPSHOT.ear /opt/jboss/wildfly/customization/deployments/
ADD ../frontend/frontend-assembly/target/frontend-assembly-1.0-SNAPSHOT.ear /opt/jboss/wildfly/customization/deployments/

CMD ["/opt/jboss/wildfly/customization/execute.sh"]
