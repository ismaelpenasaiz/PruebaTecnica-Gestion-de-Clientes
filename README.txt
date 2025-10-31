Proyecto Gestión de Clientes

Este proyecto es una aplicación de consola en Java que permite gestionar información de clientes en una base de datos MySQL. La idea principal es poder agregar, listar, modificar, eliminar y buscar clientes de manera sencilla, 

usando Java y JPA (Java Persistence API) para manejar la base de datos de forma más fácil que escribiendo SQL manualmente.

Cómo funciona

	Inicio del programa

	Al ejecutar el programa, se muestra un menú de opciones en la consola:

		Agregar un nuevo cliente

		Listar todos los clientes

		Actualizar información de un cliente

		Eliminar un cliente

		Buscar cliente por ciudad

		Salir

	El usuario puede elegir la opción que desee escribiendo el número correspondiente.

Agregar un cliente

	Se le pide al usuario que ingrese los datos del cliente: nombre, apellidos, sexo, ciudad, fecha de nacimiento, teléfono y correo electrónico.

	La aplicación valida que los datos tengan el formato correcto (por ejemplo, que el teléfono tenga 9 dígitos y el correo termine en .com o .es).

	Una vez ingresados, el cliente se guarda automáticamente en la base de datos.

Listar clientes

	Muestra todos los clientes registrados en la base de datos, incluyendo su información básica como nombre, ciudad, fecha de nacimiento, teléfono y correo electrónico.

Actualizar un cliente

	Se busca un cliente por su ID.

	El usuario puede modificar cualquiera de los datos.

	Si no quiere cambiar un dato, simplemente deja el campo vacío y ese dato se mantiene.
	
	Los cambios se guardan automáticamente en la base de datos.

Eliminar un cliente

	Se puede eliminar un cliente usando su ID o su nombre.

	La aplicación busca el cliente en la base de datos y lo elimina si existe.

Buscar por ciudad
	
	Permite listar todos los clientes que viven en una ciudad determinada.

	Es útil para filtrar información según ubicación.

Salir

	Cierra el programa de manera segura, asegurándose de cerrar la conexión con la base de datos.

Estructura del proyecto

	Main.java
		Es el punto de entrada del programa y contiene el menú principal y la interacción con el usuario.

	ClientesController.java
		Contiene la lógica de negocio. Es decir, recibe las solicitudes del Main y llama a los métodos que realmente hacen las operaciones en la base de datos.

	ClientesJPA.java
		Aquí se encuentran los métodos que interactúan directamente con la base de datos usando JPA. Por ejemplo: registrar un cliente, actualizarlo, eliminarlo, o listar todos los clientes.

	Clientes.java
		Es la clase que representa a un cliente, con todos sus atributos como nombre, apellidos, ciudad, fecha de nacimiento, teléfono y correo electrónico.

	ConfigJPA.java
		Se encarga de configurar y abrir la conexión con la base de datos usando JPA.

	persistence.xml
		Archivo de configuración de JPA donde se indica la base de datos, usuario, contraseña y otras propiedades necesarias para que Hibernate funcione correctamente.

Tecnologías usadas

Java 21

JPA (Jakarta Persistence API)

Hibernate 6

MySQL

Maven para la gestión de dependencias

ChatGPT para ciertas funciones que no supe implementar y ayuda con la limpieza de codigo