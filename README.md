ESPECIFICACIÓN TÉCNICA

Pasos para levantar ambiente de manera local:
- Clonar el proyecto desde: https://github.com/maxialmiron/decrypto-api.git
- Una vez clonado, dentro de la carpeta creada, para poder desplegar la aplicación ejecutar por consola el comando: mvn spring-boot:run

Se levanta con una base de datos en memoria. Se puede cambiar el archivo properties para apuntar, ya sea a una instancia local de MySql (previamente creada) o a la instancia en AWS, se encuentran comentados para cambiar alguna de estas opciones)

Una vez levantada la aplicacion se pueden visualizar los endpoints disponibles a traves de la interfaz de Swagger en: http://localhost:8080/swagger-ui/index.html

La API REST se encuentra publicada en Amazon AWS. Cuenta con una base de datos MySql
Para poder acceder a la interfaz Swagger se debe ingresar a:
http://decrypto-api-env.eba-4zgaxhge.us-east-1.elasticbeanstalk.com/swagger-ui/index.html

User: user
Password: pass1234
(Estas credenciales son tanto para el entorno local como el publicado en AWS)

Tecnologias utilizadas:
JAVA VERSION 17
SPRING BOOT 3
MAVEN
MYSQL

Para consultar los endpoints a traves de Postman se debe ingresar el usuario y contraseña en cada solicitud, seteando: Autorization - > Basic Auth y las credenciales mencionadas

Especificación funcional

La aplicacion permite el CRUD de Comitente, Mercado y Pais.
Al desplegarse ya se encuentran cargados los paises Argentina Y Uruguay.
Por lo cual si se intenta asociar un pais diferente a estos se devolvera un error

Ejemplos JSON para crear las distintas entidades:

MERCADO:
Metodo HTTP:POST
{
  "code": "MAE",
  "description": "descripcion de mercado",
  "country": {
    "code": "AR" // pais existente, (AR, UY)
  }
}

COMITENTE:
Metodo HTTP:POST
{
  "description": "descripcion de cuenta",
  "markets": [
    {
      "id": idMercadoExistente
    }
  ]
}

Si se intenta asociar un mercado no existente se devolvera un error.
Se puede ingresar una lista de mercados.

El endpoint: /api/stats
Devuelve cifras totalizadoras de distribución de comitentes por país y mercado
