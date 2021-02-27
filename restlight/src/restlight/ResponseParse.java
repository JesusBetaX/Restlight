package restlight;

/**
 *
 * @author jesus
 */
public interface ResponseParse<T> {
    
    /**
     * Convercion de la respuesta obtenida de la Red.
     *
     * @param response resultado obtenido.
     *
     * @return tipo generico
     *
     * @throws java.lang.Exception
     */
    T parseResponse(ResponseBody response) throws Exception;
}
