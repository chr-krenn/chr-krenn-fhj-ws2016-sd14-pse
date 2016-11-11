# create DB
CREATE DATABASE IF NOT EXISTS pse;

# grant permissions
GRANT ALL PRIVILEGES ON pse.* TO student@localhost WITH GRANT OPTION;
