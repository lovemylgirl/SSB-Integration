package org.dream.mongodb.morphia;

import java.util.List;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;

public class MorphiaInit {

	public MorphiaInit(Morphia morphia, Datastore datastore, List<Class<?>> classes) {
		for (Class<?> c : classes) {
			morphia.map(c);
		}
		datastore.ensureCaps();
		datastore.ensureIndexes();
	}
}
