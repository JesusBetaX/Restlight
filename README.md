## RestLight

Restlight es una librería **HTTP** para Android y Java, que facilita la creación de peticiones **HTTP** como: GET, POST, HEAD, OPTIONS, PUT, DELETE y TRACE; hacia servidores externos. [Descargar .jar](https://github.com/JesusBetaX/Restlight/raw/master/dist/restlight.jar) o [Ver demo](https://github.com/JesusBetaX/WebServiceDemo) 

## Ejemplos


### GET
```java
String run() throws Exception {
  Request request = new Request(
          "GET", "http://weather.livedoor.com/forecast/webservice/json/v1?city=130010");

  try (ResponseBody response = request.execute()) {
    return response.string();
  }
}
```

### POST
```java
String run() throws Exception {
  FormBody body = new FormBody()
          .add("nombre", "Elizabéth Magaña")
          .add("edad", 22)
          .add("soltera", false);
    
  Request request = new Request(
          "POST", "http://127.0.0.1/test.php", body);

  try (ResponseBody response = request.execute()) {
    return response.string();
  }
}
```

### DELETE
```java
String run() throws Exception {
  FormBody body = new FormBody()
            .add("id", 101010);

  Request request = new Request(
            "DELETE", "http://127.0.0.1/test.php", body);

  try (ResponseBody response = request.execute()) {
    return response.string();
  }
}
```

### DOWLOAD
```java
File run() throws Exception {
  String downloadPath = "C:\\Users\\Jesus\\Desktop\\restlight.jar";
  Request.Parse<File> request = new DownloadRequest(downloadPath);
  request.req("GET", "https://github.com/JesusBetaX/Restlight/raw/master/dist/restlight.jar");

  return request.executeResult();
}
```

### UPLOAD
```java
String run() throws Exception { 
  MultipartBody body = new MultipartBody()
          .addParam("nombre", "Elizabéth Magaña")
          .addFile("img", new File("C:\\Users\\jesus\\Pictures\\420089-Kycb_1600x1200.jpg"));
    
  Request request = new Request(
          "POST", "http://127.0.0.1/test.php", body);

  try (ResponseBody response = request.execute()) {
    return response.string();
  }
}
```


## [Llamadas asincrónicas y sincrónicas] 

Preparamos la solicitud

```java
public Call<ResponseBody> insert(
    String nombre, int edad, boolean soltera) {
      
    FormBody body = new FormBody()
        .add("nombre", nombre)
        .add("edad", edad)
        .add("soltera", soltera);
    
    HttpRequest request = new HttpRequest(
        "POST", "http://127.0.0.1/test.php", body);

    return request.newCall();
}
```

### Asíncrono

Envía de manera asíncrona la petición y notifica a tu aplicación con un callback cuando una respuesta regresa. Ya que esta petición es asíncrona, Restligth maneja la ejecución en el hilo de fondo para que el hilo de la 
UI principal no sea bloqueada o interfiera con esta.

```java
Call<ResponseBody> insert = insert(
            "Elizabéth Magaña", 22, true);
    
insert.execute(new Callback<ResponseBody>() {

    @Override
    public void onResponse(ResponseBody result) throws Exception {
        String str = result.string();
        System.out.println(str);
    }
        
    @Override
    public void onFailure(Exception e) {
        e.printStackTrace();
    }
});
```

### Síncrono

Envíe sincrónicamente la solicitud y devuelva su respuesta.

```java
Call<ResponseBody> insert = insert(
            "Elizabéth Magaña", 22, true);
    
try (ResponseBody body = insert.execute()) {
    System.out.println(body.string());
    
} catch(Exception e) {
    e.printStackTrace();
}
```

## [GSON](https://github.com/JesusBetaX/WebServiceDemo) 

En tu **build.gradle** necesitarás agregar las dependencias para **GSON**:

```groovy
dependencies {
  ...
  compile 'com.google.code.gson:gson:2.4'
}
```

[Y descargar restlight-converters.jar](https://github.com/JesusBetaX/Restlight/raw/master/dist/restlight-converters.jar)


Ahora estamos listos para comenzar a escribir un código. Lo primero que querremos hacer es definir nuestro modelo **Post**
Cree un nuevo archivo llamado **Post.java** y defina la clase **Post** de esta manera:

```java
public class Post {
  
  @SerializedName("id")
  public long ID;
    
  @SerializedName("date")
  public Date dateCreated;
 
  public String title;
  public String author;
  public String url;
  public String body;
}
```


Creemos una nueva instancia de **GSON** antes de llamar a la request. También necesitaremos establecer un formato de fecha personalizado en la instancia **GSON** para manejar las fechas que devuelve la API:

Definimos las interacciones de la base de datos. Pueden incluir una variedad de métodos de consulta.:

```java
public class Dao {
  private Gson gson;
    
  public Dao() {
    gson = new GsonBuilder()
        .setDateFormat("M/d/yy hh:mm a")
        .create();
  }

  public Call<Post[]> getPosts() {
    Request.Parse<Post[]> request = GsonRequest.of(gson, Post[].class);
    request.req("GET", "https://kylewbanks.com/rest/posts.json");
    
    return request.newCall();
  }
}
```

Programa la solicitud para ser ejecutada en segundo plano. Ideal para aplicaciones android. 
Envía de manera asíncrona la petición y notifica a tu aplicación con un callback cuando una respuesta regresa.
```java
...
Dao dao = new Dao();
    
Call<Post[]> call = dao.getPosts(); 

call.execute(new Callback<Post[]>() {
  
  @Override
  public void onResponse(Post[] result) throws Exception {
    List<Post> list = Arrays.asList(result);
    for (Post post : list) {
      System.out.println(post.title);
    }
  }
  
  @Override
  public void onFailure(Exception e) {
    e.printStackTrace(System.out);
  }
});
```

## [JSON](https://github.com/stleary/JSON-java)
(https://www.json.org/json-en.html)

[Se requiere restlight-converters.jar](https://github.com/JesusBetaX/Restlight/raw/master/dist/restlight-converters.jar)

Para android no es necesario descar [org.json jar](https://github.com/stleary/JSON-java)
Para otras plataformas de java como java swing si es necesario.

```java
public Call<JSON> insert(
    String nombre, int edad, boolean soltera) {
      
    FormBody body = new FormBody()
        .add("nombre", nombre)
        .add("edad", edad)
        .add("soltera", soltera);

    JsonRequest request = new JsonRequest(
        "POST", "http://127.0.0.1/test.php", body);
        
    return request.newCall();
}
```

```java
Call<JSON> insert = insert(
    "Elizabéth Magaña", 22, true);
        
try {
    JSON result = insert.execute();
    JSONObject json = result.getObject();
    System.out.println(json.toString(1));
            
} catch (Exception e) {
    e.printStackTrace();
}
```

License
=======

    Copyright 2018 JesusBetaX, Inc.

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
