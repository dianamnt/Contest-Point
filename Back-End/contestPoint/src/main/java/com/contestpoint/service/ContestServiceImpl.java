package com.contestpoint.service;

import com.contestpoint.dto.*;
import com.contestpoint.mapper.ContestMapper;
import com.contestpoint.model.Contest;
import com.contestpoint.model.User;
import com.contestpoint.repository.ContestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Component
@ComponentScan(basePackages = "com.contestpoint")
public class ContestServiceImpl implements ContestService{
    @Autowired
    private com.contestpoint.repository.ContestRepository ContestRepository;

    @Autowired
    private com.contestpoint.mapper.ContestMapper ContestMapper;

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private LocationService locationService;

    @Autowired
    private LocationContestService locationContestService;

    @Autowired
    private TagService tagService;

    @Autowired
    private TagContestService tagContestService;

    @Autowired
    private RequirementService requirementService;

    @Autowired
    private UserLikeService userLikeService;

    @Autowired
    private ParticipationContractService participationContractService;

    @Override
    @Transactional
    public ContestDTO createContest(ContestDTO contestDTO) {
        Contest contest = ContestMapper.toEntity(contestDTO);
        Long id = ContestRepository.saveData(contest);
        contest.setContestId(id);
        return ContestMapper.toDTO(contest);
    }

    @Override
    @Transactional
    public void deleteContest(Long ContestId) {
        ContestRepository.removeData(ContestId);
    }

    @Override
    @Transactional
    public void updateContest(ContestDTO ContestDTO) {
        Contest Contest = ContestMapper.toEntity(ContestDTO);
        ContestRepository.updateData(Contest);
    }

    @Override
    @Transactional
    public List<ContestDTO> findAllContests() {
        List<ContestDTO> ContestDTOList = new ArrayList<ContestDTO>();

        for (Contest Contest : ContestRepository.findAll()) {
            ContestDTO ContestDTO = ContestMapper.toDTO(Contest);
            ContestDTOList.add(ContestDTO);
        }

        return ContestDTOList;
    }

    @Override
    @Transactional
    public ContestDTO findById(Long id) {
        Contest Contest = ContestRepository.findById(id);
        if (Contest == null) {
            return null;
        }
        return ContestMapper.toDTO(Contest);
    }

    @Override
    @Transactional
    public ContestDetailedDTO findByIdDetailed(Long id) {
        Contest Contest = ContestRepository.findById(id);
        if (Contest == null) {
            return null;
        }
        ContestDTO c = ContestMapper.toDTO(Contest);
        ContestDetailedDTO contestDetailedDTO = new ContestDetailedDTO();

        List<LocationDTO> locations = new ArrayList<>();
        List<TagDTO> tags = new ArrayList<>();
        List<RequirementDTO> requirements = new ArrayList<>();
        List<UserLikeDTO> likes = new ArrayList<>();

        contestDetailedDTO.setContestId(c.getContestId());
        contestDetailedDTO.setContestName(c.getContestName());
        contestDetailedDTO.setDetails(c.getDetails());
        contestDetailedDTO.setPartners(c.getPartners());
        contestDetailedDTO.setCoverPicture(c.getCoverPicture());
        contestDetailedDTO.setStartDate(c.getStartDate());
        contestDetailedDTO.setEndDate(c.getEndDate());
        contestDetailedDTO.setEnrollmentStart(c.getEnrollmentStart());
        contestDetailedDTO.setEnrollmentDue(c.getEnrollmentDue());
        contestDetailedDTO.setUserId(c.getUserId());
        contestDetailedDTO.setUserFirstName(userService.findById(c.getUserId()).getFirstName());
        contestDetailedDTO.setUserLastName(userService.findById(c.getUserId()).getLastName());
        contestDetailedDTO.setUserEmail(userService.findById(c.getUserId()).getEmail());
        contestDetailedDTO.setUserInstitution(userService.findById(c.getUserId()).getInstitutionName());

        for(LocationContestDTO lc : locationContestService.findAllLocationContests()) {
            if(lc.getContestId() == c.getContestId()) {
                locations.add(locationService.findById(lc.getLocationId()));
            }
        }

        contestDetailedDTO.setLocations(locations);

        for(TagContestDTO tc: tagContestService.findAllTagContests()) {
            if(tc.getContestId() == c.getContestId()) {
                tags.add(tagService.findById(tc.getTagId()));
            }
        }

        contestDetailedDTO.setTags(tags);

        for(UserLikeDTO l: userLikeService.findAllUserLikes()) {
            if(l.getContestId() == c.getContestId())
                likes.add(l);
        }

        contestDetailedDTO.setLikes(likes);

        for(RequirementDTO r: requirementService.findAllRequirements()) {
            if(r.getContestId() == c.getContestId())
                requirements.add(r);
        }

        contestDetailedDTO.setRequirements(requirements);

        return contestDetailedDTO;
    }

