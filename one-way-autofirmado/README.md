# one-way-autofirmado

Ejemplo con certificado autofirmado y solo en el servidor.

## Uso

Crear los certificados y arrancar el contenedor:

```shell
make certs start
```

Comprobar si el SSL está habilitado:

```shell
make check-ssl
```

Lanzar la aplicación Java de prueba:

```shell
make run-test-app
```

Hacer el SSL obligatorio para el usuario `root`:

```shell
make force-ssl
```

> El phpMyAdmin, que no usa SSL, dejará de conectar pero la aplicación Java seguirá funcionando.
