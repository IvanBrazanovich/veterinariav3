
import java.io.Serializable;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.UUID;

public interface ListadoModelo<T> extends Serializable {

    public void agregar(T objeto);

    public void eliminar(UUID idObjeto);

    public ArrayList<T> get();

    public T getById(UUID idObjeto) throws NoSuchElementException;

    public int size();

}