    @Override
    @Transactional
    public List<ContestDetailedDTO> findAllContestDetailed() {
        List<ContestDTO> contests = new ArrayList<>();

        for (Contest Contest : ContestRepository.findAll()) {
            ContestDTO ContestDTO = ContestMapper.toDTO(Contest);
            contests.add(ContestDTO);
        }

        List<ContestDetailedDTO> contestDetailedDTOList = new ArrayList<>();

        for(ContestDTO c: contests) {
            ContestDetailedDTO contestDetailedDTO = new ContestDetailedDTO();
            List<TagDTO> tags = new ArrayList<>();
            List<UserLikeDTO> likes = new ArrayList<>();

            contestDetailedDTO.setContestId(c.getContestId());
            contestDetailedDTO.setContestName(c.getContestName());
            contestDetailedDTO.setDetails(c.getDetails());
            contestDetailedDTO.setPartners(c.getPartners());
            contestDetailedDTO.setCoverPicture(c.getCoverPicture());
            contestDetailedDTO.setStartDate(c.getStartDate());
            contestDetailedDTO.setEndDate(c.getEndDate());
            contestDetailedDTO.setEnrollmentStart(c.getEnrollmentStart());
            contestDetailedDTO.setEnrollmentDue(c.getEnrollmentDue());
            contestDetailedDTO.setUserId(c.getUserId());
            contestDetailedDTO.setUserFirstName(userService.findById(c.getUserId()).getFirstName());
            contestDetailedDTO.setUserLastName(userService.findById(c.getUserId()).getLastName());
            contestDetailedDTO.setUserEmail(userService.findById(c.getUserId()).getEmail());
            contestDetailedDTO.setUserInstitution(userService.findById(c.getUserId()).getInstitutionName());

            for(TagContestDTO tc: tagContestService.findAllTagContests()) {
                if(tc.getContestId() == c.getContestId()) {
                    tags.add(tagService.findById(tc.getTagId()));
                }
            }

            contestDetailedDTO.setTags(tags);

            for(UserLikeDTO l: userLikeService.findAllUserLikes()) {
                if(l.getContestId() == c.getContestId())
                    likes.add(l);
            }

            contestDetailedDTO.setLikes(likes);

            contestDetailedDTOList.add(contestDetailedDTO);
        }

        return  contestDetailedDTOList;
    }

    @Override
    @Transactional
    public List<ContestDetailedDTO> findAllMyContests(Long id) {
        List<ContestDTO> contests = new ArrayList<>();

        for (Contest contest : ContestRepository.findAll()) {
            if(contest.getUser().getUserId() == id) {
                ContestDTO ContestDTO = ContestMapper.toDTO(contest);
                contests.add(ContestDTO);
            }
        }

        List<ContestDetailedDTO> contestDetailedDTOList = new ArrayList<>();

        for(ContestDTO c: contests) {
            ContestDetailedDTO contestDetailedDTO = new ContestDetailedDTO();
            List<TagDTO> tags = new ArrayList<>();
            List<UserLikeDTO> likes = new ArrayList<>();

            contestDetailedDTO.setContestId(c.getContestId());
            contestDetailedDTO.setContestName(c.getContestName());
            contestDetailedDTO.setDetails(c.getDetails());
            contestDetailedDTO.setPartners(c.getPartners());
            contestDetailedDTO.setCoverPicture(c.getCoverPicture());
            contestDetailedDTO.setStartDate(c.getStartDate());
            contestDetailedDTO.setEndDate(c.getEndDate());
            contestDetailedDTO.setEnrollmentStart(c.getEnrollmentStart());
            contestDetailedDTO.setEnrollmentDue(c.getEnrollmentDue());
            contestDetailedDTO.setUserId(c.getUserId());
            contestDetailedDTO.setUserFirstName(userService.findById(c.getUserId()).getFirstName());
            contestDetailedDTO.setUserLastName(userService.findById(c.getUserId()).getLastName());
            contestDetailedDTO.setUserEmail(userService.findById(c.getUserId()).getEmail());
            contestDetailedDTO.setUserInstitution(userService.findById(c.getUserId()).getInstitutionName());

            for(TagContestDTO tc: tagContestService.findAllTagContests()) {
                if(tc.getContestId() == c.getContestId()) {
                    tags.add(tagService.findById(tc.getTagId()));
                }
            }

            contestDetailedDTO.setTags(tags);

            for(UserLikeDTO l: userLikeService.findAllUserLikes()) {
                if(l.getContestId() == c.getContestId())
                    likes.add(l);
            }

            contestDetailedDTO.setLikes(likes);

            contestDetailedDTOList.add(contestDetailedDTO);
        }

        return  contestDetailedDTOList;
    }

