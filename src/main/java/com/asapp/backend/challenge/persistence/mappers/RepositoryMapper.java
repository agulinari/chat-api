package com.asapp.backend.challenge.persistence.mappers;

import java.io.Serializable;

public interface RepositoryMapper<E extends Serializable, D> {

    default E mapToEntity(final D domainObject) {
        throw new UnsupportedOperationException();
    }

    default D mapToDomain(final E entityObject) {
        throw new UnsupportedOperationException();
    }

}
