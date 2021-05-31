package io.ngss.taksitle.report.security.util;

import io.ngss.taksitle.report.security.model.SecurityTokenModel;
import io.ngss.taksitle.report.shared.util.RandomUtils;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Random;

@Component
public class SecurityTokenGenerator {

    public static HashMap<Long, SecurityTokenModel> tokenMap = new HashMap();

    public static Long securityTokenGenerator() {
        Random r = new Random();
        Long token = RandomUtils.randomBetween(0L, Long.MAX_VALUE, r);
        if(token < 0) return securityTokenGenerator();
        try{
            Long encodedToken = encodeToken(token);
            if(tokenMap.containsKey(encodedToken)) return securityTokenGenerator();
            SecurityTokenModel securityTokenModel = new SecurityTokenModel(token, false);
            tokenMap.put(encodedToken, securityTokenModel);
            return token;
        }
        catch (NumberFormatException e){
            return securityTokenGenerator();
        }
    }

    public static boolean isUsedTokenBefore(Long encodedToken) {
        if (tokenMap.containsKey(encodedToken)) {
            SecurityTokenModel securityTokenModel = tokenMap.get(encodedToken);
            if(!securityTokenModel.isUsed){
                securityTokenModel.isUsed = true;
                tokenMap.put(encodedToken, securityTokenModel);
                return false;
            }
        }
        return true;
    }

    private static Long encodeToken(Long token) throws NumberFormatException {
        String oddDigitString = "";
        while (token > 0) {
            long rem = token % 10;
            if (rem % 2 != 0)
                oddDigitString = oddDigitString.concat(String.valueOf(rem * 2));
            token = token / 10;
        }
        return Long.parseLong(oddDigitString);
    }
}
