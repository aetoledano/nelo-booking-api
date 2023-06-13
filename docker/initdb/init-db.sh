#!/bin/bash
set -e

psql -v ON_ERROR_STOP=1 --username "$POSTGRES_USER" --dbname "$POSTGRES_DB" <<-EOSQL
	CREATE USER  nelo;
	CREATE DATABASE nelo;
	GRANT ALL PRIVILEGES ON DATABASE nelo TO nelo;
	ALTER USER nelo WITH PASSWORD 'nelo';
EOSQL
