package com.tma.demo.utils;

import com.tma.demo.exceptions.BadRequestException;
import com.tma.demo.exceptions.InternalServerException;
import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;

import java.util.UUID;

@Getter
@Setter
public class UUIDHelper {
    static Logger logger = LoggerFactory.getLogger(UUIDHelper.class);
    static LogUtil logUtil = LogUtil.getInstance();

    public static UUID toUUID(String string) {
        UUID uuid;
        try {
            uuid = UUID.fromString(string);
        }catch (IllegalArgumentException e){
            logger.error("UUIDHelper - "+e.getMessage()+" - "+logger.getName());
            logUtil.setLogUtil("Data request is invalid! Error with "+e.getMessage(), logger);
            throw new BadRequestException("Data request is invalid! Error with "+e.getMessage());
        }
        return uuid;
    }
}
