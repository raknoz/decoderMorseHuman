## decoderMorseHuman
Api para traducir bits a código Morse y Código morse a lenguaje humano.


## Recomendaciones
* Se recomienda que tanto los puntos como los trazos, mantengan un largo determinado, es decir que si se desea representar puntos con dos bits (11), el trazo no puede ser menor o igual al este. 
Ejemplo:
  - 11 -> punto / 111 -> trazo = OK
  - 11 -> punto / 11 -> trazo = ERROR
  - 11 -> punto / 1 -> trazo = ERROR


* Lo mismo que sucede con los espacios, si se desea representar el espacio entre letras con doble cero (00), la separación con las palabras, debe ser, mayor a esta última.
  - 0 -> espacio entre letas / 0000 -> espacio entre palabras = OK
  - 000 -> espacio entre letas / 0 -> espacio entre palabras = ERROR
  
## Los servicios de los que dispone la API son:

* Traducir bits a Morse
  - URL: /SpringBootRestApi/api/translateBits2Morse/
  - Método: POST
  - Parámetros (JSON - ejemplo): {"text" : "1010101010101010101000111"}
  - Respuesta (JSON): {"code": 200,"message": null,"data": ".... --- .-.. .-    -- . .-.. .."}

* Traducir Morse a lenguaje humano
  - URL: /SpringBootRestApi/api/translateMorse2Human/
  - Método: POST
  - Parámetros (JSON - ejemplo): {"text" : ".... --- .-.. .-     -- ..- -. -.. ---"}
  - Respuesta (JSON): {"code": 200,"message": null,"data": "hola    mundo"}

* Traducir Lenguaje humano a Morse
  - URL: /SpringBootRestApi/api/translateHuman2Morse/
  - Método: POST
  - Parámetros (JSON - ejemplo): {"text" : "hola mundo"}
  - Respuesta (JSON): {"code": 200,"message": null,"data": ".... --- .-.. .-   -- ..- -. -.. ---"}
