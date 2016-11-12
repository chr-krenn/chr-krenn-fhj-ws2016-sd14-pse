# create DB
CREATE DATABASE IF NOT EXISTS pse_tu;

# grant permissions
GRANT ALL PRIVILEGES ON pse_tu.* TO student@localhost WITH GRANT OPTION;
