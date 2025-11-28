package epita.projet_bensadi_vincent.exception;

/**
 * Exception levée lorsqu'une ressource demandée n'est pas trouvée
 * Cette exception est mappée automatiquement en HTTP 404 par le GlobalExceptionHandler
 */
public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String message) {
        super(message);
    }

    public ResourceNotFoundException(String resourceName, Long id) {
        super(resourceName + " avec l'ID " + id + " n'existe pas");
    }

    public ResourceNotFoundException(String resourceName, String fieldName, Object fieldValue) {
        super(String.format("%s avec %s '%s' n'existe pas", resourceName, fieldName, fieldValue));
    }
}

