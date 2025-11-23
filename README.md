Proyecto UI – Interfaz de Usuario y Control del Sistema

Este repositorio contiene la capa UI del sistema de gestión de tickets. Su responsabilidad es manejar la interacción con el usuario, presentar mensajes, solicitar datos y coordinar las operaciones mediante el Controller, conectándose con las clases del repositorio Domain.

La interfaz está diseñada para ser simple y funcional, trabajando por consola y respetando la arquitectura modular del proyecto.

Propósito del Proyecto

Este módulo actúa como el punto de entrada del sistema. Aquí se captura toda la información proveniente del usuario final y se delegan las operaciones a la capa Domain, manteniendo una separación clara entre presentación y lógica de negocio.

Estructura del Proyecto

El repositorio incluye:

1. Clase Controller

Administra las solicitudes del usuario y se comunica directamente con:

GestorUsuario

GestorDepartamento

GestorTicket

Login

El Controller interpreta las acciones seleccionadas en el menú y ejecuta los métodos correspondientes del Domain. También gestiona la impresión de listas y los flujos de registro.

2. Clase UI

Encargada de toda la interacción con el usuario por consola. Incluye funciones como:

imprimirMensaje

imprimirMensajeLn

leerTexto

Esta separación permite que la UI pueda reemplazarse en el futuro por una versión gráfica o web sin alterar la capa Domain.

3. Menú Principal

El programa presenta un menú con opciones como:

Registrar usuario

Registrar departamento

Registrar ticket

Registrar palabra emocional

Registrar palabra técnica

Listar usuarios

Listar departamentos

Listar tickets

Iniciar sesión

Cada opción del menú activa un método del Controller que coordina el resto del flujo.

Características Destacadas

Flujo interactivo sencillo para el usuario.

Manejo organizado de la comunicación con la capa Domain.

Separación clara entre presentación y lógica.

Preparación para integrar el análisis de Bag of Words en futuras versiones.

Requisitos

Para ejecutar esta interfaz, se debe tener acceso al repositorio Domain, ya que todas las operaciones dependen de las clases allí definidas.

Cómo Ejecutarlo

Clonar ambos repositorios (Domain y UI).

Configurar ambos en el mismo proyecto o como dependencias separadas.

Instanciar desde UI:

Data

Managers desde Domain

Controller

InterfazUsuario

Ejecutar el método main del módulo UI.

Seguir las instrucciones del menú en consola.

Extensibilidad

La interfaz está diseñada para expandirse de forma modular. Se pueden añadir nuevas opciones al menú, incorporar validaciones adicionales o implementar nuevas funcionalidades sin alterar la arquitectura existente.