    @Override
    @Transactional
    public List<ContestDetailedDTO> findAllContestEnrolled(Long id) {
        List<Long> contestIds = new ArrayList<>();

        for(ParticipationContractDTO pc: participationContractService.findAllParticipationContracts()) {
            if(pc.getUserId() == id) {
                contestIds.add(pc.getContestId());
            }
        }

        List<ContestDTO> contests = new ArrayList<>();

        for (Contest contest : ContestRepository.findAll()) {
            if(contestIds.contains(contest.getContestId())) {
                ContestDTO ContestDTO = ContestMapper.toDTO(contest);
                contests.add(ContestDTO);
            }
        }

        List<ContestDetailedDTO> contestDetailedDTOList = new ArrayList<>();

        for(ContestDTO c: contests) {
            ContestDetailedDTO contestDetailedDTO = new ContestDetailedDTO();
            List<TagDTO> tags = new ArrayList<>();
            List<UserLikeDTO> likes = new ArrayList<>();

            contestDetailedDTO.setContestId(c.getContestId());
            contestDetailedDTO.setContestName(c.getContestName());
            contestDetailedDTO.setDetails(c.getDetails());
            contestDetailedDTO.setPartners(c.getPartners());
            contestDetailedDTO.setCoverPicture(c.getCoverPicture());
            contestDetailedDTO.setStartDate(c.getStartDate());
            contestDetailedDTO.setEndDate(c.getEndDate());
            contestDetailedDTO.setEnrollmentStart(c.getEnrollmentStart());
            contestDetailedDTO.setEnrollmentDue(c.getEnrollmentDue());
            contestDetailedDTO.setUserId(c.getUserId());
            contestDetailedDTO.setUserFirstName(userService.findById(c.getUserId()).getFirstName());
            contestDetailedDTO.setUserLastName(userService.findById(c.getUserId()).getLastName());
            contestDetailedDTO.setUserEmail(userService.findById(c.getUserId()).getEmail());
            contestDetailedDTO.setUserInstitution(userService.findById(c.getUserId()).getInstitutionName());

            for(TagContestDTO tc: tagContestService.findAllTagContests()) {
                if(tc.getContestId() == c.getContestId()) {
                    tags.add(tagService.findById(tc.getTagId()));
                }
            }

            contestDetailedDTO.setTags(tags);

            for(UserLikeDTO l: userLikeService.findAllUserLikes()) {
                if(l.getContestId() == c.getContestId())
                    likes.add(l);
            }

            contestDetailedDTO.setLikes(likes);

            contestDetailedDTOList.add(contestDetailedDTO);
        }

        return  contestDetailedDTOList;
    }

