
import restlight.MultipartBody;
import restlight.Request;
import restlight.Restlight;
import restlight.json.JSON;
import restlight.request.JsonRequest;

public class Test {   
   
  
  public static void main(String[] args) throws Exception { 
    MultipartBody body = new MultipartBody();
    body.addParam("first", "Jesus");
    body.addParam("last", "Bx"); 
    body.addFile("archivo", new byte[]{'H', 'O', 'L', 'A', ' ', 'M', 'U', 'N', 'D', 'O'}, "texto.txt");
    
    Request request = new Request();
    request.post("http://127.0.0.1/test.php", body);
    
    Restlight rest = Restlight.get();
    Request.Parse<JSON> parse = new JsonRequest();
    
    JSON json = rest.execute(request, parse);
    System.out.println(json.toString(1));
  }
}
