package com.empik.usersservice.converters;

import com.empik.usersservice.data.UserDTO;
import com.empik.usersservice.data.UserWsDTO;
import com.empik.usersservice.strategies.CalculationStrategy;
import jakarta.annotation.Resource;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * Converter for converting object from {@link UserDTO} to {@link UserWsDTO}
 *
 * @author Marcin Kowalczyk (marcinkowalczyk1992@gmail.com)
 */
@Component
public class UserConverter implements Converter<UserDTO, UserWsDTO> {

    @Resource(name = "defaultCalculationStrategy")
    private CalculationStrategy calculationStrategy;

    /**
     * Method converts from {@link UserDTO} to {@link UserWsDTO}
     *
     * @param source the source object to convert, which must be an instance of {@code S} (never {@code null})
     * @return {@link UserWsDTO}
     */
    @Override
    public UserWsDTO convert(UserDTO source) {
        UserWsDTO target = new UserWsDTO();
        target.setId(source.getId());
        target.setLogin(source.getLogin());
        target.setName(source.getName());
        target.setAvatarUrl(source.getAvatar_url());
        target.setType(source.getType());
        target.setCreatedAt(source.getCreated_at());
        target.setCalculations(calculationStrategy.calculate(source));
        return target;
    }

}
