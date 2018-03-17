## RELEVANT INFORMATION
Every API search must be preceded by /api

## Searches
Now we show the searches refered to book, film or show. 

### OBTAIN EVERY BOOK/SHOW/FILM

Obtain every book, show or film in the db

* ##### URL

  /api/peliculas
  
  /api/libros
  
  /api/series

* ##### Method:

	`GET`
 
* ##### Success Response:

200 OK

```json
{
    "content": [
        {
            "url": "/pelicula/",
            "name": "Guardianes de la Galaxia 1",
            "synopsis": "Guardianes de la Galaxia Vol. 2 continúa las aventuras del equipo a medida que viajan cruzando\n los confines del cosmos. Los Guardianes tendrán que luchar para\n mantener su recién...",
            "image": "/img/Guardianes2.jpg",
            "trailer": "https://www.youtube.com/embed/12gvJgLE4us?rel=0&amp;controls=0&amp;showinfo=0",
            "year": 2017
        },
        {
            "url": "/pelicula/",
            "name": "Guardianes de la Galaxia 2",
            "synopsis": "Guardianes de la Galaxia Vol. 2 continúa las aventuras del equipo a medida que viajan cruzando\n los confines del cosmos. Los Guardianes tendrán que luchar para\n mantener su recién...",
            "image": "/img/Guardianes2.jpg",
            "trailer": "https://www.youtube.com/embed/12gvJgLE4us?rel=0&amp;controls=0&amp;showinfo=0",
            "year": 2017
        },
        {
            "url": "/pelicula/",
            "name": "Guardianes de la Galaxia 3",
            "synopsis": "Peter Quill, debe mantener una incomoda alianza con Gamora, Drax, Rocket y Groot para asegurar un artefacto que pone en peligro a toda la galaxia no caiga en las manos del temible Ronan el Acusador.",
            "image": "/img/Guardianes2.jpg",
            "trailer": "https://www.youtube.com/embed/dzj4P11Yr6E",
            "year": 2008
        },,
    "last": false,
    "totalElements": 22,
    "totalPages": 2,
    "size": 20,
    "number": 0,
    "sort": null,
    "first": true,
    "numberOfElements": 20
}

...etc
```
        
* ##### Error Response:

	404 NOT FOUND
  
### OBTAIN A FILM/BOOK/SHOW IN PARTICULAR
Look for a particular film, show or book given a name

* ##### URL

	 /api/pelicula/{name} 

* ##### Method:

	`GET`

* ##### URL Params

	* Required:

		`name=[String]`
    
* ##### Success Response:

200 OK

```json
{
    "url": "/pelicula/",
    "name": "Guardianes de la Galaxia 1",
    "synopsis": "Guardianes de la Galaxia Vol. 2 continúa las aventuras del equipo a medida que viajan cruzando\n los confines del cosmos. Los Guardianes tendrán que luchar para\n mantener su recién...",
    "image": "/img/Guardianes2.jpg",
    "trailer": "https://www.youtube.com/embed/12gvJgLE4us?rel=0&amp;controls=0&amp;showinfo=0",
    "year": 2017
}

```

### ADD FILM/SHOW/BOOK
Adds a particular film/show/book to the db, given an admin. 

* ##### URL

	 /api/agregarpelicula

* ##### Method:

	`POST`

* ##### URL Params

	* Required:

```json
    {
    "name": "Guardianes de la Galaxia ...",
    "synopsis": "Guardianes de la Galaxia ….",
    "image": "/img/GuardianesX.jpg",
    "trailer": "https://www.youtube.com/embed/12gvJgLE4us?rel=0&amp;controls=0&amp;showinfo=0",
    "year": 2017
}

```
    
* ##### Success Response:

  200 OK
  
```json
{
    "id": 23,
    "url": "/pelicula/",
    "name": "Guardianes de la Galaxia...",
    "synopsis": "Guardianes de la Galaxia ….",
    "image": "/img/GuardianesX.jpg",
    "trailer": "https://www.youtube.com/embed/12gvJgLE4us?rel=0&amp;controls=0&amp;showinfo=0",
    "year": 2017,
    "firstInList": false
}

```