    @Override
    @Transactional
    public List<ContestDetailedDTO> findAllContestsEnrolledUpcoming(Long id) {
        List<Long> contestIds = new ArrayList<>();

        for(ParticipationContractDTO pc: participationContractService.findAllParticipationContracts()) {
            if(pc.getUserId() == id) {
                contestIds.add(pc.getContestId());
            }
        }

        List<ContestDTO> contests = new ArrayList<>();

        for (Contest contest : ContestRepository.findAll()) {
            Date utilDate = new Date(contest.getStartDate().getTime());
            Date currDate = new Date();
            if(contestIds.contains(contest.getContestId()) && utilDate.compareTo(currDate) > 0) {
                ContestDTO ContestDTO = ContestMapper.toDTO(contest);
                contests.add(ContestDTO);
            }
        }

        List<ContestDetailedDTO> contestDetailedDTOList = new ArrayList<>();

        for(ContestDTO c: contests) {
            ContestDetailedDTO contestDetailedDTO = new ContestDetailedDTO();
            List<TagDTO> tags = new ArrayList<>();
            List<UserLikeDTO> likes = new ArrayList<>();

            contestDetailedDTO.setContestId(c.getContestId());
            contestDetailedDTO.setContestName(c.getContestName());
            contestDetailedDTO.setDetails(c.getDetails());
            contestDetailedDTO.setPartners(c.getPartners());
            contestDetailedDTO.setCoverPicture(c.getCoverPicture());
            contestDetailedDTO.setStartDate(c.getStartDate());
            contestDetailedDTO.setEndDate(c.getEndDate());
            contestDetailedDTO.setEnrollmentStart(c.getEnrollmentStart());
            contestDetailedDTO.setEnrollmentDue(c.getEnrollmentDue());
            contestDetailedDTO.setUserId(c.getUserId());
            contestDetailedDTO.setUserFirstName(userService.findById(c.getUserId()).getFirstName());
            contestDetailedDTO.setUserLastName(userService.findById(c.getUserId()).getLastName());
            contestDetailedDTO.setUserEmail(userService.findById(c.getUserId()).getEmail());
            contestDetailedDTO.setUserInstitution(userService.findById(c.getUserId()).getInstitutionName());

            for(TagContestDTO tc: tagContestService.findAllTagContests()) {
                if(tc.getContestId() == c.getContestId()) {
                    tags.add(tagService.findById(tc.getTagId()));
                }
            }

            contestDetailedDTO.setTags(tags);

            for(UserLikeDTO l: userLikeService.findAllUserLikes()) {
                if(l.getContestId() == c.getContestId())
                    likes.add(l);
            }

            contestDetailedDTO.setLikes(likes);

            contestDetailedDTOList.add(contestDetailedDTO);
        }

        return  contestDetailedDTOList;
    }

    @Override
    @Transactional
    public List<ContestDetailedDTO> findAllContestsLiked(Long id) {
        List<Long> contestIds = new ArrayList<>();

        for(UserLikeDTO like: userLikeService.findAllUserLikes()) {
            if(like.getUserId() == id) {
                contestIds.add(like.getContestId());
            }
        }

        List<ContestDTO> contests = new ArrayList<>();

        for (Contest contest : ContestRepository.findAll()) {
            if(contestIds.contains(contest.getContestId())) {
                ContestDTO ContestDTO = ContestMapper.toDTO(contest);
                contests.add(ContestDTO);
            }
        }

        List<ContestDetailedDTO> contestDetailedDTOList = new ArrayList<>();

        for(ContestDTO c: contests) {
            ContestDetailedDTO contestDetailedDTO = new ContestDetailedDTO();
            List<TagDTO> tags = new ArrayList<>();
            List<UserLikeDTO> likes = new ArrayList<>();

            contestDetailedDTO.setContestId(c.getContestId());
            contestDetailedDTO.setContestName(c.getContestName());
            contestDetailedDTO.setDetails(c.getDetails());
            contestDetailedDTO.setPartners(c.getPartners());
            contestDetailedDTO.setCoverPicture(c.getCoverPicture());
            contestDetailedDTO.setStartDate(c.getStartDate());
            contestDetailedDTO.setEndDate(c.getEndDate());
            contestDetailedDTO.setEnrollmentStart(c.getEnrollmentStart());
            contestDetailedDTO.setEnrollmentDue(c.getEnrollmentDue());
            contestDetailedDTO.setUserId(c.getUserId());
            contestDetailedDTO.setUserFirstName(userService.findById(c.getUserId()).getFirstName());
            contestDetailedDTO.setUserLastName(userService.findById(c.getUserId()).getLastName());
            contestDetailedDTO.setUserEmail(userService.findById(c.getUserId()).getEmail());
            contestDetailedDTO.setUserInstitution(userService.findById(c.getUserId()).getInstitutionName());

            for(TagContestDTO tc: tagContestService.findAllTagContests()) {
                if(tc.getContestId() == c.getContestId()) {
                    tags.add(tagService.findById(tc.getTagId()));
                }
            }

            contestDetailedDTO.setTags(tags);

            for(UserLikeDTO l: userLikeService.findAllUserLikes()) {
                if(l.getContestId() == c.getContestId())
                    likes.add(l);
            }

            contestDetailedDTO.setLikes(likes);

            contestDetailedDTOList.add(contestDetailedDTO);
        }

        return  contestDetailedDTOList;
    }

