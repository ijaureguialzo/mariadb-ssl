#!make

ifneq (,$(wildcard ./.env))
    include .env
    export
else
$(error No se encuentra el fichero .env)
endif

help: _header
	${info }
	@echo Opciones:
	@echo ---------------------------------
	@echo start / stop / restart / stop-all
	@echo certs / check-ssl
	@echo workspace
	@echo logs / stats
	@echo update
	@echo clean
	@echo ---------------------------------

_urls: _header
	${info }
	@echo Sitios disponibles:
	@echo ----------------------------------
	@echo [phpMyAdmin] http://localhost:8080
	@echo ----------------------------------

_header:
	@echo -----------
	@echo MySQL + SSL
	@echo -----------

_start-command:
	@docker compose up -d --remove-orphans

start: _start-command _urls

stop:
	@docker compose stop

restart: stop start

stop-all:
	@docker stop $(shell docker ps -aq)

certs:
	@echo "Generando certificados..."
	@docker compose run --rm cert-manager /bin/sh -c 'openssl req -newkey rsa:2048 -subj "/C=ES/ST=Araba/L=Vitoria-Gasteiz/O=Test/CN=mariadb.test" -nodes -keyout server-key.pem -x509 -days 365 -out server-cert.pem > /dev/null 2>&1'
	@docker compose run --rm cert-manager /bin/sh -c 'openssl req -newkey rsa:2048 -subj "/C=ES/ST=Araba/L=Vitoria-Gasteiz/O=Test/CN=ca.test" -nodes -keyout ca-key.pem -x509 -days 365 -out ca-cert.pem > /dev/null 2>&1'
	@docker compose run --rm mariadb /bin/sh -c 'chown mysql:mysql /etc/mysql/certs/*'
	@docker compose run --rm mariadb /bin/sh -c 'chmod 600 /etc/mysql/certs/*'
	@docker compose run --rm mariadb /bin/sh -c 'ls -l /etc/mysql/certs/*'

check-ssl:
	@docker compose exec -e MYSQL_PWD=${MYSQL_ROOT_PASSWORD} mariadb mysql -u root -e 'SHOW VARIABLES LIKE "%ssl%";'

workspace:
	@docker compose exec mariadb /bin/bash

logs:
	@docker compose logs mariadb

stats:
	@docker stats

update:
	@docker compose pull

clean:
	@docker compose down -v --remove-orphans
