# 🤝 Guía de Contribución

¡Gracias por tu interés en contribuir a **Escuela Primaria**! 🏫  
Antes de comenzar, por favor leé atentamente las siguientes pautas y recomendaciones.  
Este proyecto tiene como fin **aprender a trabajar en conjunto** y mejorar nuestras habilidades, **no está destinado a producción**.

---

## 🧩 Pasos para Contribuir

1. ✅ **Seguí las pautas de desarrollo**  
   Entendé cómo y por qué se implementaron las cosas antes de modificarlas.

2. 🧱 **Realizá cambios pequeños y específicos**  
   Los cambios grandes dificultan la revisión y aumentan el riesgo de errores.

3. ✏️ **Explicá claramente el propósito de tu cambio**  
   Cada extensión o mejora debe estar bien documentada en el *pull request*.

4. 🚫 **Recordá:**  
   Este es un proyecto **educativo**, no diseñado para un entorno productivo.  
   El objetivo es **aprender, compartir y colaborar**. 💡

---

## ⚙️ Pautas de Desarrollo

A continuación se detallan algunas reglas técnicas que deben respetarse para mantener la coherencia del código.

### 🧾 1. DTOs de entrada **no deben usar `@Enumerated`**
**Razón:**  
Los DTOs solo deben recibir **valores primitivos (String)**.  
La validación de si el valor es correcto se realizará dentro del **servicio**, contrastando con el `Enum` correspondiente.

---

### 🚫 2. No crear DTOs de entrada con un solo valor
**Razón:**  
Es innecesario y complica la estructura.  
Si un DTO solo transmite un valor, debería pasarse directamente como parámetro simple.

---

### 🧩 3. Respetar los `Enum` al agregar **roles, permisos, etc.**
**Razón:**  
Mantener **consistencia** en el sistema.  
Por ejemplo, un rol debe existir previamente en el `Enum` para poder usarse en operaciones o asignaciones.

---

## 💬 Notas Finales

- Si no estás seguro de cómo aplicar alguna pauta, **consultá antes de hacer el cambio**.
- Toda contribución es bienvenida siempre que mantenga el estilo y coherencia del proyecto.
- El enfoque principal es **aprender trabajando en equipo** 🧠🤝

---

⭐ *Gracias por ser parte de este proyecto y ayudar a que más personas aprendan y crezcan como desarrolladores.*