    @Override
    @Transactional
    public List<ContestDetailedDTO> findAllContestsLikedUpcoming(Long id) {
        List<Long> contestIds = new ArrayList<>();

        for(UserLikeDTO like: userLikeService.findAllUserLikes()) {
            if(like.getUserId() == id) {
                contestIds.add(like.getContestId());
            }
        }

        List<ContestDTO> contests = new ArrayList<>();

        for (Contest contest : ContestRepository.findAll()) {
            Date utilDate = new Date(contest.getStartDate().getTime());
            Date currDate = new Date();
            if(contestIds.contains(contest.getContestId()) && utilDate.compareTo(currDate) > 0) {
                ContestDTO ContestDTO = ContestMapper.toDTO(contest);
                contests.add(ContestDTO);
            }
        }

        List<ContestDetailedDTO> contestDetailedDTOList = new ArrayList<>();

        for(ContestDTO c: contests) {
            ContestDetailedDTO contestDetailedDTO = new ContestDetailedDTO();
            List<TagDTO> tags = new ArrayList<>();
            List<UserLikeDTO> likes = new ArrayList<>();

            contestDetailedDTO.setContestId(c.getContestId());
            contestDetailedDTO.setContestName(c.getContestName());
            contestDetailedDTO.setDetails(c.getDetails());
            contestDetailedDTO.setPartners(c.getPartners());
            contestDetailedDTO.setCoverPicture(c.getCoverPicture());
            contestDetailedDTO.setStartDate(c.getStartDate());
            contestDetailedDTO.setEndDate(c.getEndDate());
            contestDetailedDTO.setEnrollmentStart(c.getEnrollmentStart());
            contestDetailedDTO.setEnrollmentDue(c.getEnrollmentDue());
            contestDetailedDTO.setUserId(c.getUserId());
            contestDetailedDTO.setUserFirstName(userService.findById(c.getUserId()).getFirstName());
            contestDetailedDTO.setUserLastName(userService.findById(c.getUserId()).getLastName());
            contestDetailedDTO.setUserEmail(userService.findById(c.getUserId()).getEmail());
            contestDetailedDTO.setUserInstitution(userService.findById(c.getUserId()).getInstitutionName());

            for(TagContestDTO tc: tagContestService.findAllTagContests()) {
                if(tc.getContestId() == c.getContestId()) {
                    tags.add(tagService.findById(tc.getTagId()));
                }
            }

            contestDetailedDTO.setTags(tags);

            for(UserLikeDTO l: userLikeService.findAllUserLikes()) {
                if(l.getContestId() == c.getContestId())
                    likes.add(l);
            }

            contestDetailedDTO.setLikes(likes);

            contestDetailedDTOList.add(contestDetailedDTO);
        }

        return  contestDetailedDTOList;
    }

