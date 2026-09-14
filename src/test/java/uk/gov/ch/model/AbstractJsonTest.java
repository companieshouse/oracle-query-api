package uk.gov.ch.model;

import tools.jackson.databind.json.JsonMapper;

public abstract class AbstractJsonTest {

    protected JsonMapper jsonMapper = JsonMapper.builder().build();

}
