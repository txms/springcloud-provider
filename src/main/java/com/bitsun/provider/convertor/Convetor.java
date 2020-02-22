package com.bitsun.provider.convertor;

import java.util.List;

public interface Convetor<ReqDto,Entity,ResDto> {

    public ResDto entityToClient(Entity entityObject);

    public Entity clientToEntity(ReqDto reqDto );

    public List<ResDto> entityListToClientList(List<Entity> entityList);

    public List<Entity> clientListToEntityList(List<ReqDto> reqDtoList);

}
