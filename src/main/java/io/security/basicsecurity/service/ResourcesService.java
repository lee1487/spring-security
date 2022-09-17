package io.security.basicsecurity.service;

import java.util.List;

import io.security.basicsecurity.domain.entity.Resources;

public interface ResourcesService {

    Resources getResources(long id);

    List<Resources> getResources();

    void createResources(Resources Resources);

    void deleteResources(long id);
}