    @Override
    @Transactional
    public List<ContestDetailedDTO> filterByTag(String name) {
        TagDTO tagDTO = tagService.findByName(name);

        List<Long> contestIds = new ArrayList<>();

        for(TagContestDTO t: tagContestService.findAllTagContests()) {
            if(tagDTO.getTagId() == t.getTagId()) {
                contestIds.add(t.getContestId());
            }
        }

        List<ContestDTO> contests = new ArrayList<>();

        for (Contest contest : ContestRepository.findAll()) {
            if(contestIds.contains(contest.getContestId())) {
                ContestDTO ContestDTO = ContestMapper.toDTO(contest);
                contests.add(ContestDTO);
            }
        }

        List<ContestDetailedDTO> contestDetailedDTOList = new ArrayList<>();

        for(ContestDTO c: contests) {
            ContestDetailedDTO contestDetailedDTO = new ContestDetailedDTO();
            List<TagDTO> tags = new ArrayList<>();
            List<UserLikeDTO> likes = new ArrayList<>();

            contestDetailedDTO.setContestId(c.getContestId());
            contestDetailedDTO.setContestName(c.getContestName());
            contestDetailedDTO.setDetails(c.getDetails());
            contestDetailedDTO.setPartners(c.getPartners());
            contestDetailedDTO.setCoverPicture(c.getCoverPicture());
            contestDetailedDTO.setStartDate(c.getStartDate());
            contestDetailedDTO.setEndDate(c.getEndDate());
            contestDetailedDTO.setEnrollmentStart(c.getEnrollmentStart());
            contestDetailedDTO.setEnrollmentDue(c.getEnrollmentDue());
            contestDetailedDTO.setUserId(c.getUserId());
            contestDetailedDTO.setUserFirstName(userService.findById(c.getUserId()).getFirstName());
            contestDetailedDTO.setUserLastName(userService.findById(c.getUserId()).getLastName());
            contestDetailedDTO.setUserEmail(userService.findById(c.getUserId()).getEmail());
            contestDetailedDTO.setUserInstitution(userService.findById(c.getUserId()).getInstitutionName());

            for(TagContestDTO tc: tagContestService.findAllTagContests()) {
                if(tc.getContestId() == c.getContestId()) {
                    tags.add(tagService.findById(tc.getTagId()));
                }
            }

            contestDetailedDTO.setTags(tags);

            for(UserLikeDTO l: userLikeService.findAllUserLikes()) {
                if(l.getContestId() == c.getContestId())
                    likes.add(l);
            }

            contestDetailedDTO.setLikes(likes);

            contestDetailedDTOList.add(contestDetailedDTO);
        }

        return  contestDetailedDTOList;
    }

    @Override
    @Transactional
    public List<ContestDetailedDTO> trendingContests() {
        List<UserLikeDTO> likess = userLikeService.findAllUserLikes();

        Map<ContestDTO, Integer> contestLikes = new HashMap<>();

        for (Contest contest : ContestRepository.findAll()) {
            ContestDTO contestDTO = ContestMapper.toDTO(contest);
            contestLikes.put(contestDTO, 0);
        }

        for(UserLikeDTO l: likess) {
            ContestDTO aux = this.findById(l.getContestId());
            contestLikes.put(aux, contestLikes.get(aux) + 1);
        }

        Map<ContestDTO,Integer> topThree =
                contestLikes.entrySet().stream()
                        .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
                        .limit(3)
                        .collect(Collectors.toMap(
                                Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));

        List<ContestDetailedDTO> contestDetailedDTOList = new ArrayList<>();

        for(Map.Entry<ContestDTO,Integer> entry : topThree.entrySet()) {
            ContestDTO c = entry.getKey();
            ContestDetailedDTO contestDetailedDTO = new ContestDetailedDTO();
            List<TagDTO> tags = new ArrayList<>();
            List<UserLikeDTO> likes = new ArrayList<>();

            contestDetailedDTO.setContestId(c.getContestId());
            contestDetailedDTO.setContestName(c.getContestName());
            contestDetailedDTO.setDetails(c.getDetails());
            contestDetailedDTO.setPartners(c.getPartners());
            contestDetailedDTO.setCoverPicture(c.getCoverPicture());
            contestDetailedDTO.setStartDate(c.getStartDate());
            contestDetailedDTO.setEndDate(c.getEndDate());
            contestDetailedDTO.setEnrollmentStart(c.getEnrollmentStart());
            contestDetailedDTO.setEnrollmentDue(c.getEnrollmentDue());
            contestDetailedDTO.setUserId(c.getUserId());
            contestDetailedDTO.setUserFirstName(userService.findById(c.getUserId()).getFirstName());
            contestDetailedDTO.setUserLastName(userService.findById(c.getUserId()).getLastName());
            contestDetailedDTO.setUserEmail(userService.findById(c.getUserId()).getEmail());
            contestDetailedDTO.setUserInstitution(userService.findById(c.getUserId()).getInstitutionName());

            for(TagContestDTO tc: tagContestService.findAllTagContests()) {
                if(tc.getContestId() == c.getContestId()) {
                    tags.add(tagService.findById(tc.getTagId()));
                }
            }

            contestDetailedDTO.setTags(tags);

            for(UserLikeDTO l: userLikeService.findAllUserLikes()) {
                if(l.getContestId() == c.getContestId())
                    likes.add(l);
            }

            contestDetailedDTO.setLikes(likes);

            contestDetailedDTOList.add(contestDetailedDTO);
        }

        return  contestDetailedDTOList;
    }
}
