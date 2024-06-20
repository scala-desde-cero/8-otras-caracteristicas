package app

object Options {
  // Función que retorna un Option según la existencia de un valor
  def obtenerNumero(posibleNumero: Option[Int]): String = {
    posibleNumero match {
      case Some(numero) => s"El número es $numero"
      case None => "No se proporcionó ningún número"
    }
  }

  // Uso de la función
  def main(args: Array[String]): Unit = {
    val numero1: Option[Int] = Some(10)
    val numero2: Option[Int] = None

    println(obtenerNumero(numero1))  // Salida: El número es 10
    println(obtenerNumero(numero2))  // Salida: No se proporcionó ningún número
  }
}