* ##### Error Response:
  In case the name you put is the same of any item in the db

	226 IM USED
  
### DELETE A FILM/BOOK/SHOW IN PARTICULAR
Look for a particular film, show or book given a name, and delete it. You must be an admin to do so. 

* ##### URL

	 /api/borrarpelicula/{name} 

* ##### Method:

	`DELETE`

* ##### URL Params

	* Required:

		`name=[String]`
    
* ##### Success Response:

  200 OK

```json
{
    "id": 23,
    "url": "/pelicula/",
    "name": "Guardianes de la Galaxia...",
    "synopsis": "Guardianes de la Galaxia ….",
    "image": "/img/GuardianesX.jpg",
    "trailer": "https://www.youtube.com/embed/12gvJgLE4us?rel=0&amp;controls=0&amp;showinfo=0",
    "year": 2017,
    "firstInList": false
}

```

* ##### Error Response:
  In case the name you put does not exist in the db

	404 NOT FOUND
  
### MODIFY A FILM/BOOK/SHOW IN PARTICULAR
Look for a particular film, show or book given a name, and delete it. You must be an admin to do so. 

* ##### URL

	 /api/editarpelicula/{name} 

* ##### Method:

	`PUT`

* ##### URL Params

	* Required:

		`name=[String]`
   
* ##### Data Params
```json
{
    "name": "Guardianes de la Galaxia ...",
    "synopsis": "Guardianes de la Galaxia ….",
    "image": "/img/GuardianesX.jpg",
    "trailer": "https://www.youtube.com/embed/12gvJgLE4us?rel=0&amp;controls=0&amp;showinfo=0",
    "year": 2017
}
```

    
* ##### Success Response:

  200 OK

```json

{
    "url": "/pelicula/",
    "name": "Guardianes de la Galaxia 2",
    "synopsis": "Guardianes de la Galaxia ….",
    "image": "/img/GuardianesX.jpg",
    "trailer": "https://www.youtube.com/embed/12gvJgLE4us?rel=0&amp;controls=0&amp;showinfo=0",
    "year": 2017
}

```

* ##### Error Response:
  In case the name you put does not exist in the db

	404 NOT FOUND
  
### OBTAIN THE BEST RATED FILM/BOOK/SHOW 
You obtain a list sorted by best ratings of the items you were looking for (books, films or shows) 

* ##### URL

	 /api/peliculas/mejorvaloradas
   
   /api/libros/mejorvalorados
   
   /api/series/mejorvaloradas
   

* ##### Method:

	`GET`

    
* ##### Success Response:

200 OK

```json
{
    "content": [
        {
            "url": "/pelicula/",
            "name": "Buscando a Nemo",
            "synopsis": "El pequeño Nemo, un pececillo hijo único que perdió a su madre antes de nacer, es muy querido y excesivamente protegido por su padre. Nemo ha sido pescado y sacadi de la gran barrera del arrecife australiano y ahora vive en una pequeña pecera en la oficina de un dentista de Sidney. El tímido padre de Nemo se embarcará en una peligrosa aventura donde conoce Dory. Juntos vane...",
            "image": "/img/films/buscandoAnemo.jpg",
            "trailer": "https://www.youtube.com/embed/wZdpNglLbt8",
            "year": 2003
        },
        {
            "url": "/pelicula/",
            "name": "Gladiator",
            "synopsis": "Máximo Décimo Meridio, un leal general hispano del ejército de la Antigua Roma, que es traicionado por Cómodo, el ambicioso hijo del emperador, quien ha asesinado a su padre y se ha hecho con el trono. Forzado a convertirse en esclavo, Máximo triunfa como gladiador mientras anhela vengar la muerte de su familia y su emperador",
            "image": "/img/films/gladiador.jpg",
            "trailer": "https://www.youtube.com/embed/s6v-bUY_wS8",
            "year": 2000
        },
    "last": true,
    "totalElements": 20,
    "totalPages": 1,
    "size": 20,
    "number": 0,
    "sort": null,
    "first": true,
    "numberOfElements": 20
}

```

* ##### Error Response:

	404 NOT FOUND


