package com.olegzet.mservice;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class TurnServiceImpl implements TurnService {
    ConcurrentHashMap lamps;

    public TurnServiceImpl() {
        this.lamps = new ConcurrentHashMap();
    }

    @Override
    public String turnOn(String id) {


    }

    @Override
    public UserDTO attachUser(String userId, String securityCode) {
        if (securityCode == null || securityCode.isEmpty()) {
            throw new RuntimeException("securityCode is null or empty.  Cannot create user");
        }

        LampDTO lamp = lampRepo.findBySecurityCode(securityCode);
        UserDTO user = new UserDTO();
        user.setId(userId);
        user.setLampId(lamp.getId());

        // Verify security code
        if (!lamp.getSecurityCode().equals(securityCode)) {
            throw new RuntimeException("Invalid securityCode, lamp not found.");
        }

        return saveUserFirstAdminPendingAfter(user);
    }

    public UserDTO saveUserFirstAdminPendingAfter(UserDTO user) {
        List<UserDTO> usersList = userRepo.findAllByLampId(user.getLampId());

        UserDTO owner = null;
        for (UserDTO userA : usersList) {
            if (userA.isAdmin()) {
                owner = userA;
                break;
            }
        }

        // If first user to attach or if there is no ownerMap, there was a lamp reset
        if (usersList == null || usersList.isEmpty() || owner == null) {
            // auto save if first user
            user.setStatus(UserStatusEnum.APPROVED.name());
            user.setAdmin(true);
        }
        else {
            user.setStatus(UserStatusEnum.PENDING_APPROVAL.name());
        }
        return saveUser(user);
    }


    private UserDTO saveUser(UserDTO user) {

        // Make sure we do not put double mapping
        boolean alreadyExists = false;
        String status = user.getStatus();
        UserDTO existingMapDo = userRepo.findByIdAndLampId(user.getId(), user.getLampId());

        if (existingMapDo != null) {
            alreadyExists = true;
            user = existingMapDo;
        }

        if (!alreadyExists) {
            List<UserDTO> existingUserList = userRepo.findAllByLampId(user.getLampId());
            if(null == existingUserList || existingUserList.size() == 0){
                user.setAdmin(true);
            }
            user = userRepo.save(user);
            // TODO: create permissions for admin
        } else {
            UserStatusEnum savedStatus = UserStatusEnum.getEnum(user.getStatus());
            switch (savedStatus) {
                case DENIED:
                    if (UserStatusEnum.getEnum(status).equals(UserStatusEnum.PENDING_APPROVAL)) {
                        user.setStatus(status);
                        user = userRepo.save(user);
                    }
                    break;
                case PENDING_APPROVAL:
                    if (UserStatusEnum.getEnum(status).equals(UserStatusEnum.APPROVED)) {
                        user.setStatus(status);
                        user = userRepo.save(user);
                    }
                    break;
                case APPROVED:
                default:
                    break;
            }
        }

        return user;
    }

    public enum UserStatusEnum {

        APPROVED, PENDING_APPROVAL, DENIED;

        public static UserStatusEnum getEnum(String str) {
            return Stream.of(values()).filter(p -> p.name().equalsIgnoreCase(str)).findFirst().orElse(null);
        }
    }
}
