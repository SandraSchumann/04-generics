package ohm.softa.a04;

public interface SimpleList <T> extends Iterable <T> {
	/**
	 * Add a given object to the back of the list.
	 */
	void add(T o);

	@SuppressWarnings("unchecked")
	default void addDefault (Class <T> clazz){
		try{
			this.add(clazz.newInstance());
		} catch (InstantiationException | IllegalAccessException e){
			e.printStackTrace();
		}
	}

	/**
	 * @return current size of the list
	 */
	int size();

	/**
	 * Generate a new list using the given filter instance.
	 * @return a new, filtered list
	 */
	@SuppressWarnings("unchecked")
	default SimpleList <T> filter(SimpleFilter <T> filter) {
		SimpleList<T> result;
		try {
			result = (SimpleList<T>) getClass().newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			result = new SimpleListImpl<>();
		}

		for (T o : this) {
			if (filter.include(o)) {
				result.add(o);
			}
		}

		return result;
	}
}
