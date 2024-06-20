# 8-otras-caracteristicas


1. [Options](#schema1)
2. [Exceptions. Try Catch](#schema2)
3. [Exceptions. Throw](#schema3)
4. [Sobrecarga. Overloading](#schema4)

<hr>

<a name="schema1"></a>

## 1. Options



En Scala, `Option` es una clase contenedora que puede tener dos posibles estados: `Some` (alguno) que contiene un valor, o `None` (ninguno) que indica la ausencia de valor. Los `Option` son utilizados para manejar situaciones donde un valor puede estar presente o ausente de manera segura y explícita, evitando así los problemas comunes asociados con los valores nulos (null) en otros lenguajes de programación. Aquí te explico más detalles sobre los `Option` en Scala:

### **Características y Uso de `Option`**
1. Declaración y Creación:

    - Puedes crear un `Option` utilizando los métodos `Some`(valor) para un valor presente o simplemente `None` para indicar ausencia de valor.
    - Ejemplo:
    ```scala
    val maybeString: Option[String] = Some("Hola")  // Un valor presente
    val maybeNumber: Option[Int] = None              // Ausencia de valor
    ```
2. Ventajas sobre `null`:

    - Evita las excepciones `NullPointerException` al permitir un manejo explícito de la presencia o ausencia de valor.
    - Mejora la seguridad y robustez del código al obligar a los desarrolladores a manejar explícitamente ambos casos: presencia y ausencia de valor.
3. Operaciones comunes con Option:

    - `getOrElse`: Retorna el valor encapsulado dentro de Some, o un valor por defecto en caso de ser None.

    ```scala
    val valor: String = maybeString.getOrElse("Valor por defecto")
    ```
    - `map y flatMap`: Permiten aplicar transformaciones o secuencias de operaciones sobre el valor dentro de Some sin tener que desempaquetarlo explícitamente.

    ```scala
    val longitud: Option[Int] = maybeString.map(_.length)
    ```
    - `orElse`: Retorna otro Option en caso de ser None.

    ```scala
    val otroOption: Option[String] = maybeString.orElse(Some("Otro valor"))
    ```
4. Patrón de Desempaquetado (Pattern Matching):

    - Es común utilizar el pattern matching para extraer y trabajar con los valores dentro de un Option.
    ```scala
    maybeString match {
      case Some(valor) => println(s"Valor presente: $valor")
      case None => println("No hay valor presente")
    }
    ```
5. Uso en Práctica:

    - Los Option son ampliamente utilizados en APIs y bibliotecas de Scala para indicar resultados que pueden ser nulos o estar ausentes, fomentando un código más seguro y expresivo.

[Ejemplo](./options/)

### **Conclusión**
Los `Option` en Scala son una herramienta poderosa para manejar la posibilidad de valores nulos de manera segura y explícita. Al utilizar `Option`, puedes mejorar la robustez de tu código al evitar errores relacionados con null y facilitar la manipulación y transformación de valores de manera funcional y segura.

<hr>

<a name="schema2"></a>

## 2. Exceptions. Try Catch

En Scala, el manejo de excepciones se realiza principalmente utilizando bloques try-catch y la expresión Try. Aquí te explico ambos enfoques:

### **Bloque try-catch:**
El bloque try-catch en Scala se utiliza para manejar excepciones de manera similar a otros lenguajes de programación como Java. Aquí tienes un ejemplo básico:

```scala
try {
  // Código donde puede ocurrir una excepción
  val resultado = 10 / 0  // Esto generará una ArithmeticException
} catch {
  case ex: ArithmeticException => println("Error aritmético: " + ex.getMessage)
  case ex: Exception => println("Otro error: " + ex.getMessage)
} finally {
  // Opcional: Código que se ejecuta siempre, ocurra o no una excepción
  println("Finalizando el bloque try-catch")
}
```
- `try { ... } catch { ... } finally { ... }`: Es la estructura básica. El código dentro del bloque `try` es donde se coloca el código que puede lanzar una excepción. Si ocurre una excepción del tipo especificado en `catch`, Scala ejecutará el bloque correspondiente de catch. El bloque `finally` es opcional y se ejecuta siempre, ya sea que ocurra una excepción o no.
### **Expresión Try:**
Scala también proporciona una abstracción más funcional para manejar excepciones utilizando la expresión `Try`. Esta expresión se utiliza para encapsular código que puede arrojar una excepción y manejar el resultado de manera más declarativa:

```scala
import scala.util.{Try, Success, Failure}

def dividir(a: Int, b: Int): Try[Int] = Try {
  a / b
}

// Uso de Try
dividir(10, 2) match {
  case Success(resultado) => println("Resultado de la división: " + resultado)
  case Failure(ex) => println("Error: " + ex.getMessage)
}
```
- `Try { ... }`: Encapsula el código que puede lanzar una excepción. Si el código dentro de `Try` tiene éxito, devuelve un `Success` con el resultado. Si ocurre una excepción, devuelve un `Failure` con la excepción capturada.

- `Success y Failure`: Son subtipos de `Try`. Success encapsula un valor exitoso y `Failure` encapsula una excepción.

### **Consideraciones:**
- Tipos de Excepciones: Puedes capturar excepciones específicas `(catch { case ex: MiExcepcion => ... })` o más generales `(catch { case ex: Exception => ... })`.

- Propagación de Excepciones: Si una función devuelve un `Try` con un `Failure`, puedes manejarlo en niveles superiores de tu código o propagarlo según sea necesario.

- Combinación con Patrón match: Puedes utilizar patrones de coincidencia `(match)` con `Success y Failure` para manejar diferentes resultados o errores de manera estructurada.

El manejo de excepciones en Scala combina elementos tradicionales de lenguajes orientados a objetos con una sintaxis más funcional y expresiva. Esto permite un manejo robusto y flexible de errores en tus aplicaciones Scala.


### **Bloque try-catch-finally**
En Scala, el bloque try-catch-finally te permite manejar excepciones de manera estructurada y garantizar la ejecución de código que debe ejecutarse siempre, ya sea que se produzca una excepción o no. Veamos un ejemplo:

```scala
try {
  // Código donde puede ocurrir una excepción
  val resultado = 10 / 0  // Esto generará una ArithmeticException
} catch {
  case ex: ArithmeticException => println("Error aritmético: " + ex.getMessage)
  case ex: Exception => println("Otro error: " + ex.getMessage)
} finally {
  // Código que se ejecuta siempre, ocurra o no una excepción
  println("Bloque finally: finalizando el bloque try-catch")
}
```
En este ejemplo:

- `try { ... }`: Aquí se coloca el código que puede lanzar una excepción, como la división por cero (10 / 0).

- `catch { ... }`: Dentro de este bloque se capturan y manejan las excepciones lanzadas en el bloque try. Puedes especificar diferentes casos de excepción (case ex: ArithmeticException, case ex: Exception) para manejarlas de manera específica.

- `finally { ... }`: Este bloque es opcional pero muy útil. Contiene código que se ejecutará siempre, independientemente de si se lanzó una excepción o no. En el ejemplo, simplemente imprime un mensaje indicando que se está finalizando el bloque try-catch.


<hr>

<a name="schema3"></a>

### 3. Exceptions. Throw

La sintaxis básica para lanzar una excepción en Scala es la siguiente:

```scala
throw new Exception("Mensaje de error")
```
En este ejemplo:

- `new Exception("Mensaje de error")` crea una instancia de la clase `Exception` con un mensaje descriptivo que explica la naturaleza del error.

- `throw` se utiliza para lanzar esa instancia de excepción.

Cuando `throw` se ejecuta, el flujo de control se interrumpe y la excepción se eleva hacia arriba en la pila de llamadas en busca de un bloque `try-catch` que pueda manejarla.

### **Ejemplo de Uso**
Aquí tienes un ejemplo más completo que ilustra cómo se puede utilizar `throw`:

```scala
// Función que calcula el factorial de un número entero positivo
def factorial(n: Int): Int = {
  if (n < 0) {
    throw new IllegalArgumentException("El número debe ser positivo")
  } else if (n == 0) {
    1
  } else {
    n * factorial(n - 1)
  }
}

// Uso de la función factorial
try {
  val resultado = factorial(-5)
  println(s"Factorial: $resultado")
} catch {
  case ex: IllegalArgumentException => println(s"Error: ${ex.getMessage}")
}
```
En este ejemplo:

- La función `factorial` calcula el factorial de un número n. Si n es negativo, se lanza una `IllegalArgumentException` utilizando throw.

- En el bloque `try`, se llama a `factorial(-5)`. Esto provocará que se lance la excepción debido a que `-5` no es un número positivo.

- En el bloque catch, se captura y maneja la excepción `IllegalArgumentException`, imprimiendo un mensaje de error adecuado.

### **Consideraciones**
- Tipos de Excepciones: Puedes lanzar cualquier tipo de excepción en Scala, ya sea una excepción estándar de Scala (como IllegalArgumentException, NullPointerException, etc.) o tus propias excepciones personalizadas que hereden de Throwable.

- Propagación de Excepciones: Cuando lanzas una excepción con throw, ésta se propaga automáticamente hacia arriba en la pila de llamadas hasta que se encuentra un bloque try-catch que pueda manejarla.

- Manejo de Excepciones: Es una buena práctica manejar las excepciones que puedan ocurrir de manera controlada, proporcionando mensajes de error significativos para el usuario o para los registros de la aplicación.


<hr>

<a name="schema4"></a>


## 4. Sobrecarga. Overloading


En programación, la sobrecarga (overloading en inglés) se refiere a la capacidad de definir múltiples funciones o métodos en un mismo ámbito (como una clase o un objeto) con el mismo nombre pero con diferentes listas de parámetros. Esto permite a los desarrolladores usar el mismo nombre para diferentes operaciones que pueden realizar acciones similares pero con distintos tipos de datos o cantidades de argumentos.

### **Características de la Sobrecarga:**
1. Mismo Nombre, Distintos Parámetros: Las funciones o métodos sobrecargados tienen el mismo nombre pero diferentes listas de parámetros, lo que les permite ser diferenciados por el compilador en función de los tipos y el número de argumentos que reciben.

2. Polimorfismo de Sobrecarga: Es una forma de polimorfismo donde múltiples métodos pueden tener el mismo nombre pero comportarse de manera diferente según los tipos de datos con los que se invoquen.

3. Ejemplo de Sobrecarga:

```scala
class Calculadora {
  // Método sobrecargado para sumar dos enteros
  def sumar(a: Int, b: Int): Int = a + b
  
  // Método sobrecargado para sumar dos números flotantes
  def sumar(a: Float, b: Float): Float = a + b
  
  // Método sobrecargado para sumar tres enteros
  def sumar(a: Int, b: Int, c: Int): Int = a + b + c
}

object Principal {
  def main(args: Array[String]): Unit = {
    val calc = new Calculadora()
    
    println(calc.sumar(2, 3))            // Salida: 5
    println(calc.sumar(2.5f, 3.7f))      // Salida: 6.2
    println(calc.sumar(2, 3, 4))         // Salida: 9
  }
}
```
En este ejemplo:

- `Calculadora` tiene tres métodos `sumar`, cada uno con diferentes tipos de parámetros.
- El compilador de Scala distingue entre ellos según los tipos de datos y el número de argumentos que se pasan al método `sumar`.
4. Consideraciones:

- Tipos de Parámetros: La sobrecarga se basa en los tipos y el número de parámetros, no en los nombres de los parámetros o el tipo de retorno.
- Compatibilidad con Herencia: Los métodos sobrecargados pueden ser heredados y pueden coexistir con métodos de la clase base o interfaz.
5. Beneficios:

- Legibilidad: Permite usar nombres de método intuitivos y descriptivos sin necesidad de añadir nombres adicionales para diferenciar funcionalidades similares.
- Flexibilidad: Facilita la adaptación del código a diferentes tipos de datos o situaciones sin necesidad de crear nombres de métodos complicados o redundantes.
