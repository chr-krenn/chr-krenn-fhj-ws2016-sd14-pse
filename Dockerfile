FROM jboss/wildfly

MAINTAINER Richard Raumberger <richard.raumberger@edu.fh-joanneum.at>
LABEL Adds the project specific deployment artifacts to the wildfly deployment folder.

ADD backend/backend-assembly/target/backend-assembly-1.0-SNAPSHOT.ear /opt/jboss/wildfly/standalone/deployments/
ADD frontend/frontend-assembly/target/frontend-assembly-1.0-SNAPSHOT.ear /opt/jboss/wildfly/standalone/deployments/