package com.example.demo.Service;
import com.example.demo.dto.CommunityDto;
import com.example.demo.Entity.Community;
import com.example.demo.Exceptions.BlogPortException;
import com.example.demo.Mapper.CommunityMapper;
import com.example.demo.Repository.CommunityRepo;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import org.mapstruct.InheritInverseConfiguration;
import static java.util.stream.Collectors.toList;
import org.mapstruct.Mapping;
import org.mapstruct.Mapper;
@Service
@AllArgsConstructor
@Slf4j
public class CommunityService {

    private final CommunityRepo communityRepo;
    private final CommunityMapper communityMapper;

    @Transactional
    public CommunityDto save(CommunityDto communityDto) {
        Community save = communityRepo.save(communityMapper.mapDtoToCommunity(communityDto));
        communityDto.setId(save.getId());
        return communityDto;
    }

    @Transactional(readOnly = true)
    public List<CommunityDto> getAll() {
        return communityRepo.findAll()
                .stream()
                .map(communityMapper::mapCommunitytToDto)
                .collect(toList());
    }

    public CommunityDto getCommunity(Long id) {
        Community community = communityRepo.findById(id)
                .orElseThrow(() -> new BlogPortException("No community found with ID - " + id));
        return communityMapper.mapCommunitytToDto(community);
    }
}