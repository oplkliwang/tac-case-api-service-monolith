package com.beaconstrategists.clientcaseapi.mappers.impl;

import com.beaconstrategists.clientcaseapi.controllers.dto.RmaCaseDto;
import com.beaconstrategists.clientcaseapi.mappers.Mapper;
import com.beaconstrategists.clientcaseapi.model.entities.RmaCaseAttachmentEntity;
import com.beaconstrategists.clientcaseapi.model.entities.RmaCaseEntity;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class RmaCaseMapperImpl implements Mapper<RmaCaseEntity, RmaCaseDto> {

    private final ModelMapper modelMapper;

    public RmaCaseMapperImpl(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public RmaCaseDto mapTo(RmaCaseEntity rmaCaseEntity) {
        RmaCaseDto rmaCaseDto = modelMapper.map(rmaCaseEntity, RmaCaseDto.class);

        // Populate attachmentIds or attachments
        List<Long> attachmentIds = rmaCaseEntity.getAttachments().stream()
                .map(RmaCaseAttachmentEntity::getId)
                .collect(Collectors.toList());
        rmaCaseDto.setAttachmentIds(attachmentIds);

        return rmaCaseDto;
    }

    @Override
    public RmaCaseEntity mapFrom(RmaCaseDto rmaCaseDto) {
        return modelMapper.map(rmaCaseDto, RmaCaseEntity.class);
    }

    public void mapFrom(RmaCaseDto rmaCaseDto, RmaCaseEntity rmaCaseEntity) {
        modelMapper.map(rmaCaseDto, rmaCaseEntity);
    }
}
