package com.WayFinder.Server.Main.Helpers;

import com.WayFinder.Server.Main.Enums.RequestType;
import com.WayFinder.Server.Main.Model.RestAPIRequestInformation;

public class RequestHelper {

    public static boolean isValidRequest(RestAPIRequestInformation request, RequestType requestType) {
        boolean isValid = true;
        switch (requestType) {
            case ROUTE:
                if (request == null || request.getStartLocation() == null || request.getEndLocation() == null
                        || request.getId() == 0 || request.getSettings() == null) {
                    isValid = false;
                    break;
                }
        }

        return isValid;
    }

}