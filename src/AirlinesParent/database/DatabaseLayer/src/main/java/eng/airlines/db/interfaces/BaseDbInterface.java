package eng.airlines.db.interfaces;

import java.util.List;

public interface BaseDbInterface<T> {

	List<? extends T> findAll();

	T findById(Long id);

	T save(T object);

	Boolean deleteById(Long id);
}
