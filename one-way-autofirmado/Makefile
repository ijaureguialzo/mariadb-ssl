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
	@echo workspace
	@echo update
	@echo stats
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

workspace:
	@docker compose exec mariadb mysql -u root -p${MYSQL_ROOT_PASSWORD}

update:
	@docker compose pul

stats:
	@docker stats

clean:
	@docker compose down -v --remove-orphans
